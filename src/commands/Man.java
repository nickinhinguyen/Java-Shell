package commands;

import java.util.List;
import constants.Constants;
import driver.IShellState;
/**
 * This class will get the documentation for a given command
 * @author Devanshi Parekh
 *
 */
public class Man extends Command {

  public Man() {
    super(1, 1);
  }
  
  /**
   * Prints the documentation for the given command
   * @param shellState is the current state of JShell program
   * @param arguments is the command that indicates what should be printed
   * @return the current FileSystemObject the path indicates
   * @throws Exception if command is invalid
   */
  @Override
  public String executeCommand(IShellState shellState, List<String> arguments)
      throws Exception{
    checkArgumentsNum(arguments);
    String commandToPrint = arguments.get(0);
    if (!CommandReader.isValidCommand(commandToPrint)) {
      throw new Exception("command to print documentation not found");
    }
    return getDocumentation(commandToPrint);
  }

  /**
   * Gets the documentation for the given command
   * @param command is the command that indicates what documentation to get
   * @return the documentation of the given command
   */
  private String getDocumentation(String command) {
    return Constants.COMMAND_DOCUMENTATION.get(command);
  }

}
