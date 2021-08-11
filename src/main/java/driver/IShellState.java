package driver;

import java.util.ArrayList;
import java.util.Stack;

import fileSystem.Directory;
import fileSystem.FileSystem;

public interface IShellState {
    public boolean isRunning();

    public void stopRunning();

    public FileSystem getFileSystem();

    public Directory getRootDir();

    public Directory getCurrentDir();

    public void setCurrentDir(Directory newCurrentDir);

    public void addHistory(String inputLine);

    public void removeHistory(int index);

    public ArrayList<String> getHistory();

    public void addCorrectHistory(String inputLine);

    public ArrayList<String> getCorrectHistory();

    public Stack<Directory> getDirectoryStack();

}
