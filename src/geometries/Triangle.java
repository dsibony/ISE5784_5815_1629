/**
 * 
 */
package geometries;

import java.util.List;
import primitives.*;
import static primitives.Util.isZero;

/**
 * This class will be used as a Triangle, a geometric object
 */
public class Triangle extends Polygon {

	/**
	 * Constructor to initialize Triangle based object with its three Point values
	 * 
	 * @param p1 - first point value
	 * @param p2 - second point value
	 * @param p3 - third point value
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		List<Point> intersections = plane.findIntersections(ray);
		if (intersections == null)
			return null;

		if (intersections.get(0).equals(vertices.get(0)) || intersections.get(0).equals(vertices.get(1))
				|| intersections.get(0).equals(vertices.get(2)))
			return null;
		Vector v1 = vertices.get(0).subtract(ray.getHead());
		Vector v2 = vertices.get(1).subtract(ray.getHead());
		Vector v3 = vertices.get(2).subtract(ray.getHead());
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();
		double dp1 = ray.getDirection().dotProduct(n1);
		double dp2 = ray.getDirection().dotProduct(n2);
		double dp3 = ray.getDirection().dotProduct(n3);
		if (isZero(dp1) || isZero(dp2) || isZero(dp3))
			return null;
		return ((dp1 > 0 && dp2 > 0 && dp3 > 0) || (dp1 < 0 && dp2 < 0 && dp3 < 0)) ? intersections : null;
	}
}
