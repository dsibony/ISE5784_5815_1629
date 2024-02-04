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
	 * NB: ray direction is normalized when initializing the ray
	 * 
	 * @param h - ray head
	 * @param v - vector of ray direction
	 */
	public Ray(Point h, Vector v) {
		head = h;
		direction = v.normalize();
	}

	/**
	 * getter for head
	 * 
	 * @return head
	 */
	public Point getHead() {
		return head;
	}

	/**
	 * getter for direction (it's already normalized!)
	 * 
	 * @return direction
	 */
	public Vector getDirection() {
		return direction;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Ray other) && direction.equals(other.direction) && head.equals(other.head);
	}

	@Override
	public String toString() {
		return "Ray:" + head + "->" + direction;
	}

	/**
	 * getter for specific point on the ray
	 * 
	 * @param t, the scalar used for scaling the vector
	 * @return the point
	 */
	public Point getPoint(double t) {
		return isZero(t) ? head : this.head.add(this.direction.scale(t));
	}

}
