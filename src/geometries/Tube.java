/**
 * 
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * This class will be used as a Tube, a geometric object
 */
public class Tube extends RadialGeometry {
	protected final Ray mainRay;

	/**
	 * Constructor to initialize Tube based object with its Ray and number values
	 * 
	 * @param mainRay Ray value
	 * @param radius number value
	 */
	public Tube(Ray mainRay, double radius) {
		this.mainRay = mainRay;
		this.radius = radius;
	}

	@Override
	public Vector getNormal(Point p) {
		return null;
	}

}
