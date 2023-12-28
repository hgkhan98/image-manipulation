package controller.io;

import model.image.IImage;
import model.image.IImageState;
import model.image.ImageImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Implements the IImageLoader interface to load an image from a PPM file.
 * It reads the image data from the file and creates an IImageState object representing the
 * loaded image.
 */
public class PPMImageLoader implements IImageLoader {
  private final String filePath;

  /**
   * Constructs a new PPMImageLoader with the specified file path.
   *
   * @param filePath The path of the PPM file to be loaded.
   * @throws NullPointerException If the filePath is null.
   */
  public PPMImageLoader(String filePath) {
    this.filePath = Objects.requireNonNull(filePath);
  }

  @Override
  public IImageState run() {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(this.filePath));
    } catch (FileNotFoundException e) {
      System.out.println("File " + this.filePath + " not found!");
    }
    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    IImage image = new ImageImpl(width, height);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        image.setPixel(col, row, r, g, b);
      }
    }
    return image;
  }
}
