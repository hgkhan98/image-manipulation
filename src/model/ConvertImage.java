package model;

import model.image.IImage;
import model.image.IImageState;
import model.image.ImageImpl;

import java.awt.image.BufferedImage;

/**
 * The ConvertImage class provides utility methods for converting between BufferedImage and
 * IImageState representations.
 */
public class ConvertImage {

  /**
   * Converts a BufferedImage to an IImageState representation.
   *
   * @param bufferedImage The BufferedImage to convert.
   * @return An IImageState representing the input BufferedImage.
   */
  public static IImage convertFromBuffered(BufferedImage bufferedImage) {
    IImage newImage = new ImageImpl(bufferedImage.getWidth(), bufferedImage.getHeight());

    for (int x = 0; x < bufferedImage.getWidth(); x++) {
      for (int y = 0; y < bufferedImage.getHeight(); y++) {
        int rgb = bufferedImage.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        newImage.setPixel(x, y, red, green, blue);
      }
    }

    return newImage;
  }

  /**
   * Converts an IImageState to a BufferedImage representation.
   *
   * @param image The IImageState to convert.
   * @return A BufferedImage representing the input IImageState.
   */
  public static BufferedImage convertToBuffered(IImageState image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = (image.getRedChannel(x, y) << 16) | (image.getGreenChannel(x, y) << 8)
            | image.getBlueChannel(x, y);
        bufferedImage.setRGB(x, y, rgb);
      }
    }

    return bufferedImage;
  }
}
