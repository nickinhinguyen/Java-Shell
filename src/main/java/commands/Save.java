package commands;

import java.io.*;
import java.util.List;
import constants.Exceptions;
import driver.IShellState;
/**
 * This class is responsible for saving the current JShell session into a file
 */
public class Save extends Command {

    public Save() {
        super(1, 1);
    }

    /**
     * This method will save the current JShell session into a file which will
     * created/ overwritten at the given path
     * @param shellState is the current state of JShell program
     * @param arguments  list of arguments that were passed along with command
     * @throws Exception if any of the paths is invalid
     */
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {
        try {
            checkArgumentsNum(arguments);
            String path = arguments.get(0);
            if (!path.contains("/")) {
                throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
            }

            saveToFile2(shellState, path);
            return "";
        } catch (IOException e) {
            throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
        }
    }

    private void saveToFile2(IShellState shellState, String path) throws IOException {

        FileOutputStream fileOut =
                new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(shellState);
        out.close();
        fileOut.close();
    }

    private void saveToFile1(@org.jetbrains.annotations.NotNull IShellState shellState, String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        //add history
        for (String commandLine : shellState.getHistory()) {
            bw.write(commandLine);
            bw.newLine();
        }
        bw.write("*****");
        bw.newLine();
        // add content to file
        for (String commandLine : shellState.getCorrectHistory()) {
            bw.write(commandLine);
            bw.newLine();
        }
        bw.close();
    }

}
