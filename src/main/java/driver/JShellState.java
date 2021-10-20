package driver;

import file_system.Directory;
import file_system.VirtualFileSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class holds all the states of the Shell Program
 */
public class JShellState implements IShellState {
    private VirtualFileSystem fs;
    private Directory currentDir;
    private boolean running;
    private  Stack<Directory> directoryStack;
    private  List<String> commandsHistory;
    private  List<String> correctCommandsHistory;

    /**
     * Constructs an object that holds JShell's important states including
     * the root directory, current directory, directory stack and a boolean that
     * indicates JShell is running
     */
    public JShellState() {
        this.running = true;
        this.fs = VirtualFileSystem.createFileSystemInstance();
        this.currentDir = fs.getRoot();
        this.commandsHistory = new ArrayList<>();
        this.correctCommandsHistory = new ArrayList<>();
        this.directoryStack = new Stack<>();
    }

    public void loadExistedJShellState(IShellState shellState){
        this.fs = shellState.getFileSystem();
        this.currentDir = shellState.getCurrentDir();
        this.directoryStack = shellState.getDirectoryStack();
        this.commandsHistory = shellState.getHistory();
        this.correctCommandsHistory = shellState.getCorrectHistory();
    }

    public boolean isRunning() {
        return running;
    }

    public void stopRunning() {
        running = false;
    }

    public VirtualFileSystem getFileSystem() {
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

    public void removeHistory(int index) {
        commandsHistory.remove(index);
    }

    public List<String> getHistory() {
        return commandsHistory;
    }

    public void addCorrectHistory(String inputLine) {
        correctCommandsHistory.add(inputLine);
    }

    public List<String> getCorrectHistory() {
        return correctCommandsHistory;
    }

    public Stack<Directory> getDirectoryStack(){
        return directoryStack;
    }

}
