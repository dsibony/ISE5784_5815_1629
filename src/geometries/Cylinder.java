package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * This class will be used as a cylinder, a geometric object
 */
public class Cylinder extends Tube {
	@SuppressWarnings("unused")
	private final double height;

	/**
	 * Constructor to initialize Cylinder based object with its two number values and one Ray value
	 * 
	 * @param mainRay, Ray value
	 * @param radius value
	 * @param height value
	 */
	public Cylinder(Ray mainRay, double radius, double height) {
		super(mainRay, radius);
		this.height = height;
	}

	@Override
	public Vector getNormal(Point p) {
		// TODO
		return null;
	}
}
