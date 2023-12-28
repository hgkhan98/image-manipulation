package controller.commands;

import controller.io.IImageLoader;
import controller.io.ImageLoader;
import controller.io.PPMImageLoader;
import model.IImageDataBase;
import model.image.IImageState;

import java.util.Objects;
import java.util.Scanner;

/**
 * A command that loads an image from a file and stores it in the image database model.
 */
public class LoadCommand implements ICommand {

  @Override
  public void run(Scanner scanner, IImageDataBase model) {
    Objects.requireNonNull(scanner);
    Objects.requireNonNull(model);

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Second argument must be the path to load.");
    }

    String pathToLoad = scanner.next();

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be the image id.");
    }

    String imageId = scanner.next();

    String fileFormat = pathToLoad.substring(pathToLoad.length() - 3);
    IImageLoader loader;

    if (fileFormat.equals("ppm")) {
      loader = new PPMImageLoader(pathToLoad);
    } else {
      loader = new ImageLoader(pathToLoad);
    }

    IImageState image = loader.run();

    model.add(imageId, image);
  }
}
