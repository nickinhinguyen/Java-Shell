package view;

import commands.CommandController;
import driver.IShellState;
import driver.JShellState;

import java.util.Scanner;

public class CommandLineUI implements ICommandLineUI {
    public IShellState shellState;
    public CommandController executor;
    public Scanner in;

    public CommandLineUI(JShellState shellState, CommandController commandController) {
        this.shellState = shellState;
        this.executor = commandController;
        this.in = new Scanner(System.in);
    }

    public void scanCommand(){
        // keep reading the command and executing it until user exits the program
        while (shellState.isRunning()) {
            System.out.print("$ ");
            // read next input line parse it and check if it is valid
            String commandLine = in.nextLine();
            try {
                String output = executor.executeCommand(shellState, commandLine);
                if (!output.equals("")) {
                    System.out.println(output);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        in.close();
    }


}
