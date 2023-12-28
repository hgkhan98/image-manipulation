package model.image;

/**
 * Represents an image implementation that can be modified pixel by pixel.
 * It implements the IImage interface and provides methods to access and modify the image data.
 */
public class ImageImpl implements IImage {
  private final int width;
  private final int height;
  private final IPixel[][] data;

  /**
   * Constructs a new ImageImpl object with the specified width and height.
   *
   * @param width  The width of the image, representing the number of columns (pixels) in the image.
   * @param height The height of the image, representing the number of rows (pixels) in the image.
   */
  public ImageImpl(int width, int height) {
    this.width = width;
    this.height = height;
    this.data = new IPixel[width][height];
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getRedChannel(int x, int y) {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("x or y outside of bounds");
    }
    return this.data[x][y].getR();
  }

  @Override
  public int getGreenChannel(int x, int y) {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("x or y outside of bounds");
    }
    return this.data[x][y].getG();
  }

  @Override
  public int getBlueChannel(int x, int y) {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("x or y outside of bounds");
    }
    return this.data[x][y].getB();
  }

  @Override
  public void setPixel(int x, int y, int r, int g, int b) {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("x or y outside of bounds");
    }
    this.data[x][y] = new Pixel(r, g, b);
  }
}
