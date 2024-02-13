/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A class representing a directional light source
 */
public class DirectionalLight extends Light implements LightSource {
	private final Vector direction;

	/**
	 * Constructor to initialize DirectionalLight based object
	 * 
	 * @param intensity - the intensity of the DirectionalLight
	 * @param direction - the direction of the DirectionalLight
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point p) {
		return this.intensity;
	}

	@Override
	public Vector getL(Point p) {
		return this.direction;
	}

	@Override
	public double getDistance(Point p) {
		return Double.POSITIVE_INFINITY;
	}
}
