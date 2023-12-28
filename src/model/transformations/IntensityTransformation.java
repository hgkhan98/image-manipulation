package model.transformations;

import model.image.IImage;
import model.image.IImageState;
import model.image.ImageImpl;

/**
 * Implements the ITransformation interface.
 */
public class IntensityTransformation implements ITransformation {

  /**
   * Clamps the given value to the range of [0, 255].
   *
   * @param value The value to be clamped.
   * @return The clamped value, ensuring it lies within the range [0, 255].
   */
  private int clamp(int value) {
    if (value < 0) {
      return 0;
    }
    if (value > 255) {
      return 255;
    }
    return value;
  }

  @Override
  public IImageState run(IImageState sourceImage) {
    IImage newImage = new ImageImpl(sourceImage.getWidth(), sourceImage.getHeight());

    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int averageValue = (sourceImage.getRedChannel(col, row)
            + sourceImage.getGreenChannel(col, row) + sourceImage.getBlueChannel(col, row)) / 3;
        int newR = clamp(averageValue);
        int newG = clamp(averageValue);
        int newB = clamp(averageValue);
        newImage.setPixel(col, row, newR, newG, newB);
      }
    }
    return newImage;
  }
}
