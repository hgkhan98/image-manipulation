package controller.io;

import model.image.IImageState;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Implements the IImageSaver interface to save an image to a PNG file.
 * It takes an IImageState object representing the image and writes it in P3 format to the
 * specified file path.
 */
public class PNGImageSaver implements IImageSaver {
  private final String pathToSave;
  private final IImageState image;

  /**
   * Constructs a PNGImageSaver object with the given parameters.
   *
   * @param pathToSave The file path to which the PNG image will be saved.
   * @param image      The IImageState object representing the image to be saved.
   * @throws NullPointerException if either pathToSave, image, or output is null.
   */
  public PNGImageSaver(String pathToSave, IImageState image) {
    this.pathToSave = Objects.requireNonNull(pathToSave);
    this.image = Objects.requireNonNull(image);
  }

  @Override
  public void run() {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = image.getRedChannel(col, row);
        int g = image.getGreenChannel(col, row);
        int b = image.getBlueChannel(col, row);
        int rgb = (r << 16) | (g << 8) | b;
        bufferedImage.setRGB(col, row, rgb);
      }
    }

    try {
      File file = new File(pathToSave);
      ImageIO.write(bufferedImage, "png", file);
    } catch (IOException e) {
      System.out.println("Error saving PNG image: " + e.getMessage());
    }
  }
}
