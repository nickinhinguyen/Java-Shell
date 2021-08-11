package helperClasses;

/**
 * This class contains methods that help make operations with strings
 */
public class StringHelper {

    /**
     * Method checks if a string contains any of the characters in the array
     * @param str is the string
     * @param charArray is an array of characters
     * @return boolean
     */
    public static boolean containsAny(String str, char[] charArray) {
        boolean result = false;
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            for (int j = 0; j < charArray.length; j++) {
                if (charArray[j] == currentChar) {
                    return true;
                }
            }
        }
        return result;
    }

    /**
     * This method multiplies concatenates given string given number of times
     * @param str
     * @param numRepeate
     * @return
     */
    public static String repeate(String str, int numRepeate) {
        String output = "";
        for (int i = 0; i <  numRepeate; i++) {
            output += str;
            i++;
        }
        return output;
    }
}
