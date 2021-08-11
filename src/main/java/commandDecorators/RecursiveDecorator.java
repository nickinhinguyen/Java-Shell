package commandDecorators;

import java.util.List;

import commands.Command;
import commands.RecursiveInterface;
import constants.Exceptions;
import driver.IShellState;

/**
 * This class is a decorator for command that can be executed recursively
 * (can take -R as an optional parameter)
 */
public class RecursiveDecorator extends CommandDecorator {

    public RecursiveDecorator(Command command) {
        super(command);
    }

    /**
     * Checks if given command takes -R as an optional parameter. Then execute
     * accordingly
     * @param shellState is state of program
     * @param arguments are base arguments provided
     * @throws Exception if command doesn't take -R
     */
    @Override
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {
        // if command does not support -R as optional parameter
        if (!(command instanceof RecursiveInterface)) {
            throw new Exception(Exceptions.INVALID_OPTIONAL_PARAM);
        }
        return ((RecursiveInterface) command).
                executeRecursively(shellState, arguments);
    }

}
