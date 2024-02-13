/**
 * 
 */
package lighting;

import primitives.*;
import static primitives.Util.*;

/**
 * A class representing a spot light source
 */
public class SpotLight extends PointLight {
	private final Vector direction;
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
	public Color getIntensity(Point p) {
		var lDir = this.direction.dotProduct(this.getL(p));
		return alignZero(lDir) <= 0 ? Color.BLACK //
				: super.getIntensity(p).scale(Math.pow(lDir, narrowBeam));
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
