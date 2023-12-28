package model.image;

/**
 * Represents an image that can be modified pixel by pixel.
 * It extends the IImageState interface, providing read-only access to the image data.
 */
public interface IImage extends IImageState {
  /**
   * Sets the color components (red, green, and blue) of the pixel at the specified coordinates.
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @param r The new value for the red color component (in the range [0, 255]).
   * @param g The new value for the green color component (in the range [0, 255]).
   * @param b The new value for the blue color component (in the range [0, 255]).
   * @throws IllegalArgumentException If the specified coordinates (x, y) are outside the image
   *                                  dimensions.
   */
  void setPixel(int x, int y, int r, int g, int b);
}
