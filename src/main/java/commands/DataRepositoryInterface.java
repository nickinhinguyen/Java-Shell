package commands;

import driver.IShellState;

public interface DataRepositoryInterface {
    void writeJShell(IShellState serializeObj, String path) throws Exception;
    void loadJShell(IShellState currentShell, String path) throws Exception;
}
