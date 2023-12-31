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
	            && isZero(head.coordinate.d1 - other.head.coordinate.d1)
	            && isZero(head.coordinate.d2 - other.head.coordinate.d2)
	            && isZero(head.coordinate.d3 - other.head.coordinate.d3)
				&& isZero(direction.coordinate.d1 - other.direction.coordinate.d1)
				&& isZero(direction.coordinate.d2 - other.direction.coordinate.d2)
				&& isZero(direction.coordinate.d3 - other.direction.coordinate.d3);
	}

	@Override
	public String toString() {
		return super.toString();
		// return "(" + coordinate.d1 + "," + coordinate.d2 + "," + coordinate.d3 + ")";
	}

}
