package helperClasses;

import driver.IShellState;
import fileSystem.Directory;
import fileSystem.File;
import fileSystem.FileSystem;
import fileSystem.FileSystemObject;

/**
 * This is class finds files and directories related to the shell given path
 */
public class PathHandler {

    /**
     * This method is used when trying to create or access some file or directory
     * with the path. Gets the directory from which the file/directory is
     * attempted to be created/accessed from and its name
     * @param shellState holds state of the program including root directory and
     * current directory
     * @param path is the specified path that indicates which file/directory is
     * attempted to be created/accessed
     * @return a tuple that holds directory from which the file/directory is
     * attempted to be created/accessed from and its name
     * @throws Exception if the given path is invalid
     */
    public static DirectoryFileNameTuple getDirectoryAndFileName
    (IShellState shellState, String path) throws Exception {
        FileSystem fs = shellState.getFileSystem();
        return fs.getDirectoryAndFileName(shellState.getCurrentDir(), path);
    }

    /**
     * This method is used when trying to access some file system object such as
     * file or directory with the path.
     * @param shellState holds state of the program including root directory and
     * current directory
     * @param path is the specified path that indicates which file/directory is
     * attempted to be accessed
     * @return a the file system object indicated by path
     * @throws Exception if the object does not exist at given path
     */
    public static FileSystemObject getFileSystemObject
    (IShellState shellState, String path) throws Exception {
        FileSystem fs = shellState.getFileSystem();
        return fs.getFileSystemObject(shellState.getCurrentDir(), path);
    }

    /**
     * Get the file specified by path
     * @param shellState holds state of the program including root directory and
     * current directory
     * @param path is the specified path(complete or relative)
     * @return path described by path
     * @throws Exception if the path specified object at this path is not a file
     */
    public static File getFileByPath
    (IShellState shellState, String path) throws Exception {
        FileSystem fs = shellState.getFileSystem();
        return fs.getFileByPath(shellState.getCurrentDir(), path);
    }

    /**
     * Get the directory specified by path
     * @param shellState holds state of the program including root directory and
     * current directory
     * @param path is the specified path(complete or relative)
     * @return directory described by path
     * @throws Exception if the path specified object at this path is not a
     * directory
     */
    public static Directory getDirectoryByPath
    (IShellState shellState, String path) throws Exception {
        FileSystem fs = shellState.getFileSystem();
        return fs.getDirectoryByPath(shellState.getCurrentDir(), path);
    }
}
