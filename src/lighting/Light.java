/**
 * 
 */
package lighting;

import primitives.Color;

/**
 * 
 */
abstract class Light {
	/** The intensity of the light */
	protected Color intensity;

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
