package constants;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * This class holds all the exception messages in the program
 */
public class Exceptions {
    public static final String WRONG_PATH_INPUT_MSG = "No such file or directory";
    public static final String INVALID_STRING_MSG = "Invalid string input";
    public static final String POP_EMPTY_STACK =
            "cannot execute: use pushd to push a directory into the stack first ";
    public static final String FILE_EXISTS = "File/directory exists";
    public static final String COMMAND_NOT_FOUND = "command not found";
    public static final String  OPTIONAL_PARAM_ERROR = "invalid option";
    public static final String  INVALID_ARGUMENT = "invalid argument";
    public static final String  MOVE_INTO_SUBCHILD_ERROR =
            "Can't move or copy into sub child";
    public static final String INVALID_OPTIONAL_PARAM =
            "this command doesn't support this optional parameter";

    public static final String NOT_A_FILE_ERROR = "Not a file";
    public static final String NOT_A_DIRECTORY_ERROR = "Not a file";
    public static final String INVALID_NAME =
            "name cannot contain some of the given characters";
    public static final String MOVE_DIRECTORY_INTO_FILE_ERROR =
            "Cannot move a directory into a file";

    // Returns a list of all exceptions
    public static ArrayList<String> getErrors() {
        ArrayList<String> errorMessages = new ArrayList<String>();
        Field[] fields = Exceptions.class.getFields();
        for (Field field : fields) {
            try {
                String errorMessage =  (String) field.get(Exceptions.class);
                errorMessages.add(errorMessage);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return errorMessages;
    }
}
