package controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import model.LayeredImageProcessingModel;
import model.LayeredModel;
import model.SimpleImageProcessingModel;
import model.functionobjects.Blur;
import model.functionobjects.Greyscale;
import model.functionobjects.Sepia;
import model.functionobjects.Sharpen;
import view.ImageProcessingView;
import view.SimpleImageProcessingView;

/**
 * A simple image processing controller with a simple image processing view..
 */
public class SimpleImageProcessingController implements ImageProcessingController {

  private final LayeredImageProcessingModel model;
  private final Readable in;
  private Map<String, Integer> layers;
  private Integer currentLayer;
  private String lastLayer;
  private ImageProcessingView view;

  /**
   * Creates a simple image processing controller.
   *
   * @param in  the readable
   * @param out the appendable
   */
  public SimpleImageProcessingController(Readable in, Appendable out) {
    if (in == null) {
      throw new IllegalArgumentException("Readable cannot be null.");
    } else if (out == null) {
      throw new IllegalArgumentException("Appendable cannot be null.");
    }

    this.model = new LayeredModel();
    this.in = in;
    this.view = new SimpleImageProcessingView(model, out);
    this.currentLayer = 0;
    this.layers = new LinkedHashMap<String, Integer>();
    this.lastLayer = "";
  }

  @Override
  public void startImageModification() throws IllegalStateException {
    Scanner scanner = new Scanner(in);

    while (scanner.hasNext()) {
      String command = scanner.next();

      if (command.equals("q")) {
        transmitToView("Stopped modifying images and executed all necessary exportation");
        return;
      }

      doCommand(command, scanner);
    }
  }

  private void doCommand(String command, Scanner scanner) {
    switch (command) {
      case "load":
        String file = scanner.next();
        load(file);
        transmitToView("Loaded image " + file + ".");
        break;
      case "save":
        String file1 = scanner.next();
        save(file1);
        transmitToView("Saved image " + file1 + ".");
        break;
      case "loadLayered":
        String textFile = scanner.next();
        loadLayered(textFile);
        transmitToView("Loaded layered image " + textFile + ".");
        break;
      case "saveAll":
        String textFileName = scanner.next();
        saveAll(textFileName);
        transmitToView("Saved layered image " + textFileName + ".");
        break;
      case "create":
        String wordLayer = scanner.next();
        String layerName = scanner.next();
        for (String layer : layers.keySet()) {
          if (layerName.equals(layer)) {
            transmitToView("Cannot add another layer of the same name.");
            break;
          }
        }
        layers.put(layerName, layers.size());
        model.addBlankLayer();
        lastLayer = layerName;
        transmitToView("Created layer " + layerName);
        break;
      case "remove":
        model.removeLayer();
        layers.remove(lastLayer);
        transmitToView("Removed last layer.");
        break;
      case "blur":
        model.applyTransformationToLayer(new Blur(), currentLayer);
        transmitToView("Blurred the current layer.");
        break;
      case "sharpen":
        model.applyTransformationToLayer(new Sharpen(), currentLayer);
        transmitToView("Sharpened the current layer.");
        break;
      case "greyscale":
        model.applyTransformationToLayer(new Greyscale(), currentLayer);
        transmitToView("Applied greyscale to the current layer.");
        break;
      case "sepia":
        model.applyTransformationToLayer(new Sepia(), currentLayer);
        transmitToView("Applied sepia to the current layer.");
        break;
      case "current":
        String layer = scanner.next();
        setCurrentLayer(layer);
        transmitToView("Set the current layer as " + layer + ".");
        break;
      case "visible":
        String layer1 = scanner.next();
        setVisibility(layer1, "visible");
        transmitToView("Set layer " + layer1 + " as visible.");
        break;
      case "invisible":
        String layer2 = scanner.next();
        setVisibility(layer2, "invisible");
        transmitToView("Set layer " + layer2 + " as visible.");
        break;
      default:
        transmitToView("Invalid command. Please re-read the allowed commands.");
    }
    transmitImageToView(layers);
  }

  //load the image at this filename to the current layer
  private void load(String filename) {
    SimpleImageProcessingModel img;
    if (filename.substring(filename.length() - 4).equals("ppm")) {
      img = new SimpleImageProcessingModel(ImageUtil.importPPM(filename));
    } else {
      img = new SimpleImageProcessingModel(ImageUtil.importImage(filename));
    }
    try {
      model.addImageToLayer(img, currentLayer);
    } catch (IllegalArgumentException e) {
      //when view is implemented, change this to show this message, and resume waiting for input
      transmitToView("Layer is occupied.");
    }
  }

  //load the layeredImage model to its previous state based on the contents of the text file
  private void loadLayered(String text) {
    ImageUtil.importLayeredImages(model, text);
    for (int x = 0; x < model.getNumberOfLayers(); x++) {
      layers.put("Layer" + x, x);
      lastLayer = "Layer" + x;
    }
  }

  //save the current project
  private void saveAll(String name) {
    try {
      ImageUtil.exportAllImages(model, name);
    } catch (IOException e) {
      transmitToView("Could not transmit to something.");
    }

  }

  //save the image at the current layer with the name that is passed in
  // decide what type to save the image as based on the extension of the parameter
  private void save(String filename) {
    String extension = filename.substring(filename.length() - 4);

    int topVisible = 0;
    for (String s : layers.keySet()) {
      if (layers.get(s) > topVisible && model.getStatusOfImageAtLayer(layers.get(s))) {
        topVisible = layers.get(s);
      }
    }
    if (extension.equals("ppm")) {
      try {
        ImageUtil.exportImageToPPM(filename, model.getImageAtLayer(topVisible));
      } catch (IOException e) {
        transmitToView("Could not transmit something.");
      }
    } else if (extension.equals("png")) {
      ImageUtil.exportImageToPNG(filename, model.getImageAtLayer(topVisible));
    } else {
      ImageUtil.exportImageToJPEG(filename, model.getImageAtLayer(topVisible));
    }
  }

  //set the current layer to the string passed in. If the map does not contain such a string, throw
  // an error
  private void setCurrentLayer(String layer) {
    for (String s : layers.keySet()) {
      if (layer.equals(s)) {
        this.currentLayer = layers.get(s);
        return;
      }
    }

    transmitToView("Invalid layer identifier.");
  }

  //set the layer parameter passed in to be either visible or
  // invisible. If it is already the correct state, do nothing.
  private void setVisibility(String layer, String whatToDo) {
    for (String s : layers.keySet()) {
      if (layer.equals(s)) {
        int layerToSwitch = layers.get(s);
        if (model.getStatusOfImageAtLayer(layerToSwitch) && whatToDo.equals("invisible")) {
          model.switchVisibility(layerToSwitch);
        } else if (!model.getStatusOfImageAtLayer(layerToSwitch) && whatToDo.equals("visible")) {
          model.switchVisibility(layerToSwitch);
        }
        return;
      }
    }

    transmitToView("Invalid layer identifier.");
  }

  /**
   * Transmits a message through the view.
   *
   * @param message the message we wish to transmit
   */
  private void transmitToView(String message) {
    try {
      view.renderMessage(message);
    } catch (IOException ioException) {
      throw new IllegalStateException("Cannot write to appendable.");
    }
  }

  /**
   * Transmits an image through the view.
   *
   * @param layers the image we wish to transmit
   */
  private void transmitImageToView(Map<String, Integer> layers) {
    try {
      view.renderImage(layers);
    } catch (IOException ioException) {
      throw new IllegalStateException("Cannot write to appendable.");
    }
  }

}

