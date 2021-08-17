package commands;

import java.util.List;

import driver.IShellState;
import fileSystem.Directory;
import helperClasses.DirectoryFileNameTuple;
import helperClasses.PathHandler;

/**
 * Creates directories in the file system
 */
public class Mkdir extends Command {
    private static final String startingErrorMsg = "cannot create directory";

    public Mkdir() {
        super(100, 1);
    }

    /**
     * Creates many directories according to each path
     * @param shellState is the current state of JShell program
     * @param arguments is the list of paths that indicate where new directories
     * should be created
     * @throws Exception if any of the paths is invalid
     */
    public String executeCommand
    (IShellState shellState, List<String> arguments) throws Exception {
        // check for arguments
        checkArgumentsNum(arguments);
        // initialize string that holds all error messages that might occur
        String allErrorMsg = "";
        // loop through each argument(path)
        for (String path: arguments) {
            try {
                createDir(shellState, path);
            }
            catch (Exception e) {
                String fullErrorMsg = constructFullErrorMsg(path, e.getMessage());
                allErrorMsg += (allErrorMsg == "") ?
                        fullErrorMsg : ("\n" + fullErrorMsg);
            }
        }
        // throw all the message errors at once if there are any
        if (allErrorMsg != "") {
            throw new Exception(allErrorMsg);
        }
        return "";
    }

    /**
     * Creates a single new directory according to the given path
     * @param shellState is the current state of JShell program
     * @param path indicates where the new directory should be created
     * @throws Exception if given path is invalid
     */
    private void createDir(IShellState shellState, String path)
            throws Exception {
        /*
         * get the directory from which new directory should be added from and
         * name of the new directory
         */
        DirectoryFileNameTuple wantedDirAndFileName =
                PathHandler.getDirectoryAndFileName(shellState, path);
        // create the new directory
        new Directory(wantedDirAndFileName.getFileName(),
                wantedDirAndFileName.getDirectory());
    }

    /**
     * Constructs a complete detailed error message for this command subclass
     * @param path is the path from which directory could not be created
     * @param detailedErrorMsg is the error message that was returned when
     * creating directory failed
     * @return string that represents the complete detailed error message
     */
    private String constructFullErrorMsg(String path, String detailedErrorMsg) {
        return startingErrorMsg + " '" + path + "': " + detailedErrorMsg;
    }
}
