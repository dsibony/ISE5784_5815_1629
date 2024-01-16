/**
 * 
 */
package geometries;

import java.util.List;

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
		return null;
	}
}
