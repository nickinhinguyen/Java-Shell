package file_system;

import java.util.Arrays;
import java.util.List;

import constants.Constants;
import constants.Exceptions;
import helper_classes.DirectoryFileNameTuple;
import helper_classes.StringHelper;

/**
 * This class is a virtual file system that stores directories and files.
 */
public class FileSystem {
    // this is a single reference to a File System
    private static FileSystem singleReference = null;
    // this is the root directory of file system
    private Directory root;

    /**
     * This is a private constructor that creates a new file system
     */
    private FileSystem() {
        root = new Directory();
    }

    /**
     * If file system hasn't been created before, create a new file system; else
     * return the reference to the file system
     * @return the reference to a single file system
     */
    public static FileSystem createFileSystemInstance() {
        if (singleReference == null) {
            singleReference = new FileSystem();
        }
        return singleReference;
    }

    /**
     * Return root directory of the file system
     */
    public Directory getRoot() {
        return root;
    }

    public FileSystemObject getFileSystem(String path) {
        return null;
    }

    /**
     * Get the directory from which the path starts (root directory if path is
     * full and current directory if path is relative)
     * @param shellState is current State of the program
     * @param path is the given path
     * @return directory from which the path starts
     */
    private Directory getStartingDirectoryToGoFrom
    (Directory curDir, String path) {
        return (path.startsWith(Constants.SYSTEM_FILE_ROOT_NAME) ?
                root : curDir);
    }

    /**
     * This method parses given path into a list of string names
     * (never including root)
     * @param path is a given string path
     * @return list of SystemFile names
     */
    private List<String> parsePath(String path) {
        // split the path by system file separator (by default: /)
        List<String> fileSystemObjNameList =
                Arrays.asList(path.split(Constants.SYSTEM_FILE_PATH_SEPERATOR));
        return fileSystemObjNameList;
    }

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
    public DirectoryFileNameTuple getDirectoryAndFileName
    (Directory curDir, String path) throws Exception {
        List<String> pathAsList = parsePath(path);
        List<String> pathToDirToBeAddedFrom =
                pathAsList.subList(0, pathAsList.size() - 1);
        String nameOfFileToBeCreated = pathAsList.get(pathAsList.size() - 1);
        Directory startingDir = getStartingDirectoryToGoFrom(curDir, path);
        Directory dirToBeAddedFrom =
                Directory.findDirectory(startingDir, pathToDirToBeAddedFrom);
        return new DirectoryFileNameTuple(dirToBeAddedFrom, nameOfFileToBeCreated);
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
    public FileSystemObject getFileSystemObject
    (Directory curDir, String path) throws Exception {
        if (path.equals(Constants.SYSTEM_FILE_ROOT_NAME)) {
            return root;
        }
        DirectoryFileNameTuple dirAndFileName =
                getDirectoryAndFileName(curDir, path);
        return Directory.findChild
                (dirAndFileName.getDirectory(), dirAndFileName.getFileName());
    }

    /**
     * Get the file specified by path
     * @param shellState holds state of the program including root directory and
     * current directory
     * @param path is the specified path(complete or relative)
     * @return path described by path
     * @throws Exception if the path specified object at this path is not a file
     */
    public File getFileByPath
    (Directory curDir, String path) throws Exception {
        FileSystemObject fsObject = getFileSystemObject(curDir, path);
        if (!(fsObject instanceof File)) {
            throw new Exception(Exceptions.NOT_A_FILE_ERROR);
        }
        return (File) fsObject;
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
    public Directory getDirectoryByPath
    (Directory curDir, String path) throws Exception {
        FileSystemObject fsObject = getFileSystemObject(curDir, path);
        if (!(fsObject instanceof Directory)) {
            throw new Exception(Exceptions.NOT_A_DIRECTORY_ERROR);
        }
        return (Directory) fsObject;
    }

    /**
     * Return tree representation of current file system
     */
    public String getTreeRepresentation() {
        return getTreeRepresentationHelper(root, 0);
    }

    /**
     * Make tree representation of file system rooted at given root; root is
     * indented by given number of spaces
     * @param root is given the root directory
     * @param numIndent is the number of spaces to indent root
     * @return tree representation
     */
    private String getTreeRepresentationHelper(Directory root, int numIndent) {
        // initialize output with root's name
        String output = StringHelper.repeate(" ", numIndent) +
                root.getName() + "\n";
        for (FileSystemObject child: root) {
            if (child instanceof File) {
                output += StringHelper.repeate(" ", numIndent + 1) +
                        child.getName() + "\n";
            } else {
                output += getTreeRepresentationHelper((Directory) child, numIndent + 2);
            }
        }
        return output;
    }

    /**
     * Move an object from one path to another (path can be absolute or relative
     * to given directory)
     * @param curDir given directory
     * @param oldPath is the path of object to be moved
     * @param newPath is the path of object to be moved into
     * @throws Exception if either of the paths are invalid or if trying to move
     * a directory inside one of its sub-directories
     */
    public void moveFsObject
    (Directory curDir, String oldPath, String newPath) throws Exception {
        FileSystemObject oldFsObject = getFileSystemObject(curDir, oldPath);
        DirectoryFileNameTuple newPathDirFileName =
                getDirectoryAndFileName(curDir, newPath);
        Directory dirWithNewObject = newPathDirFileName.getDirectory();
        String newFileName = newPathDirFileName.getFileName();
        // check if object at newPath is a subChild of object at oldPath
        if (dirWithNewObject.hasAncestor(oldFsObject)) {
            throw new Exception(Exceptions.MOVE_INTO_SUBCHILD_ERROR);
        }
        // if there is already an object at newPath, overwrite it
        try {
            FileSystemObject newFsObject =
                    Directory.findChild(dirWithNewObject, newFileName);
            // if object at newPath and oldPath are the same do nothing
            if (!newFsObject.equals(oldFsObject)) {
                // else move accordingly
                newFsObject.moveFsObjectIn(oldFsObject);
            }
        }
        // else move oldFsObject into the directory with new object with new name
        catch (Exception e) {
            oldFsObject.setName(newFileName);
            oldFsObject.setParent(dirWithNewObject);
        }
    }

    /**
     * Copy an object from one path to another (path can be absolute or relative
     * to given directory)
     * @param curDir given directory
     * @param oldPath is the path of object to be moved
     * @param newPath is the path of object to be moved into
     * @throws Exception if either of the paths are invalid or if trying to move
     * a directory inside one of its sub-directories
     */
    public void copyFsObject
    (Directory curDir, String oldPath, String newPath) throws Exception {
        FileSystemObject oldFsObject = getFileSystemObject(curDir, oldPath);
        FileSystemObject parentOfOld = oldFsObject.getParent();
        FileSystemObject clonedOldFsObject = oldFsObject.cloneObject();
        moveFsObject(curDir, oldPath, newPath);
        clonedOldFsObject.setParent((Directory) parentOfOld);
    }

}
