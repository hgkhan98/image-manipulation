package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Canvas class represents a custom JPanel used for displaying images.
 * It extends JPanel and provides methods to set the image and image ID to be displayed.
 */
public class Canvas extends JPanel {
  private BufferedImage image;
  private String imageID;

  /**
   * Returns the currently set image in the Canvas.
   *
   * @return The BufferedImage currently displayed in the Canvas.
   */
  public BufferedImage getImage() {
    return this.image;
  }

  /**
   * Returns the ID of the currently set image in the Canvas.
   *
   * @return The ID of the currently displayed image.
   */
  public String getImageID() {
    return this.imageID;
  }

  /**
   * Sets the image to be displayed in the Canvas.
   * It triggers a repaint to update the display.
   *
   * @param newImage The BufferedImage to be displayed in the Canvas.
   */
  public void setImage(BufferedImage newImage) {
    this.image = newImage;
    repaint();
  }

  /**
   * Sets the ID of the image to be displayed in the Canvas.
   *
   * @param newImageID The ID of the image to be displayed.
   */
  public void setImageID(String newImageID) {
    this.imageID = newImageID;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      super.paintComponent(g);
      if (image != null) {
        int width = getWidth();
        int height = getHeight();
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        double scaleFactor = Math.min((double) width / imageWidth, (double) height / imageHeight);
        int scaledWidth = (int) (imageWidth * scaleFactor);
        int scaledHeight = (int) (imageHeight * scaleFactor);

        int x = (width - scaledWidth) / 2;
        int y = (height - scaledHeight) / 2;

        g.drawImage(image, x, y, scaledWidth, scaledHeight, this);
      }
    }
  }
}
