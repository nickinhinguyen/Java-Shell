package command_decorators;

import commands.Command;

/**
 * This is an abstract class that is a base for all decorators of the command
 */
public abstract class CommandDecorator extends Command {
    // this is the command we want to decorate
    final Command command;

    public CommandDecorator(Command command) {
        // create a command decorator with provided command
        super(command.getMaxArg(), command.getMinArg());
        this.command = command;
    }

}
