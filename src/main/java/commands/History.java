package commands;

import java.util.ArrayList;
import java.util.List;

import constants.Exceptions;
import driver.IShellState;

public class History extends Command {

    public History() {
        super(1, 0);
    }

    /**
     * print out recent commands, one command per line
     * @param shellState is the state of JShell program
     * @param arguments is list of arguments that were passed along with command
     * (includes the path of directory to be switched to)
     */
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {
        int numberOfCommand;
        checkArgumentsNum(arguments);
        /* if no argument is passed,
         * set numberOfCommand to print the all history
         */
        if (arguments.size() == 0) {
            numberOfCommand =shellState.getHistory().size();
        } else {
            numberOfCommand = Integer.parseInt(arguments.get(0));
        }
        return getHistory(shellState, numberOfCommand);
    }

    /**
     * print out recent commands, one command per line
     * @param shellState is the state of JShell program
     * @param numberOfCommand is the number of command in the history
     * the user want to see
     * @throws Exception if numberOfCommand < 0
     */
    private String getHistory(IShellState shellState, int numberOfCommand)
            throws Exception {
        List<String> history = shellState.getHistory();
        int i = history.size() - 1 ;
        StringBuilder output = new StringBuilder();
        /*
         *  if numberOfCommand exceed the number of history,
         *  set it to print up to all history
         */
        if (numberOfCommand > history.size()) {
            numberOfCommand = history.size();
        } else if (numberOfCommand < 0) {
            throw new Exception(Exceptions.INVALID_ARGUMENT);
        }
        /*
         * loop and print the number of commands required
         * or until all history commands have been printed
         */
        while (numberOfCommand > 0 && i <= history.size()) {
            output.insert(0, i + 1 + ". " + history.get(i) + "\n");
            i--;
            numberOfCommand--;
        }
        return output.toString();
    }
}