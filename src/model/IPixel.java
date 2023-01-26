package model;

/**
 * Represents a pixel containing a red, blue, and green value. These values are represented
 * by integers.
 */
public interface IPixel {

  /**
   * Get the red value of this pixel.
   *
   * @return the red value of the pixel.
   */
  public int getRed();

  /**
   * Get the green value of this pixel.
   *
   * @return the green value of this pixel
   */
  public int getGreen();

  /**
   * Get the blue value of this pixel.
   *
   * @return the blue value of this pixel
   */
  public int getBlue();

  /**
   * Get the max value of this pixel.
   *
   * @return return the maximum
   */
  public int getMax();

  /**
   * Ensures the value is withing the range 0 to 255, setting it to the max or min if it is not in
   * this range.
   *
   * @return the value or 255/0 if it is out of bounds.
   */
  public int enforceValue(int value);
}
