package helper_classes;

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
            for (char c : charArray) {
                if (c == currentChar) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * This method multiplies concatenates given string given number of times
     * @param str any string
     * @param numRepeat number of repetition
     * @return repeated string
     */
    public static String repeat(String str, int numRepeat) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i <  numRepeat; i++) {
            output.append(str);
            i++;
        }
        return output.toString();
    }
}
