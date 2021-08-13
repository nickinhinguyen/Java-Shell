package helper_classes;

import file_system.Directory;

/**
 * This is a class that mimics a tuple in python
 */
public class DirectoryFileNameTuple {
    // this is first directory object in the tuple
    private final Directory directory;
    // this is the second object in the tuple
    private final String fileName;

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
