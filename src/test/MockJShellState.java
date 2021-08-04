package test;


import fileSystem.Directory;
import fileSystem.FileSystem;

import java.util.ArrayList;
import java.util.Stack;

import driver.IShellState;

/**
 * A mock shell state
 * @author Nhi Nguyen 
 */
public class MockJShellState implements IShellState{
	// this is the file system
		private FileSystem fs;
		// this is the current directory of JShell program
		private Directory currentDir;
		// this indicates if the program is running or not
		private boolean running;
		// a stack used for push and pop command
		private Stack<Directory> directoryStack;
		private ArrayList<String> commandsHistory;
		private ArrayList<String> correctCommandsHistory;
		
		/**
		 * Constructs an object that holds JShell's important states including
		 * the root directory, current directory, directory stack and a boolean that
		 * indicates JShell is running
		 * @param rootDir determines root and current directory of the program
		 */
		public MockJShellState() throws Exception {
			this.running = true;
			this.fs = FileSystem.createFileSystemInstance();
			this.currentDir = fs.getRoot();
			this.commandsHistory = new ArrayList<String>();
			this.correctCommandsHistory = new ArrayList<>();
			this.directoryStack = new Stack<Directory>();
			
		}
    
	public boolean isRunning() {
		return running;
	}
	
	public void stopRunning() {
		running = false;
	}
	
	public FileSystem getFileSystem() {
		return fs;
	}
	
	public Directory getRootDir() {
		return fs.getRoot();
	}
	
	public Directory getCurrentDir() {
		return currentDir;
	}
	
	public void setCurrentDir(Directory newCurrentDir) {
		currentDir = newCurrentDir;
	}
	
	public void addHistory(String inputLine) {
		commandsHistory.add(inputLine);
	}
	
	public ArrayList<String> getHistory() {
		return commandsHistory;
	}
	
	public void addCorrectHistory(String inputLine) {
	  correctCommandsHistory.add(inputLine);
	}
	
	public ArrayList<String> getCorrectHistory() {
	  return correctCommandsHistory;
	}
	
	public Stack<Directory> getDirectoryStack(){
		return directoryStack;
	}

	@Override
	public void removeHistory(int index) {
		
	}
	
	}
