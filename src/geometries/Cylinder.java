package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * This class will be used as a cylinder, a geometric object
 */
public class Cylinder extends Tube {
	private final double height;

	/**
	 * Constructor to initialize Cylinder based object with its two number values and one Ray value
	 * 
	 * @param mainRay, Ray value
	 * @param radius value
	 * @param height value
	 */
	public Cylinder(Ray mainRay, double radius, double height) {
		super(mainRay, radius);
		this.height = height;
	}

	@Override
	public Vector getNormal(Point p) {
		
		// bottom base check
		if (p.equals(mainRay.head))
			return mainRay.direction;
		
		 // Project Point P onto the Axis
        double t = mainRay.direction.dotProduct(p.subtract(mainRay.head));
        
        // Check if the point is in the base (at the center or on the circular boundary)
        if (t <= 0 || t >= height)
            return mainRay.direction; // normal is v

        Point projectionOnAxis = mainRay.head.add(mainRay.direction.scale(t));

        // Calculate the Normal Vector for points on the side surface
        return (p.subtract(projectionOnAxis).normalize());
	}
}
