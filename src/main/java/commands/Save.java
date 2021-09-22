package commands;

import org.apache.commons.io.FilenameUtils;
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
        checkArgumentsNum(arguments);
        String path = arguments.get(0);
        if (!path.contains("/")) {
            throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
        }

        String fileExt = FilenameUtils.getExtension(path);
        if (!fileExt.equals("ser")){
            throw new Exception(Exceptions.NOT_SER_FILE);
        }
        saveToRepository(shellState.getFileSystem(), shellState, path);
        return "";

    }

    public void saveToRepository(DataRepositoryInterface repo, IShellState shellState, String path ) throws Exception {
        repo.writeJShell(shellState, path);
    }


}
