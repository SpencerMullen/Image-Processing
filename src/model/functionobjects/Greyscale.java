package model.functionobjects;

import model.IPixel;
import model.Pixel;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a greyscale transformation. This can be applied to any 2d array of pixels,
 * and will subsequently alter the color channels of each pixel to produce a black and white image.
 */
public class Greyscale extends ATransformation {

  private List<Double> operation;

  /**
   * Creates an object representing a greyscale filter, which can loop through an image and change
   * each pixel to black and white.
   */
  public Greyscale() {
    this.operation = new ArrayList<>(3);
    operation.add(0.2126);
    operation.add(0.7152);
    operation.add(0.0722);
  }

  @Override
  public ArrayList<ArrayList<IPixel>> apply(ArrayList<ArrayList<IPixel>> image) {

    checkForValidImage(image);
    ArrayList<ArrayList<IPixel>> newList = createNewList(image);
    int max = image.get(0).get(0).getMax();

    for (int y = 0; y < image.size(); y++) {
      for (int x = 0; x < image.get(0).size(); x++) {
        int newColor = (int) (image.get(y).get(x).getRed() * this.operation.get(0)
            + image.get(y).get(x).getGreen() * this.operation.get(1)
            + image.get(y).get(x).getBlue() * this.operation.get(2));
        IPixel newPixel = new Pixel(newColor, newColor, newColor, max);
        newList.get(y).add(newPixel);
      }
    }

    return newList;
  }
}