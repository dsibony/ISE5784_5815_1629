/**
 * 
 */
package geometries;

/**
 * This class will serve as a basis for the geometric objects
 */
public abstract class RadialGeometry implements Geometry {
	protected double radius;

	/**
	 * setter for the radius field
	 * 
	 * @param radius number value
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
}
