package model;

import model.image.IImageState;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a database that stores and manages images.
 * It implements the IImageDataBase interface and allows adding new images, retrieving images
 * by their unique identifier, and accessing all the stored images as a map.
 */
public class ImageDataBase implements IImageDataBase {
  private final Map<String, IImageState> images;

  /**
   * Constructs a new ImageDataBase object.
   * This initializes an empty database for storing images.
   */
  public ImageDataBase() {
    this.images = new HashMap<String, IImageState>();
  }

  @Override
  public void add(String id, IImageState image) {
    if (id == null || image == null) {
      throw new IllegalArgumentException("id or image is null");
    }
    this.images.put(id, image);
  }

  @Override
  public IImageState get(String id) {
    Objects.requireNonNull(id);
    return this.images.get(id);
  }

  @Override
  public Map<String, IImageState> getImages() {
    return this.images;
  }
}
