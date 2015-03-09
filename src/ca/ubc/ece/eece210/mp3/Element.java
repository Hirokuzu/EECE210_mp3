package ca.ubc.ece.eece210.mp3;

import java.util.List;
import java.util.ArrayList;

/**
 * An abstract class to represent an entity in the catalogue. The element (in
 * this implementation) can either be an album or a genre.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public abstract class Element {

	// Representation Invariant:
	// If hasChildren( ) == true then children != null, otherwise
	// children == null.
	
	private List<Element> children;

	public Element() {
		if (hasChildren()) {
			children = new ArrayList<Element>();
		}
	}

	/**
	 * Returns all the children of this entity. They can be albums or genres. In
	 * this particular application, only genres can have children. Therefore,
	 * this method will return the albums or genres contained in this genre.
	 * 
	 * @param this method takes no arguments
	 * @return the children
	 */
	public List<Element> getChildren() {

		return children;
	}

	/**
	 * Adds a child to this entity. Basically, it is adding an album or genre to
	 * an existing genre
	 * 
	 * @param b
	 *            the entity to be added. A precondition is that hasChildren( )
	 *            must be true.
	 */
	protected void addChild(Element b) throws NullPointerException {
		if (children == null) {
			throw new NullPointerException(
					"Attempt to add child to a leaf node.");
		}
		children.add(b);
	}
	
	/**
	 * Removes a child from this entity.
	 * 
	 * @param b
	 *            the entity to be removed.
	 * @return 
	 *			true if the element is found and removed, false otherwise
	 */
	protected boolean removeChild(Element b) {
		if (hasChildren()) {
			return children.remove(b);
		}
		return false;
	}

	/**
	 * Abstract method to determine if a given entity can (or cannot) contain
	 * any children.
	 * 
	 * @return true if the entity can contain children, or false otherwise.
	 */
	public abstract boolean hasChildren();
}