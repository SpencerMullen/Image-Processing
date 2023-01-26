package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import model.IPixel;
import model.LayeredImageProcessingModel;
import model.Pixel;
import model.SimpleImageProcessingModel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static ArrayList<ArrayList<IPixel>> importPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw (new IllegalArgumentException("File " + filename + " not found!"));
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    ArrayList<ArrayList<IPixel>> image = new ArrayList<>();

    for (int y = 0; y < height; y++) {
      image.add(new ArrayList<IPixel>());
      for (int x = 0; x < width; x++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        image.get(y).add(new Pixel(r, g, b, maxValue));
      }
    }

    return image;
  }


  /**
   * Reads an image and imports it based on the filetype.
   *
   * @param filename the name of the file
   * @return the image
   */
  public static ArrayList<ArrayList<IPixel>> importImage(String filename) {
    BufferedImage img;
    ArrayList<ArrayList<IPixel>> convertedImg = new ArrayList<>();

    try {
      img = javax.imageio.ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new IllegalStateException("Could not read from file.");
    }

    for (int y = 0; y < img.getHeight(); y++) {
      convertedImg.add(new ArrayList<IPixel>());
      for (int x = 0; x < img.getWidth(); x++) {
        int rgb = img.getRGB(x, y);

        int r = (int) ((Math.pow(256, 3) + rgb) / 65536);
        int g = (int) (((Math.pow(256, 3) + rgb) / 256) % 256);
        int b = (int) ((Math.pow(256, 3) + rgb) % 256);

        convertedImg.get(y).add(new Pixel(r, g, b));
      }
    }

    return convertedImg;
  }

  /**
   * Exports an image to ppm.
   *
   * @param filename the name of the file
   * @param image    the image
   * @throws IOException if file reading fails
   */
  public static void exportImageToPPM(String filename, ArrayList<ArrayList<IPixel>> image)
      throws IOException {

    if (filename == null) {
      throw new IllegalArgumentException("Filename cannot be null.");
    }
    StringBuilder build = new StringBuilder();
    build.append("P3\n" + image.get(0).size() + " " + image.size());
    build.append("\n" + image.get(0).get(0).getMax());

    for (int y = 0; y < image.size(); y++) {
      for (int x = 0; x < image.get(y).size(); x++) {
        IPixel pix = image.get(y).get(x);
        build.append("\n" + pix.getRed() + "\n" + pix.getGreen() + "\n" + pix.getBlue());
      }
    }

    Path file = Paths.get(filename);
    List<String> lines = Arrays.asList(build.toString());

    Files.write(file, lines, StandardCharsets.UTF_8);
  }

  /**
   * Exports an image to JPEG.
   *
   * @param filename the filename
   * @param image    the image
   */
  public static void exportImageToJPEG(String filename, ArrayList<ArrayList<IPixel>> image) {
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

    try {
      File outputFile = new File(filename);
      ImageIO.write(img, "jpeg", outputFile);
    } catch (IOException e) {
      throw new IllegalStateException("Could not write to file.");
    }
  }

  /**
   * Exports an image to PNG.
   *
   * @param filename the filename
   * @param image    the image
   */
  public static void exportImageToPNG(String filename, ArrayList<ArrayList<IPixel>> image) {
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

    try {
      File outputFile = new File(filename);
      ImageIO.write(img, "png", outputFile);
    } catch (IOException e) {
      throw new IllegalStateException("Could not write to file.");
    }
  }


  /**
   * Exports a multilayered image.
   *
   * @param model    the model
   * @param filename the filename
   * @throws IOException if file reading fails
   */
  public static void exportAllImages(LayeredImageProcessingModel model, String filename)
      throws IOException {
    StringBuilder textInfo = new StringBuilder();

    for (int x = 0; x < model.getNumberOfLayers(); x++) {
      if (model.getImageAtLayer(x).size() != 0) {
        String visibility;
        if (model.getStatusOfImageAtLayer(x)) {
          visibility = "visible";
        } else {
          visibility = "invisible";
        }
        textInfo.append("Layer" + x + " " + visibility + "\n");
        ImageUtil.exportImageToPPM("Layer" + x, model.getImageAtLayer(x));
      } else {
        textInfo.append("Empty\n");
      }

      Path file = Paths.get(filename);
      List<String> lines = Arrays.asList(textInfo.toString());

      Files.write(file, lines, StandardCharsets.UTF_8);
    }
  }

  /**
   * Imports a layered image.
   *
   * @param model the model
   * @param textFileName the filename
   */
  public static void importLayeredImages(LayeredImageProcessingModel model,
      String textFileName) {
    if (textFileName == null) {
      throw new IllegalArgumentException("File name cannot be null.");
    }

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(textFileName));
    } catch (FileNotFoundException e) {
      throw (new IllegalArgumentException("File " + textFileName + " not found!"));
    }

    while (sc.hasNext()) {
      String next = sc.next();

      if (next.equals("Empty")) {
        model.addBlankLayer();
      } else {
        String visibility = sc.next();

        SimpleImageProcessingModel image =
            new SimpleImageProcessingModel(ImageUtil.importPPM(next));
        model.addBlankLayer();
        model.addImageToLayer(image, model.getNumberOfLayers() - 1);
        if (visibility.equals("invisible")) {
          model.switchVisibility(model.getNumberOfLayers() - 1);
        }
      }
    }
  }
}


