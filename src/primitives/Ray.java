/**
 * 
 */
package primitives;

/**
 * Ray is used for some geometric objects
 */
public class Ray {
	//?not supposed to be public?
	public final Point head;
	public final Vector direction;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return (obj instanceof Ray other) && direction.equals(other.direction) && head.equals(other.head);
	}

	@Override
	public String toString() {
		return "Ray:" + direction;
		
	}

}
