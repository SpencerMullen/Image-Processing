package model.functionobjects;

import model.IPixel;
import model.Pixel;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a sharpen transformation. This can be applied to any 2d array of pixels,
 * and will subsequently alter the color channels of each pixel to produce a sharpened image.
 */
public class Sharpen extends AFilter {

  private List<Double> operation;

  /**
   * Creates a sharpen object with an operation that represents the kernel, starting from the top
   * left, then going row by row.
   */
  public Sharpen() {
    this.operation = new ArrayList<>(25);
    for (int i = 0; i < 6; i++) {
      this.operation.add(-0.125);
    }

    this.operation.add(0.25);
    this.operation.add(0.25);
    this.operation.add(0.25);
    this.operation.add(-0.125);
    this.operation.add(-0.125);
    this.operation.add(0.25);
    this.operation.add(1.0);
    this.operation.add(0.25);
    this.operation.add(-0.125);
    this.operation.add(-0.125);
    this.operation.add(0.25);
    this.operation.add(0.25);
    this.operation.add(0.25);

    for (int i = 0; i < 6; i++) {
      this.operation.add(-0.125);
    }

  }

  @Override
  public ArrayList<ArrayList<IPixel>> apply(ArrayList<ArrayList<IPixel>> image) {
    checkForValidImage(image);

    ArrayList<ArrayList<IPixel>> newList = new ArrayList<>();

    for (int i = 0; i < image.size(); i++) {
      newList.add(new ArrayList<IPixel>());
    }

    ArrayList<IPixel> tempList = new ArrayList<>(25);
    int max = image.get(0).get(0).getMax();
    double pixelRed;
    double pixelGreen;
    double pixelBlue;
    for (int y = 0; y < image.size(); y++) {
      for (int x = 0; x < image.get(0).size(); x++) {
        tempList = this.getSurrounding(y, x, image, 5);
        pixelRed = 0;
        pixelGreen = 0;
        pixelBlue = 0;
        for (int i = 0; i < this.operation.size(); i++) {
          pixelRed += tempList.get(i).getRed() * operation.get(i);
          pixelGreen += tempList.get(i).getGreen() * operation.get(i);
          pixelBlue += tempList.get(i).getBlue() * operation.get(i);
        }
        IPixel newPixel = new Pixel((int) pixelRed, (int) pixelGreen, (int) pixelBlue, max);
        newList.get(y).add(newPixel);
      }
    }

    return newList;
  }
}
