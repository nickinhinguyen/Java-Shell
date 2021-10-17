package commands;

import java.util.List;

import driver.IShellState;
import file_system.Directory;

import java.net.*;
import java.io.*;

/**
 * This class create a new file with content from given url
 */
public class Get extends Command{
    public Get() {
        super(1, 1);
    }

    /**
     * Retrieve file at given URL and add it to the current working directory
     * @param shellState is the current state of JShell program
     * @param arguments is the list of paths that indicate where new directories
     * should be created
     * @throws Exception if any of the paths is invalid
     */
    public String executeCommand(IShellState shellState, List<String> arguments)
            throws Exception {
        checkArgumentsNum(arguments);
        Directory currentDir = shellState.getCurrentDir();

        //read through each line of the file and append to content
        StringBuilder content = new StringBuilder();
        URL url = new URL(arguments.get(0));
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            content.append(inputLine).append("\n");
        in.close();

        //get the url to find the file name
        String urlLink = arguments.get(0);

        String fileName = extractName(urlLink);
        /*
         * create a file in current directory with founded file name
         * and content
         */
        new file_system.File(fileName, currentDir, content.toString());

        return "";
    }

    /**
     * Get the file name from a url
     * @param urlLink is a url link
     */
    public String extractName(String urlLink) {
        int start=0;
        int end=urlLink.length();

        boolean foundName = false;
        // loop from the end until find the first "/"
        int i = urlLink.length() - 1;
        while ( i >= 0 && !foundName) {
            char c = urlLink.charAt(i);
            // exclude the file type
            if (c == '.') {
                end = i;
            } else if (c == '/') {
                start = i+1;
                foundName = true;
            }
            i--;
        }
        return urlLink.substring(start, end);
    }
}
