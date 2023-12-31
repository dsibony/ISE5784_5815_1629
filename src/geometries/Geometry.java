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
	 * get normal method, used for getting the normal vector in a given geometric object
	 * 
	 * @param p, given point
	 * @return normal vector of the geometric object
	 */
	public Vector getNormal(Point p);
}
