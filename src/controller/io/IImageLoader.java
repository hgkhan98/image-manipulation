package controller.io;

import model.image.IImageState;

/**
 * Represents an image loader that can load an image from a file.
 */
public interface IImageLoader {
  /**
   * Loads an image from a file and returns its representation as an IImageState object.
   *
   * @return The loaded image as an IImageState object.
   */
  IImageState run();
}
