package viewtests;

import static org.junit.Assert.assertEquals;

import controller.JFrameController;
import java.awt.event.ActionEvent;
import org.junit.Test;
import view.GraphicalImageProcessingView;
import view.JFrameView;

/**
 * Tests for the graphical view.
 */
public class GraphicalViewTests {

  @Test
  public void testViewActions() {
    JFrameController controller = new JFrameController();
    GraphicalImageProcessingView view = new JFrameView(controller);

    ActionEvent load = new ActionEvent(view, 0, "Load");
    ActionEvent save = new ActionEvent(view, 0, "Save");
    ActionEvent create = new ActionEvent(view, 0, "Create");
    ActionEvent remove = new ActionEvent(view, 0, "Remove Layer");
    ActionEvent sharpen = new ActionEvent(view, 0, "Sharpen");
    ActionEvent invis = new ActionEvent(view, 0, "Make Layer Invisible");

    controller.actionPerformed(create);
    controller.actionPerformed(load);

    assertEquals("ClassDiagram.png", view.getPassword("loadPass"));

    controller.actionPerformed(save);

    assertEquals("ClassDiagram.jpeg", view.getPassword("savePass"));

    controller.actionPerformed(create);
    controller.actionPerformed(load);

    assertEquals("newKoala.png", view.getPassword("loadPass"));

    controller.actionPerformed(sharpen);
    controller.actionPerformed(invis);

    assertEquals("Layer 2", view.getPassword("invisPass"));

    controller.actionPerformed(remove);

    assertEquals(1, controller.getTopVisible());
  }
}
