/**
 *  
 */
package primitives;

import static primitives.Util.isZero;

/**
 * This class will serve all classes who want to use points 
 */
public class Point {
	public static final Point ZERO = new Point(0, 0, 0);
	protected final Double3 coordinate;

	/**
	 * Constructor to initialize Point based object with its three number values
	 * 
	 * @param d1 first number value
	 * @param d2 second number value
	 * @param d3 third number value
	 */
	public Point(double d1, double d2, double d3) {
		coordinate = new Double3(d1, d2, d3);
	}
	//public?
	Point(Double3 xyz) {
		coordinate = xyz;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return (obj instanceof Point other)
	            && isZero(coordinate.d1 - other.coordinate.d1)
	            && isZero(coordinate.d2 - other.coordinate.d2)
	            && isZero(coordinate.d3 - other.coordinate.d3);
		//return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString();
		// return "(" + coordinate.d1 + "," + coordinate.d2 + "," + coordinate.d3 + ")";
	}

	/**
	 * Subtract between two points resulting in a vector
	 * 
	 * @param p2 point two, to find the vector that is the result of the subtraction between 2 points 
	 * @return the vector from p2 to 
	 */
	public Vector subtract(Point p2) {
		return new Vector(coordinate.subtract(p2.coordinate));
	}

	/**
	 * Add a vector to a point resulting in another point
	 * 
	 * @param v vector, which will be added to the point
	 * @return the point which is the result of the addition of the vector to the point
	 */
	public Point add(Vector v) {
		return new Point(coordinate.add(v.coordinate));
	}

	/**
	 * Distance squared between 2 points, basic subtraction between the coordinates of the points squared and summed up together 
	 * 
	 * @param p2 point two, to find the distance squared between the two points
	 * @return the absolute value of the distance squared
	 */
	public double distanceSquared(Point p2) {
		double xDistance = coordinate.d1 - p2.coordinate.d1;
		double yDistance = coordinate.d2 - p2.coordinate.d2;
		double zDistance = coordinate.d3 - p2.coordinate.d3;
		return Math.abs((xDistance * xDistance) + (yDistance * yDistance) + (zDistance * zDistance));
	}

	/**
	 * Distance between 2 points, while using the distanceSquared method
	 * 
	 * @param p2 point two, to find the distance between the two points
	 * @return the distance between the two points (=square root of the distance squared)
	 */
	public double distance(Point p2) {
		return Math.sqrt(distanceSquared(p2));
	}
}