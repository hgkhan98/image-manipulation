package view;

import model.IImageDataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

/**
 * The View class represents the main graphical user interface of the application.
 * It extends JFrame and implements ActionListener and KeyListener interfaces to handle events.
 */
public class View extends JFrame implements ActionListener, KeyListener {
  private final JButton saveButton;
  private final JButton loadButton;
  private final JButton brightenButton;
  private final JButton valueButton;
  private final JButton intensityButton;
  private final JButton lumaButton;
  private final JButton redButton;
  private final JButton greenButton;
  private final JButton blueButton;
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton grayscaleButton;
  private final JButton sepiaButton;
  private final IImageDataBase model;
  private final Canvas canvas;
  private final List<ViewListener> listenersToNotify;

  /**
   * Creates a new View object with the specified model.
   *
   * @param model The IImageDataBase model to use with the view.
   */
  public View(IImageDataBase model) {
    this.listenersToNotify = new ArrayList<>();

    setSize(800, 600);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.model = Objects.requireNonNull(model);

    setLayout(new BorderLayout());

    JPanel westPanel = new JPanel();
    westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
    this.saveButton = new JButton("Save Image");
    this.loadButton = new JButton("Load Image");
    westPanel.add(this.loadButton);
    westPanel.add(this.saveButton);
    add(westPanel, BorderLayout.WEST);

    JPanel eastPanel = new JPanel();
    eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
    this.brightenButton = new JButton("Brighten");
    this.valueButton = new JButton("Value-component");
    this.intensityButton = new JButton("Intensity-component");
    this.lumaButton = new JButton("Luma-component");
    this.redButton = new JButton("Red-component");
    this.greenButton = new JButton("Green-component");
    this.blueButton = new JButton("Blue-component");
    this.blurButton = new JButton("Blur");
    this.sharpenButton = new JButton("Sharpen");
    this.grayscaleButton = new JButton("Grayscale");
    this.sepiaButton = new JButton("Sepia");
    eastPanel.add(this.brightenButton);
    eastPanel.add(this.valueButton);
    eastPanel.add(this.intensityButton);
    eastPanel.add(this.lumaButton);
    eastPanel.add(this.redButton);
    eastPanel.add(this.greenButton);
    eastPanel.add(this.blueButton);
    eastPanel.add(this.blurButton);
    eastPanel.add(this.sharpenButton);
    eastPanel.add(this.grayscaleButton);
    eastPanel.add(this.sepiaButton);
    add(eastPanel, BorderLayout.EAST);

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout(1, 1));
    this.canvas = new Canvas();
    centerPanel.add(this.canvas);
    add(centerPanel, BorderLayout.CENTER);

    this.saveButton.setActionCommand("save");
    this.loadButton.setActionCommand("load");
    this.brightenButton.setActionCommand("brighten");
    this.valueButton.setActionCommand("value");
    this.intensityButton.setActionCommand("intensity");
    this.lumaButton.setActionCommand("luma");
    this.redButton.setActionCommand("red");
    this.greenButton.setActionCommand("green");
    this.blueButton.setActionCommand("blue");
    this.blurButton.setActionCommand("blur");
    this.sharpenButton.setActionCommand("sharpen");
    this.grayscaleButton.setActionCommand("grayscale");
    this.sepiaButton.setActionCommand("sepia");

    this.saveButton.addActionListener(this);
    this.loadButton.addActionListener(this);
    this.brightenButton.addActionListener(this);
    this.valueButton.addActionListener(this);
    this.intensityButton.addActionListener(this);
    this.lumaButton.addActionListener(this);
    this.redButton.addActionListener(this);
    this.greenButton.addActionListener(this);
    this.blueButton.addActionListener(this);
    this.blurButton.addActionListener(this);
    this.sharpenButton.addActionListener(this);
    this.grayscaleButton.addActionListener(this);
    this.sepiaButton.addActionListener(this);
    this.addKeyListener(this);

    this.setFocusable(true);
  }

  /**
   * Adds a ViewListener to the list of listeners to be notified of events.
   *
   * @param listener The ViewListener to be added.
   */
  public void addViewListener(ViewListener listener) {
    this.listenersToNotify.add(listener);
  }

  /**
   * Notifies all registered listeners of the "Load" event.
   */
  private void emitLoadEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleLoadEvent();
    }
  }

  /**
   * Notifies all registered listeners of the "Save" event.
   */
  private void emitSaveEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleSaveEvent();
    }
  }

  /**
   * Notifies all registered listeners of the "Brighten" event.
   */
  private void emitBrightenEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleBrightenEvent();
    }
  }

  /**
   * Notifies all registered listeners of the "Value" event.
   */
  private void emitValueEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleValueEvent();
    }
  }

  /**
   * Notifies all registered listeners of the "Intensity" event.
   */
  private void emitIntensityEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleIntensityEvent();
    }
  }

  /**
   * Notifies all registered listeners of the "Luma" event.
   */
  private void emitLumaEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleLumaEvent();
    }
  }

  /**
   * Notifies all registered listeners of the "Red" event.
   */
  private void emitRedEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleRedEvent();
    }
  }

  /**
   * Notifies all registered listeners of the "Green" event.
   */
  private void emitGreenEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleGreenEvent();
    }
  }

  /**
   * Notifies all registered listeners of the "Blue" event.
   */
  private void emitBlueEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleBlueEvent();
    }
  }

  /**
   * Notifies all registered listeners of the "Blur" event.
   */
  private void emitBlurEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleBlurEvent();
    }
  }

  /**
   * Notifies all registered listeners of the "Sharpen" event.
   */
  private void emitSharpenEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleSharpenEvent();
    }
  }

  /**
   * Notifies all registered listeners of the "Grayscale" event.
   */
  private void emitGrayscaleEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleGrayscaleEvent();
    }
  }

  /**
   * Notifies all registered listeners of the "Sepia" event.
   */
  private void emitSepiaEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleSepiaEvent();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch(e.getActionCommand()) {
      case "load":
        emitLoadEvent();
        break;
      case "save":
        emitSaveEvent();
        break;
      case "brighten":
        emitBrightenEvent();
        break;
      case "value":
        emitValueEvent();
        break;
      case "intensity":
        emitIntensityEvent();
        break;
      case "luma":
        emitLumaEvent();
        break;
      case "red":
        emitRedEvent();
        break;
      case "green":
        emitGreenEvent();
        break;
      case "blue":
        emitBlueEvent();
        break;
      case "blur":
        emitBlurEvent();
        break;
      case "sharpen":
        emitSharpenEvent();
        break;
      case "grayscale":
        emitGrayscaleEvent();
        break;
      case "sepia":
        emitSepiaEvent();
        break;
      default:
        throw new IllegalStateException("Unknown action command.");
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (e.getKeyChar() == 's') {
      emitSaveEvent();
    }
    if (e.getKeyChar() == 'l') {
      emitLoadEvent();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    emitLoadEvent();
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  /**
   * Returns the canvas object used in the View.
   *
   * @return The Canvas object used for displaying images.
   */
  public Canvas getCanvas() {
    return this.canvas;
  }

  /**
   * Refreshes the canvas by setting the image and triggering a repaint.
   *
   * @param image The BufferedImage to be displayed on the canvas.
   */
  public void refreshCanvas(BufferedImage image) {
    canvas.setImage(image);
    canvas.repaint();
  }
}
