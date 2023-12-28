package model.image;

/**
 * Represents a pixel in an image and provides methods to access and modify its color components.
 * It extends the IPixelState interface, which provides read-only access to the pixel's color
 * components.
 */
public interface IPixel extends IPixelState {

  /**
   * Sets the value of the red color component of the pixel.
   *
   * @param val The new value for the red color component, in the range [0, 255].
   */

  void setR(int val);

  /**
   * Sets the value of the green color component of the pixel.
   *
   * @param val The new value for the green color component, in the range [0, 255].
   */

  void setG(int val);

  /**
   * Sets the value of the blue color component of the pixel.
   *
   * @param val The new value for the blue color component, in the range [0, 255].
   */

  void setB(int val);
}

