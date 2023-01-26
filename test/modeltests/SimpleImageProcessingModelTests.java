package modeltests;

import controller.ImageUtil;
import model.functionobjects.Sharpen;
import model.IPixel;
import model.SimpleImageProcessingModel;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the SimpleImageProcessingModel class: unit tests to ensure that the model is
 * behaving correctly and that the methods work as intended.
 */
public class SimpleImageProcessingModelTests {

  @Test
  public void testConstructor() {
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();
    assertEquals(0, model.getHeight());
    assertEquals(0, model.getWidth());
  }

  @Test
  public void testCheckerBoard() throws IOException {
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();
    model.createCheckerboard(1, 5, 5);
    ImageUtil.exportImageToPPM("Checkerboard.ppm", model.getImage());
    model = new SimpleImageProcessingModel(ImageUtil.importPPM("Checkerboard.ppm"));
    assertEquals(5, model.getHeight());
    assertEquals(5, model.getWidth());

    ArrayList<ArrayList<IPixel>> image = model.getImage();

    boolean firstColor = true;
    int color;
    for (int y = 0; y < image.size(); y++) {
      if (y != 0) {
        firstColor = !firstColor;
      }
      for (int x = 0; x < image.size(); x++) {
        if (x != 0) {
          firstColor = !firstColor;
        }
        if (firstColor) {
          color = 0;
        } else {
          color = 255;
        }
        assertEquals(color, image.get(y).get(x).getRed());
        assertEquals(color, image.get(y).get(x).getGreen());
        assertEquals(color, image.get(y).get(x).getBlue());
      }
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCheckerBoardThrowsExceptionWithInvalidSquareSize() {
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();
    model.createCheckerboard(0, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCheckerBoardThrowsExceptionWithInvalidWidth() {
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();
    model.createCheckerboard(1, 0, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCheckerBoardThrowsExceptionWithInvalidHeight() {
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();
    model.createCheckerboard(1, 1, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testApplyTransformationThrowsExceptionWithNullTransformation() {
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();
    model.applyTransformation(null);
  }


  @Test (expected = IllegalArgumentException.class)
  public void testImportPPMThrowsExceptionWithNullFilename() {
    SimpleImageProcessingModel model = new SimpleImageProcessingModel(ImageUtil.importPPM(null));

  }

  @Test (expected = IllegalArgumentException.class)
  public void testExportImageThrowsExceptionWithNullFilename() throws IOException {
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();
    ImageUtil.exportImageToPPM(null, model.getImage());
  }


  @Test
  public void testImportPPM() {
    SimpleImageProcessingModel model =
        new SimpleImageProcessingModel(ImageUtil.importPPM("Checkerboard.ppm"));

    ArrayList<ArrayList<IPixel>> list = model.getImage();

    assertEquals(500, list.size());

    boolean firstColor = true;
    int color;
    for (int y = 0; y < list.size(); y++) {
      if (y % 100 == 0 && y != 0) {
        firstColor = !firstColor;
      }
      for (int x = 0; x < list.get(y).size(); x++) {
        if (x % 100 == 0 && x != 0) {
          firstColor = !firstColor;
        }
        if (firstColor) {
          color = 0;
        } else {
          color = 255;
        }
        assertEquals(color, list.get(y).get(x).getRed());
        assertEquals(color, list.get(y).get(x).getGreen());
        assertEquals(color, list.get(y).get(x).getBlue());
      }
    }
  }

  @Test
  public void testApplyWorksCorrectly() {
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();
    model.createCheckerboard(1, 2, 1);

    model.applyTransformation(new Sharpen());

    IPixel pix = model.getImage().get(0).get(0);
    IPixel pix2 = model.getImage().get(0).get(1);

    assertEquals(63, pix.getRed());
    assertEquals(63, pix.getGreen());
    assertEquals(63, pix.getBlue());

    assertEquals(255, pix2.getRed());
    assertEquals(255, pix2.getBlue());
    assertEquals(255, pix2.getGreen());
  }

}
