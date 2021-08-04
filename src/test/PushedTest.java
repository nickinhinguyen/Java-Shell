package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import commands.Pushd;
import driver.JShellState;
import fileSystem.Directory;

public class PushedTest {
  JShellState shellState;
  List<String> arguments = new ArrayList<String>();
  List<String> argumentsForMkdir;
  

  @Before
  public void setUp() throws Exception {
    arguments.add("a");
    shellState = new JShellState();
    Directory d1 = shellState.getRootDir();
    new Directory("a", d1);
    
  }

  @Test
  public void testExecuteCommand() throws Exception {
    Pushd push = new Pushd();
    String output = push.executeCommand(shellState, arguments);
    assertEquals(null ,output);
  }

}
