package modeltests;

import model.IPixel;
import controller.ImageUtil;
import java.util.ArrayList;
import model.LayeredImageProcessingModel;
import model.LayeredModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the ImageUtil class: unit tests to ensure that the ImageUtil is behaving correctly
 * and that the methods work as intended.
 */
public class ImageUtilTests {

  @Test
  public void testImportPPM() {
    ArrayList<ArrayList<IPixel>> list = ImageUtil.importPPM("Checkerboard.ppm");

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
  public void testImportPNG() {
    ArrayList<ArrayList<IPixel>> list = ImageUtil.importImage("Checkerboard.png");

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
  public void testImportJPEG() {
    ArrayList<ArrayList<IPixel>> list = ImageUtil.importImage("Checkerboard.jpeg");

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
  public void testImportLayeredImages() {
    LayeredImageProcessingModel model = new LayeredModel();

    ImageUtil.importLayeredImages(model, "BirdLayer.txt");

    assertEquals(model.getNumberOfImages(), 2);
    assertTrue(model.getStatusOfImageAtLayer(0));
    assertFalse(model.getStatusOfImageAtLayer(1));
    assertEquals(model.getNumberOfLayers(), 3);
  }


}
