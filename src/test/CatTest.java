package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import fileSystem.File;

/**
 * @author Nhi Nguyen Tests for cat commnand
 */
public class CatTest {
  private commands.Cat cat = new commands.Cat();
  private MockJShellState shellState;
  private List<String> arg =  new ArrayList<String>();

  @Before
  public void setUp() throws Exception {
	  shellState = new MockJShellState();
	  arg.add("java1");
	  arg.add("java2");
	  arg.add("python");
  }

  /**
   * Testing get content of one file
   */
  
  @Test
  public void testOneFile() throws Exception {
	  new File("java1", shellState.getCurrentDir(), "hello world");
	  try {
		  String result = cat.executeCommand(shellState, arg.subList(0, 1));
		  assertEquals("hello world", result);
	  } catch (Exception e) {
		  fail("fail to cat a single file");
	  }
  }
  
  /**
   * Testing more than one file
   */
  @Test
  public void testMultipleFile() throws Exception {
	  // create files in current dir for testing purpose
	  new File("java2", shellState.getCurrentDir(), "hello world");
	  new File("python", shellState.getCurrentDir(), "hello");
	  try {
		  String result = cat.executeCommand(shellState, arg.subList(1, 3));
		  assertEquals("hello world\n" + 
		  		"\n" + 
		  		"hello", result);
	  } catch (Exception e) {
		  fail("fail to cat a single file");
	  }
  }
}
  
 


