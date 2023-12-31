/**
 * 
 */
package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This class will be used as a Sphere, a geometric object
 */
public class Sphere extends RadialGeometry {
	private final Point center;

	/**
	 * Constructor to initialize Double3 based object with its Point and number values
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
		// TODO
		return null;
	}

}
