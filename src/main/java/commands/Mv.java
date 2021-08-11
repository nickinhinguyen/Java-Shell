package commands;

import java.util.List;

import driver.IShellState;
import fileSystem.FileSystem;

/**
 * Moves a file or directory from one path to another
 */
public class Mv extends Command {

    public Mv() {
        super(2, 2);
    }

    /**
     * Moves a file or directory at one path to another path, possibly
     * overwritting a file or directory.
     * @param shellState is the state of the program
     * @param arguments is list of provided arguments
     * @throws Exception if given paths are invalid
     */
    @Override
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {
        checkArgumentsNum(arguments);
        FileSystem fs = shellState.getFileSystem();
        String oldPath = arguments.get(0);
        String newPath = arguments.get(1);
        fs.moveFsObject(shellState.getCurrentDir(), oldPath, newPath);
        return "";
    }

}
