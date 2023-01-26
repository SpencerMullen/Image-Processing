package modeltests;

import model.Pixel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Pixel class: unit tests to ensure that Pixel is behaving correctly and that
 * the methods work as intended.
 */
public class PixelTests {

  @Test
  public void testConstructor() {
    Pixel p = new Pixel(255, 155, 55, 255);
    assertEquals(p.getRed(), 255);
    assertEquals(p.getGreen(), 155);
    assertEquals(p.getBlue(), 55);
  }

  @Test
  public void testConstructorOutOfRange() {
    Pixel p = new Pixel(255, -10, 300, 255);
    assertEquals(p.getRed(), 255);
    assertEquals(p.getGreen(), 0);
    assertEquals(p.getBlue(), 255);
  }

  @Test
  public void testGetRed() {
    Pixel p = new Pixel(255, 10, 255);
    assertEquals(255, p.getRed());
  }

  @Test
  public void testGetGreen() {
    Pixel p = new Pixel(255, 10, 255);
    assertEquals(10, p.getGreen());
  }

  @Test
  public void testGetBlue() {
    Pixel p = new Pixel(255, 10, 25);
    assertEquals(25, p.getBlue());
  }

  @Test
  public void testGetMax() {
    Pixel p1 = new Pixel(0, 0, 0);
    Pixel p2 = new Pixel(0, 0, 0, 255);
    Pixel p3 = new Pixel(0, 0, 0, 5);
    assertEquals(p1.getMax(), p2.getMax());
    assertEquals(5, p3.getMax());
  }

  @Test
  public void testEnforceValue() {
    Pixel p = new Pixel(0, 0, 0, 255);
    assertEquals(50, p.enforceValue(50));
    assertEquals(0, p.enforceValue(-50));
    assertEquals(255, p.enforceValue(350));
  }
}
