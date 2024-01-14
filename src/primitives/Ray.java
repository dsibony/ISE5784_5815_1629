/**
 * 
 */
package primitives;

/**
 * Ray is used for some geometric objects
 */
public class Ray {
	private final Point head;
	private final Vector direction;

	/**
	 * Constructor to initialize Ray based object with its Point and Vector values
	 *
	 * @param h - Point value
	 * @param v - Vector value
	 */
	public Ray(Point h, Vector v) {
		head = h;
		direction = v.normalize();
	}
	
	/** getter for head
	 * 
	 * @return head
	 */
	public Point getHead() {
		return head;
	}

	/** getter for direction
	 * 
	 * @return direction
	 */
	public Vector getDirection() {
		return direction;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return (obj instanceof Ray other) && direction.equals(other.direction) && head.equals(other.head);
	}

	@Override
	public String toString() {
		return "Ray:" + head + "->" + direction;

	}

}
