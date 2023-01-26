package controllertests;

import controller.ImageProcessingController;
import controller.SimpleImageProcessingController;
import java.io.StringReader;
import org.junit.Test;

/**
 * Contains Unit tests for the implementation of the ImageProcessingController interface.
 */
public class SimpleControllerUnitTest {
  ImageProcessingController controller;

  @Test (expected = IllegalArgumentException.class)
  public void testControllerThrowsErrorWithNullReadable() {
    controller = new SimpleImageProcessingController(null, System.out);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testControllerThrowsErrorWithNullAppendable() {
    Readable in = new StringReader("sss");
    controller = new SimpleImageProcessingController(in,null);
  }

  @Test (expected = IllegalStateException.class)
  public void testControllerThrowsErrorForUnknownCommand() {
    Readable in = new StringReader("transparent");
    Appendable out = System.out;
    controller = new SimpleImageProcessingController(in, out);
    controller.startImageModification();
  }

  @Test (expected = IllegalStateException.class)
  public void testControllerThrowsErrorForMalformedCommand() {
    Readable in = new StringReader("create layer layer1 \n current layer2");
    Appendable out = System.out;
    controller = new SimpleImageProcessingController(in, out);
    controller.startImageModification();
  }

  @Test (expected = IllegalStateException.class)
  public void cannotAddImageToOccupiedLayer() {
    Readable in = new StringReader("create layer layer1 \n current layer1"
        + "\n load Koala.ppm \n load Koala.ppm");
    Appendable out = System.out;
    controller = new SimpleImageProcessingController(in, out);
    controller.startImageModification();
  }

  @Test (expected = IllegalStateException.class)
  public void switchingVisibilityOfInvalidLayerThrowsError() {
    Readable in = new StringReader("create layer layer1 \n visible layer2");
    Appendable out = System.out;
    controller = new SimpleImageProcessingController(in, out);
    controller.startImageModification();
  }

  /*
  @Test
  public void commandsProperlyExecute() {
    Readable in = new StringReader("create layer layer1 \n visible layer1");
    Appendable out = System.out;
    controller = new SimpleImageProcessingController(in, out);
    controller.startImageModification();
    //stuck
  }
  */
}
