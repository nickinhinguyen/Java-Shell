package commands;

import java.util.List;

import driver.IShellState;
import file_system.Directory;
import file_system.FileSystemObject;
import helper_classes.PathHandler;

/**
 * This class finds the content of some directory (recursively if specified)
 */
public class Ls extends Command implements RecursiveInterface {

    public Ls() {
        super(100, 0);
    }

    /**
     * Returns the content of the current Directory if path not given
     * If path p specifies a file, print p
     * if path p specifies a directory, print the content of the directory
     * @param shellState is the current state of JShell program
     * @param arguments is the paths that indicates what should be printed
     * @throws Exception if path is invalid
     */
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {
        checkArgumentsNum(arguments);
        String output = "";
        // if no path is given
        if (arguments.size() == 0) {
            // get content of the current directory
            output += shellState.getCurrentDir().getContent();
        }
        // go through all the paths and print content accordingly
        else {
            for (String path : arguments) {
                output += path + getContentOfObject(shellState, path) + "\n";
            }
        }
        return output.endsWith("\n")
                ? output.substring(0, output.length() - 1) : output;
    }

    /**
     * Given the path of a FileSystem object, if path specifies a file,
     * return the path, else if path p specifies a directory, return the content
     * of the directory
     * @param shellState that holds root directory of file system
     * @param path is the given path
     * @return string as specified in the description
     * @throws Exception if path is not valid
     */
    public String getContentOfObject
    (IShellState shellState, String path) throws Exception {
        String output = "";
        try {
            FileSystemObject fsObject = PathHandler.getFileSystemObject(shellState, path);
            // if object is directory print out the path with the content
            if (fsObject instanceof Directory) {
                String objContent = fsObject.getContent();
                output += objContent.equals("")
                        ? ": \n" :  ": \n"  + fsObject.getContent() + "\n";
            }
        }
        catch (Exception e) {
            output += ": " + e.getMessage() + "\n";
        }
        return output;
    }

    /**
     * Get content (recursively) of file or directory at specified path
     * @param state is the state of the program
     * @param path is the given path
     * @return content
     */
    public String getRecursiveContent (IShellState state, String path){
        String output = "";
        try {
            FileSystemObject fsObject = PathHandler.getFileSystemObject(state, path);
            // if object is directory print out the path with the content
            if (fsObject instanceof Directory) {
                output += recrusiveListing((Directory) fsObject, path);
            } else {
                output += path + "\n";
            }
        }
        catch (Exception e) {
            output += path + ": " + e.getMessage() + "\n\n";
        }
        return output;
    }

    /**
     * Lists content of current directory, and subdirectories
     * @param dir given directory
     * @param parentPath path of parent directory
     * @return content of current directory, and subdirectories
     */
    public String recrusiveListing(Directory dir, String path) {
        // get current directories content
        String content = dir.getContent();
        String output = path.equals("") ? content : path + ": \n" + content;
        output += content.equals("") ? "\n" : "\n\n";
        // get content of subdirectories recursively
        for (FileSystemObject child: dir) {
            if (child instanceof Directory) {
                String childPath = path + "/" + child.getName();
                output += recrusiveListing((Directory) child, childPath);
            }
        }
        return output;
    }

    /**
     * Get content (recursively) of file or directory at specified paths
     * @param shellState is the state of the program
     * @param arguments is the list of given path
     * @return needed content
     */
    @Override
    public String executeRecursively
    (IShellState shellState, List<String> arguments) throws Exception {
        String output = "";
        // if no arguments are provided
        if (arguments.size() == 0) {
            output += recrusiveListing(shellState.getCurrentDir(), "");
        } else {
            // for each path, recursively return content of that path
            for (String path: arguments) {
                output += getRecursiveContent(shellState, path);
            }
        }
        // return output (remove last line break if present
        return output.endsWith("\n")
                ? output.substring(0, output.length() - 1) : output;
    }
}
