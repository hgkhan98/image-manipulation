package model;

import model.image.IImageState;

import java.util.Map;

/**
 * Represents a database that stores and manages images.
 * It allows adding new images, retrieving images by their unique identifier, and accessing all
 * the stored images as a map.
 */
public interface IImageDataBase {
  /**
   * Adds a new image to the database with the specified unique identifier.
   *
   * @param id    The unique identifier for the image to be added.
   * @param image The IImageState representing the image to be added to the database.
   * @throws NullPointerException If the provided image is null.
   */
  void add(String id, IImageState image);

  /**
   * Retrieves the image associated with the specified unique identifier from the database.
   *
   * @param id The unique identifier of the image to be retrieved.
   * @return The IImageState representing the image associated with the specified identifier.
   */
  IImageState get(String id);

  /**
   * Retrieves all the images stored in the database as a map.
   *
   * @return A map containing the identifiers as keys and the corresponding IImageState objects.
   */
  Map<String, IImageState> getImages();
}
