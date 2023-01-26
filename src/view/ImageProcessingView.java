package view;

import java.io.IOException;
import java.util.Map;

/**
 * A view to process images.
 */
public interface ImageProcessingView {
  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the message to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;

  /**
   * Render the state of the image to the provided data destination.
   *
   * @param layers a map of the layers of the
   * @throws IOException if transmission of the message to the provided data destination fails
   */
  void renderImage(Map<String, Integer> layers) throws IOException;
}
