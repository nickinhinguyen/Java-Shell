package test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import commands.CommandReader;
import commands.Man;
import driver.JShellState;

public class ManTest {
  JShellState shellState;
  List<String> arguments;

  @Before
  public void setUp() throws Exception {
    shellState = new JShellState();
    arguments = CommandReader.parseCommandLine("man man").getArguments();
    
  }

  @Test
  public void testExecuteCommand() throws Exception {
    Man m = new Man();
    String output = m.executeCommand(shellState, arguments);
    assertEquals("Print documentation for command" ,output);
   
  }


}
