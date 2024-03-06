/**
 * 
 */
package primitives;

import java.util.List;

/**
 * This class will serve all classes who use vectors extends the Point class
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
		if (coordinates.equals(Double3.ZERO)) {
			throw new IllegalArgumentException("zero vector");
		}
	}

	/**
	 * Constructor to initialize Vector based object with its Double3 coordinate
	 * value
	 * 
	 * @param xyz - the only Double3 value needed for the Vector
	 */
	// supposed to be package permission (=no permission)
	public Vector(Double3 xyz) {
		super(xyz);
		if (xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("zero vector");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Vector other) && super.equals(obj);
	}

	@Override
	public String toString() {
		return "Vector:" + super.toString();
	}

	/**
	 * Vector addition (between 2 vectors)
	 * 
	 * @param v right hand vector, the one being added to the first vector
	 * @return the vector that is the result of summing up the vectors
	 */
	public Vector add(Vector v) {
		return new Vector(coordinates.add(v.coordinates));
	}

	/**
	 * Scales the vector by a scalar
	 * 
	 * @param rhs right hand side scalar, used to scale the vector
	 * @return the vector scaled by rhs
	 */
	public Vector scale(double rhs) {
		return new Vector(coordinates.scale(rhs));
	}

	/**
	 * Dot product between two vectors
	 * 
	 * @param v - right hand vector, the second vector in the dot product
	 * @return the dot product of the vectors
	 */
	public double dotProduct(Vector v) {
		return coordinates.d1 * v.coordinates.d1 //
				+ coordinates.d2 * v.coordinates.d2 //
				+ coordinates.d3 * v.coordinates.d3;
	}

	/**
	 * Cross product between two vectors
	 * 
	 * @param v - vector, the second vector in the cross product
	 * @return the vector that is the result of the cross product between those two
	 *         vectors
	 */
	public Vector crossProduct(Vector v) {
		double s1 = coordinates.d2 * v.coordinates.d3 - coordinates.d3 * v.coordinates.d2;
		double s2 = coordinates.d3 * v.coordinates.d1 - coordinates.d1 * v.coordinates.d3;
		double s3 = coordinates.d1 * v.coordinates.d2 - coordinates.d2 * v.coordinates.d1;
		return new Vector(s1, s2, s3);
	}

	/**
	 * Length squared of a given vector
	 * 
	 * @return absolute value of the length squared of the vector
	 */
	public double lengthSquared() {
		return (coordinates.d1 * coordinates.d1) //
				+ (coordinates.d2 * coordinates.d2) //
				+ (coordinates.d3 * coordinates.d3);
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

	/**
	 * Reduces the vector by a vector
	 * 
	 * @param direction - the direction of the vector to reduce with
	 * @return the reduced vector
	 */
	public List<Double> reduce(Vector direction) {
		return List.of(this.coordinates.d1 / direction.coordinates.d1, //
					   this.coordinates.d2 / direction.coordinates.d2, //
					   this.coordinates.d3 / direction.coordinates.d3);
	}
}
