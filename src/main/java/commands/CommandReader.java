package commands;
import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import constants.Exceptions;
import helperClasses.CmdArgTuple;

/**
 * Parses command line input, recognizes a string surrounded by quotes as one
 * argument
 */
public class CommandReader {

    /**
     * Parses the input into a tuple that holds command as String and arguments
     * as List of Strings
     * @param commandLine the input command line
     * @return CmdArgTuple the tuple that holds command and its arguments
     */
    public static CmdArgTuple parseCommandLine(String commandLine)
            throws Exception {
        // split up the command line by the spaces
        List<String> splited = manuallySplit(commandLine);
        if (splited.size() == 0) {
            throw new Exception(Exceptions.COMMAND_NOT_FOUND);
        }
        // get the command, and the arguments
        String command = splited.get(0);
        List<String> arguments = splited.subList(1, splited.size());
        // return the command - arguments tuple
        return new CmdArgTuple(command, arguments);
    }

    /**
     * Split a string into 'arguments' where 'arguments' words surrounded by
     * white spaces. Anything surrounded by double quotes is considered as one
     * argument
     * @param commandLine is the
     * @return the list of arguments
     * @throws Exception if STRING input is invalid (i.e STRING input has
     * quotation mark inside it"
     */
    private static List<String> manuallySplit(String commandLine)
            throws Exception {
        // initialize list of split arguments and current index
        List<String> splittedCommandLine = new ArrayList<String>();
        int currI = 0;
        // loop through command line
        while (currI < commandLine.length()) {
            char currChar = commandLine.charAt(currI);
            if (currChar == ' ') {
                // move on if current character is a white space
                currI += 1;
            } else {
                /*
                 * else, current character is the start of an argument that is needed
                 * to be split. Get the end index of the argument and split it.
                 */
                int endSplitI = (currChar == '"') ?
                        getEndStringIndex(commandLine, currI) :
                        getEndNonStrIndex(commandLine, currI);
                String splittedArgument = commandLine.substring(currI, endSplitI);
                // remove the quotes if the argument is the STRING argument
                if (currChar == '"') {
                    splittedArgument =
                            splittedArgument.substring(1, splittedArgument.length() - 1);
                }
                // add the argument to the list, move on
                splittedCommandLine.add(splittedArgument);
                currI = endSplitI;
            }
        }
        // return the split list
        return splittedCommandLine;
    }

    /**
     * Get end of a STRING input inside given string that starts at given index
     * @param str given string
     * @param startI given start index
     * @return index of the last character of the STRING input
     * @throws Exception if STRING input is invalid
     */
    private static int getEndStringIndex(String str, int startI)
            throws Exception {
        boolean foundEndI = false;
        int endI = 0;
        int currI = startI + 1;
        while (!foundEndI && currI < str.length()) {
            if (str.charAt(currI) == '"') {
                // throw error if next character is not a white space
                if (currI + 1 != str.length() && str.charAt(currI + 1) != ' ') {
                    throw new Exception(Exceptions.INVALID_STRING_MSG);
                }
                // make the current index the wanted end index of the string argument
                endI = currI + 1;
                foundEndI = true;
            }
            currI ++;
        }
        // throw error if no other quotation mark was found
        if (endI == 0) {
            throw new Exception(Exceptions.INVALID_STRING_MSG);
        }
        return endI;
    }

    /**
     * Get end of an input surrounded by spaces
     * @param str given string
     * @param startI given start index
     * @return index of the last character of the input surrounded by spaces
     */
    private static int getEndNonStrIndex(String str, int startI) {
        boolean foundEndI = false;
        int endI = startI + 1;
        while (!foundEndI && endI < str.length()) {
            if (str.charAt(endI) == ' ') {
                foundEndI = true;
            } else {
                endI += 1;
            }
        }
        return endI;
    }

    /**
     * This method checks if the given command is valid
     * @param command the input command
     * @return boolean that indicates if command is valid
     */
    public static boolean isValidCommand(String command) {
        return Constants.COMMAND_DIC.containsKey(command);
    }

}
