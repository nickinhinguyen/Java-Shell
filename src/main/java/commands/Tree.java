package commands;

import java.util.List;

import driver.IShellState;
import file_system.VirtualFileSystem;

/**
 * Display the entire file system as a tree
 */
public class Tree extends Command {

    public Tree() {
        super(0, 0);
    }

    /**
     * Displays the entire file system as a tree
     * @param shellState the state of JShell program
     * @param arguments  list of arguments that were passed along with command
     * @return Representation of the entire file system as a tree
     */
    @Override
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {
        checkArgumentsNum(arguments);
        VirtualFileSystem fs = shellState.getFileSystem();
        return fs.getTreeRepresentation();
    }



}
