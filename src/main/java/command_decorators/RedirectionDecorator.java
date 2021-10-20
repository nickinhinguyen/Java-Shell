package command_decorators;

import java.util.ArrayList;
import java.util.List;

import commands.Command;
import constants.Constants;
import constants.Exceptions;
import driver.IShellState;
import file_system.Directory;
import file_system.File;
import helper_classes.DirectoryFileNameTuple;
import helper_classes.PathHandler;

/**
 * This is a decorator that redirects output to a provided outfile
 */
public class RedirectionDecorator extends CommandDecorator {

    public RedirectionDecorator(Command command) {
        super(command);
    }

    /**
     * Redirect result from the command to a file provided in the arguments
     * @param shellState is the state of the program
     * @param arguments are provided arguments, including redirection type and
     * path of file to execute redirection on (length >= 2)
     * @throws Exception if path of redirection file is invalid
     */
    @Override
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {
        // get arguments excluding the redirection part
        List<String> baseArgs = arguments.subList(0, arguments.size() - 2);
        // get the output that the command executes with base arguments
        String contentToRedirect = command.executeCommand(shellState, baseArgs);
        contentToRedirect = removeErrors(contentToRedirect);
        // get the redirection type and path of file to redirect output to
        String redirectionType = arguments.get(arguments.size() - 2);
        String path = arguments.get(arguments.size() - 1);
        // get fileName and directory from which the outfile should be in
        DirectoryFileNameTuple wantedDirAndFileName =
                PathHandler.getDirectoryAndFileName(shellState, path);
        Directory dirToCreateFileFrom = wantedDirAndFileName.getDirectory();
        String fileName = wantedDirAndFileName.getFileName();
        // if file doesn't yet exist
        if (!dirToCreateFileFrom.hasChildWithTypeAndName(File.class, fileName)) {
            // create new file with String argument as its content
            new File(fileName, dirToCreateFileFrom, contentToRedirect);
        }
        else {
            // get the existing file
            File existingFile =
                    Directory.findChildFile(dirToCreateFileFrom, fileName);
            // append or overwrite accordingly
            if (redirectionType.equals(Constants.REDIRECTION_OVERWRITE)) {
                existingFile.setContent(contentToRedirect);
            } else {
                existingFile.appendContent(contentToRedirect);
            }
        }
        return "";
    }

    /**
     * Forward the error messages to StdErr and return the given string without errors
     * @param result is the given string
     * @return the given string without error messages
     */
    private String removeErrors(String result) {
        // get all the error messages
        ArrayList<String> errorMessages = Exceptions.getErrors();
        // remove all the error messages
        for (String errorMessage : errorMessages) {
            result = removeError(result, errorMessage);
        }
        return result;
    }

    /**
     * Remove the specified error message from the given string
     * @param result is the given string
     * @param error is the specified error message
     * @return given string without specified error message
     */
    private String removeError(String result, String error) {
        // remove the error message until are none left
        while (result.contains(error)) {
            int firstIndex = result.indexOf(error);
            int startIndex = 0;
            for (int i = firstIndex; i >= 0; i--) {
                startIndex = i;
                if (result.charAt(i) == '\n') {
                    break;
                }
            }
            int lastIndex = firstIndex + error.length();
            if (lastIndex < result.length()) {
                lastIndex ++;
            }
            // print given string contain error msg to stderr
            System.err.println("Redirection removeError: " + result.substring(startIndex, lastIndex));
            result = result.substring(0, startIndex)
                    + result.substring(lastIndex);
        }
        return result;

    }
}
