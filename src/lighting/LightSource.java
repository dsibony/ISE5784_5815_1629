/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A class which will help in representing different types of light sources
 */
public interface LightSource {
	/** 
	 *  finds the intensity of the LightSource
	 * 
	 * @param p - the intersection point with the object
	 * @return the intensity at the point
	 */
	public Color getIntensity(Point p);
	
	/**
	 * finds the vector between the LightSource and the point 
	 * 
	 * @param p - the intersection point with the object
	 * @return the normalized vector of the direction of the LightSource
	 */
	public Vector getL(Point p);
	
	/**
	 * finds the distance between the light source and the point
	 * 
	 * @param p - the point
	 * @return the distance
	 */
	public double getDistance(Point p);
	
	/**
	 * getter for radius
	 * 
	 * @return the radius
	 */
	public double getRadius();
	
	/**
	 * getter for the position
	 * 
	 * @return the position
	 */
	public Point getPosition();
}
