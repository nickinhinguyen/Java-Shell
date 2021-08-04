package commands;

import java.util.List;

import driver.IShellState;

/**
 * This is a base class for any commands that might be needed in the program
 * @author Minh Hoang Nguyen
 *
 */
public abstract class Command {
	// this is maximum number of arguments that command takes
	protected int maxArguments;
	//this is minimum number of arguments that command takes
	protected int minArguments;
	
	public Command(int maxArguments, int minArguments) {
		this.maxArguments = maxArguments;
		this.minArguments = minArguments;		
	}
	
	public int getMaxArg() {
		return maxArguments;
	}
	
	public int getMinArg() {
		return minArguments;
	}
	
	abstract public String executeCommand
	(IShellState shellState, List<String> arguments) throws Exception;
	
	protected void checkArgumentsNum(List<String> arguments) throws Exception {
		if (arguments.size() > maxArguments) {
			throw new Exception("too many arguments provided");
		}
		if (arguments.size() < minArguments) {
			throw new Exception("too little arguments provided");
		}
	}
	
}
