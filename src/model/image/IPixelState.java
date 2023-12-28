package model.image;

/**
 * The IPixelState interface represents the state of a pixel in an image and provides read-only
 * access to its color components (red, green, and blue).
 */
public interface IPixelState {

  /**
   * Gets the value of the red color component of the pixel.
   *
   * @return The red color component value of the pixel, in the range [0, 255].
   */

  int getR();

  /**
   * Gets the value of the green color component of the pixel.
   *
   * @return The green color component value of the pixel, in the range [0, 255].
   */

  int getG();

  /**
   * Gets the value of the blue color component of the pixel.
   *
   * @return The blue color component value of the pixel, in the range [0, 255].
   */

  int getB();
}

