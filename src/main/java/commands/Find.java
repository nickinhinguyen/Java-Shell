package commands;

import java.util.List;

import constants.Exceptions;
import driver.IShellState;
import file_system.Directory;
import file_system.File;
import file_system.FileSystemObject;
import helper_classes.PathHandler;


/**
 * This class searches for the file or directory in a given path
 *
 */

public class Find extends Command{

    public Find() {
        super(100, 5);
    }

    /**
     * Searches for the file or directory in a given path
     * @param shellState is the current state of JShell program
     * @param arguments includes all of: [file_path], --type [f/d], --name [file_name]
     * @throws Exception if path, type or name is invalid
     *
     */
    public String executeCommand(IShellState shellState,
                                 List<String> arguments) throws Exception {

        checkArgumentsNum(arguments);
        String output = "";
        String name = arguments.get(arguments.size()-1);
        String type = arguments.get(arguments.size()-3);

        if ((!arguments.get(arguments.size()-2).equals("-name"))||
                !arguments.get(arguments.size()-4).equals("-type")){
            throw new Exception(Exceptions.INVALID_ARGUMENT);
        }
        if (!type.equals("d") && !type.equals("f")){
            throw new Exception("Invalid type");
        }


        try {
            // get arguments excluding the type and expression
            List<String> baseArgs = arguments.subList(0, arguments.
                    size() - 4);
            // Search the given paths for the file/directory
            for (String paths:baseArgs) {
                FileSystemObject fsObject = PathHandler
                        .getFileSystemObject(shellState, paths);
                // if path is a file, check if it matches type
                // and name
                if (fsObject.isEqualByClassAndName(File.class,name)){
                    output = name + "found in " + paths;
                }
                // if path is a directory, check its contents to find
                // the file or the directory
                if (fsObject instanceof Directory) {
                    // if it has a child that is of type directory
                    if (((Directory) fsObject).
                            hasChildWithTypeAndName(Directory.class,name)){
                        output = "directory found in " + paths;}
                    // if it has a child that is of type file
                    else if (((Directory) fsObject).
                            hasChildWithTypeAndName(File.class, name)){
                        output = name + "found in " + paths;
                    }
                    else {
                        output = "File or Directory not found";
                    }
                }}}
        catch (Exception e) {
            output = "File/Directory not found";
        }
        return output;
    }}

