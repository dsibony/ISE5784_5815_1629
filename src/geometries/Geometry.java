/**
 * 
 */
package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Geometry interface used by every geometric object
 */
public abstract class Geometry extends Intersectable {
	
	/** The emission (initialized to black) */
	protected Color emission = Color.BLACK;
	
	/** 
	 * getter for emission
	 * 
	 * @return the emission
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * setter for emission
	 * 
	 * @param emission -  the emission to set
	 * @return Geometry after its been updated
	 */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}

	/**
	 * get normal method, used for getting the normal vector of a surface point in a given geometric object
	 * 
	 * @param p, given point
	 * @return the normal vector
	 */
	public abstract Vector getNormal(Point p);
}
