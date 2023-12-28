package model.image;

import model.image.IPixel;

/**
 * Represents a pixel in an image with color components (red, green, and blue).
 * It implements the IPixel interface, providing methods to access and modify the pixel's color
 * components.
 */
public class Pixel implements IPixel {
  private int r;
  private int g;
  private int b;

  /**
   * Constructs a new Pixel object with the specified color components (red, green, and blue).
   *
   * @param r The value for the red color component, in the range [0, 255].
   * @param g The value for the green color component, in the range [0, 255].
   * @param b The value for the blue color component, in the range [0, 255].
   * @throws IllegalArgumentException If any of the color component values (r, g, b) are outside
   *                                  the valid range [0, 255].
   */
  public Pixel(int r, int g, int b) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Pixel values out of bounds.");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public int getR() {
    return r;
  }

  @Override
  public void setR(int val) {
    if (val < 0 || val > 255) {
      throw new IllegalArgumentException("Channel value is invalid.");
    }
    this.r = val;
  }

  @Override
  public int getG() {
    return g;
  }

  @Override
  public void setG(int val) {
    if (val < 0 || val > 255) {
      throw new IllegalArgumentException("Channel value is invalid.");
    }
    this.g = val;
  }

  @Override
  public int getB() {
    return b;
  }

  @Override
  public void setB(int val) {
    if (val < 0 || val > 255) {
      throw new IllegalArgumentException("Channel value is invalid.");
    }
    this.b = val;
  }

  @Override
  public String toString() {
    return this.r + " " + this.g + " " + this.b;
  }
}
