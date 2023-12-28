package controller.commands;

import model.IImageDataBase;

import java.util.Scanner;

/**
 * Represents the ICommand interface that performs specific operations on the database.
 */
public interface ICommand {
  /**
   * Executes the command with the given scanner and image database model.
   *
   * @param scanner The scanner to read user input from.
   * @param model   The image database model on which the command will be executed.
   * @throws NullPointerException If the scanner or model is null.
   */
  void run(Scanner scanner, IImageDataBase model);
}
