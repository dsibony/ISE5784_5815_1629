/**
 * 
 */
package geometries;

import java.util.List;
import static primitives.Util.*;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * This class will be used as a plane and will have more uses as other classes
 * extend it
 */
public class Plane implements Geometry {
	private final Point q;
	private final Vector normal;

	/**
	 * Constructor to initialize Plane based object with its Point value and normal
	 * Vector value
	 * 
	 * @param p1 first point value (used for getting the normal vector too)
	 * @param p2 second point value (used for getting the normal vector too)
	 * @param p3 third point value (used for getting the normal vector too)
	 * 
	 * @throws IllegalArgumentException when all the points are on the same line
	 */
	public Plane(Point p1, Point p2, Point p3) {
		// Calculate vectors v1 and v2 in the plane
		Vector v1 = p2.subtract(p1);
		Vector v2 = p3.subtract(p1);

		// Calculate the cross product of v1 and v2 to get the normal vector
		normal = v1.crossProduct(v2).normalize();
		q = p3;
	}

	/**
	 * Constructor to initialize Plane based object with its Point value and normal
	 * Vector value
	 * 
	 * @param q      Point value
	 * @param normal Vector value
	 */
	public Plane(Point q, Vector normal) {
		this.q = q;
		this.normal = normal.normalize();
	}

	@Override
	public Vector getNormal(Point p) {
		return normal;
	}

	/**
	 * get normal method, used for getting the normal vector for the plane
	 * 
	 * @return the normal vector value
	 */
	public Vector getNormal() {
		return normal;
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		if (q.equals(ray.getHead()))
			return null;

		double nv = normal.dotProduct(ray.getDirection());
		double nQMinusP0 = normal.dotProduct(q.subtract(ray.getHead()));
		if (isZero(nv))
			return null;

		double t = alignZero(nQMinusP0 / nv);
		return t <= 0 ? null : List.of(ray.getPoint(t));
	}
}
