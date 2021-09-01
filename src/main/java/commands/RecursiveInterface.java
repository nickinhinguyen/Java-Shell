package commands;

import java.util.List;

import driver.IShellState;

/**
 * Interface for commands that can be executed recursively (have -R as an
 * optional parameter)
 */
public interface RecursiveInterface {
    String executeRecursively
    (IShellState shellState, List<String> arguments);
}
