package driver;

import java.util.Scanner;

import commands.CommandController;

/**
 * This is the Shell that prompts user for input and prints the output
 * according to specified input
 */
public class JShell {

    public static void main(String[] args) {
        JShellState shellState = new JShellState();
        CommandController commandExecutor = new CommandController();
        Scanner in = new Scanner(System.in);
        // keep reading the command and executing it until user exits the program
        while (shellState.isRunning()) {
            System.out.print("$ ");
            // read next input line parse it and check if it is valid
            String commandLine = in.nextLine();
            try {
                String output = commandExecutor.executeCommand(shellState, commandLine);
                if (!output.equals("")) {
                    System.out.println(output);
                }
            }
            catch (Exception e) {
                // TODO: change to CommandException everywhere.
                System.out.println(e.getMessage());
            }
        }
        in.close();
    }
}
