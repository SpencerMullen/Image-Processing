package view;

import java.io.IOException;
import java.util.Map;
import model.LayeredImageProcessingModelState;

/**
 * A simple text view.
 */
public class SimpleImageProcessingView implements ImageProcessingView {

  LayeredImageProcessingModelState state;

  Appendable ap;

  public SimpleImageProcessingView(LayeredImageProcessingModelState state, Appendable ap) {
    this.state = state;
    this.ap = ap;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    if (ap == null) {
      if (message == null) {
        System.out.println("");
      } else {
        System.out.println(message);
      }
    } else {
      this.ap.append(String.format("%s\n", message));
    }
  }

  @Override
  public void renderImage(Map<String, Integer> layers) throws IOException {
    String image = "\n";
    for (String s : layers.keySet()) {
      image += "Layer " + s + ": ";
      if (state.getImageAtLayer(layers.get(s)).size() == 0) {
        image += "Blank | ";
      } else {
        image += "Occupied | ";
      }

      if (state.getStatusOfImageAtLayer(layers.get(s))) {
        image += "Visible";
      } else {
        image += "Invisible";
      }
      image += "\n";
    }
  }
}
