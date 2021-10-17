package commands;

import java.util.List;
import java.util.Stack;

import constants.Exceptions;
import driver.IShellState;
import file_system.Directory;

/**
 * Pop class Remove the top entry from
 * the directory stack, and cd into it
 * 
 */
public class Popd extends Command {
    public Popd() {
        super(0, 0);
    }

    /**
     * Remove the top entry from the directory stack, and cd into it
     * @param shellState is the state of JShell program
     * @param arguments is list of arguments that were passed along with command
     * (includes the path of directory to be switched to)
     * @throws Exception if pop from empty directory stack
     */
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {
        checkArgumentsNum(arguments);
        Stack<Directory> dirStack = shellState.getDirectoryStack();
        int stackLen = dirStack.size();
        if (stackLen == 0) {
            throw new Exception(Exceptions.POP_EMPTY_STACK);
        }
        // switch to last directory in the directoryStack(removing it from stack)
        shellState.setCurrentDir(dirStack.pop());
        return "";
    }
}