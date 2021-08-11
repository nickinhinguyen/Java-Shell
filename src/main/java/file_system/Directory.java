package file_system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import constants.Constants;
import constants.Exceptions;

/**
 * This class represents a virtual directory that can hold other FileSystem
 * objects such as file or directory
 */
public class Directory extends FileSystemObject
        implements Iterable<FileSystemObject> {
    // this is a list that holds this directory's children
    private ArrayList<FileSystemObject> children;

    /**
     * This is an Directory Iterator
     */
    public class DirectoryIterator implements Iterator<FileSystemObject> {
        // this is array of objects to loop through
        private ArrayList<FileSystemObject> childrenArr;
        // this is a counter
        int counter;

        /**
         * Create directory iterator with an array of objects to loop through
         * @param children
         */
        public DirectoryIterator(ArrayList<FileSystemObject> children) {
            this.childrenArr = children;
            counter = 0;
        }

        @Override
        public boolean hasNext() {
            if (counter >= childrenArr.size() || childrenArr.get(counter) == null) {
                return false;
            }
            return true;
        }

        @Override
        public FileSystemObject next() {
            FileSystemObject fsObject = children.get(counter);
            counter ++;
            return fsObject;
        }

    }

    /** Constructs a directory without a parent (root directory) */
    public Directory() {
        // set name and create empty list of children
        super();
        children = new ArrayList<FileSystemObject>();
    }

    /**
     * Constructs a Directory that has a name, and a parent directory
     * @param name is a name of the directory
     * @param parent is the parent directory of the directory
     * @throws Exception if name has invalid characters in it
     */
    public Directory(String name, Directory parent) throws Exception {
        // assign name, and parent directory, create list of children
        super(name, parent);
        children = new ArrayList<FileSystemObject>();
        // add this directory as a child of parent directory
        if (parent != null) {
            parent.addChild(this);
        }

    }

    /** Return children of this directory */
    public ArrayList<FileSystemObject> getChildren() {
        return children;
    }

    /** Add a child to this directory
     *  @throws Exception if child with that type and name already exists
     */
    public void addChild(FileSystemObject child) throws Exception {
        if (this.hasChildWithName(child.name)) {
            throw new Exception(Exceptions.FILE_EXISTS);
        }
        children.add(child);
    }

    @Override
    public String getContent() {
        String content = "";
        for (FileSystemObject child : this) {
            content += child.getName() + "\n";
        }
        if (!content.equals("")) {
            content = content.substring(0, content.length() - 1);
        }
        return content;
    }

    /**
     * Checks if this directory has a child with given name and type
     * @param childClass is the type of the child
     * @param name is the given name of the child
     * @return boolean that indicates if directory has child with that name
     */
    public boolean hasChildWithTypeAndName(Class<?> childClass, String name) {
        boolean result = false;
        for (FileSystemObject currentChild : this.children) {
            if (currentChild.isEqualByClassAndName(childClass, name)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Checks if this directory has a child with given name
     * @param name is the given name of the child
     * @return boolean that indicates if directory has child with that name
     */
    public boolean hasChildWithName(String name) {
        boolean result = false;
        for (FileSystemObject currentChild : this.children) {
            if (name.equals(currentChild.getName())) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Check if given directory has a child with given type and name
     * @param currentDir is given directory
     * @param classType is the given child type
     * @param name is the given child name
     * @return child with matching name and type
     * @throws Exception if currentDir directory has no such child
     */
    public static FileSystemObject findChild
    (Directory currentDir, Class<?> classType, String name) throws Exception {
        List<FileSystemObject> curDirChildren = currentDir.getChildren();
        /*
         * loop through children to find and return child directory that
         * matches by name
         */
        for (FileSystemObject currentChild : curDirChildren) {
            if (currentChild.isEqualByClassAndName(classType, name)) {
                return (FileSystemObject) currentChild;
            }
        }
        // throw exception because if method hasn't returned yet, no child matched
        throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
    }

    /**
     * Check if given directory has a child with given name; if name is '.' refer
     * to current directory, if name is '..' refer to parent directory
     * @param currentDir is given directory
     * @param fileName is the given fileSystemObject name
     * @return child with matching name
     * @throws Exception if this directory has no such child
     */
    public static FileSystemObject findChild
    (Directory currentDir, String childName) throws Exception {
        List<FileSystemObject> curDirChildren = currentDir.getChildren();
        /*
         * if name is '.' refer
         * to current directory, if name is '..' refer to parent directory
         */
        if (childName.equals(Constants.PARENT_OF_SYSTEM_FILE_INDICATOR)) {
            return (currentDir.getParent() != null)
                    ? currentDir.getParent() : currentDir;
        } else if (childName.equals(Constants.CURRENT_SYSTEM_FILE_INDICATOR)) {
            return currentDir;
        }
        /*
         * else loop through children to find and return child directory
         * that matches by name
         */
        for (FileSystemObject currentChild : curDirChildren) {
            if (currentChild.getName().equals(childName)) {
                return currentChild;
            }
        }
        // throw exception because if method hasn't returned yet, no child matched
        throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
    }

    /**
     * Removes directory's child with current name(if present)
     * @param name given child name
     */
    public void removeChild(FileSystemObject fsObject) {
        children.remove(fsObject);
    }

    /**
     * Removes directory's child with current name(if present)
     * @param name given child name
     */
    public void removeChildByName(String fsObjectName) {
        try {
            removeChild(findChild(this, fsObjectName));
        } catch (Exception e) {}
    }

    /**
     * Check if given directory has a directory child with given name
     * @param currentDir is given directory
     * @param dirName is the given directory name
     * @return child with matching name
     * @throws Exception currentDir directory has no such child
     */
    public static Directory findChildDirectory
    (Directory currentDir, String dirName) throws Exception {
        return (Directory) findChild(currentDir, Directory.class, dirName);
    }

    /**
     * Check if given directory has a file child with given name
     * @param currentDir is given directory
     * @param fileName is the given directory name
     * @return file child with matching name
     * @throws Exception currentDir directory has no such child
     */
    public static File findChildFile
    (Directory currentDir, String fileName) throws Exception {
        return (File) findChild(currentDir, File.class, fileName);
    }

    /**
     * Find next directory by moving either backwards (to parent) or forward
     * (some child directory) from given directory according to the given string
     * @param currentDir is the given directory
     * @param nextDirectory is string that indicates which directory to move to
     * @return directory that matches nextDirectory
     * @throws Exception if cannot move from this directory to directory that
     * matches nextDirectory
     */
    public static Directory findDirectory
    (Directory currentDir, String nextDirectory) throws Exception{
        Directory wantedDirectory = currentDir;
        if (nextDirectory.equals(Constants.PARENT_OF_SYSTEM_FILE_INDICATOR)) {
            /*
             * if nextDirectory is the 'go to parent directory' indicator,
             * move to current directory parent; remain in the current directory if
             * it doesn't have a parent or if nextDirectory is an empty String
             */
            if (currentDir.getParent() != null) {
                wantedDirectory = (Directory) currentDir.getParent();
            }
        } else if (!nextDirectory.equals(Constants.CURRENT_SYSTEM_FILE_INDICATOR)
                && !nextDirectory.equals("")) {
            /*
             * else if nextDirectory isn't the 'current directory' indicator (do
             * nothing if it is), check to see any child directory of current one
             * matches the next directory name in the list by name
             */
            wantedDirectory = findChildDirectory(currentDir, nextDirectory);
        }
        return wantedDirectory;
    }

    /**
     * Find the directory starting from given directory, moving either backwards
     * (to its parent) or forward (to one of its children) according to the
     * given list of directory names
     * @param currentDir is the starting directory
     * @param listOfDirNames is list of directory names to move through
     * @return directory that is there after moved through the entire list
     * @throws Exception if at some point, next item on the list cannot be moved
     * to from current directory
     */
    public static Directory findDirectory
    (Directory currentDir, List<String> listOfDirNames) throws Exception {
        // move in order through each directory in the list
        for (String nextDirectory: listOfDirNames) {
            currentDir = findDirectory(currentDir, nextDirectory);
        }
        return currentDir;
    }

    /**
     * Moves given file system object into the current directory, overwriting
     * existing file in current directory (if they have the same name)
     * @param fsObject
     */
    public void moveFsObjectIn(FileSystemObject fsObject) {
        removeChildByName(fsObject.getName());
        try {
            fsObject.setParent(this);
        } catch (Exception e) {}
    }

    @Override
    public Iterator<FileSystemObject> iterator() {
        return new DirectoryIterator(children);
    }

    /**
     * Constructor for cloning purposes
     */
    @Override
    public FileSystemObject cloneObject(){
        Directory clonedDirectory = new Directory();
        try {
            clonedDirectory = new Directory(name, null);
            for (FileSystemObject child : this) {
                FileSystemObject childClone = child.cloneObject();
                childClone.setParent(clonedDirectory);
            }
        } catch (Exception e) {}
        return clonedDirectory;
    }
}
