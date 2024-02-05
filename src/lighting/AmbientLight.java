/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class used for taking the ambient light into account when calculating the color of an object
 */
public class AmbientLight {
	private final Color intensity;

	/** no light */
	public static final AmbientLight NONE = new AmbientLight(Color.BLACK, 0);
	
	/**
	 * Constructor to initialize AmbientLight based object by calculating its intensity
	 * 
	 * @param iA - the intensity
	 * @param kA - the color
	 */
	public AmbientLight(Color iA, Double3 kA) {
		intensity = iA.scale(kA);
	}

	/**
	 * Constructor to initialize AmbientLight based object by calculating its intensity
	 * 
	 * @param iA - the intensity
	 * @param kA - the color
	 */
	public AmbientLight(Color iA, double kA) {
		intensity = iA.scale(kA);
	}

	/**
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}

}
