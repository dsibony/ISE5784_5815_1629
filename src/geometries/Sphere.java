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
 * This class will be used as a Sphere, a geometric object
 */
public class Sphere extends RadialGeometry {
	private final Point center;

	/**
	 * Constructor to initialize Double3 based object with its Point and number
	 * values
	 * 
	 * @param center Point value
	 * @param radius number value
	 */
	public Sphere(Point center, double radius) {
		super(radius);
		this.center = center;
	}

	@Override
	public Vector getNormal(Point p) {
		return p.subtract(center).normalize();
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		if (center == ray.getHead())
			return List.of(ray.getPoint(radius));

		Vector u = center.subtract(ray.getHead());
		double tm = alignZero(ray.getDirection().dotProduct(u));
		double dSquared = u.lengthSquared() - tm * tm;
		double thSquared = alignZero(radiusSquared - dSquared);
		if (thSquared <= 0)
			return null;

		double th = Math.sqrt(thSquared);
		double t2 = alignZero(tm + th);
		if (t2 <= 0)
			// since t2>t1 - both the points are behind the head of the ray
			return null;

		double t1 = alignZero(tm - th);
		return t1 <= 0 ? List.of(ray.getPoint(t2)) : List.of(ray.getPoint(t2), ray.getPoint(t1));
	}
}
