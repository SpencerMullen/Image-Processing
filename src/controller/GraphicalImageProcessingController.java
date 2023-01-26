package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import model.IPixel;

/**
 * A graphical image processing controller.
 */
public interface GraphicalImageProcessingController {

  /**
   * Gets the topmost, visible layer.
   *
   * @return the index
   */
  public int getTopVisible();

  /**
   * Converts an image made up of our interface (IPixel) to a buffered image.
   *
   * @param image the image
   * @return the buffered image representation
   */
  public BufferedImage convertToBuffered(ArrayList<ArrayList<IPixel>> image);

  /**
   * Converts a buffered image to a byte array.
   *
   * @param bi the buffered image
   * @param format the selected format
   * @return the bytearray
   * @throws IOException if conversion fails
   */
  public byte[] convertToByte(BufferedImage bi, String format) throws IOException;
}
