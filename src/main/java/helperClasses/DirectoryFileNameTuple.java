package helperClasses;

import fileSystem.Directory;

/**
 * This is a class that mimics a tuple in python
 */
public class DirectoryFileNameTuple {
    // this is first directory object in the tuple
    private Directory directory;
    // this is the second object in the tuple
    private String fileName;

    /**
     * Constructor for a tuple
     */
    public DirectoryFileNameTuple(Directory directory, String fileName) {
        this.directory = directory;
        this.fileName = fileName;
    }

    /**
     * Return first object
     */
    public Directory getDirectory() {
        return this.directory;
    }

    /**
     * Return second object
     */
    public String getFileName() {
        return this.fileName;
    }

}
