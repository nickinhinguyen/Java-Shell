package fileSystem;

import constants.Exceptions;

/**
 * This class represents a virtual file that has content and is a child of a
 * directory
 * @author Minh Hoang Nguyen
 *
 */
public class File extends FileSystemObject {
	private String content;
	
	
	/**
	 * Constructs a file with given name and parent
	 * @param name given name
	 * @param parent given parent
	 * @throws Exception if object with given name exists in given parent
	 */
	public File(String name, Directory parent) throws Exception {
		this(name, parent, "");
	}
	
	/**
	 * Constructs a file with given name and parent and content
	 * @param name given name
	 * @param parent given parent
	 * @param content given content
	 * @throws Exception if object with given name exists in given parent
	 */
	public File(String name, Directory parent, String content) throws Exception {
		super(name, parent);
		parent.addChild(this);
		this.content = content;
	}
	
	/**
	 * Return content of file
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * Set content of file
	 * @param content is given content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Append content to the file
	 * @param contentToAppend is content to append to the file
	 */
	public void appendContent(String contentToAppend) {
		this.content += "\n" + contentToAppend;
	}
	
	@Override
	public void moveFsObjectIn(FileSystemObject fsObject) throws Exception {
		if (!(fsObject instanceof File)) {
			throw new Exception(Exceptions.MOVE_DIRECTORY_INTO_FILE_ERROR);
		}
		setContent(fsObject.getContent());
	}

	/**
	 * Constructor for cloning purpose
	 */
	private File(String name, String content) {
		this.name = name;
		this.content = content;
		this.parent = null;
	}
	
	@Override
	public FileSystemObject cloneObject() {
		return new File(name, content);
	}
	
	
}
