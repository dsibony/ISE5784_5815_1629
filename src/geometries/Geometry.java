/**
 * 
 */
package geometries;

import primitives.*;

/**
 * Geometry interface used by every geometric object
 */
public abstract class Geometry extends Intersectable {
	private Material material = new Material();
	
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

	/**
	 * getter for material
	 * 
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * setter for material
	 * 
	 * @param material the material to set
	 * @return Geometry after its been updated
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;		
		return this;
	}
}
