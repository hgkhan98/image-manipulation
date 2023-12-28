package controller.commands;

import model.IImageDataBase;
import model.image.IImageState;
import model.transformations.BrightenTransformation;
import model.transformations.ITransformation;

import java.util.Objects;
import java.util.Scanner;

/**
 * A command that applies a brighten transformation to an image and stores the result in the
 * model.
 */
public class BrightenCommand implements ICommand {

  @Override
  public void run(Scanner scanner, IImageDataBase model) {
    Objects.requireNonNull(scanner);
    Objects.requireNonNull(model);

    if (!scanner.hasNextInt()) {
      throw new IllegalStateException("Second argument must be an int.");
    }

    int value = scanner.nextInt();

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be the image id.");
    }

    String sourceImageId = scanner.next();

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Fourth argument must be the image id.");
    }

    String destId = scanner.next();

    IImageState sourceImage = model.get(sourceImageId);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified id doesn't exist.");
    }

    ITransformation brightenTransformation = new BrightenTransformation(value);
    IImageState brightenedImage = brightenTransformation.run(sourceImage);

    model.add(destId, brightenedImage);
  }
}
