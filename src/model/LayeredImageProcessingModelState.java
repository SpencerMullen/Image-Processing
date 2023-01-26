package model;

import java.util.ArrayList;

/**
 * A layered image processing model interface that deals with observing the model.
 */
public interface LayeredImageProcessingModelState {


  /**
   * Return the width of each image in the model. (The width of the first layer).
   * @return the width of each image in the model, 0 if there are no images
   */
  public int getWidthOfImages();

  /**
   * Return the height of each image in the model. (The height of the first layer).
   * @return the height of each image in the model, 0 if there are no images
   */
  public int getHeightOfImages();

  /**
   * Return the 2d Array of pixels representing the image at the specified layer.
   * @param layer the layer to get the image from
   * @return the 2d array of pixels representing the image at the specified layer
   * @throws IllegalArgumentException if an invalid layer is passed, or there are no images
   *     in the model
   */
  public ArrayList<ArrayList<IPixel>> getImageAtLayer(int layer) throws IllegalArgumentException;

  /**
   * Return the status of the image at the given layer (whether it is visible or invisible).
   * Return false if there is no image in the layer.
   * @param layer the indexed layer to be looked at
   * @return the boolean representation of whether the layer is visible
   * @throws IllegalArgumentException if the layer is invalid
   */
  public boolean getStatusOfImageAtLayer(int layer) throws IllegalArgumentException;

  /**
   * Return the number of images in the model (number of occupied layers).
   * @return the number of images in the model
   */
  public int getNumberOfImages();

  /**
   * Return the total number of layers in the model.
   * @return the number of layers in the model
   */
  public int getNumberOfLayers();

}
