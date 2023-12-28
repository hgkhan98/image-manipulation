package model.transformations;

import model.image.IImage;
import model.image.IImageState;
import model.image.ImageImpl;

/**
 * Implements the ITransformation interface.
 */
public class BlueTransformation implements ITransformation {

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
        int newR = clamp(sourceImage.getBlueChannel(col, row));
        int newG = clamp(sourceImage.getBlueChannel(col, row));
        int newB = clamp(sourceImage.getBlueChannel(col, row));
        newImage.setPixel(col, row, newR, newG, newB);
      }
    }
    return newImage;
  }
}
