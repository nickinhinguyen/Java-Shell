package file_system;

import constants.Constants;
import constants.Exceptions;
import helper_classes.StringHelper;

/**
 * This class represents an object in a file system (can be a directory of file)
 */
public abstract class FileSystemObject implements java.io.Serializable {
    // this is object's name
    protected String name;
    // this is object's parent object
    protected Directory parent;

    /** Constructs an object that will be considered as root */
    public FileSystemObject() {
        // set name and create empty list of children
        this.name = Constants.SYSTEM_FILE_ROOT_NAME;
        this.parent = null;
    }

    /**
     * Constructs an object that has a name, and a parent object
     * @param name is a name of the object
     * @param parent is the parent object of the object
     * @throws Exception if name has invalid characters in it
     */
    public FileSystemObject
    (String name, Directory parent) throws Exception {
        // check if name is valid, throw error if not
        if (StringHelper.containsAny(name, Constants.INVALID_NAMING_CHAR)) {
            throw new Exception(this.getClass().getSimpleName() + " " +
                    Exceptions.INVALID_NAME);
        }
        // set name and create empty list of children
        this.name = name;
        this.parent = parent;
    }

    /** Return name of this FileSystemObject */
    public String getName() {
        return name;
    }


    /** Set name of this FileSystemObject
     * @throws Exception if name contains invalid characters */
    public void setName(String name) throws Exception {
        // check if name is valid, throw error if not
        if (StringHelper.containsAny(name, Constants.INVALID_NAMING_CHAR)) {
            throw new Exception(this.getClass().getSimpleName() + " " +
                    Exceptions.INVALID_NAME);
        }
        this.name = name;
    }

    /** Return parent of this FileSystemObject */
    public FileSystemObject getParent() {
        return parent;
    }

    /**
     * Set new parent to this object
     * @param newParent is the new parent (directory)
     * @throws Exception if newParent already has child with this object's name
     */
    public void setParent(Directory newParent) throws Exception {
        if (parent != null) {
            parent.removeChild(this);
        }
        newParent.addChild(this);
        parent = newParent;
    }

    /** Return the path of this FileSystemObject */
    public String getPath() {
        // initialize result path
        String result;
        // recursively get the result path
        if (parent == null) {
            result = name;
        } else if (parent.getParent() == null) {
            result = parent.getPath() + name;
        } else {
            result = parent.getPath() + Constants.SYSTEM_FILE_PATH_SEPERATOR + name;
        }
        // return the result path
        return result;
    }

    /**
     * Check if this fileSystemObject instance is of given type and name
     * @param classToComp given type
     * @param name given name
     * @return boolean
     */
    public boolean isEqualByClassAndName(Class<?> classToComp, String name) {
        return (classToComp.equals(this.getClass()) &&
                this.name.equals(name));
    }

    abstract public String getContent();

    /**
     * Check if current object has given fsObject as an ancestor
     * @param fsObject is given file system object
     * @return boolean
     */
    public boolean hasAncestor(FileSystemObject fsObject) {
        // if fsObject is a directory and this object isn't the fsObject directory
        if (fsObject instanceof Directory) {
            if (this.equals(fsObject)) {
                return true;
            }
            // get current object
            FileSystemObject currentObject = this;
            // loop until meet the root or until we find fsObject as ancestor
            while (currentObject.getParent() != null) {
                FileSystemObject curParent = currentObject.getParent();
                if (curParent.equals(fsObject)) {
                    return true;
                }
                currentObject = currentObject.getParent();
            }
        }
        // return false if no ancestor found
        return false;
    }

    abstract public void moveFsObjectIn
            (FileSystemObject fsObject) throws Exception;

    abstract public FileSystemObject cloneObject();
}
