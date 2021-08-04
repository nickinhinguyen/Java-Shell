package commands;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import commandDecorators.Redirection;
import constants.Constants;
import constants.Exceptions;
import driver.IShellState;
import helperClasses.CmdArgTuple;

/**
 * Decorates and executes command appropriately based on arguments provided
 * @author Minh Hoang Nguyen
 *
 */
public class CommandExecutor {
    
    /**
     * Executes the provided command line accordingly
     * @param shellState is the state of the program
     * @param commandLine is the given command line
     * @return string that is given back as command is executed
     * @throws Exception if any of the provided arguments is invalid
     */
    public String executeCommand(IShellState shellState, String commandLine) 
    throws Exception {
      // add the input line to the history list
      shellState.addHistory(commandLine);
      if (commandLine.equals("")) {
        return "";
      }
        // use command reader to separate command with arguments
      CmdArgTuple cmdArgTuple = CommandReader.parseCommandLine(commandLine);
      String command = cmdArgTuple.getCommand();
      List<String> arguments = cmdArgTuple.getArguments();
      // throw exception if invalid command is provided
      if (!CommandReader.isValidCommand(command)) {
          throw new Exception(Exceptions.COMMAND_NOT_FOUND);
      }
      // get the appropriate command object to execute the command
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
        // throw error if there is more than one optional parameter
        if (optParam.size() > 1) {
            throw new Exception("commands can only take 1 optional parameter");
        }
        // decorate with optional parameter if necessary
        if (optParam.size() == 1) {
            // get appropriate decorator
            String aprropDecoratorName =
                    Constants.OPTIONAL_PARAM_DIC.get(optParam.get(0));
            Class<?> appropDecoratorClass =
                    Class.forName("commandDecorators." + aprropDecoratorName);
            Constructor<?> decConstructor =
                    appropDecoratorClass.getConstructor(Command.class);
            // decorate command
            decoratedCommand = (Command) decConstructor.newInstance(command);
        }
        // decorate with redirection if necessary
      int argLen = arguments.size();
      if (argLen >= 2) {
        if (arguments.get(argLen - 2).equals(Constants.REDIRECTION_APPEND) ||
                arguments.get(argLen - 2).equals(Constants.REDIRECTION_OVERWRITE)) {
            decoratedCommand = new Redirection(decoratedCommand);
        }
      }
      // return decorated command
        return decoratedCommand;
    }
}
