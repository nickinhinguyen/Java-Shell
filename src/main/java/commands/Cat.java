package commands;

import java.util.List;

import driver.IShellState;
import file_system.File;
import helper_classes.PathHandler;

/**
 * Cat class Returns content of the file(s) at path(s) that are provided
 */
public class Cat extends Command {

    public Cat() {
        super(100, 1);
    }

    /**
     * Returns content of the file(s) at path(s) that are provided
     * @param shellState is the state of the program
     * @param arguments is list of arguments that were passed along with command
     * @throws Exception if path is invalid
     */
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {
        /*
         * loop through each path in the argument list
         * and get the directory of the requirement file and its name
         */
        checkArgumentsNum(arguments);
        StringBuilder output = new StringBuilder();
        for (String path: arguments) {
            try {
                File filetoCat = PathHandler.getFileByPath(shellState, path);
                /*
                 * if this is the first file get cat, do not include line break
                 * else concatenate the current file after the previous
                 * file with 3 lines break
                 */
                output.append((output.toString().equals("")) ?
                        filetoCat.getContent() : ("\n\n" + filetoCat.getContent()));
            }
            catch (Exception e) {
                String errorString = "cannot view file " + path + ": " + e.getMessage();
                output.append((output.toString().equals("")) ?
                        errorString : "\n\n" + errorString);
            }
        }
        return output.toString();
    }


}
