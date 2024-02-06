/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * 
 */
public class SpotLight extends PointLight {
	private Vector direction;

	/**
	 * Constructor to initialize SpotLight based object
	 * 
	 * @param intensity - the intensity of the SpotLight
	 * @param position  - the position of the SpotLight
	 * @param direction - the direction of the SpotLight
	 */
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction;
	}

	@Override
	public SpotLight setkC(double kC) {
		super.setkC(kC);
		return this;
	}

	public SpotLight setkL(double kL) {
		super.setkL(kL);
		return this;
	}

	public SpotLight setkQ(double kQ) {
		super.setkQ(kQ);
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		return this.intensity;
	}

	@Override
	public Vector getL(Point p) {
		return this.direction.normalize();
	}
}
