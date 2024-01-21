/**
 * 
 */
package geometries;

/**
 * This class will serve as a basis for the geometric objects
 */
public abstract class RadialGeometry implements Geometry {
	protected final double radius;
	protected final double radiusSquared;

	
	/**
	 * Constructor for initializing the radius
	 * @param radius - the radius of the RadialGeomertry
	 */
	protected RadialGeometry(double radius) {
		this.radius = radius;
		radiusSquared = radius * radius;
	}
	
	
}
