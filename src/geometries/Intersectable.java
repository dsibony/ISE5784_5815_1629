package geometries;

import java.util.List;
import primitives.*;

/**
 * The interface that deals with calculating all of the intersections
 */
public interface Intersectable {
	/**
	 * A method that receives a ray and returns a list of intersection points
	 * between the ray and the geometry shape
	 * 
	 * @param ray - a given ray
	 */
	List<Point> findIntersections(Ray ray);
}