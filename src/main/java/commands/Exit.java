package commands;

import java.util.List;

import driver.IShellState;

/**
 * This class is responsible for letting user to exit the program
 */
public class Exit extends Command {

    public Exit() {
        super(0, 0);
    }

    /**
     * Stop the JShell program by changing its state
     * @param shellState is the object that holds JShell's state
     * @throws Exception if number of arguments provided is incorrect
     */
    public String executeCommand
    (IShellState shellState, List<String> arguments) throws Exception{
        // check if there is correct number of arguments, raise exception if not
        checkArgumentsNum(arguments);
        // stop the program through its state
        shellState.stopRunning();
        return "";
    }

}
