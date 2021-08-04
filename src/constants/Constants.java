package constants;

import java.util.Hashtable;

import commands.Cat;
import commands.Cd;
import commands.Command;
import commands.Cp;
import commands.Echo;
import commands.Exit;
import commands.Find;
import commands.Get;
import commands.History;
import commands.Load;
import commands.Ls;
import commands.Man;
import commands.Mkdir;
import commands.Mv;
import commands.Popd;
import commands.Pushd;
import commands.Pwd;
import commands.Save;
import commands.Tree;

/**
 * This class holds the constants variables in the program
 * @author Minh Hoang Nguyen
 *
 */
public class Constants {
	// path related constants
	public static final String SYSTEM_FILE_PATH_SEPERATOR = "/";
	public static final String SYSTEM_FILE_ROOT_NAME = "/";
	public static final String PARENT_OF_SYSTEM_FILE_INDICATOR = "..";
	public static final String CURRENT_SYSTEM_FILE_INDICATOR = ".";
	// invalid file naming characters
	public static final char[] INVALID_NAMING_CHAR = {'/','.', '!', '@', '#',
	    '$', '%', '^', '&', '*', '(', ')', '{', '}', '~', '|', '<', '>', '?'};
	public static final String REDIRECTION_OVERWRITE = ">";
	public static final String REDIRECTION_APPEND = ">>";
	// command dictionary
	public static final Hashtable<String, Command> COMMAND_DIC = 
			new Hashtable<String, Command>();
	
	static {
		COMMAND_DIC.put("get", new Get());
		COMMAND_DIC.put("exit", new Exit());
		COMMAND_DIC.put("mkdir", new Mkdir());
		COMMAND_DIC.put("cd", new Cd());
		COMMAND_DIC.put("ls", new Ls());
		COMMAND_DIC.put("pwd", new Pwd());
		COMMAND_DIC.put("pushd", new Pushd());
		COMMAND_DIC.put("popd", new Popd());
		COMMAND_DIC.put("history", new History());
		COMMAND_DIC.put("cat", new Cat());
		COMMAND_DIC.put("echo", new Echo());
		COMMAND_DIC.put("man", new Man());
		COMMAND_DIC.put("mv", new Mv());
		COMMAND_DIC.put("cp", new Cp());
		COMMAND_DIC.put("tree", new Tree());
		COMMAND_DIC.put("save", new Save());
    COMMAND_DIC.put("load", new Load());
    COMMAND_DIC.put("find", new Find());
        
	}
	
	//optional parameters dictionary maps optional parameter to its decorator
	public static final Hashtable<String, String> OPTIONAL_PARAM_DIC = 
			new Hashtable<String, String>();
	
	static {
		OPTIONAL_PARAM_DIC.put("-R", "RecursiveDecorator");
	}
	
	public static final Hashtable<String, String> COMMAND_DOCUMENTATION = 
	    new Hashtable<String, String>();
	
