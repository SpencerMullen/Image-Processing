package model.functionobjects;

import model.IPixel;
import model.Pixel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing a sepia transformation. This can be applied to any 2d array of pixels,
 * and will subsequently alter the color channels of each pixel to produce a sepia image.
 */
public class Sepia extends ATransformation {

  private List<Double> redOperation;
  private List<Double> greenOperation;
  private List<Double> blueOperation;

  /**
   * Creates a sepia object, which can be passed an image and will alter each of the image's pixels
   * to be of a sepia color tone.
   */
  public Sepia() {
    this.redOperation = new ArrayList<>(Arrays.asList(0.393, 0.769, 0.189));
    this.greenOperation = new ArrayList<>(Arrays.asList(0.349, 0.686, 0.168));
    this.blueOperation = new ArrayList<>(Arrays.asList(0.272, 0.534, 0.131));
  }

  @Override
  public ArrayList<ArrayList<IPixel>> apply(ArrayList<ArrayList<IPixel>> image) {

    checkForValidImage(image);
    ArrayList<ArrayList<IPixel>> newList = createNewList(image);
    int max = image.get(0).get(0).getMax();

    for (int y = 0; y < image.size(); y++) {
      for (int x = 0; x < image.get(0).size(); x++) {
        int newRed = (int) (image.get(y).get(x).getRed() * this.redOperation.get(0)
            + image.get(y).get(x).getBlue() * this.redOperation.get(2)
            + image.get(y).get(x).getGreen() * this.redOperation.get(1));

        int newBlue = (int) (image.get(y).get(x).getRed() * this.blueOperation.get(0)
            + image.get(y).get(x).getBlue() * this.blueOperation.get(2)
            + image.get(y).get(x).getGreen() * this.blueOperation.get(1));

        int newGreen = (int) (image.get(y).get(x).getRed() * this.greenOperation.get(0)
            + image.get(y).get(x).getBlue() * this.greenOperation.get(2)
            + image.get(y).get(x).getGreen() * this.greenOperation.get(1));
        IPixel newPixel = new Pixel(newRed, newGreen, newBlue, max);
        newList.get(y).add(newPixel);
      }
    }

    return newList;
  }
}
