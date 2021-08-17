package driver;

import java.util.ArrayList;
import java.util.Stack;

import file_system.Directory;
import file_system.FileSystem;

public interface IShellState {
    boolean isRunning();

    void stopRunning();

    FileSystem getFileSystem();

    Directory getRootDir();

    Directory getCurrentDir();

    void setCurrentDir(Directory newCurrentDir);

    void addHistory(String inputLine);

    void removeHistory(int index);

    ArrayList<String> getHistory();

    void addCorrectHistory(String inputLine);

    ArrayList<String> getCorrectHistory();

    Stack<Directory> getDirectoryStack();

}
