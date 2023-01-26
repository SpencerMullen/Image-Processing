package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import model.IPixel;
import model.LayeredImageProcessingModel;
import model.LayeredModel;
import model.SimpleImageProcessingModel;
import model.functionobjects.Blur;
import model.functionobjects.Greyscale;
import model.functionobjects.Sepia;
import model.functionobjects.Sharpen;
import view.GraphicalImageProcessingView;
import view.JFrameView;

/**
 * A controller that takes in actions from a JFrame view.
 */
public class JFrameController implements GraphicalImageProcessingController, ActionListener {

  private LayeredImageProcessingModel model;
  private GraphicalImageProcessingView view;
  private Map<String, Integer> layers;
  private Integer currentLayer;
  private String lastLayer;

  /**
   * Constructs a new JFrame controller.
   */
  public JFrameController() {
    this.model = new LayeredModel();
    this.view = new JFrameView(this);
    this.currentLayer = 0;
    this.layers = new LinkedHashMap<String, Integer>();
    this.lastLayer = "";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    switch (command) {
      case "Load":
        String loadPass = view.getPassword("loadPass");
        load(loadPass);
        break;
      case "Save":
        String savePass = view.getPassword("savePass");
        save(savePass);
        break;
      case "Load All":
        String loadAllPass = view.getPassword("loadLayeredPass");
        loadLayered(loadAllPass);
        break;
      case "Save All":
        String saveAllPass = view.getPassword("saveAllPass");
        saveAll(saveAllPass);
        break;
      case "Create":
        String layerName = view.getPassword("createPass");
        create(layerName);
        break;
      case "Remove Layer":
        model.removeLayer();
        layers.remove(lastLayer);
        break;
      case "Set":
        // Set new current layer
        String currentPass = view.getPassword("currentPass");
        setCurrentLayer(currentPass);
        break;
      case "Blur":
        model.applyTransformationToLayer(new Blur(), currentLayer);
        break;
      case "Sharpen":
        model.applyTransformationToLayer(new Sharpen(), currentLayer);
        break;
      case "Greyscale":
        model.applyTransformationToLayer(new Greyscale(), currentLayer);
        break;
      case "Sepia":
        model.applyTransformationToLayer(new Sepia(), currentLayer);
        break;
      case "Make Layer Invisible":
        String invisiblePass = view.getPassword("invisPass");
        setVisibility(invisiblePass, "invisible");
        break;
      case "Make Layer Visible":
        String visiblePass = view.getPassword("visiblePass");
        setVisibility(visiblePass, "visible");
        break;
      default:
        throw new IllegalArgumentException("Invalid command: " + command);
    }

    if (this.model.getNumberOfImages() > 0) {
      ArrayList<ArrayList<IPixel>> image = model.getImageAtLayer(this.getTopVisible());
      BufferedImage bufferedImage = this.convertToBuffered(image);
      byte[] bytes;
      try {
        bytes = this.convertToByte(bufferedImage, "jpeg");
        view.setImage(bytes);
      } catch (IOException ioException) {
        transmitToView("Image conversion failed.");
      }
    }
  }

  /**
   * Loads a file.
   *
   * @param filename the filename
   */
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
      transmitToView("Invalid layer.");
    }
  }

  /**
   * Save the image at the current layer with the name that is passed in. Decide what type to save
   * the image as based on the extension of the parameter
   *
   * @param filename the filename
   */
  private void save(String filename) {
    String extension = filename.substring(filename.length() - 4);

    int topVisible = this.getTopVisible();
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

  /**
   * Load the layeredImage model to its previous state based on the contents of the text file.
   *
   * @param filename the name to load from
   */
  private void loadLayered(String filename) {
    ImageUtil.importLayeredImages(model, filename);
    for (int x = 0; x < model.getNumberOfLayers(); x++) {
      layers.put("Layer" + x, x);
      lastLayer = "Layer" + x;
    }
  }

  /**
   * Save the current project.
   *
   * @param filename the name to save to
   */
  private void saveAll(String filename) {
    try {
      ImageUtil.exportAllImages(model, filename);
    } catch (IOException e) {
      transmitToView("Could not save.");
    }
  }

  /**
   * Creates a blank layer.
   *
   * @param layerName the name of the layer
   */
  private void create(String layerName) {
    for (String layer : layers.keySet()) {
      if (layerName.equals(layer)) {
        transmitToView("Cannot add another layer of the same name.");
        break;
      }
    }
    layers.put(layerName, layers.size());
    model.addBlankLayer();
    lastLayer = layerName;
  }

  /**
   * Set the current layer to the string passed in. If the map does not contain such a string, throw
   * an error
   *
   * @param layer the name of the layer
   */
  private void setCurrentLayer(String layer) {
    for (String s : layers.keySet()) {
      if (layer.equals(s)) {
        this.currentLayer = layers.get(s);
        return;
      }
    }
    transmitToView("Invalid layer identifier.");
  }

  /**
   * Set the layer parameter passed in to be either visible or invisible. If it is already the
   * correct state, do nothing.
   *
   * @param layer    the layer
   * @param whatToDo the operation
   */
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
      throw new IllegalStateException("IO Error.");
    }
  }

  @Override
  public int getTopVisible() {
    int topVisible = 0;
    for (String s : layers.keySet()) {
      if (layers.get(s) > topVisible && model.getStatusOfImageAtLayer(layers.get(s))) {
        topVisible = layers.get(s);
      }
    }
    return topVisible;
  }

  @Override
  public BufferedImage convertToBuffered(ArrayList<ArrayList<IPixel>> image) {
    BufferedImage img = new BufferedImage(image.get(0).size(), image.size(), 1);

    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        IPixel pixel = image.get(y).get(x);
        int rgb = pixel.getRed();
        rgb = (rgb << 8) + pixel.getGreen();
        rgb = (rgb << 8) + pixel.getBlue();

        img.setRGB(x, y, rgb);
      }
    }
    return img;
  }

  @Override
  public byte[] convertToByte(BufferedImage bi, String format) throws IOException {

    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    ImageIO.write(bi, format, byteStream);
    return byteStream.toByteArray();
  }
}
