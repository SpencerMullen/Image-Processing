package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

/**
 * A graphical image processing view using JFrame.
 */
public class JFrameView extends JFrame implements ActionListener, GraphicalImageProcessingView {

  private ActionListener controller;
  private JPanel imagePanel;


  private JMenuBar commandMenu;

  private JMenuItem load;
  private JMenuItem save;
  private JMenuItem loadLayered;
  private JMenuItem saveAll;
  private JMenuItem create;
  private JMenuItem current;
  private JMenuItem invisible;
  private JMenuItem visible;

  private JDialog loadPanel;
  private JDialog savePanel;
  private JDialog loadLayeredPanel;
  private JDialog saveAllPanel;
  private JDialog currentPanel;
  private JDialog createPanel;
  private JDialog messagePanel;
  private JDialog visiblePanel;
  private JDialog invisPanel;

  private JPasswordField loadPass;
  private JPasswordField savePass;
  private JPasswordField loadLayeredPass;
  private JPasswordField saveAllPass;
  private JPasswordField currentPass;
  private JPasswordField createPass;
  private JPasswordField visiblePass;
  private JPasswordField invisPass;

  private JButton loadButton;
  private JButton saveButton;
  private JButton loadLayeredButton;
  private JButton saveAllButton;
  private JButton currentButton;
  private JButton createButton;
  private JButton messageButton;
  private JButton visibleButton;
  private JButton invisButton;

  /**
   * Constructs a new JFrameView.
   *
   * @param controller the controller to send actions to.
   */
  public JFrameView(ActionListener controller) {
    super();
    this.controller = controller;
    setTitle("Graphical Image Processing View");
    setSize(1000, 800);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setBackground(Color.black);

    this.initializeImagePanel();
    JScrollPane scrollPane;
    scrollPane = new JScrollPane(imagePanel);
    this.add(scrollPane);

    this.initializeCommandMenu();
    this.setJMenuBar(commandMenu);

    this.setVisible(true);
  }


  /**
   * Initializes the image panel.
   */
  private void initializeImagePanel() {
    imagePanel = new JPanel();
    imagePanel.setBounds(0, 0, 1000, 800);
    imagePanel.setBackground(new Color(180, 180, 180));

    this.add(imagePanel);
    imagePanel.setVisible(true);
  }

  /**
   * Initializes the command menu bar.
   */
  private void initializeCommandMenu() {
    commandMenu = new JMenuBar();
    JMenu file;
    JMenu edit;
    file = new JMenu("File");
    commandMenu.add(file);

    load = new JMenuItem("Load");
    save = new JMenuItem("Save");
    loadLayered = new JMenuItem("Load Layered");
    saveAll = new JMenuItem("Save Layered");
    file.add(load);
    file.add(save);
    file.add(loadLayered);
    file.add(saveAll);
    load.addActionListener(this);
    save.addActionListener(this);
    loadLayered.addActionListener(this);
    saveAll.addActionListener(this);

    edit = new JMenu("Edit");
    commandMenu.add(edit);
    create = new JMenuItem("Create Layer");
    JMenuItem remove = new JMenuItem("Remove Layer");
    current = new JMenuItem("Change Current Layer");
    JMenuItem blur = new JMenuItem("Blur");
    JMenuItem sharpen = new JMenuItem("Sharpen");
    JMenuItem greyscale = new JMenuItem("Greyscale");
    JMenuItem sepia = new JMenuItem("Sepia");
    invisible = new JMenuItem("Make Layer Invisible");
    visible = new JMenuItem("Make Layer Visible");
    edit.add(create);
    edit.add(remove);
    edit.add(current);
    edit.add(blur);
    edit.add(sharpen);
    edit.add(greyscale);
    edit.add(sepia);
    edit.add(invisible);
    edit.add(visible);
    create.addActionListener(this);
    remove.addActionListener(controller);
    blur.addActionListener(controller);
    sharpen.addActionListener(controller);
    greyscale.addActionListener(controller);
    sepia.addActionListener(controller);
    current.addActionListener(this);
    invisible.addActionListener(controller);
    visible.addActionListener(controller);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == load) {
      this.openLoad();
    }
    if (e.getSource() == loadButton) {
      loadPanel.setVisible(false);
    }

    if (e.getSource() == save) {
      this.openSave();
    }
    if (e.getSource() == saveButton) {
      savePanel.setVisible(false);
    }

