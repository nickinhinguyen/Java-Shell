package commands;

import java.util.List;

import driver.IShellState;

/**
 * Display path of current directory in the program
 */
public class Pwd extends Command {

    public Pwd() {
        super(0, 0);
    }

    /**
     * Print the current working directory (including the whole path).
     * @param shellState is the state of JShell program
     * @param arguments is list of arguments that were passed along with command
     * (includes the path of directory to be switched to)
     */
    public String executeCommand
    (IShellState shellState, List<String> arguments) throws Exception{
        checkArgumentsNum(arguments);
        return shellState.getCurrentDir().getPath();
    }

}
