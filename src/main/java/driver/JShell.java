package driver;

import commands.CommandController;
import repository.DataRepositoryInterface;
import repository.LocalRepositoryImpl;
import view.CommandLineUI;
import view.ICommandLineUI;

/**
 * This is the Shell that prompts user for input and prints the output
 * according to specified input
 */
public class JShell {

    public static void main(String[] args) throws Exception {
        DataRepositoryInterface repository = new LocalRepositoryImpl();
        JShellState shellState = new JShellState();
        CommandController commandController = new CommandController(repository);
        ICommandLineUI ui = new CommandLineUI(shellState, commandController);
        ui.scanCommand();
    }
}
