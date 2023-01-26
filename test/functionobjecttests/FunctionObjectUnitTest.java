package functionobjecttests;

import model.functionobjects.Blur;
import model.functionobjects.Greyscale;
import model.functionobjects.ITransformation;
import model.functionobjects.Sepia;
import model.functionobjects.Sharpen;
import model.IPixel;
import model.ImageProcessingModel;
import model.Pixel;
import model.SimpleImageProcessingModel;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Contains unit tests to check the functionality of various objects used to apply
 * transformations to images.
 */
public class FunctionObjectUnitTest {

  ITransformation testBlur = new Blur();
  ITransformation testSharpen = new Sharpen();
  ITransformation testGreyscale = new Greyscale();
  ITransformation testSepia = new Sepia();

  ImageProcessingModel testImage = new SimpleImageProcessingModel();


  //tests for blur**************************
  @Test (expected = IllegalArgumentException.class)
  public void blurThrowsErrorWhenImageIsNull() {
    testBlur.apply(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void blurThrowsErrorWhenImageIsEmpty() {
    testBlur.apply(new ArrayList<ArrayList<IPixel>>());
  }

  @Test
  public void blurCorrectlyHandlesMultiplication() {
    testImage.createCheckerboard(1 , 2, 1);

    //manually create the image that applying blur will produce
    ArrayList<ArrayList<IPixel>> compImage = new ArrayList<>();
    compImage.add(new ArrayList<>());
    compImage.get(0).add(new Pixel(31, 31, 31, 255));
    compImage.get(0).add(new Pixel(63, 63, 63, 255));

    //call blur on testImage
    testImage.applyTransformation(testBlur);

    for (int x = 0; x < compImage.get(0).size(); x++) {
      assertEquals(compImage.get(0).get(x).getRed(),
          testImage.getImage().get(0).get(x).getRed());
      assertEquals(compImage.get(0).get(x).getGreen(),
          testImage.getImage().get(0).get(x).getGreen());
      assertEquals(compImage.get(0).get(x).getBlue(),
          testImage.getImage().get(0).get(x).getBlue());
    }
  }
  //****************************************

  //tests for sharpen***********************
  @Test (expected = IllegalArgumentException.class)
  public void sharpenThrowsErrorWhenImageIsNull() {
    testSharpen.apply(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void sharpenThrowsErrorWhenImageIsEmpty() {
    testSharpen.apply(new ArrayList<ArrayList<IPixel>>());
  }

  @Test
  public void sharpenCorrectlyHandlesMultiplication() {
    testImage.createCheckerboard(1, 2, 1);

    //manually create the image that applying sharpen will produce
    ArrayList<ArrayList<IPixel>> compImage = new ArrayList<>();
    compImage.add(new ArrayList<>());
    compImage.get(0).add(new Pixel(63, 63, 63, 255));
    compImage.get(0).add(new Pixel(255, 255, 255, 255));

    //call sharpen on testImage
    testImage.applyTransformation(testSharpen);

    for (int x = 0; x < compImage.get(0).size(); x++) {
      assertEquals(compImage.get(0).get(x).getRed(),
          testImage.getImage().get(0).get(x).getRed());
      assertEquals(compImage.get(0).get(x).getGreen(),
          testImage.getImage().get(0).get(x).getGreen());
      assertEquals(compImage.get(0).get(x).getBlue(),
          testImage.getImage().get(0).get(x).getBlue());
    }
  }
  //****************************************

  //tests for sepia*********************
  @Test (expected = IllegalArgumentException.class)
  public void sepiaThrowsErrorWhenImageIsNull() {
    testSepia.apply(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void sepiaThrowsErrorWhenImageIsEmpty() {
    testSepia.apply(new ArrayList<ArrayList<IPixel>>());
  }

  @Test
  public void sepiaCorrectlyHandlesMultiplication() {
    testImage.createCheckerboard(1, 2, 1);

    //manually create the image that applying sharpen will produce
    ArrayList<ArrayList<IPixel>> compImage = new ArrayList<>();
    compImage.add(new ArrayList<>());
    compImage.get(0).add(new Pixel(0, 0, 0, 255));
    compImage.get(0).add(new Pixel(255, 255, 238, 255));

    //call sharpen on testImage
    testImage.applyTransformation(testSepia);

    for (int x = 0; x < compImage.get(0).size(); x++) {
      assertEquals(compImage.get(0).get(x).getRed(),
          testImage.getImage().get(0).get(x).getRed());
      assertEquals(compImage.get(0).get(x).getGreen(),
          testImage.getImage().get(0).get(x).getGreen());
      assertEquals(compImage.get(0).get(x).getBlue(),
          testImage.getImage().get(0).get(x).getBlue());
    }
  }
  //****************************************

  //tests for greyScale*********************
  @Test (expected = IllegalArgumentException.class)
  public void greyScaleThrowsErrorWhenImageIsNull() {
    testGreyscale.apply(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void greyScaleThrowsErrorWhenImageIsEmpty() {
    testGreyscale.apply(new ArrayList<ArrayList<IPixel>>());
  }

  @Test
  public void greyScaleCorrectlyHandlesMultiplication() {
    testImage.createCheckerboard(1, 2, 1);

    //manually create the image that applying sharpen will produce
    ArrayList<ArrayList<IPixel>> compImage = new ArrayList<>();
    compImage.add(new ArrayList<>());
    compImage.get(0).add(new Pixel(0, 0, 0, 255));
    compImage.get(0).add(new Pixel(254, 254, 254, 255));

    //call sharpen on testImage
    testImage.applyTransformation(testGreyscale);

    for (int x = 0; x < compImage.get(0).size(); x++) {
      assertEquals(compImage.get(0).get(x).getRed(),
          testImage.getImage().get(0).get(x).getRed());
      assertEquals(compImage.get(0).get(x).getGreen(),
          testImage.getImage().get(0).get(x).getGreen());
      assertEquals(compImage.get(0).get(x).getBlue(),
          testImage.getImage().get(0).get(x).getBlue());
    }
  }
  //****************************************

}
