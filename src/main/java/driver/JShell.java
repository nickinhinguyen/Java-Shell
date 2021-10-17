package driver;

import java.util.Scanner;

import commands.CommandExecutor;
import view.CommandLineUI;
import view.ICommandLineUI;

/**
 * This is the Shell that prompts user for input and prints the output
 * according to specified input
 */
public class JShell {

    public static void main(String[] args){
        JShellState shellState = new JShellState();
        CommandExecutor commandExecutor = new CommandExecutor();
        ICommandLineUI ui = new CommandLineUI(shellState, commandExecutor);
        ui.scanCommand();
    }
}
