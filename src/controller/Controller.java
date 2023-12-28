package controller;

import controller.io.IImageLoader;
import controller.io.ImageLoader;
import controller.io.PPMImageLoader;
import model.*;
import model.image.IImageState;
import model.transformations.*;
import model.transformations.filter.BlurTransformation;
import model.transformations.filter.GrayscaleTransformation;
import model.transformations.filter.SepiaTransformation;
import model.transformations.filter.SharpenTransformation;
import view.View;
import view.ViewListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Implements the IController interface and serves as the GUI controller of the application.
 */
public class Controller implements IController, ViewListener {
  private final IImageDataBase model;
  private final View view;

  /**
   * Constructs a new Controller with the given Model and View, and registers the ViewListener
   * with the View.
   *
   * @param model The Model (IImageDataBase) to interact with.
   * @param view  The View to manage.
   */
  public Controller(IImageDataBase model, View view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.view.addViewListener(this);
  }

  @Override
  public void run() {
    view.setVisible(true);
  }

  @Override
  public void handleLoadEvent() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(view);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String fileExtension = getFileExtension(selectedFile);
      String fileName = getFileName(selectedFile);

      try {
        IImageState image;
        BufferedImage bufferedImage;

        if (fileExtension.equalsIgnoreCase("ppm")) {
          IImageLoader loader = new PPMImageLoader(selectedFile.getAbsolutePath());
          image = loader.run();
          bufferedImage = ConvertImage.convertToBuffered(image);
        } else if (fileExtension.equalsIgnoreCase("png")
            || fileExtension.equalsIgnoreCase("jpeg")
            || fileExtension.equalsIgnoreCase("jpg")) {
          IImageLoader loader = new ImageLoader(selectedFile.getAbsolutePath());
          image = loader.run();
          bufferedImage = ConvertImage.convertToBuffered(image);
        } else {
          throw new IOException("Unsupported file format.");
        }

        model.add(fileName, image);
        view.getCanvas().setImage(bufferedImage);
        view.getCanvas().setImageID(fileName);
        view.refreshCanvas(bufferedImage);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(view, "Error loading the image.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  @Override
  public void handleSaveEvent() {
    BufferedImage currentImage = view.getCanvas().getImage();
    if (currentImage == null) {
      JOptionPane.showMessageDialog(view, "No image to save.", "Error",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showSaveDialog(view);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();

      String extension = getFileExtension(selectedFile);

      String imageFormat;
      switch (extension.toLowerCase()) {
        case "png":
          imageFormat = "png";
          break;
        case "jpg":
        case "jpeg":
          imageFormat = "jpeg";
          break;
        case "ppm":
          imageFormat = "ppm";
          break;
        default:
          JOptionPane.showMessageDialog(view, "Unsupported file format.", "Error",
              JOptionPane.ERROR_MESSAGE);
          return;
      }

      try {
        ImageIO.write(currentImage, imageFormat, new File(filePath));
        JOptionPane.showMessageDialog(view, "Image saved successfully.", "Success",
            JOptionPane.INFORMATION_MESSAGE);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(view, "Error saving the image.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /**
   * Extracts the file extension from the given file.
   *
   * @param file The file from which to extract the extension.
   * @return The file extension as a lowercase string, or an empty string if the file has no
   * extension.
   */
  private String getFileExtension(File file) {
    String fileName = file.getName();
    int dotIndex = fileName.lastIndexOf(".");
    if (dotIndex == -1) {
      return "";
    } else {
      return fileName.substring(dotIndex + 1).toLowerCase();
    }
  }

  /**
   * Extracts the file name from the given file, excluding the extension.
   *
   * @param file The file from which to extract the name.
   * @return The file name as a string, excluding the extension.
   */
  private String getFileName(File file) {
    String fileName = file.getName();
    int dotIndex = fileName.lastIndexOf(".");
    if (dotIndex == -1) {
      return fileName;
    } else {
      return fileName.substring(0, dotIndex);
    }
  }

  @Override
  public void handleBrightenEvent() {
    String inputValue = JOptionPane.showInputDialog(view, "Enter value:");

    if (inputValue == null || inputValue.isEmpty()) {
      return;
    }

    try {
      int brightenValue = Integer.parseInt(inputValue);
      BufferedImage currentImage = view.getCanvas().getImage();

      if (currentImage == null) {
        JOptionPane.showMessageDialog(view, "No image.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      IImageState image = ConvertImage.convertFromBuffered(currentImage);

      ITransformation brightenTransformation = new BrightenTransformation(brightenValue);
      IImageState brightenedImage = brightenTransformation.run(image);

      String newID = view.getCanvas().getImageID() + "-brighten";
      model.add(newID, brightenedImage);

      BufferedImage bufferedBrightenedImage = ConvertImage.convertToBuffered(brightenedImage);
      view.getCanvas().setImage(bufferedBrightenedImage);
      view.getCanvas().setImageID(newID);
      view.refreshCanvas(bufferedBrightenedImage);
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(view, "Invalid input. Please enter an integer value.",
          "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public void handleValueEvent() {
    BufferedImage currentImage = view.getCanvas().getImage();

    if (currentImage == null) {
      JOptionPane.showMessageDialog(view, "No image.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    IImageState image = ConvertImage.convertFromBuffered(currentImage);

    ITransformation valueTransformation = new ValueTransformation();
    IImageState valueImage = valueTransformation.run(image);

    String newID = view.getCanvas().getImageID() + "-value";
    model.add(newID, valueImage);

    BufferedImage bufferedValueImage = ConvertImage.convertToBuffered(valueImage);
    view.getCanvas().setImage(bufferedValueImage);
    view.getCanvas().setImageID(newID);
    view.refreshCanvas(bufferedValueImage);
  }

  @Override
  public void handleIntensityEvent() {
    BufferedImage currentImage = view.getCanvas().getImage();

    if (currentImage == null) {
      JOptionPane.showMessageDialog(view, "No image.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    IImageState image = ConvertImage.convertFromBuffered(currentImage);

    ITransformation intensityTransformation = new IntensityTransformation();
    IImageState intensityImage = intensityTransformation.run(image);

    String newID = view.getCanvas().getImageID() + "-intensity";
    model.add(newID, intensityImage);

    BufferedImage bufferedValueImage = ConvertImage.convertToBuffered(intensityImage);
    view.getCanvas().setImage(bufferedValueImage);
    view.getCanvas().setImageID(newID);
    view.refreshCanvas(bufferedValueImage);
  }

  @Override
  public void handleLumaEvent() {
    BufferedImage currentImage = view.getCanvas().getImage();

    if (currentImage == null) {
      JOptionPane.showMessageDialog(view, "No image.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    IImageState image = ConvertImage.convertFromBuffered(currentImage);

    ITransformation lumaTransformation = new LumaTransformation();
    IImageState lumaImage = lumaTransformation.run(image);

    String newID = view.getCanvas().getImageID() + "-luma";
    model.add(newID, lumaImage);

    BufferedImage bufferedValueImage = ConvertImage.convertToBuffered(lumaImage);
    view.getCanvas().setImage(bufferedValueImage);
    view.getCanvas().setImageID(newID);
    view.refreshCanvas(bufferedValueImage);
  }

  @Override
  public void handleRedEvent() {
    BufferedImage currentImage = view.getCanvas().getImage();

    if (currentImage == null) {
      JOptionPane.showMessageDialog(view, "No image.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    IImageState image = ConvertImage.convertFromBuffered(currentImage);

    ITransformation redTransformation = new RedTransformation();
    IImageState redImage = redTransformation.run(image);

    String newID = view.getCanvas().getImageID() + "-red";
    model.add(newID, redImage);

    BufferedImage bufferedValueImage = ConvertImage.convertToBuffered(redImage);
    view.getCanvas().setImage(bufferedValueImage);
    view.getCanvas().setImageID(newID);
    view.refreshCanvas(bufferedValueImage);
  }

  @Override
  public void handleGreenEvent() {
    BufferedImage currentImage = view.getCanvas().getImage();

    if (currentImage == null) {
      JOptionPane.showMessageDialog(view, "No image.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    IImageState image = ConvertImage.convertFromBuffered(currentImage);

    ITransformation greenTransformation = new GreenTransformation();
    IImageState greenImage = greenTransformation.run(image);

    String newID = view.getCanvas().getImageID() + "-green";
    model.add(newID, greenImage);

    BufferedImage bufferedValueImage = ConvertImage.convertToBuffered(greenImage);
    view.getCanvas().setImage(bufferedValueImage);
    view.getCanvas().setImageID(newID);
    view.refreshCanvas(bufferedValueImage);
  }

  @Override
  public void handleBlueEvent() {
    BufferedImage currentImage = view.getCanvas().getImage();

    if (currentImage == null) {
      JOptionPane.showMessageDialog(view, "No image.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    IImageState image = ConvertImage.convertFromBuffered(currentImage);

    ITransformation blueTransformation = new BlueTransformation();
    IImageState blueImage = blueTransformation.run(image);

    String newID = view.getCanvas().getImageID() + "-blue";
    model.add(newID, blueImage);

    BufferedImage bufferedValueImage = ConvertImage.convertToBuffered(blueImage);
    view.getCanvas().setImage(bufferedValueImage);
    view.getCanvas().setImageID(newID);
    view.refreshCanvas(bufferedValueImage);
  }

  @Override
  public void handleBlurEvent() {
    BufferedImage currentImage = view.getCanvas().getImage();

    if (currentImage == null) {
      JOptionPane.showMessageDialog(view, "No image.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    IImageState image = ConvertImage.convertFromBuffered(currentImage);

    ITransformation blurTransformation = new BlurTransformation();
    IImageState blurredImage = blurTransformation.run(image);

    String newID = view.getCanvas().getImageID() + "-blur";
    model.add(newID, blurredImage);

    BufferedImage bufferedValueImage = ConvertImage.convertToBuffered(blurredImage);
    view.getCanvas().setImage(bufferedValueImage);
    view.getCanvas().setImageID(newID);
    view.refreshCanvas(bufferedValueImage);
  }

  @Override
  public void handleSharpenEvent() {
    BufferedImage currentImage = view.getCanvas().getImage();

    if (currentImage == null) {
      JOptionPane.showMessageDialog(view, "No image.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    IImageState image = ConvertImage.convertFromBuffered(currentImage);

    ITransformation sharpenTransformation = new SharpenTransformation();
    IImageState sharpenedImage = sharpenTransformation.run(image);

    String newID = view.getCanvas().getImageID() + "-sharpen";
    model.add(newID, sharpenedImage);

    BufferedImage bufferedValueImage = ConvertImage.convertToBuffered(sharpenedImage);
    view.getCanvas().setImage(bufferedValueImage);
    view.getCanvas().setImageID(newID);
    view.refreshCanvas(bufferedValueImage);
  }

  @Override
  public void handleGrayscaleEvent() {
    BufferedImage currentImage = view.getCanvas().getImage();

    if (currentImage == null) {
      JOptionPane.showMessageDialog(view, "No image.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    IImageState image = ConvertImage.convertFromBuffered(currentImage);

    ITransformation grayscaleTransformation = new GrayscaleTransformation();
    IImageState grayscaleImage = grayscaleTransformation.run(image);

    String newID = view.getCanvas().getImageID() + "-grayscale";
    model.add(newID, grayscaleImage);

    BufferedImage bufferedValueImage = ConvertImage.convertToBuffered(grayscaleImage);
    view.getCanvas().setImage(bufferedValueImage);
    view.getCanvas().setImageID(newID);
    view.refreshCanvas(bufferedValueImage);
  }

  @Override
  public void handleSepiaEvent() {
    BufferedImage currentImage = view.getCanvas().getImage();

    if (currentImage == null) {
      JOptionPane.showMessageDialog(view, "No image.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    IImageState image = ConvertImage.convertFromBuffered(currentImage);

    ITransformation sepiaTransformation = new SepiaTransformation();
    IImageState sepiaImage = sepiaTransformation.run(image);

    String newID = view.getCanvas().getImageID() + "-sepia";
    model.add(newID, sepiaImage);

    BufferedImage bufferedValueImage = ConvertImage.convertToBuffered(sepiaImage);
    view.getCanvas().setImage(bufferedValueImage);
    view.getCanvas().setImageID(newID);
    view.refreshCanvas(bufferedValueImage);
  }
}
