package model.transformations.filter;

import model.image.IImage;
import model.image.IImageState;
import model.image.ImageImpl;
import model.transformations.ITransformation;

/**
 * Implements the ITransformation interface.
 */
public class SepiaTransformation implements ITransformation {

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

    double[][] kernel = {
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}
    };

    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {

        int red = sourceImage.getRedChannel(col, row);
        int green = sourceImage.getGreenChannel(col, row);
        int blue = sourceImage.getBlueChannel(col, row);

        double redSum = (kernel[0][0] * red) + (kernel[0][1] * green) + (kernel[0][2] * blue);
        double greenSum = (kernel[1][0] * red) + (kernel[1][1] * green) + (kernel[1][2] * blue);
        double blueSum = (kernel[2][0] * red) + (kernel[2][1] * green) + (kernel[2][2] * blue);

        int newR = clamp((int) redSum);
        int newG = clamp((int) greenSum);
        int newB = clamp((int) blueSum);

        newImage.setPixel(col, row, newR, newG, newB);
      }
    }
    return newImage;
  }
}
