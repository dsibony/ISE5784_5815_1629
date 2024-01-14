package geometries;

import primitives.*;
import static primitives.Util.*;

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
		if (p.equals(mainRay.getHead()))
			return mainRay.getDirection();
		
		 // Project Point P onto the Axis
        double t = mainRay.getDirection().dotProduct(p.subtract(mainRay.getHead()));
        
        // Check if the point is in the base (at the center or on the circular boundary)
        if (isZero(t) || isZero(t - height))
            return mainRay.getDirection(); // normal is v

        Point projectionOnAxis = mainRay.getHead().add(mainRay.getDirection().scale(t));

        // Calculate the Normal Vector for points on the side surface
        return (p.subtract(projectionOnAxis).normalize());
	}
}
