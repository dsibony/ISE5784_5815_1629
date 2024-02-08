/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A class representing a spot light source
 */
public class SpotLight extends PointLight {
	private Vector direction;
	private double narrowBeam = 1;

	/**
	 * Constructor to initialize SpotLight based object
	 * 
	 * @param intensity - the intensity of the SpotLight
	 * @param position  - the position of the SpotLight
	 * @param direction - the direction of the SpotLight
	 */
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
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
		return super.getIntensity(p).scale(Math.pow(Math.max(0, this.direction.dotProduct(this.getL(p))),narrowBeam));
	}

	@Override
	public Vector getL(Point p) {
		return super.getL(p);
	}

	/**
	 * setter for narrowBeam
	 * 
	 * @param narrowBeam - the new narrowBeam value
	 * @return SpotLight after its been updated
	 */
	public SpotLight setNarrowBeam(double narrowBeam) {
		this.narrowBeam = narrowBeam;
		return this;
	}
}
