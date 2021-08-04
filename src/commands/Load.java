package commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import constants.Exceptions;
import driver.IShellState;
/**
 * This class is responsible for loading up the previously saved JShell session
 * @author Devanshi Parekh
 *
 */
public class Load extends Command{

  public Load() {
    super(1, 1);
  }

  /**
   * This method will load the previous JShell session that was saved on the
   * file from the given path
   * @param shellState is the current state of JShell program
   * @param arguments  list  of arguments that were passed along with command
   * @throws Exception if any of the paths is invalid
   */
  public String executeCommand(IShellState shellState, List<String> arguments)
      throws Exception {
    
    try {
      checkArgumentsNum(arguments);
      // check if load was the first to be called in new session
      ArrayList<String> his = shellState.getHistory();
      if (his.size() != 1) {
        throw new Exception("Load can only be called at the start of a session");
      }
      String path = arguments.get(0);
      if (path.indexOf("/") == -1) {
        throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
      }
      String line = null;
      
      FileReader fileReader = new FileReader(path);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      // create a command executor
      CommandExecutor commandExecutor = new CommandExecutor();
      // initialize variables
      boolean nextPart = false;
      int count = 0;
      // get current history
      String currentHistory = shellState.getHistory().get(0);
      // remove current history
      shellState.removeHistory(0);
      // add previous history
      
      // load the file system
      while((line = bufferedReader.readLine()) != null) {
        if (line.equals("*****")) {
          nextPart = true;
        }
        else if (nextPart == false) {
          shellState.addHistory(line);
          
        }
        else if (nextPart == true) {
          // executeCommand correct command
          count ++;
          @SuppressWarnings("unused")
          String output = commandExecutor.executeCommand(shellState, line);
        }
        
        }
      
      // close bufferedReader
      bufferedReader.close();
      // remove double history
      for (int i = 0; i<count; i++) {
        int currentSize = shellState.getHistory().size();
        shellState.removeHistory(currentSize-1);
      }
      // add exit
      shellState.addHistory("exit");
      // add current history
      shellState.addHistory(currentHistory);
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
