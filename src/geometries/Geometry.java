/**
 * 
 */
package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Geometry interface used by every geometric object
 */
public interface Geometry {
	
	/**
	 * get normal method, used for getting the normal vector of a surface point in a given geometric object
	 * 
	 * @param p, given point
	 * @return the normal vector
	 */
	public Vector getNormal(Point p);
}
