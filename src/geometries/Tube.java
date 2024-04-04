/**
 * 
 */
package geometries;

import java.util.List;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * This class will be used as a Tube, a geometric object
 */
public class Tube extends RadialGeometry {
	/** The ray of the tube */
	protected final Ray mainRay;

	/**
	 * Constructor to initialize Tube based object with its Ray and number values
	 * 
	 * @param mainRay Ray value
	 * @param radius  number value
	 */
	public Tube(Ray mainRay, double radius) {
		super(radius);
		this.mainRay = mainRay;
	}

	@Override
	public Vector getNormal(Point p) {
		// Project Point P onto the Axis
        double t = mainRay.getDirection().dotProduct(p.subtract(mainRay.getHead()));
        Point projectionOnAxis = mainRay.getHead().add(mainRay.getDirection().scale(t));
        
        if (projectionOnAxis.equals(p))
        	return mainRay.getDirection();
        
        // Calculate the Normal Vector
        return p.subtract(projectionOnAxis).normalize();
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		return null;
	}

	@Override
	protected void updateRegion(Point minPoint, Point maxPoint) {}
}
