/**
 * 
 */
package geometries;

import java.util.List;
import primitives.*;
import static primitives.Util.*;

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
		createRegion(p1.calcMinimumPoint(p2), p1.calcMaximumPoint(p2));
		updateRegion(p3, p3);
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		var intersections = plane.findGeoIntersections(ray);
		if (intersections == null)
			return null;

		var p0 = ray.getHead();

		Vector v1 = vertices.get(0).subtract(p0);
		Vector v2 = vertices.get(1).subtract(p0);
		Vector n1 = v1.crossProduct(v2).normalize();
		double dp1 = alignZero(ray.getDirection().dotProduct(n1));
		if (dp1 == 0)
			return null;

		Vector v3 = vertices.get(2).subtract(p0);
		Vector n2 = v2.crossProduct(v3).normalize();
		double dp2 = alignZero(ray.getDirection().dotProduct(n2));
		if (dp1 * dp2 <= 0)
			return null;

		Vector n3 = v3.crossProduct(v1).normalize();
		double dp3 = alignZero(ray.getDirection().dotProduct(n3));
		if (dp1 * dp3 <= 0)
			return null;

		intersections.get(0).geometry = this;
		return intersections;
	}
}
