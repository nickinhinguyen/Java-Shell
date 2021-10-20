package commands;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import command_decorators.RedirectionDecorator;
import constants.Constants;
import constants.Exceptions;
import driver.IShellState;
import helper_classes.CmdArgTuple;
import repository.DataRepositoryInterface;

/**
 * Decorates and executes command appropriately based on arguments provided
 */
public class CommandController {

    public CommandController(DataRepositoryInterface repository) {

        Constants.COMMAND_DIC.put("get", new Get());
        Constants.COMMAND_DIC.put("exit", new Exit());
        Constants.COMMAND_DIC.put("mkdir", new Mkdir());
        Constants.COMMAND_DIC.put("cd", new Cd());
        Constants.COMMAND_DIC.put("ls", new Ls());
        Constants.COMMAND_DIC.put("pwd", new Pwd());
        Constants.COMMAND_DIC.put("pushd", new Pushd());
        Constants.COMMAND_DIC.put("popd", new Popd());
        Constants.COMMAND_DIC.put("history", new History());
        Constants.COMMAND_DIC.put("cat", new Cat());
        Constants.COMMAND_DIC.put("echo", new Echo());
        Constants.COMMAND_DIC.put("man", new Man());
        Constants.COMMAND_DIC.put("mv", new Mv());
        Constants.COMMAND_DIC.put("cp", new Cp());
        Constants.COMMAND_DIC.put("tree", new Tree());
        Constants.COMMAND_DIC.put("save", new Save(repository));
        Constants.COMMAND_DIC.put("load", new Load(repository));
        Constants.COMMAND_DIC.put("find", new Find());
    }

    /**
     * Executes the provided command line accordingly
     * @param shellState is the state of the program
     * @param commandLine is the given command line
     * @return string that is given back as command is executed
     * @throws Exception if any of the provided arguments is invalid
     */
    public String executeCommand(IShellState shellState, String commandLine)
            throws Exception {
        shellState.addHistory(commandLine);
        if (commandLine.equals("")) {
            return "";
        }
        // use command reader to separate command with arguments
        CmdArgTuple cmdArgTuple = CommandReader.parseCommandLine(commandLine);
        String command = cmdArgTuple.getCommand();
        List<String> arguments = cmdArgTuple.getArguments();
        if (!CommandReader.isValidCommand(command)) {
            throw new Exception(Exceptions.COMMAND_NOT_FOUND);
        }
        Command apprCmdObject = Constants.COMMAND_DIC.get(command);
        // remove optional parameters, make a new list
        List<String> optParams =  new ArrayList<String>();
        for (String arg : arguments) {
            if (Constants.OPTIONAL_PARAM_DIC.containsKey(arg)) {
                optParams.add(arg);
            }
        }
        for (String optParam : optParams) {
            arguments.remove(optParam);
        }
        // decorate command appropriately
        apprCmdObject = decorateCommand(apprCmdObject, arguments, optParams);
        String result = apprCmdObject.executeCommand(shellState, arguments);
        // if command doesn't result in an error add it to STD OUPOUT history
        shellState.addCorrectHistory(commandLine);
        return result;
    }

    /**
     * Decorates the command with optional parameter(if present) and
     * redirection (if present)
     * @param command is the command to decorate
     * @param arguments are base arguments provided
     * @param optParam is a list of optional parameters provided
     * @return command that is decorated
     * @throws Exception if there is more than one optional parameter
     */
    private Command decorateCommand
    (Command command, List<String> arguments, List<String> optParam)
            throws Exception {
        Command decoratedCommand = command;
        if (optParam.size() > 1) {
            throw new Exception("commands can only take 1 optional parameter");
        }
        // decorate with optional parameter if necessary
        if (optParam.size() == 1) {
            String aprropDecoratorName =
                    Constants.OPTIONAL_PARAM_DIC.get(optParam.get(0));
            Class<?> appropDecoratorClass =
                    Class.forName("commandDecorators." + aprropDecoratorName);
            Constructor<?> decConstructor =
                    appropDecoratorClass.getConstructor(Command.class);
            decoratedCommand = (Command) decConstructor.newInstance(command);
        }
        // decorate with redirection if necessary
        int argLen = arguments.size();
        if (argLen >= 2) {
            if (arguments.get(argLen - 2).equals(Constants.REDIRECTION_APPEND) ||
                    arguments.get(argLen - 2).equals(Constants.REDIRECTION_OVERWRITE)) {
                decoratedCommand = new RedirectionDecorator(decoratedCommand);
            }
        }
        return decoratedCommand;
    }
}
