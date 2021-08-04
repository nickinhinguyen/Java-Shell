package test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import driver.JShellState;


/**
 * This class test the history command
 * @author Nhi Nguyen
 *
 */
public class HistoryTest {
  private commands.History history = new commands.History();
  private JShellState shellState;
  private List<String> numberOfCommand =  new ArrayList<String>();
  
  /**
   * Setup method, creates a shellstate and adds some histories to it
   */
  @Before
  public void setUp() throws Exception {
  shellState = new JShellState();
	//add commands
	shellState.addHistory("cmd1");
	shellState.addHistory("cmd2");
	shellState.addHistory("cmd3");
  }

  @Test
  public void testInvalidNumberofHistory() {
  	// with negative input, there should be an error message
    try{
    	numberOfCommand.add("-1");
    	history.executeCommand(shellState, numberOfCommand);
    } catch (Exception e) {
    	assertEquals(e.getMessage(), "invalid argument");
    }
  }
  
  @Test
  public void tesValidNumberofHistory() {
  	// if number of command is given > history data, all histories should return
    try{
    	numberOfCommand.add("100");
    	assertEquals(history.executeCommand(shellState, numberOfCommand),
				"1. cmd1\n2. cmd2\n3. cmd3\n");
    }
    catch (Exception e) {
    	System.out.println("error with out of rage number of command ");
    }
    // if no number of command is given, all histories should be return
    try{
    	numberOfCommand.add("");
    	assertEquals(history.executeCommand(shellState, numberOfCommand),
				"1. cmd1\\n2. cmd2\\n3. cmd3\\n");
    } catch (Exception e) {
    	System.out.println("error with no input");
    }
	}
}