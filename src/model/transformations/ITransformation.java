package model.transformations;

import model.image.IImageState;

/**
 * Represents an image transformation operation.
 * Image transformations are used to process an IImageState object and produce a modified version
 * of the image.
 */
public interface ITransformation {
  /**
   * Transforms the source image using the specific transformation operation.
   *
   * @param sourceImage The IImageState representing the source image to be transformed.
   * @return The IImageState representing the transformed image after applying the operation.
   * @throws NullPointerException If the sourceImage is null.
   */
  IImageState run(IImageState sourceImage);
}
