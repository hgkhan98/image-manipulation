package model.transformations.filter;

import model.image.IImage;
import model.image.IImageState;
import model.image.ImageImpl;
import model.transformations.ITransformation;

/**
 * Implements the ITransformation interface.
 */
public class BlurTransformation implements ITransformation {

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
        {1.0 / 16, 1.0 / 8, 1.0 / 16},
        {1.0 / 8, 1.0 / 4, 1.0 / 8},
        {1.0 / 16, 1.0 / 8, 1.0 / 16}
    };

    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {

        double redSum = 0;
        double greenSum = 0;
        double blueSum = 0;

        for (int kernelRow = 0; kernelRow < kernel.length; kernelRow++) {
          for (int kernelCol = 0; kernelCol < kernel[kernelRow].length; kernelCol++) {

            int sumRow = row + kernelRow - 1;
            int sumCol = col + kernelCol - 1;

            if (sumRow >= 0 && sumRow < sourceImage.getHeight() && sumCol >= 0
                    && sumCol < sourceImage.getWidth()) {
              redSum += kernel[kernelRow][kernelCol]
                      * sourceImage.getRedChannel(sumCol, sumRow);
              greenSum += kernel[kernelRow][kernelCol]
                      * sourceImage.getGreenChannel(sumCol, sumRow);
              blueSum += kernel[kernelRow][kernelCol]
                      * sourceImage.getBlueChannel(sumCol, sumRow);
            }
          }
        }
        int newR = clamp((int) redSum);
        int newG = clamp((int) greenSum);
        int newB = clamp((int) blueSum);
        newImage.setPixel(col, row, newR, newG, newB);
      }
    }
    return newImage;
  }
}
