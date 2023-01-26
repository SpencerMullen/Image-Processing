package model;

import model.functionobjects.ITransformation;

/**
 * Represents operations to mutate and process the model.
 */
public interface ImageProcessingModel extends ImageProcessingModelState {

  /**
   * Applies the given transformation to the image.
   *
   * @param transformation the transformation given
   */
  public void applyTransformation(ITransformation transformation);

  /**
   * Creates a checkerboard pattern image.
   *
   * @param squareSize the size of each square
   * @param width width of the board
   * @param height height of the board
   */
  public void createCheckerboard(int squareSize, int width, int height);

  //removed importImage, will be handled through imageUtil now
}
