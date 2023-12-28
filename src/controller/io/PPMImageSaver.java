package controller.io;

import model.image.IImageState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * Implements the IImageSaver interface to save an image to a PPM file.
 * It takes an IImageState object representing the image and writes it in P3 format to the
 * specified file path.
 */
public class PPMImageSaver implements IImageSaver {
  private final String pathToSave;
  private final IImageState image;
  private final Appendable output;

  /**
   * Constructs a PPMImageSaver object with the given parameters.
   *
   * @param pathToSave The file path to which the PPM image will be saved.
   * @param image      The IImageState object representing the image to be saved.
   * @param output     The Appendable object where the image content will be written.
   * @throws NullPointerException if either pathToSave, image, or output is null.
   */
  public PPMImageSaver(String pathToSave, IImageState image, Appendable output) {
    this.pathToSave = Objects.requireNonNull(pathToSave);
    this.image = Objects.requireNonNull(image);
    this.output = output;
  }

  private void write(String message) {
    try {
      this.output.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Writing failed.");
    }
  }

  @Override
  public void run() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToSave))) {

      write("P3\n");
      write(image.getWidth() + " " + image.getHeight() + "\n");
      write("255\n");

      for (int row = 0; row < image.getHeight(); row++) {
        for (int col = 0; col < image.getWidth(); col++) {
          write(image.getRedChannel(col, row) + " " + image.getGreenChannel(col, row) + " "
              + image.getBlueChannel(col, row) + " ");
        }
        write("\n");
      }
      writer.write(output.toString());
    } catch (IOException e) {
      throw new IllegalStateException("Saving file failed.");
    }
  }
}
