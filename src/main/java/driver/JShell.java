package driver;

import java.util.Scanner;

import commands.CommandExecutor;

/**
 * This is the Shell that prompts user for input and prints the output
 * according to specified input
 */
public class JShell {

    public static void main(String[] args) {
        // initialize JShell state with the new root directory
        JShellState shellState = new JShellState();
        // create a command executor
        CommandExecutor commandExecutor = new CommandExecutor();
        // get the line reader
        Scanner in = new Scanner(System.in);
        // keep reading the command and executing it until user exits the program
        while (shellState.isRunning()) {
            // print $ to distinguish input with output
            System.out.print("$ ");
            // read next input line parse it and check if it is valid
            String commandLine = in.nextLine();
            // use command executor to execute the command line
            try {
                String output = commandExecutor.executeCommand(shellState, commandLine);
                // print output (if any)
                if (!output.equals("")) {
                    System.out.println(output);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        // close the scanner
        in.close();
    }
}
