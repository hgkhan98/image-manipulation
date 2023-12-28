package controller.commands;

import model.IImageDataBase;
import model.image.IImageState;
import model.transformations.GreenTransformation;
import model.transformations.ITransformation;

import java.util.Objects;
import java.util.Scanner;

/**
 * A command that applies a green component transformation to an image and stores the result in
 * the model.
 */
public class GreenComponentCommand implements ICommand {

  @Override
  public void run(Scanner scanner, IImageDataBase model) {
    Objects.requireNonNull(scanner);
    Objects.requireNonNull(model);

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Second argument must be the image id.");
    }

    String sourceImageId = scanner.next();

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be the image id.");
    }

    String destId = scanner.next();

    IImageState sourceImage = model.get(sourceImageId);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified id doesn't exist.");
    }

    ITransformation greenTransformation = new GreenTransformation();
    IImageState greenImage = greenTransformation.run(sourceImage);

    model.add(destId, greenImage);
  }
}
