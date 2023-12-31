/**
 * 
 */
package primitives;

/**
 * This class will serve all classes who use vectors
 * extends the Point class 
 */
public class Vector extends Point {

	/**
	 * Constructor to initialize Vector based object with its three number values
	 * 
	 * @param d1 first number value
	 * @param d2 second number value
	 * @param d3 third number value
	 */
	public Vector(double d1, double d2, double d3) {
		super(d1, d2, d3);
		if (d1 == 0 && d2 == 0 && d3 == 0) {
			throw new IllegalArgumentException("zero vector");
		}
	}

	/**
	 * Constructor to initialize Vector based object with its Double3 coordinate value
	 * 
	 * @param xyz the only Double3 value needed for the Vector
	 */
	//supposed to be package permission (=no permission)
	public Vector(Double3 xyz) {
		super(xyz);
		if (xyz.equals(xyz.ZERO)) {
			throw new IllegalArgumentException("zero vector");
		}
	}

	@Override
	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		return (obj instanceof Point other)
//	            && isZero(coordinate.d1 - other.coordinate.d1)
//	            && isZero(coordinate.d2 - other.coordinate.d2)
//	            && isZero(coordinate.d3 - other.coordinate.d3);
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString();
		// return "(" + coordinate.d1 + "," + coordinate.d2 + "," + coordinate.d3 + ")";
	}

	/**
	 * Vector addition (between 2 vectors)
	 * 
	 * @param v right hand vector, the one being added to the first vector 
	 * @return the vector that is the result of summing up the vectors
	 */
	public Vector add(Vector v) {
		return new Vector(coordinate.add(v.coordinate));
	}

	/**
	 * Scale the vector by a scalar
	 * 
	 * @param rhs right hand side scalar, used to scale the vector 
	 * @return the vector scaled by rhs
	 */
	public Vector scale(double rhs) {
		return new Vector(coordinate.scale(rhs));
	}

	/**
	 * Dot product between two vectors
	 * 
	 * @param right hand vector, the second vector in the dot product
	 * @return the dot product of the vectors
	 */
	public double dotProduct(Vector v) {
		return coordinate.d1 * v.coordinate.d1 + coordinate.d2 * v.coordinate.d2 + coordinate.d3 * v.coordinate.d3;
	}

	/**
	 * Cross product between two vectors
	 * 
	 * @param v vector, the second vector in the cross product
	 * @return the vector that is the result of the cross product between those two vectors
	 */
	public Vector crossProduct(Vector v) {
		double s1 = coordinate.d2 * v.coordinate.d3 - coordinate.d3 * v.coordinate.d2;
		double s2 = coordinate.d3 * v.coordinate.d1 - coordinate.d1 * v.coordinate.d3;
		double s3 = coordinate.d1 * v.coordinate.d2 - coordinate.d2 * v.coordinate.d1;
		return new Vector(s1, s2, s3);
	}

	/**
	 * Length squared of a given vector
	 * 
	 * @return absolute value of the length squared of the vector
	 */
	public double lengthSquared() {
		return Math.abs(
				(coordinate.d1 * coordinate.d1) + (coordinate.d2 * coordinate.d2) + (coordinate.d3 * coordinate.d3));
	}

	/**
	 * Length of a given vector
	 * 
	 * @return square root of the length squared of the vector
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}

	/**
	 * Normalization of a vector
	 * 
	 * @return the vector normalized
	 */
	public Vector normalize() {
		return this.scale(1 / this.length());
	}
}
