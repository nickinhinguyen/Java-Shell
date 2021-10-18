package commands;

import java.io.*;
import java.util.List;

import constants.Exceptions;
import driver.IShellState;

/**
 * This class is responsible for loading up the previously saved JShell session
 */
public class Load extends Command {

    public Load() {
        super(1, 1);
    }

    /**
     * This method will load the previous JShell session that was saved on the
     * file from the given path
     *
     * @param shellState is the current state of JShell program
     * @param arguments  list  of arguments that were passed along with command
     * @throws Exception if any of the paths is invalid
     */
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {

        try {
            checkArgumentsNum(arguments);
            // check if load was the first to be called in new session
            List<String> his = shellState.getHistory();
            if (his.size() != 1) {
                throw new Exception("Load can only be called at the start of a session");
            }
            String path = arguments.get(0);
            if (!path.contains("/")) {
                throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
            }

            loadShellState1(shellState, path);
            return "";
        } catch (IOException e) {
            throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
        }

    }

    private void loadShellState2(IShellState shellState, String path) throws Exception {
        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        IShellState loadShellState = (IShellState) in.readObject();
        // what do we do with it now? We want to replace the ShellState
        // object in method main, but we don't have a way for this use
        // case to reach into that method. A design problem!
        System.out.println(shellState.getCorrectHistory());
        in.close();
        fileIn.close();
    }

    private void loadShellState1(IShellState shellState, String path) throws Exception {
        String line;
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        CommandController commandExecutor = new CommandController();
        boolean nextPart = false;
        int count = 0;
        String currentHistory = shellState.getHistory().get(0);
        shellState.removeHistory(0);

        // load the file system
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals("*****")) {
                nextPart = true;
            } else if (!nextPart) {
                shellState.addHistory(line);

            } else {
                // executeCommand correct command
                count++;
                commandExecutor.executeCommand(shellState, line);
            }

        }

        bufferedReader.close();
        // remove double history
        for (int i = 0; i < count; i++) {
            int currentSize = shellState.getHistory().size();
            shellState.removeHistory(currentSize - 1);
        }
        shellState.addHistory("exit");
        shellState.addHistory(currentHistory);
    }

}
