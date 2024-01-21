/**
 * 
 */
package geometries;

import java.util.List;
import primitives.*;
import static primitives.Util.isZero;

/**
 * This class will be used as a Triangle, a geometric object
 */
public class Triangle extends Polygon {

	/**
	 * Constructor to initialize Triangle based object with its three Point values
	 * 
	 * @param p1 - first point value
	 * @param p2 - second point value
	 * @param p3 - third point value
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		List<Point> intersections = plane.findIntersections(ray);
		if (intersections == null)
			return null;

		if (intersections.get(0).equals(vertices.get(0)) || intersections.get(0).equals(vertices.get(1))
				|| intersections.get(0).equals(vertices.get(2)))
			return null;

		Vector v01 = vertices.get(1).subtract(vertices.get(0));
		Vector v0P = intersections.get(0).subtract(vertices.get(0));
		Vector v12 = vertices.get(2).subtract(vertices.get(1));
		Vector v02 = vertices.get(2).subtract(vertices.get(0));
		Vector v2P = intersections.get(0).subtract(vertices.get(2));

		Vector cp1 = v12.crossProduct(v2P);
		Vector cp2 = v12.crossProduct(v01.scale(-1));
		Vector cp3 = v02.crossProduct(v0P);
		Vector cp4 = v02.crossProduct(v01);
		Vector cp5 = v01.crossProduct(v0P);
		Vector cp6 = v01.crossProduct(v02);

		return (cp1.dotProduct(cp2) >= 0 && cp3.dotProduct(cp4) >= 0 && cp5.dotProduct(cp6) >= 0) ? intersections
				: null;
		/*
		 * if (isZero(n1.length()) || isZero(n2.length()) || isZero(n3.length())) throw
		 * new
		 * IllegalArgumentException("The length of one or more of the vectors is zero");
		 * return (n1.dotProduct(n2) >= 0 && n1.dotProduct(n3) >= 0) ? intersections :
		 * null;
		 */
	}
}
