package model;

/**
 * Represents a pixel with a red, blue, and green channel.
 */
public class Pixel implements IPixel {

  private int max;
  private int red;
  private int blue;
  private int green;

  /**
   * Constructs a pixel given 3 color channels and a max.
   *
   * @param red   the red channel
   * @param green the green channel
   * @param blue  the blue channel
   */
  public Pixel(int red, int green, int blue, int max) {
    this.max = max;
    this.red = this.enforceValue(red);
    this.green = this.enforceValue(green);
    this.blue = this.enforceValue(blue);
  }

  /**
   * Constructs a pixel given 3 color channels and sets the max to 255.
   *
   * @param red   the red channel
   * @param green the blue channel
   * @param blue  the green channel
   */
  public Pixel(int red, int green, int blue) {
    this(red, green, blue, 255);
  }


  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }

  @Override
  public int getMax() {
    return this.max;
  }

  @Override
  public int enforceValue(int value) {
    if (value > max) {
      return max;
    } else if (value < 0) {
      return 0;
    } else {
      return value;
    }
  }

  @Override
  public String toString() {
    return this.getRed() + ", " + this.getGreen() + ", " + this.getBlue();
  }

}
