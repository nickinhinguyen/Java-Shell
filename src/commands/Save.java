package commands;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import constants.Exceptions;
import driver.IShellState;
/**
 * This class is responsible for saving the current JShell session into a file
 * @author Devanshi Parekh
 *
 */
public class Save extends Command {

  public Save() {
    super(1, 1);
  }

  /**
   * This method will save the current JShell session into a file which will
   * created/ over written at the given path 
   * @param shellState is the current state of JShell program
   * @param arguments  list of arguments that were passed along with command
   * @throws Exception if any of the paths is invalid
   */
  public String executeCommand(IShellState shellState, List<String> arguments)
      throws Exception {
    try {
      checkArgumentsNum(arguments);
      String path = arguments.get(0);
      if (path.indexOf("/") == -1) {
        throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
      }
      FileWriter fileWriter = new FileWriter(path);
      BufferedWriter bw = new BufferedWriter(fileWriter);
      //add history
      for (String commadLine : shellState.getHistory()) {
        bw.write(commadLine);
        bw.newLine();
      }
      bw.write("*****");
      bw.newLine();
      // add content to file
      for (String commadLine : shellState.getCorrectHistory()) {
        bw.write(commadLine);
        bw.newLine();
      }
      // close bufferedWriter
      bw.close();
      return "";
    } 
    catch (FileNotFoundException e) {
      throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
    }
    catch(IOException ex) {
      throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
    }
  }

}
