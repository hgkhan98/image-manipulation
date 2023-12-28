package view;

/**
 * The ViewListener interface defines a set of callback methods that a class can implement
 * to receive events from the View. These events are triggered by user interactions or actions
 * in the graphical user interface (GUI).
 */
public interface ViewListener {

  /**
   * Called when the "Load Image" button is clicked in the View.
   * Implement this method to handle the event of loading an image.
   */
  void handleLoadEvent();

  /**
   * Called when the "Save Image" button is clicked in the View.
   * Implement this method to handle the event of saving an image.
   */
  void handleSaveEvent();

  /**
   * Called when the "Brighten" button is clicked in the View.
   * Implement this method to handle the event of applying a brightening effect to the image.
   */
  void handleBrightenEvent();

  /**
   * Called when the "Value-component" button is clicked in the View.
   * Implement this method to handle the event of adjusting the value component of the image.
   */
  void handleValueEvent();

  /**
   * Called when the "Intensity-component" button is clicked in the View.
   * Implement this method to handle the event of adjusting the intensity component of the image.
   */
  void handleIntensityEvent();

  /**
   * Called when the "Luma-component" button is clicked in the View.
   * Implement this method to handle the event of adjusting the luma component of the image.
   */
  void handleLumaEvent();

  /**
   * Called when the "Red-component" button is clicked in the View.
   * Implement this method to handle the event of adjusting the red component of the image.
   */
  void handleRedEvent();

  /**
   * Called when the "Green-component" button is clicked in the View.
   * Implement this method to handle the event of adjusting the green component of the image.
   */
  void handleGreenEvent();

  /**
   * Called when the "Blue-component" button is clicked in the View.
   * Implement this method to handle the event of adjusting the blue component of the image.
   */
  void handleBlueEvent();

  /**
   * Called when the "Blur" button is clicked in the View.
   * Implement this method to handle the event of applying a blur effect to the image.
   */
  void handleBlurEvent();

  /**
   * Called when the "Sharpen" button is clicked in the View.
   * Implement this method to handle the event of applying a sharpen effect to the image.
   */
  void handleSharpenEvent();

  /**
   * Called when the "Grayscale" button is clicked in the View.
   * Implement this method to handle the event of converting the image to grayscale.
   */
  void handleGrayscaleEvent();

  /**
   * Called when the "Sepia" button is clicked in the View.
   * Implement this method to handle the event of applying a sepia effect to the image.
   */
  void handleSepiaEvent();
}
