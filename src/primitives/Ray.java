/**
 * 
 */
package primitives;
import static primitives.Util.isZero;

/**
 * Ray is used for some geometric objects
 */
public class Ray {
	private final Point head;
	private final Vector direction;

	/**
	 * Constructor to initialize Ray based object with its Point and Vector values
	 *
	 * @param h, Point value
	 * @param v, Vector value
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
		return (obj instanceof Ray other)
	            && isZero(head.coordinates.d1 - other.head.coordinates.d1)
	            && isZero(head.coordinates.d2 - other.head.coordinates.d2)
	            && isZero(head.coordinates.d3 - other.head.coordinates.d3)
				&& isZero(direction.coordinates.d1 - other.direction.coordinates.d1)
				&& isZero(direction.coordinates.d2 - other.direction.coordinates.d2)
				&& isZero(direction.coordinates.d3 - other.direction.coordinates.d3);
	}

	@Override
	public String toString() {
		return super.toString();
		// return "(" + coordinate.d1 + "," + coordinate.d2 + "," + coordinate.d3 + ")";
	}

}
