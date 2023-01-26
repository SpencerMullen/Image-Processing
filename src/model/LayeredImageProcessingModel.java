package model;

import model.functionobjects.ITransformation;

/**
 * A layered image processing model.
 */
public interface LayeredImageProcessingModel extends LayeredImageProcessingModelState {


  /**
   * Applies the given transformation to the image at the given layer.
   *
   * @param transformation the transformation to be applied
   * @param layer          the layer to apply the transformation
   * @throws IllegalArgumentException if the layer is invalid, or the transformation is null
   */
  public void applyTransformationToLayer(ITransformation transformation, int layer)
      throws IllegalArgumentException;

  /**
   * Add a new blank layer to the model.
   */
  public void addBlankLayer();


  /**
   * Add the image to the desired layer.
   *
   * @param img   the image to be added
   * @param layer the layer to which the image is to be added
   * @throws IllegalArgumentException if the img is null, there exists an image of different
   *                                  dimensions in a different layer, or the layer is invalid or
   *                                  already occupied
   */
  public void addImageToLayer(SimpleImageProcessingModel img, int layer)
      throws IllegalArgumentException;

  /**
   * Add a copy of an existing layer to the model.
   *
   * @param layer the index of the layer to be copied
   * @throws IllegalArgumentException if the index is invalid
   */
  public void addCopyOfLayer(int layer) throws IllegalArgumentException;

  /**
   * Removes the topmost layer from the model. If there are no layers, does nothing.
   */
  public void removeLayer();

  /**
   * Switch the visibility of the image indexed at the given layer (if it is invisible, turn it on,
   * vice versa).
   *
   * @param layer the index of the layer to be switched
   * @throws IllegalArgumentException if the index is invalid
   */
  public void switchVisibility(int layer) throws IllegalArgumentException;
}
