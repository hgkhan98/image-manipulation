import controller.Controller;
import controller.ControllerImpl;
import controller.IController;
import model.IImageDataBase;
import model.ImageDataBase;
import view.View;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * The Main class is the entry point of the image processing application.
 * It initializes the required components (model, controller) and starts the processing by
 * invoking the controller's "run" method.
 */
public class Main {
  /**
   * The main method runs the controller.
   *
   * @param args The command-line arguments.
   */
  public static void main(String[] args) {
    IImageDataBase model = new ImageDataBase();
    IController controller;

    try {
      if (args.length == 1 && args[0].equals("-text")) {
        Reader input = new InputStreamReader(System.in);
        controller = new ControllerImpl(input, model, System.out);
      } else if (args.length == 2 && args[0].equals("-file")) {
        String scriptFileName = args[1];
        Reader input = new FileReader(scriptFileName);
        controller = new ControllerImpl(input, model, System.out);
      } else if (args.length == 0 || (args.length == 1 && args[0].trim().isEmpty())) {
        controller = new Controller(model, new View(model));
      } else {
        System.err.println("Invalid command-line arguments.");
        System.err.println("Usage: ");
        System.err.println("  java Main -text");
        System.err.println("  java Main -file path-of-script-file");
        System.err.println("  java Main");
        return;
      }

      controller.run();
    } catch (IOException e) {
      System.out.println("Error reading script file: " + e.getMessage());
    }
  }
}
