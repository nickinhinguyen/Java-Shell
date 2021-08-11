package commands;

import java.util.List;

import driver.IShellState;
import file_system.Directory;

public class Pushd extends Command {

    /**
     * Pushd class save the current working directory
     *  to directory stack and switch to given dir
     *
     */
    public Pushd() {
        super(1, 1);
    }

    /**
     * Saves the current working directory to directory stack
     * and then changes the new current working directory to given directory
     * @param shellState is the state of JShell program
     * @param arguments is list of arguments that were passed along with command
     * (includes the path of directory to be switched to)
     */
    public String executeCommand(IShellState shellState,  List<String> arguments)
            throws Exception {
        checkArgumentsNum(arguments);
        // get current dir
        Directory currentDir = shellState.getCurrentDir();
        // use cd method to move to the new directory
        Cd cd = new Cd();
        cd.executeCommand(shellState, arguments);
        // add current dir to the end of the stack
        shellState.getDirectoryStack().push(currentDir);
        return "";
    }

}