/**
 * 
 */
package primitives;

import static primitives.Util.isZero;

import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * Ray is used for some geometric objects
 */
public class Ray {
	/** Tolerance value for ray-surface intersection calculations */
	private static final double DELTA = 0.1;

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
		this.head = h;
		this.direction = v.normalize();
	}

	/**
	 * Constructor to 
	 * 
	 * @param head      - the head of the ray
	 * @param direction - the direction vector
	 * @param normal    - the normal to the object the point is on
	 */
	public Ray(Point head, Vector direction, Vector normal) {
		double vn = direction.dotProduct(normal);
		this.head = head.add(normal.scale(vn < 0 ? -DELTA : DELTA));
		this.direction = direction.normalize();
	}

	/**
	 * getter for head
	 * 
	 * @return head
	 */
	public Point getHead() {
		return this.head;
	}

	/**
	 * getter for direction (it's already normalized!)
	 * 
	 * @return direction
	 */
	public Vector getDirection() {
		return this.direction;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Ray other) && this.direction.equals(other.direction) && this.head.equals(other.head);
	}

	@Override
	public String toString() {
		return "Ray:" + this.head + "->" + this.direction;
	}

	/**
	 * getter for specific point on the ray
	 * 
	 * @param t, the scalar used for scaling the vector
	 * @return the point
	 */
	public Point getPoint(double t) {
		return isZero(t) ? this.head : this.head.add(this.direction.scale(t));
	}

	/**
	 * this method finds the closest point to the ray's head
	 * 
	 * @param points - list of points
	 * @return the closest point to the ray's head
	 */
	public Point findClosestPoint(List<Point> points) {
		return points == null || points.isEmpty() ? null
				: findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
	}

	/**
	 * A method to find the closest GeoPoint out of a list
	 * 
	 * @param list - A list of GeoPoints
	 * @return the closest point
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> list) {
		if (list == null || list.isEmpty())
			return null;
		GeoPoint closestPoint = list.get(0);
		double distance = this.head.distance(closestPoint.point);
		double newDistance;
		for (GeoPoint p : list) {
			newDistance = this.head.distance(p.point);
			if (newDistance < distance) {
				distance = newDistance;
				closestPoint = p;
			}
		}
		return closestPoint;
	}
}
