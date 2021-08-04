package commands;

import java.util.List;

import driver.IShellState;

/**
 * Interface for commands that can be executed recursively (have -R as an
 * optional parameter)
 * @author Minh Hoang Nguyen
 *
 */
public interface RecursiveInterface {
	// method for a command to execute recursively
	public String executeRecursively
	(IShellState shellState, List<String> arguments) throws Exception ;
}
