import controller.GraphicalImageProcessingController;
import controller.ImageProcessingController;
import controller.JFrameController;
import controller.SimpleImageProcessingController;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main method, used by the jar.
 */
public class Main {

  /**
   * Represents a main method, used to check functionality of the ImageProcessingModel
   * implementation.
   *
   * @param args the arguments passed to the method
   * @throws IOException if reading or writing to/from a file fails
   */
  public static void main(String[] args) throws IOException {
    String type;
    //if(args.length == 0) {
    //  type = "interactive";
    //} else {
    type = args[0];
    //}

    switch (type) {
      case "-script":
        String filepath = args[1];
        FileReader scriptReader = new FileReader(filepath);
        ImageProcessingController scriptController =
            new SimpleImageProcessingController(scriptReader, System.out);
        scriptController.startImageModification();
        break;
      case "-text":
        InputStreamReader textReader = new InputStreamReader(System.in);
        ImageProcessingController textController =
            new SimpleImageProcessingController(textReader, System.out);
        textController.startImageModification();
        break;
      case "-interactive":
        GraphicalImageProcessingController interactiveController = new JFrameController();
        break;
      default:
        throw new IllegalArgumentException("Please specify a valid command line input.");
    }
  }
}

