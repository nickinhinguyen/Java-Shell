package commands;

import org.apache.commons.io.FilenameUtils;
import java.util.List;
import constants.Exceptions;
import driver.IShellState;
import repository.DataRepositoryInterface;

/**
 * This class is responsible for saving the current JShell session into a file
 */
public class Save extends Command {
    private DataRepositoryInterface repository;

    public Save(DataRepositoryInterface repository) {
        super(1, 1);
        this.repository = repository;
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
        saveToRepository( shellState, path);
        return "";

    }

    private void saveToRepository(IShellState shellState, String path ) throws Exception {
        repository.writeJShell(shellState, path);
    }


}
