package model.functionobjects;

import model.IPixel;
import model.Pixel;
import java.util.ArrayList;

/**
 * Represents an abstract class to filter images. Concrete implementations of this class
 * specify the type of filter.
 */
public abstract class AFilter implements ITransformation {

  /**
   * Applies the filter to each pixel in the given image.
   * @param image the image to be filtered
   * @return the filtered image
   */
  public abstract ArrayList<ArrayList<IPixel>> apply(ArrayList<ArrayList<IPixel>> image);

  protected void checkForValidImage(ArrayList<ArrayList<IPixel>> image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }

    if (image.size() < 1 || image.get(0).size() < 1) {
      throw new IllegalArgumentException("Image must contain pixels to be transformed.");
    }
  }

  /**
   * Gets the surrounding pixels to use for filtering.
   *
   * @param pixelY the y index of the pixel being filtered
   * @param pixelX the x index of the pixel being filtered
   * @param image the image being filtered
   * @return the list of pixels representing the pixels surrounding and including the one being
   *     blurred
   */
  protected ArrayList<IPixel> getSurrounding(int pixelY, int pixelX,
      ArrayList<ArrayList<IPixel>> image, int sideOfKernel) {

    checkForValidInputInGetSurrounding(pixelY, pixelX, image, sideOfKernel);

    ArrayList<IPixel> tempList = new ArrayList<>(9);
    int sizeToDisplace = (sideOfKernel - 1) / 2;

    for (int y = pixelY - sizeToDisplace; y < pixelY + sizeToDisplace + 1; y++) {
      for (int x = pixelX - sizeToDisplace; x < pixelX + sizeToDisplace + 1; x++) {
        try {
          IPixel pixel = image.get(y).get(x);
          tempList.add(pixel);
        } catch (IndexOutOfBoundsException e) {
          tempList.add(new Pixel(0, 0, 0, 255));
        }
      }
    }

    return tempList;
  }

  //check all the inputs of getSurrounding, throw an error if one is invalid.
  private void checkForValidInputInGetSurrounding(int pixelY,
      int pixelX, ArrayList<ArrayList<IPixel>> image, int sideOfKernel) {
    if (image.size() < 1 || image.get(0).size() < 1) {
      throw new IllegalArgumentException("Image must contain pixels.");
    }

    if (pixelY < 0 || pixelY > image.size()) {
      throw new IllegalArgumentException("Pixel's y position is invalid.");
    }

    if (pixelX < 0 || pixelX > image.get(0).size()) {
      throw new IllegalArgumentException("Pixel's x position is invalid.");
    }

    if (sideOfKernel % 2 == 0) {
      throw new IllegalArgumentException("Kernel must have odd side lengths.");
    }
  }

}
