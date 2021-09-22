package driver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import file_system.Directory;
import file_system.FileSystem;

public interface IShellState extends Serializable {
    boolean isRunning();

    void stopRunning();

    FileSystem getFileSystem();

    Directory getRootDir();

    Directory getCurrentDir();

    void setCurrentDir(Directory newCurrentDir);

    void addHistory(String inputLine);

    void removeHistory(int index);

    List<String> getHistory();

    void addCorrectHistory(String inputLine);

    List<String> getCorrectHistory();

    Stack<Directory> getDirectoryStack();

    void loadExistedJShellState(IShellState shellState);

}
