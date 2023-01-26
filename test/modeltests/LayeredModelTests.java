package modeltests;

import model.LayeredImageProcessingModel;
import model.LayeredModel;
import model.SimpleImageProcessingModel;
import model.functionobjects.Sepia;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the LayeredModel class: unit tests to ensure that the LayeredModel is behaving
 * correctly and that the methods work as intended.
 */
public class LayeredModelTests {

  @Test
  public void testBlankLayer() {
    LayeredImageProcessingModel model = new LayeredModel();

    model.addBlankLayer();
    assertEquals(model.getWidthOfImages(), 0);
    assertEquals(model.getHeightOfImages(), 0);
    assertFalse(model.getStatusOfImageAtLayer(0));
    assertEquals(model.getNumberOfLayers(), 1);
    assertEquals(model.getNumberOfImages(), 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlankLayerNullTransformation() {
    LayeredImageProcessingModel model = new LayeredModel();

    model.addBlankLayer();
    model.applyTransformationToLayer(new Sepia(), 0);
  }

  @Test
  public void testAddImageToLayerAndVisibility() {
    LayeredImageProcessingModel model = new LayeredModel();
    SimpleImageProcessingModel image = new SimpleImageProcessingModel();

    image.createCheckerboard(25, 10, 10);
    model.addBlankLayer();
    model.addBlankLayer();
    model.addImageToLayer(image, 0);
    model.addCopyOfLayer(0);

    assertEquals(model.getWidthOfImages(), 250);
    assertEquals(model.getHeightOfImages(), 250);
    assertTrue(model.getStatusOfImageAtLayer(0));
    assertFalse(model.getStatusOfImageAtLayer(1));
    assertTrue(model.getStatusOfImageAtLayer(2));
    assertEquals(model.getNumberOfLayers(), 3);
    assertEquals(model.getNumberOfImages(), 2);

    model.switchVisibility(2);

    assertEquals(model.getWidthOfImages(), 250);
    assertEquals(model.getHeightOfImages(), 250);
    assertTrue(model.getStatusOfImageAtLayer(0));
    assertFalse(model.getStatusOfImageAtLayer(1));
    assertFalse(model.getStatusOfImageAtLayer(2));
    assertEquals(model.getNumberOfLayers(), 3);
    assertEquals(model.getNumberOfImages(), 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddCopyofBlankLayer() {
    LayeredImageProcessingModel model = new LayeredModel();
    SimpleImageProcessingModel image = new SimpleImageProcessingModel();

    image.createCheckerboard(25, 10, 10);
    model.addBlankLayer();
    model.addBlankLayer();
    model.addImageToLayer(image, 0);

    model.addCopyOfLayer(1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddCopyAtInvalidIndex() {
    LayeredImageProcessingModel model = new LayeredModel();
    SimpleImageProcessingModel image = new SimpleImageProcessingModel();

    image.createCheckerboard(25, 10, 10);
    model.addBlankLayer();
    model.addBlankLayer();
    model.addImageToLayer(image, 0);

    model.addCopyOfLayer(2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullImageToLayer() {
    LayeredImageProcessingModel model = new LayeredModel();
    SimpleImageProcessingModel image = null;

    model.addBlankLayer();
    model.addBlankLayer();
    model.addImageToLayer(image, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToInvalidIndex() {
    LayeredImageProcessingModel model = new LayeredModel();
    SimpleImageProcessingModel image = new SimpleImageProcessingModel();

    image.createCheckerboard(25, 10, 10);
    model.addBlankLayer();
    model.addBlankLayer();
    model.addImageToLayer(image, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToOccupiedLayer() {
    LayeredImageProcessingModel model = new LayeredModel();
    SimpleImageProcessingModel image = new SimpleImageProcessingModel();

    image.createCheckerboard(25, 10, 10);
    model.addBlankLayer();
    model.addBlankLayer();
    model.addImageToLayer(image, 0);
    model.addImageToLayer(image, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageAtInvalidIndex() {
    LayeredImageProcessingModel model = new LayeredModel();
    SimpleImageProcessingModel image = new SimpleImageProcessingModel();

    image.createCheckerboard(25, 10, 10);
    model.addBlankLayer();
    model.addBlankLayer();
    model.addImageToLayer(image, 0);

    model.getImageAtLayer(2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageStatusAtInvalidIndex() {
    LayeredImageProcessingModel model = new LayeredModel();
    SimpleImageProcessingModel image = new SimpleImageProcessingModel();

    image.createCheckerboard(25, 10, 10);
    model.addBlankLayer();
    model.addBlankLayer();
    model.addImageToLayer(image, 0);

    model.getStatusOfImageAtLayer(2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyNullTransformation() {
    LayeredImageProcessingModel model = new LayeredModel();
    SimpleImageProcessingModel image = new SimpleImageProcessingModel();

    image.createCheckerboard(25, 10, 10);
    model.addBlankLayer();
    model.addBlankLayer();
    model.addImageToLayer(image, 0);

    model.applyTransformationToLayer(null, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyTrasnformatioInvalidIndex() {
    LayeredImageProcessingModel model = new LayeredModel();
    SimpleImageProcessingModel image = new SimpleImageProcessingModel();

    image.createCheckerboard(25, 10, 10);
    model.addBlankLayer();
    model.addBlankLayer();
    model.addImageToLayer(image, 0);

    model.applyTransformationToLayer(null, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyTransformationToBlank() {
    LayeredImageProcessingModel model = new LayeredModel();
    SimpleImageProcessingModel image = new SimpleImageProcessingModel();

    image.createCheckerboard(25, 10, 10);
    model.addBlankLayer();
    model.addBlankLayer();
    model.addImageToLayer(image, 0);

    model.applyTransformationToLayer(new Sepia(), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddIncorrectDimensionsImage() {
    LayeredImageProcessingModel model = new LayeredModel();
    SimpleImageProcessingModel image1 = new SimpleImageProcessingModel();
    SimpleImageProcessingModel image2 = new SimpleImageProcessingModel();

    image1.createCheckerboard(25, 10, 10);
    model.addBlankLayer();
    model.addBlankLayer();
    model.addImageToLayer(image1, 0);

    image2.createCheckerboard(30, 10, 10);
    model.addImageToLayer(image2, 1);
  }
}
