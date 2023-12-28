package controller;

import controller.commands.BlueComponentCommand;
import controller.commands.BlurCommand;
import controller.commands.BrightenCommand;
import controller.commands.GrayscaleCommand;
import controller.commands.GreenComponentCommand;
import controller.commands.ICommand;
import controller.commands.IntensityComponentCommand;
import controller.commands.LoadCommand;
import controller.commands.LumaComponentCommand;
import controller.commands.RedComponentCommand;
import controller.commands.SaveCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;
import controller.commands.ValueComponentCommand;
import model.IImageDataBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Implements the IController interface and serves as the main controller of the application.
 */
public class ControllerImpl implements IController {
  private final Readable input;
  private final IImageDataBase model;
  private final Appendable appendable;
  private final Map<String, ICommand> commandMap;

  /**
   * Constructs a new ControllerImpl with the specified input source, image database model, and
   * appendable output.
   *
   * @param input      The Readable object representing the input source for user interactions.
   * @param model      The image database model used to store and process image data.
   * @param appendable The Appendable object representing the output destination for command
   *                   responses.
   * @throws NullPointerException If input, model, or appendable is null.
   */
  public ControllerImpl(Readable input, IImageDataBase model, Appendable appendable) {
    this.input = Objects.requireNonNull(input);
    this.model = Objects.requireNonNull(model);
    this.appendable = Objects.requireNonNull(appendable);

    this.commandMap = new HashMap<String, ICommand>();
    this.commandMap.put("brighten", new BrightenCommand());
    this.commandMap.put("value-component", new ValueComponentCommand());
    this.commandMap.put("intensity-component", new IntensityComponentCommand());
    this.commandMap.put("luma-component", new LumaComponentCommand());
    this.commandMap.put("red-component", new RedComponentCommand());
    this.commandMap.put("green-component", new GreenComponentCommand());
    this.commandMap.put("blue-component", new BlueComponentCommand());
    this.commandMap.put("load", new LoadCommand());
    this.commandMap.put("save", new SaveCommand());
    this.commandMap.put("blur", new BlurCommand());
    this.commandMap.put("sharpen", new SharpenCommand());
    this.commandMap.put("grayscale", new GrayscaleCommand());
    this.commandMap.put("sepia", new SepiaCommand());
  }

  /**
   * Appends the given message to the appendable output.
   *
   * @param message The message to be appended to the output.
   * @throws IllegalStateException If writing to the appendable output fails due to an IOException.
   */
  private void write(String message) {
    try {
      this.appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Writing to the appendable failed");
    }
  }

  @Override
  public void run() {
    Scanner scanner = new Scanner(this.input);

    while (scanner.hasNext()) {
      String command = scanner.next();

      if (command.equals("-file")) {
        if (!scanner.hasNext()) {
          write("Invalid command. Please provide the script file name.");
          continue;
        }

        String scriptPath = scanner.next();
        File scriptFile = new File(scriptPath);

        Reader reader;
        try {
          reader = new FileReader(scriptFile);
          Readable script = new BufferedReader(reader);
          Scanner scriptScanner = new Scanner(script);

          while (scriptScanner.hasNext()) {
            String scriptCommand = scriptScanner.next();
            ICommand commandToRun = this.commandMap.getOrDefault(scriptCommand, null);
            if (commandToRun == null) {
              write("Invalid command in script: " + scriptCommand);
              continue;
            }
            try {
              commandToRun.run(scriptScanner, this.model);
            } catch (IllegalStateException e) {
              write(e.getMessage());
            }
          }

          scriptScanner.close();
          System.exit(0);
        } catch (IOException e) {
          System.out.println("Error reading script file: " + e.getMessage());
          System.exit(1);
        }
      } else {
        ICommand commandToRun = this.commandMap.getOrDefault(command, null);
        if (command == null) {
          write("Invalid command.");
          continue;
        }
        try {
          commandToRun.run(scanner, this.model);
        } catch (IllegalStateException e) {
          write(e.getMessage());
        }
      }
    }
  }
}
