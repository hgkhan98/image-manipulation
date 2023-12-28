package controller.io;

import model.image.IImage;
import model.image.IImageState;
import model.image.ImageImpl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Implements the IImageLoader interface to load an image from a PPM file.
 * It reads the image data from the file and creates an IImageState object representing the
 * loaded image.
 */
public class ImageLoader implements IImageLoader {
  private final String filePath;

  /**
   * Constructs a new PNGImageLoader with the specified file path.
   *
   * @param filePath The path of the PNG or JPEG file to be loaded.
   * @throws NullPointerException If the filePath is null.
   */
  public ImageLoader(String filePath) {
    this.filePath = Objects.requireNonNull(filePath);
  }

  @Override
  public IImageState run() {
    try {
      BufferedImage bufferedImage = ImageIO.read(new File(filePath));
      int width = bufferedImage.getWidth();
      int height = bufferedImage.getHeight();
      IImage image = new ImageImpl(width, height);

      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          int rgb = bufferedImage.getRGB(col, row);
          int r = (rgb >> 16) & 0xFF;
          int g = (rgb >> 8) & 0xFF;
          int b = rgb & 0xFF;
          image.setPixel(col, row, r, g, b);
        }
      }
      return image;

    } catch (IOException e) {
      System.out.println("Error loading image: " + e.getMessage());
    }
    return null;
  }
}
