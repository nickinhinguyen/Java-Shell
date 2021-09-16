package commands;

import java.util.List;
import constants.Exceptions;
import driver.IShellState;
/**
 * This class is responsible for loading up the previously saved JShell session
 */
public class Load extends Command{

    public Load() {
        super(1, 1);
    }

    /**
     * This method will load the previous JShell session that was saved on the
     * file from the given path
     * @param shellState is the current state of JShell program
     * @param arguments  list  of arguments that were passed along with command
     * @throws Exception if any of the paths is invalid
     */
    public String executeCommand(IShellState shellState, List<String> arguments) throws Exception {
        checkArgumentsNum(arguments);
        // check if load was the first to be called in new session
        List<String> his = shellState.getHistory();
        if (his.size() != 1) {
            throw new Exception("Load can only be called at the start of a session");
        }
        String path = arguments.get(0);
        if (path.indexOf("/") == -1) {
            throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
        }

        loadFromRepository(shellState.getFileSystem(), shellState, path);
        return "";
    }

    public void loadFromRepository(DataRepositoryInterface repo, IShellState shellState, String path ) throws Exception {
        repo.loadJShell(shellState, path);
    }

}
