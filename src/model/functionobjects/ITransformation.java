package model.functionobjects;

import model.IPixel;
import java.util.ArrayList;

/**
 * Represents a transformation that modifies an image. The modifications are achieved through
 * altering the color channels of each pixel in the image.
 */
public interface ITransformation {

  /**
   * Applies this transformation to a given image.
   *
   * @param image the image to be transformed
   */
  ArrayList<ArrayList<IPixel>> apply(ArrayList<ArrayList<IPixel>> image);

}