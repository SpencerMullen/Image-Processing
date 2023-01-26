package controllertests;

import static org.junit.Assert.assertEquals;

import controller.JFrameController;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import model.IPixel;
import model.SimpleImageProcessingModel;
import org.junit.Test;

/**
 * Tests the graphical controller.
 */
public class GraphicalControllerTests {

  @Test
  public void testGetTopVisible() {
    JFrameController controller = new JFrameController();
    assertEquals(controller.getTopVisible(), 0);
  }

  @Test
  public void testBufferedImage() {
    JFrameController controller = new JFrameController();
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();
    model.createCheckerboard(50, 10, 10);
    ArrayList<ArrayList<IPixel>> image = model.getImage();
    BufferedImage img = new BufferedImage(image.get(0).size(), image.size(), 1);

    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        IPixel pixel = image.get(y).get(x);
        int rgb = pixel.getRed();
        rgb = (rgb << 8) + pixel.getGreen();
        rgb = (rgb << 8) + pixel.getBlue();

        img.setRGB(x, y, rgb);
      }
    }
    assertEquals(controller.convertToBuffered(model.getImage()), img);
  }

  @Test
  public void testConvertBytes() throws IOException {
    JFrameController controller = new JFrameController();
    SimpleImageProcessingModel model = new SimpleImageProcessingModel();
    model.createCheckerboard(50, 10, 10);
    ArrayList<ArrayList<IPixel>> image = model.getImage();
    BufferedImage img = new BufferedImage(image.get(0).size(), image.size(), 1);

    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        IPixel pixel = image.get(y).get(x);
        int rgb = pixel.getRed();
        rgb = (rgb << 8) + pixel.getGreen();
        rgb = (rgb << 8) + pixel.getBlue();

        img.setRGB(x, y, rgb);
      }
    }

    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    ImageIO.write(img, "jpeg", byteStream);
    byte[] byteArray = byteStream.toByteArray();

    assertEquals(byteArray, controller.convertToByte(img, "jpeg"));
  }

  @Test
  public void testCommands() {
    JFrameController controller = new JFrameController();

    ActionEvent load = new ActionEvent(controller, 0, "Load");
    ActionEvent save = new ActionEvent(controller, 0, "Save");
    ActionEvent create = new ActionEvent(controller, 0, "Create");
    ActionEvent remove = new ActionEvent(controller, 0, "Remove Layer");
    ActionEvent sharpen = new ActionEvent(controller, 0, "Sharpen");
    ActionEvent invis = new ActionEvent(controller, 0, "Make Layer Invisible");

    controller.actionPerformed(create);
    controller.actionPerformed(load);
    controller.actionPerformed(save);
    controller.actionPerformed(create);
    controller.actionPerformed(load);
    controller.actionPerformed(sharpen);
    controller.actionPerformed(invis);
    controller.actionPerformed(remove);

    assertEquals(1, controller.getTopVisible());
  }

}
