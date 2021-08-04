package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fileSystem.Directory;

/**
 * Testing class for cd command
 * @author Nhi Nguyen
 *
 */
public class CdTest {

  private commands.Cd cd = new commands.Cd();
  private MockJShellState shellState;
  private List<String> arg =  new ArrayList<String>();
  
  @Before
  public void setUp() throws Exception {
  shellState = new MockJShellState();
  //create a root dir
  Directory rootdir = shellState.getRootDir();
  //create a testing directory
  try {
	  new Directory("child1", rootdir);
  
  } catch (Exception e) {
	  
  }
  }
  
  /**
   * Test change to a directory within the filesystem
   */
  @Test
  public void testGotoValidDir() {
  arg.add("child1");
  try {
	  cd.executeCommand(shellState, arg);
	  assertEquals("child1", shellState.getCurrentDir().getName());
	  
  } catch (Exception e) {
	  fail("Error when go to valid directory");
  }
  }
  
  /*
   * Testing move to a directory that's not existed
   */
  @Test
  public void testGotoNonExistingDir() {
	  arg.add("nonexistingdir");
	  try {
		  cd.executeCommand(shellState, arg);
	  } catch (Exception e) {
		  assertEquals(e.getMessage(),"No such file or directory");
	  }
  }

}