    if (e.getSource() == loadLayered) {
      this.openLoadLayered();
    }
    if (e.getSource() == loadLayeredButton) {
      loadLayeredPanel.setVisible(false);
    }

    if (e.getSource() == saveAll) {
      this.openSaveAll();
    }
    if (e.getSource() == saveAllButton) {
      saveAllPanel.setVisible(false);
    }

    if (e.getSource() == current) {
      this.openCurrent();
    }
    if (e.getSource() == currentButton) {
      currentPanel.setVisible(false);
    }

    if (e.getSource() == create) {
      this.openCreate();
    }
    if (e.getSource() == createButton) {
      createPanel.setVisible(false);
    }

    if (e.getSource() == visible) {
      this.openVisible();
    }
    if (e.getSource() == visibleButton) {
      visiblePanel.setVisible(false);
    }

    if (e.getSource() == invisible) {
      this.openInvisible();
    }
    if (e.getSource() == invisButton) {
      invisPanel.setVisible(false);
    }

    if (e.getSource() == messageButton) {
      messagePanel.setVisible(false);
    }
  }

  /**
   * Opens the load panel.
   */
  private void openLoad() {
    loadPanel = new JDialog();
    loadPanel.setLayout(new FlowLayout());
    loadPanel.setTitle("Enter the file name to load from");
    loadPanel.setBounds(50, 200, 400, 90);
    loadPanel.setBackground(new Color(220, 220, 220));
    loadPanel.setResizable(false);

    loadPass = new JPasswordField(15);
    loadPass.setEchoChar((char) 0);
    loadPanel.add(loadPass);
    loadButton = new JButton("Load");
    loadButton.addActionListener(controller);
    loadButton.addActionListener(this);
    loadPanel.add(loadButton);

    loadPanel.setVisible(true);
  }

  /**
   * Opens the save panel.
   */
  private void openSave() {
    savePanel = new JDialog();
    savePanel.setLayout(new FlowLayout());
    savePanel.setTitle("Enter the file name to save to");
    savePanel.setBounds(50, 200, 400, 90);
    savePanel.setBackground(new Color(220, 220, 220));
    savePanel.setResizable(false);

    savePass = new JPasswordField(15);
    savePass.setEchoChar((char) 0);
    savePanel.add(savePass);
    saveButton = new JButton("Save");
    saveButton.addActionListener(controller);
    saveButton.addActionListener(this);
    savePanel.add(saveButton);

    savePanel.setVisible(true);
  }

  /**
   * Opens the load layered panel.
   */
  private void openLoadLayered() {
    loadLayeredPanel = new JDialog();
    loadLayeredPanel.setLayout(new FlowLayout());
    loadLayeredPanel.setTitle("Enter the file name to load from");
    loadLayeredPanel.setBounds(50, 200, 400, 90);
    loadLayeredPanel.setBackground(new Color(220, 220, 220));
    loadLayeredPanel.setResizable(false);

    loadLayeredPass = new JPasswordField(15);
    loadLayeredPass.setEchoChar((char) 0);
    loadLayeredPanel.add(loadLayeredPass);
    loadLayeredButton = new JButton("Load All");
    loadLayeredButton.addActionListener(controller);
    loadLayeredButton.addActionListener(this);
    loadLayeredPanel.add(loadLayeredButton);

    loadLayeredPanel.setVisible(true);
  }

  /**
   * Opens the save all panel.
   */
  private void openSaveAll() {
    saveAllPanel = new JDialog();
    saveAllPanel.setLayout(new FlowLayout());
    saveAllPanel.setTitle("Enter the file name to save to");
    saveAllPanel.setBounds(50, 200, 400, 90);
    saveAllPanel.setBackground(new Color(220, 220, 220));
    saveAllPanel.setResizable(false);

    saveAllPass = new JPasswordField(15);
    saveAllPass.setEchoChar((char) 0);
    saveAllPanel.add(saveAllPass);
    saveAllButton = new JButton("Save All");
    saveAllButton.addActionListener(controller);
    saveAllButton.addActionListener(this);
    saveAllPanel.add(saveAllButton);

    saveAllPanel.setVisible(true);
  }

  /**
   * Opens the current panel.
   */
  private void openCurrent() {
    currentPanel = new JDialog();
    currentPanel.setLayout(new FlowLayout());
    currentPanel.setTitle("Change the current layer to");
    currentPanel.setBounds(50, 200, 400, 90);
    currentPanel.setBackground(new Color(220, 220, 220));
    currentPanel.setResizable(false);

    currentPass = new JPasswordField(15);
    currentPass.setEchoChar((char) 0);
    currentPanel.add(currentPass);
    currentButton = new JButton("Set");
    currentButton.addActionListener(controller);
    currentButton.addActionListener(this);
    currentPanel.add(currentButton);

    currentPanel.setVisible(true);
  }

  /**
   * Opens the create panel.
   */
  private void openCreate() {
    createPanel = new JDialog();
    createPanel.setLayout(new FlowLayout());
    createPanel.setTitle("Enter the name of the layer to create");
    createPanel.setBounds(50, 200, 400, 90);
    createPanel.setBackground(new Color(220, 220, 220));
    createPanel.setResizable(false);

    createPass = new JPasswordField(15);
    createPass.setEchoChar((char) 0);
    createPanel.add(createPass);
    createButton = new JButton("Create");
    createButton.addActionListener(controller);
    createButton.addActionListener(this);
    createPanel.add(createButton);

    createPanel.setVisible(true);
  }

  /**
   * Opens the visible panel.
   */
  private void openVisible() {
    visiblePanel = new JDialog();
    visiblePanel.setLayout(new FlowLayout());
    visiblePanel.setTitle("Enter the name of the layer to create");
    visiblePanel.setBounds(50, 200, 400, 90);
    visiblePanel.setBackground(new Color(220, 220, 220));
    visiblePanel.setResizable(false);

    visiblePass = new JPasswordField(15);
    visiblePass.setEchoChar((char) 0);
    visiblePanel.add(visiblePass);
    visibleButton = new JButton("Create");
    visibleButton.addActionListener(controller);
    visibleButton.addActionListener(this);
    visiblePanel.add(visibleButton);

    visiblePanel.setVisible(true);
  }

  /**
   * Opens the invisible panel.
   */
  private void openInvisible() {
    invisPanel = new JDialog();
    invisPanel.setLayout(new FlowLayout());
    invisPanel.setTitle("Enter the name of the layer to create");
    invisPanel.setBounds(50, 200, 400, 90);
    invisPanel.setBackground(new Color(220, 220, 220));
    invisPanel.setResizable(false);

    invisPass = new JPasswordField(15);
    invisPass.setEchoChar((char) 0);
    invisPanel.add(invisPass);
    invisButton = new JButton("Create");
    invisButton.addActionListener(controller);
    invisButton.addActionListener(this);
    invisPanel.add(invisButton);

    invisPanel.setVisible(true);
  }

  @Override
  public void setImage(byte[] bytes) {
    imagePanel.removeAll();
    JLabel imageLabel;
    ImageIcon image;
    image = new ImageIcon(bytes);
    imageLabel = new JLabel("", image, JLabel.CENTER);
    imagePanel.add(imageLabel);
    imageLabel.setVisible(true);

    this.setVisible(true);
  }

  @Override
  public String getPassword(String password) {
    switch (password) {
      case "loadPass":
        return new String(this.loadPass.getPassword());
      case "savePass":
        return new String(this.savePass.getPassword());
      case "loadLayeredPass":
        return new String(this.loadLayeredPass.getPassword());
      case "saveAllPass":
        return new String(this.saveAllPass.getPassword());
      case "currentPass":
        return new String(this.currentPass.getPassword());
      case "createPass":
        return new String(this.createPass.getPassword());
      case "visiblePass":
        return new String(this.visiblePass.getPassword());
      case "invisPass":
        return new String(this.invisPass.getPassword());
      default:
        throw new IllegalArgumentException("Invalid password name.");
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    messagePanel = new JDialog();
    messagePanel.setLayout(new FlowLayout());
    messagePanel.setTitle("Message");
    messagePanel.setBounds(50, 200, 400, 90);
    messagePanel.setBackground(new Color(220, 220, 220));
    messagePanel.setResizable(false);

    JLabel messageLabel;
    messageLabel = new JLabel(message);
    messageButton = new JButton("Ok");
    messageButton.addActionListener(this);
    messagePanel.add(messageLabel);
    messagePanel.add(messageButton);

    messagePanel.setVisible(true);
  }
}