	static {
	  COMMAND_DOCUMENTATION.put("exit","Quit the program");
	  COMMAND_DOCUMENTATION.put("mkdir", "Create directories, each of which may"
	      + " be relative to the current directory \n or may be a full path.");
	  COMMAND_DOCUMENTATION.put("cd", "Change directory to DIR, which may be"
	      + " relative to the current directory or \n may be a full path. As"
	      + " with Unix, .. means a parent directory and a .means the current"
	      + " directory.");
	  COMMAND_DOCUMENTATION.put("ls", "If no paths are given, print the"
	      + " contents (file or directory) of the current \n directory, with a"
	      + " new line following each of the content (file or directory). \n"
	      + " Otherwise, for each path p, the order listed: \n"
	      + " •If p specifies a file, print p \n"
	      + " •If p specifies a directory, print p, a colon, then"
	      + " the contents of that directory, then an extra new line. \n"
	      + " •If p does not exist, print a suitable message. ");
	  COMMAND_DOCUMENTATION.put("pwd", "Print the current working directory"
	      + " (including the whole path). ");
	  COMMAND_DOCUMENTATION.put("cp"," Move item OLDPATH to NEWPATH, but don’t"
	      + " remove OLDPATH. \n If OLDPATHis a directory, recursively copy the"
	      + " contents.");
	  COMMAND_DOCUMENTATION.put("mv"," Move item OLDPATH to NEWPATH. Both "
	      + "OLDPATH and NEWPATH \n may be relative to the current directory"
	      + "or may be full paths. If NEWPATH is a directory, move the item"
	      + "into the directory.");
	  COMMAND_DOCUMENTATION.put("pushd","Saves the current working directory by"
	      + " pushing onto directory stack and \n then changes the new current"
	      + " working directory to DIR. The push must be\nconsistent as per"
	      + " the LIFO behavior of a stack. The pushd command \n saves the"
	      + " old current working directory in directory stack so that it can"
	      + " be \n returned to at any time (via the popd command). The size of"
	      + " the directory \n stack is dynamic and dependent on the pushd and"
	      + " the popd commands. ");
	  COMMAND_DOCUMENTATION.put("popd", "Remove the top entry from the"
	      + " directory stack, and cd into it. The removal \n must be"
	      + " consistent as per the LIFO behavior of  a stack. The popd \n"
	      + "command removes the top most directory from the directory stack"
	      + " and \n makes it the current working directory.  If there is no"
	      + " directory onto the \n stack, then give appropriate error message."
	      + " ");
	  COMMAND_DOCUMENTATION.put("history","This command will print out recent"
	      + " commands, one command per line. \nHistory will also include"
	      + "any syntactical errors typed by the user ");
	  COMMAND_DOCUMENTATION.put("cat", "Display the contents of FILE1 and other"
	      + " files (i.e. File2 ....) concatenated in the shell.");
	  COMMAND_DOCUMENTATION.put("echo","•echo STRING: prints the provided " +
	  		"STRING argument.\nSTRING argument must be surrounded by double quotes"
	  		+ "unless it consists of only one word");
	  COMMAND_DOCUMENTATION.put("man", "Print documentation for command");
	  COMMAND_DOCUMENTATION.put("get", "Retrieve the file at given URL and add" +
	  " it to the current working directory");
	  COMMAND_DOCUMENTATION.put("save", "Saves the session of the JShell"
	      + " onto a file in the given path");
	  COMMAND_DOCUMENTATION.put("load", "Loads the session of the JShell"
          + " from the file of the given path. Load must be called at the start"
          + "of the new session");
	  COMMAND_DOCUMENTATION.put("find", "The syntax of thefindcommand is as"
	      + "follows:find path ... -type [f|d] -name expression. \n So here are"
	      + " some examples of how thefindcommand may be used:"
	      + "\n •find /users/Desktop -type f -name \"xyz\". This command will"
	      + " search the directoryDesktopand find all files (indicated by type"
	      + " f) that have the name exactlyxyz. \n"
	      + " •find /users/Desktop -type d -name \"abc\". This command will"
	      + " search the directoryDesktopand find all directories (indicated by"
	      + " type d) that have the name exactlyabc. \n"
	      + " •find /users/Desktop.  This command will result in an error"
	      + " because it has missing arguments. \n"
	      + " •find /users/Desktop /users/Desktop1 -type d -name \"abc\". "
	      + " This  command  will  searchthe directoryDesktopandDesktop1 and"
	      + " find all directories (indicated bytype d) that have the name"
	      + " exactly abc. \n "
	      + "•find /users/Desktop /users/Desktop1 -type f -name  \"abc\". "
	      + " This  command  will  searchthe directory Desktop and Desktop1 and"
	      + " find all directories (indicated bytype f) that have the name"
	      + " exactly abc.\n"
	      + " •If at any point one of the path in this command is invalid,"
	      + " shell will print out an error for that path,however, you must"
	      + " continue searching for any other files or directories that may"
	      + " exist in other valid paths. \n"
	      + " •Thefindcommand must accept the path as relative or in absolute"
	      + " path.");
	  COMMAND_DOCUMENTATION.put("tree", "Tree will display the entire"
	      + " filesystem as a tree starting from the root directory");
	}
}
