package commands;

import driver.JShellState;
import file_system.Directory;
import file_system.VirtualFileSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MkdirTest {
    JShellState shellState;
    CommandController commandExecutor;

    @Before
    public void setUp() throws Exception {
        VirtualFileSystem.resetFileSystemInstance();
        shellState = new JShellState();
        commandExecutor = new CommandController();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testExecuteCommandHistory() {
        try {
            String output = commandExecutor.executeCommand(shellState, "mkdir test");
            List<String> history = shellState.getHistory();
            assertEquals("mkdir test", history.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testExecuteCommandNewDir() {
        try {
            String output = commandExecutor.executeCommand(shellState, "mkdir test");
            VirtualFileSystem fileSystem = shellState.getFileSystem();
            Directory newDir = fileSystem.getDirectoryByPath(fileSystem.getRoot(), "test");
        } catch (Exception e) {
            fail("Could not retrieve new directory using getDirectoryByPath");
        }
    }
}