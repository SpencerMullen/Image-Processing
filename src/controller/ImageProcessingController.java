package controller;

/**
 * A controller for image processing.
 */
public interface ImageProcessingController {

  /**
   * Start modifying a new Layered Image. Can be accomplished either by typing commands
   * manually, or inputting a script file as the readable object in the Controller constructor.
   * @throws IllegalStateException if writing to the Appendable fails, or reading from the
   *     Readable fails
   */
  void startImageModification() throws IllegalStateException;

}
