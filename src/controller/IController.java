package controller;

/**
 * Represents a controller. It starts the application and handles user input.
 */
public interface IController {
  /**
   * Starts the application and handles user interactions.
   *
   * @throws IllegalStateException If an error occurs while processing commands or writing to
   *                               the appendable output.
   */
  void run();
}
