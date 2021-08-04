package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import fileSystem.Directory;


/**
 * this class test the Mkdir command
 * @author Nhi Nguyen
 */
public class MkdirTest {
	private commands.Mkdir mkdir = new commands.Mkdir();
	  MockJShellState shellState;
	  List<String> arguments = new ArrayList<String>();

  @Before
  public void setUp() throws Exception {
    shellState = new MockJShellState();
    
    arguments.add("dir 1");
  }

  /**
   * Testing the method with a relative path input
   */
  @Test
  public void testMkdirRelativePath() {
    try {
	  mkdir.executeCommand(shellState, arguments);
    } catch(Exception e) {
	  fail("error creating with a relative path");
    } 
    try {
	  Directory curdir = shellState.getCurrentDir();
	  assertTrue(curdir.hasChildWithName("dir 1"));
    } catch (Exception e) {
	  fail("directory has not been created");
	  }
    }
  
  
  /**
   * Testing mkdir with an absolute path
   */
  @Test
  public void testMkdirWithAbsolutePath() throws Exception {
	Directory testdir = new Directory
	    		("testdir",shellState.getCurrentDir());
    //set current dir to some dir thats not root
    shellState.setCurrentDir(testdir);
    arguments.clear();
    arguments.add("/testdir/child");
	// make a directory inside this current dir
	try {
      mkdir.executeCommand(shellState, arguments);
    } catch(Exception e) {
      fail("error creating with a absolute path");
    } 
    try {
  	  Directory curdir = shellState.getCurrentDir();
  	  assertTrue(curdir.hasChildWithName("child"));
      } catch (Exception e) {
  	  fail("directory has not been created with abs path");
  	  }

}
}
