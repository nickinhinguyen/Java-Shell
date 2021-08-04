package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import driver.JShellState;
//import fileSystem.Directory;


/**
 * This class tests the Echo method
 * @author Nhi Nguyen
 *
 */
public class EchoTest {
	private commands.Echo echo = new commands.Echo();
	private JShellState shellState;
	private List<String> arg =  new ArrayList<String>();
	  
	@Before
	public void setUp() throws Exception {
		shellState = new JShellState();
		arg.add(" ");
		arg.add("hello");
		
	}
	
	/*
	 * Test echo an empty string
	 */
	@Test
	public void testEmptyStringInput() {
		//empty string
		try {
			assertEquals(echo.executeCommand(shellState, arg.subList(0, 1))," ");
		} catch (Exception e) {
			fail("error with echo empty string");
		}
	}
	
	/*
	 * Test echo a valid string
	 */
	@Test
	public void testValidStringInput() {
		try {
			assertEquals(echo.executeCommand(shellState, arg.subList(1, 2)),"hello");
		} catch (Exception e) {
			fail("error with echo a valid string");
	
		}
	}
}
