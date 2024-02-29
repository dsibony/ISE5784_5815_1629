/**
 * 
 */
package renderer;

import static primitives.Util.*;

import java.util.ArrayList;
import java.util.List;

import lighting.LightSource;
import primitives.*;

/**
 * A class to calculate the grid on the light relative to a specific point
 */
public class Blackboard {
	private final Point p0;
	private final Vector vRight;
	private final Vector vUp;
	private final Vector vTo;
	private final LightSource light;
	private final double radius;
	private final int sqrtShadowRaysNum;

	/**
	 * Constructor to initialize the blackboard
	 * 
	 * @param light - the light source
	 * @param l - the vector from the light to the point
	 * @param sqrtShadowRaysNum - the square root of the amount of the shadow rays
	 */
	public Blackboard(LightSource light, Vector l, int sqrtShadowRaysNum) {
		this.light = light;
		this.p0 = light.getPosition();
		this.radius = light.getRadius();
		this.sqrtShadowRaysNum = sqrtShadowRaysNum;
		this.vTo = l;
		this.vUp = (this.p0.subtract(new Point(this.p0.getD1() + 1, this.p0.getD2() + 1, //
				isZero(this.vTo.getD3()) ? 1 : //
						-(this.vTo.getD1() * (this.p0.getD1() + 1) + this.vTo.getD2() * (this.p0.getD2() + 1) //
						-(this.vTo.getD1() * this.p0.getD1() + this.vTo.getD2() * this.p0.getD2() + this.vTo.getD3() * this.p0.getD3())) //
						/ this.vTo.getD3())))
						.normalize();
		this.vRight = this.vUp.crossProduct(this.vTo).normalize();
	}

	/**
	 * A method to construct a point in a grid structure on the light
	 * 
	 * @param j - the x location of the grid pixel
	 * @param i - the y location of the grid pixel
	 * @return the grid point
	 */
	public Point constructPoint(int j, int i) {
		double r = this.radius * 2 / (double) this.sqrtShadowRaysNum;
		double xJ = r * ((double) j - ((double) this.sqrtShadowRaysNum - 1) / 2);
		double yI = r * -((double) i - ((double) this.sqrtShadowRaysNum - 1) / 2);

		Point pIJ = this.p0;
		if (!isZero(xJ))
			pIJ = pIJ.add(this.vRight.scale(xJ));
		if (!isZero(yI))
			pIJ = pIJ.add(this.vUp.scale(yI));
		return (alignZero(this.radius - this.light.getDistance(pIJ)) > 0 ? pIJ : null);
	}

	/**
	 * creates the grid by calculating the points on the light
	 *
	 * @return the list of the grid points
	 */
	public List<Point> createGrid() {
		List<Point> list = new ArrayList<Point>();
		for (int i = 0; i < this.sqrtShadowRaysNum; i++) {
			for (int j = 0; j < this.sqrtShadowRaysNum; j++) {
				Point p = constructPoint(j, i);
				if (p != null)
					list.add(p);
			}
		}
		return list;
	}
}
