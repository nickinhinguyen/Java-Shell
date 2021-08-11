package commands;

import java.util.List;

import driver.IShellState;
import fileSystem.FileSystem;

/**
 * This class copies a file or directory from one place to another
 */
public class Cp extends Command {

    public Cp() {
        super(2, 2);
    }

    /**
     * Copies a file or directory at one path to another path, possibly
     * overwritting a file or directory.
     * @param shellState is the state of the program
     * @param arguments is list of provided arguments
     * @throws Exception if given paths are invalid
     */
    @Override
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {
        // check number of arguments
        checkArgumentsNum(arguments);
        // copy object
        FileSystem fs = shellState.getFileSystem();
        String oldPath = arguments.get(0);
        String newPath = arguments.get(1);
        fs.copyFsObject(shellState.getCurrentDir(), oldPath, newPath);
        return "";
    }

}
