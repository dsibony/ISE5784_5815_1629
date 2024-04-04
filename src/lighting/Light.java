/**
 * 
 */
package lighting;

import primitives.Color;

/**
 * An abstract class representing a general light source
 */
abstract class Light {
	/** The intensity of the light */
	protected final Color intensity;

	/**
	 * Constructor for initializing the intensity of the light
	 * 
	 * @param intensity - the intensity of the light
	 */
	protected Light(Color intensity) {
		this.intensity = intensity;
	}
	
	/**
	 * getter for intensity
	 * 
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}
}
