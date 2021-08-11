package commands;

import java.util.List;

import driver.IShellState;

/**
 * This class reads the provided STRING argument
 */
public class Echo extends Command {

    public Echo() {
        super(1, 1);
    }

    /**
     * Returns provided STRING argument.
     * @param shellState is the state of the program
     * @param arguments is list of arguments that were passed along with command
     * @throws Exception number of arguments provided is incorrect
     */
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {
        // check arguments
        checkArgumentsNum(arguments);
        return arguments.get(0);
    }

}
