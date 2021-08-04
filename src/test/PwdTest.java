package test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import commands.CommandReader;
import commands.Pwd;
import driver.JShellState;

public class PwdTest {
  JShellState shellState;
  List<String> arguments;

  @Before
  public void setUp() throws Exception {
    shellState = new JShellState();
    arguments = CommandReader.parseCommandLine("pwd").getArguments();
    
  }

  @Test
  public void test() throws Exception {
    Pwd p = new Pwd();
    String output = p.executeCommand(shellState, arguments);
    assertEquals("/" ,output);
  }

}
