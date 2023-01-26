package model;

import model.functionobjects.ITransformation;
import java.util.ArrayList;

/**
 * Represents an image, with functions that apply various filters and transformations to said
 * image.
 */
public class SimpleImageProcessingModel implements ImageProcessingModel {

  private ArrayList<ArrayList<IPixel>> image;

  /**
   * Creates a model.
   */
  public SimpleImageProcessingModel() {
    this.image = new ArrayList<>();
  }

  //added when created the layered image processing model.

  /**
   * Creates a specific model given an image.
   * @param image the image
   */
  public SimpleImageProcessingModel(ArrayList<ArrayList<IPixel>> image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    this.image = image;
  }

  @Override
  public int getWidth() {
    if (this.getHeight() == 0) {
      return this.getHeight();
    }
    return this.image.get(0).size();
  }

  @Override
  public int getHeight() {
    return this.image.size();
  }

  @Override
  public ArrayList<ArrayList<IPixel>> getImage() {
    ArrayList<ArrayList<IPixel>> list = new ArrayList<>(image.size());

    IPixel pix;
    for (int y = 0; y < image.size(); y++) {
      list.add(new ArrayList<IPixel>());
      for (int x = 0; x < image.get(y).size(); x++) {
        pix = image.get(y).get(x);
        list.get(y).add(new Pixel(pix.getRed(), pix.getGreen(), pix.getBlue(), pix.getMax()));
      }
    }

    return list;
  }

  @Override
  public void applyTransformation(ITransformation transformation) {

    if (transformation == null) {
      throw new IllegalArgumentException("Transformation cannot be null.");
    }

    ArrayList<ArrayList<IPixel>> newImage = transformation.apply(this.image);

    this.image = newImage;
  }

  @Override
  public void createCheckerboard(int squareSize, int width, int height) {

    checkDimensions(squareSize, width, height);

    boolean firstColor = false;
    ArrayList<ArrayList<IPixel>> grid = new ArrayList<>();
    IPixel pixel1 = new Pixel(0, 0, 0, 255);
    IPixel pixel2 = new Pixel(255, 255, 255, 255);

    for (int y = 0; y < squareSize * height; y++) {
      if (y % squareSize == 0) {
        if (firstColor) {
          firstColor = false;
        } else {
          firstColor = true;
        }
      }

      if (firstColor) {
        grid.add(createCheckerboardRow(squareSize, width, pixel1, pixel2));
      } else {
        grid.add(createCheckerboardRow(squareSize, width, pixel2, pixel1));
      }
    }

    this.image = grid;
  }

  private void checkDimensions(int squareSize, int width, int height) {
    if (squareSize < 1) {
      throw new IllegalArgumentException("Invalid square size.");
    }
    if (width < 1) {
      throw new IllegalArgumentException("Invalid width.");
    }
    if (height < 1) {
      throw new IllegalArgumentException("Invalid height.");
    }
  }

  /**
   * Creates a single row for the checkerboard.
   *
   * @param squareSize the square size
   * @param width      the width of the board
   * @param color1     the first color
   * @param color2     the second color
   * @return the row we generated
   */
  private ArrayList<IPixel> createCheckerboardRow(int squareSize, int width, IPixel color1,
      IPixel color2) {
    boolean firstColor = false;
    ArrayList<IPixel> row = new ArrayList<IPixel>();

    for (int x = 0; x < squareSize * width; x++) {
      if (x % squareSize == 0) {
        if (firstColor) {
          firstColor = false;
        } else {
          firstColor = true;
        }
      }

      if (firstColor) {
        row.add(new Pixel(color1.getRed(), color1.getGreen(), color1.getBlue(), 255));
      } else {
        row.add(new Pixel(color2.getRed(), color2.getGreen(), color2.getBlue(), 255));
      }
    }

    return row;
  }
}
