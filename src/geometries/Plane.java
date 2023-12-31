/**
 * 
 */
package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This class will be used as a plane and will have more uses as other classes extend it
 */
public class Plane implements Geometry {
	@SuppressWarnings("unused")
	private final Point q;
	private final Vector normal;

	/**
	 * Constructor to initialize Plane based object with its Point value and normal Vector value
	 * 
	 * @param p1 first point value (used for getting the normal vector too)
	 * @param p2 second point value (used for getting the normal vector too)
	 * @param p3 third point value (used for getting the normal vector too)
	 */
	public Plane(Point p1, Point p2, Point p3) {
		normal = null;
		q = p3;
	}

	/**
	 * Constructor to initialize Plane based object with its Point value and normal Vector value
	 * 
	 * @param q Point value
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
}
