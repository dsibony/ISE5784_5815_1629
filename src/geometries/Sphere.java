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
		this.center = center;
		this.radius = radius;
	}

	@Override
	public Vector getNormal(Point p) {
		return p.subtract(center).normalize();
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		Vector u = center.subtract(ray.getHead());
		double tm = alignZero(ray.getDirection().dotProduct(u));
		if (tm > 0) {
			double d = Math.sqrt(u.lengthSquared() - tm * tm);
			if (d < radius) {
				double th = Math.sqrt(radius * radius - d * d);
				double t1 = alignZero(tm + th);
				double t2 = alignZero(tm - th);
				if (t1 > 0 && t2 > 0)
					return List.of(ray.getPoint(t2), ray.getPoint(t1));
				if (t1 > 0)
					return List.of(ray.getPoint(t1));
				if (t2 > 0)
					return List.of(ray.getPoint(t2));
			}
		}
		return null;
	}
}
