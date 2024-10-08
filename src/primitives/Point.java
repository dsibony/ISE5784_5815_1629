/**
 *  
 */
package primitives;

/**
 * This class will serve all classes who want to use points
 */
public class Point {
	/** zero point */
	public static final Point ZERO = new Point(0, 0, 0);
	/** x, y, z coordinates */
	protected final Double3 coordinates;

	/**
	 * Constructor to initialize Point based object with its three number values
	 * 
	 * @param d1 - first number value
	 * @param d2 - second number value
	 * @param d3 - third number value
	 */
	public Point(double d1, double d2, double d3) {
		coordinates = new Double3(d1, d2, d3);
	}

	/**
	 * Constructor to initialize Point based object with its Double3 coordinate
	 * 
	 * @param xyz - the only Double3 value needed for the Point
	 */
	public Point(Double3 xyz) {
		coordinates = xyz;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return (obj instanceof Point other) && coordinates.equals(other.coordinates);
	}

	@Override
	public String toString() {
		return "Point:" + coordinates;
	}

	/**
	 * Subtract between two points resulting in a vector
	 * 
	 * @param p2 point two, to find the vector that is the result of the subtraction
	 *           between 2 points
	 * @return the vector from p2 to p1
	 */
	public Vector subtract(Point p2) {
		return new Vector(coordinates.subtract(p2.coordinates));
	}

	/**
	 * Add a vector to a point resulting in another point
	 * 
	 * @param v vector, which will be added to the point
	 * @return the point which is the result of the addition of the vector to the
	 *         point
	 */
	public Point add(Vector v) {
		return new Point(coordinates.add(v.coordinates));
	}

	/**
	 * Add x, y, z values to a point resulting in another point
	 * 
	 * @param d1 - the x value
	 * @param d2 - the y value
	 * @param d3 - the z value
	 * @return the point which is the result of the addition of the x, y, z values
	 *         to the point
	 */
	public Point add(double d1, double d2, double d3) {
		return new Point(coordinates.d1 + d1, coordinates.d2 + d2, coordinates.d3 + d3);
	}

	/**
	 * Distance squared between 2 points, basic subtraction between the coordinates
	 * of the points squared and summed up together
	 * 
	 * @param p2 point two, to find the distance squared between the two points
	 * @return the absolute value of the distance squared
	 */
	public double distanceSquared(Point p2) {
		double xDistance = coordinates.d1 - p2.coordinates.d1;
		double yDistance = coordinates.d2 - p2.coordinates.d2;
		double zDistance = coordinates.d3 - p2.coordinates.d3;
		return Math.abs((xDistance * xDistance) + (yDistance * yDistance) + (zDistance * zDistance));
	}

	/**
	 * Distance between 2 points, while using the distanceSquared method
	 * 
	 * @param p2 point two, to find the distance between the two points
	 * @return the distance between the two points (=square root of the distance
	 *         squared)
	 */
	public double distance(Point p2) {
		return Math.sqrt(distanceSquared(p2));
	}

	/**
	 * finds a point with the smallest x, y, z values between two points
	 * 
	 * @param p - the other point to compare with
	 * @return the point with the smallest x, y, z values
	 */
	public Point calcMinimumPoint(Point p) {
		return p == null ? new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY)
				: new Point(
						Util.alignZero(this.coordinates.d1 - p.coordinates.d1) > 0 ? p.coordinates.d1
								: this.coordinates.d1,
						Util.alignZero(this.coordinates.d2 - p.coordinates.d2) > 0 ? p.coordinates.d2
								: this.coordinates.d2,
						Util.alignZero(this.coordinates.d3 - p.coordinates.d3) > 0 ? p.coordinates.d3
								: this.coordinates.d3);
	}

	/**
	 * finds a point with the biggest x, y, z values between two points
	 * 
	 * @param p - the other point to compare with
	 * @return the point with the biggest x, y, z values
	 */
	public Point calcMaximumPoint(Point p) {
		return p == null ? new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
				: new Point(
						Util.alignZero(this.coordinates.d1 - p.coordinates.d1) < 0 ? p.coordinates.d1
								: this.coordinates.d1,
						Util.alignZero(this.coordinates.d2 - p.coordinates.d2) < 0 ? p.coordinates.d2
								: this.coordinates.d2,
						Util.alignZero(this.coordinates.d3 - p.coordinates.d3) < 0 ? p.coordinates.d3
								: this.coordinates.d3);
	}

	/**
	 * Calculating a plane intersection on an axis
	 * 
	 * @param ray - the intersecting ray
	 * @param i   - the index
	 * @return the value of the intersection point
	 */
	public double calcCoordinatesValues(Ray ray, int i) {
		if (i == 1)
			return (this.coordinates.d1 - ray.getHead().coordinates.d1) / ray.getDirection().coordinates.d1;
		if (i == 2)
			return (this.coordinates.d2 - ray.getHead().coordinates.d2) / ray.getDirection().coordinates.d2;
		if (i == 3)
			return (this.coordinates.d3 - ray.getHead().coordinates.d3) / ray.getDirection().coordinates.d3;
		return 0;
	}

	/**
	 * Calculating an addition between 2 points
	 * 
	 * @param p - the second point
	 * @return the addition
	 */
	public Point add(Point p) {
		return new Point(this.coordinates.add(p.coordinates));
	}

	/**
	 * Calculating a scale between 2 points
	 * 
	 * @param p - the second point
	 * @return the scaled point
	 */
	public Point scale(double d) {
		return new Point(this.coordinates.scale(d));
	}

}