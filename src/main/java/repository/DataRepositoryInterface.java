package repository;

import driver.IShellState;

/**
 * Interface for the Repository for the shell. The Repository handles the saving and loading data operations
 */
public interface DataRepositoryInterface {

    /**
     * save/write the given JShell session into the repository with given path
     * @param serializeObj a serialized JShell object to write to the repository
     * @param path the path where the object will be written to
     * @throws Exception if any of the provided arguments is invalid
     */
    void writeJShell(IShellState serializeObj, String path) throws Exception;

    /**
     * load an existed JShell session from a given location in the repository
     * @param currentShell current shell session which will be replaced with the given JShell session
     * @param path the path where the object will be written to
     * @throws Exception if any of the provided arguments is invalid
     */
    void loadJShell(IShellState currentShell, String path) throws Exception;
}