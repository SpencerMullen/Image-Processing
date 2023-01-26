package model;

import java.util.ArrayList;

/**
 * Represents operations to get various aspects of the state of the model. Does not provide
 * any operations to mutate the state of the model.
 */
public interface ImageProcessingModelState {

  //moved export image to imageUtil and abstracted it slightly

  /**
   * Returns the width, in pixels, of the image.
   *
   * @return the width of the pixels.
   */
  public int getWidth();

  /**
   * Returns the height, in pixels, of the image.
   *
   * @return the height of the pixels.
   */
  public int getHeight();

  /**
   * Returns the 2d Array of pixels representing the image.
   * @return the 2d array of pixels representint the image
   */
  public ArrayList<ArrayList<IPixel>> getImage();

}
