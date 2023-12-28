package controller.commands;

import controller.io.IImageSaver;
import controller.io.JPEGImageSaver;
import controller.io.PNGImageSaver;
import controller.io.PPMImageSaver;
import model.IImageDataBase;
import model.image.IImageState;

import java.util.Objects;
import java.util.Scanner;

/**
 * A command that saves an image as a PPM file.
 */
public class SaveCommand implements ICommand {

  @Override
  public void run(Scanner scanner, IImageDataBase model) {
    Objects.requireNonNull(scanner);
    Objects.requireNonNull(model);

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Second argument must be the path to save.");
    }

    String pathToSave = scanner.next();

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be the image id.");
    }

    String imageId = scanner.next();

    IImageState image = model.get(imageId);
    if (image == null) {
      throw new IllegalStateException("Image with specified id doesn't exist.");
    }

    String fileFormat = pathToSave.substring(pathToSave.length() - 3);
    IImageSaver saver;
    Appendable output = new StringBuilder();

    if (fileFormat.equals("ppm")) {
      saver = new PPMImageSaver(pathToSave, image, output);
    } else if (fileFormat.equals("png")) {
      saver = new PNGImageSaver(pathToSave, image);
    } else {
      saver = new JPEGImageSaver(pathToSave, image);
    }

    saver.run();
  }
}
