package model.image;

/**
 * Represents the state of an image and provides read-only access to its properties.
 * It allows accessing the dimensions (width and height) of the image and the individual color
 * channels (red, green, and blue) of each pixel at specific coordinates.
 */
public interface IImageState {
  /**
   * Gets the height of the image.
   *
   * @return The height of the image, representing the number of rows (pixels) in the image.
   */
  int getHeight();

  /**
   * Gets the width of the image.
   *
   * @return The width of the image, representing the number of columns (pixels) in the image.
   */
  int getWidth();

  /**
   * Gets the red color component of the pixel at the specified coordinates (x, y).
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @return The red color component value at the specified pixel, in the range [0, 255].
   * @throws IllegalArgumentException If the specified coordinates (x, y) are outside the image
   *                                  dimensions.
   */
  int getRedChannel(int x, int y);

  /**
   * Gets the green color component of the pixel at the specified coordinates (x, y).
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @return The green color component value at the specified pixel, in the range [0, 255].
   * @throws IllegalArgumentException If the specified coordinates (x, y) are outside the image
   *                                  dimensions.
   */
  int getGreenChannel(int x, int y);

  /**
   * Gets the blue color component of the pixel at the specified coordinates (x, y).
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @return The blue color component value at the specified pixel, in the range [0, 255].
   * @throws IllegalArgumentException If the specified coordinates (x, y) are outside the image
   *                                  dimensions.
   */
  int getBlueChannel(int x, int y);
}
