package file_system;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VirtualFileSystemTest {

    private VirtualFileSystem fs;

    private Directory root;

    @BeforeAll
    public void setUp() {
        fs = VirtualFileSystem.createFileSystemInstance();
        root = fs.getRoot();
    }

    @Test
    public void createFileSystemInstance_testSingleton() {
        VirtualFileSystem fs2 = VirtualFileSystem.createFileSystemInstance();
        assertEquals(fs, fs2);
    }

    @Test
    public void testGetFileByPath_validCall() throws Exception {
        File file = new File("file_name", root);
        File result = fs.getFileByPath(root, "file_name");
        assertEquals(result, file);
    }

}