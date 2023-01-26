package view;

import java.io.IOException;

/**
 * A graphical image processing view.
 */
public interface GraphicalImageProcessingView {

  /**
   * Sets the image of the view to display this image.
   *
   * @param bytes array of bytes
   */
  public void setImage(byte[] bytes);

  /**
   * Receives the password of a given field.
   *
   * @param password the name of the password
   * @return the password
   */
  public String getPassword(String password);

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the message to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;
}
