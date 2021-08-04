package test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commands.Cp;
import constants.Exceptions;
import driver.JShellState;
import fileSystem.Directory;
import fileSystem.FileSystem;
import fileSystem.FileSystemObject;

public class CpTest {
	 JShellState shellState;
	  List<String> arguments = new ArrayList<String>();
	  Directory d1;
	  Directory d2;
	  Directory d3;
	  Directory d4;

		@Before
	  public void setUp() throws Exception {
	    shellState = new JShellState();
	    d1 = shellState.getRootDir();
	    d2 = new Directory("a", d1);
	    d3 = new Directory("b", d1);
	    d4 = new Directory("a", d2);
	  }
		
		@After
		public void tearDown() throws Exception {
			FileSystem fs = shellState.getFileSystem();
			Field field = fs.getClass().getDeclaredField("singleReference");
			field.setAccessible(true);
			field.set(fs, null);
		}
		
		@Test
		public void testMoveCorrectly() {
	    arguments.add("a");
	    arguments.add("b");
	    Cp mv = new Cp();
	    try {
				mv.executeCommand(shellState, arguments);
				List<FileSystemObject> children = d1.getChildren();
				// check children of root
				assertEquals(children.size(), 2);
				assertEquals(children.get(0), d3);
				Directory copied = (Directory) children.get(1);
				assertEquals(copied.getName(), "a");
				// check children of "b" directory in the root
				assertEquals(d3.getChildren().size(), 1);
				assertEquals(d2, d3.getChildren().get(0));
			} catch (Exception e) {
				fail("Failed - not suppose to throw exception");
			}
		}
		
		@Test
		public void testMoveIntoSubDirectory() {
			arguments.add("a");
	    arguments.add("a/a");
	    Cp mv = new Cp();
	    try {
				mv.executeCommand(shellState, arguments);
				fail("Failed - suppose to throw exception");
			} catch (Exception e) {
				assertEquals(Exceptions.MOVE_INTO_SUBCHILD_ERROR, e.getMessage());
			}
		}
}
