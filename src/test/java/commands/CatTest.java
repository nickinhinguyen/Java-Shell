package commands;

import constants.Exceptions;
import driver.JShellState;
import file_system.VirtualFileSystem;
import file_system.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CatTest {
    private Cat cat;

    private JShellState shell;
    private VirtualFileSystem fs;
    private List<String> arg;

    // run before each instead of before all to reset all the mock objects
    @BeforeEach
    public void setUp() throws Exception {
        cat = new Cat();
        shell = mock(JShellState.class);
        fs = mock(VirtualFileSystem.class);
        arg =  new ArrayList<>();
        when(shell.getFileSystem()).thenReturn(fs);
    }

    @Test
    public void testExecuteCommand_oneValidFile() throws Exception {
        arg.add("file1");
        File file = mock(File.class);
        when(fs.getFileByPath(any(), eq("file1"))).thenReturn(file);
        when(file.getContent()).thenReturn("sample content");
        String result = cat.executeCommand(shell, arg);
        assertEquals("sample content", result);
    }

    @Test
    public void testExecuteCommand_nonExistingFile() throws Exception {
        arg.add("file2");
        when(fs.getFileByPath(any(), any())).thenThrow(new Exception(Exceptions.WRONG_PATH_INPUT_MSG));
        String result = cat.executeCommand(shell, arg);
        assertEquals("cannot view file file2: No such file or directory", result);
    }
}
