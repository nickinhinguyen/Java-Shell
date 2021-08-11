package helperClasses;

import java.util.List;

/**
 * This is a class that mimics a tuple in python
 */
public class CmdArgTuple {
    // this is first object
    private String command;
    // this is second object
    private List<String> arguments;

    /**
     * Constructor for the tuple
     */
    public CmdArgTuple(String command, List<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Return first object
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Return second object
     */
    public List<String> getArguments() {
        return this.arguments;
    }

}
