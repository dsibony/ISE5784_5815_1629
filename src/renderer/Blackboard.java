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
 * 
 */
public class Blackboard {
	private Point p0;
	private Point pIJ;
	private Vector vRight;
	private Vector vUp;
	private Vector vTo;
	private double radius;

	public Blackboard(LightSource light, Vector l) {
		p0 = light.getPosition();
		radius = light.getRadius();
		pIJ = p0;
		vTo = l;
//		vUp = vTo.crossProduct(new Vector(1,2,3)).normalize();
		double d = -(vTo.getD1() * p0.getD1() + vTo.getD2() * p0.getD2() + vTo.getD3() * p0.getD3());
		double z = -(vTo.getD1()*(p0.getD1()+1) + vTo.getD2()*(p0.getD2()+1) + d) / vTo.getD3();
		//what if d3 == 0
		vUp = (p0.subtract(new Point(p0.getD1()+1, p0.getD2()+1, z))).normalize();
		vRight = vUp.crossProduct(vTo).normalize();
	}

	public Point constructPoint(int n, int j, int i, LightSource light, Vector l) {
		double r = radius * 2 / (double) n;
		double xJ = r * ((double) j - ((double) n - 1) / 2);
		double yI = r * -((double) i - ((double) n - 1) / 2);

		pIJ = p0;
		if (!isZero(xJ))
			pIJ = pIJ.add(vRight.scale(xJ));
		if (!isZero(yI))
			pIJ = pIJ.add(vUp.scale(yI));
		return (alignZero(radius - light.getDistance(pIJ)) > 0 ? pIJ : null);
	}

	public List<Point> getShadowPoints(LightSource light, Vector l, int shadowRaysNum) {
		List<Point> list = new ArrayList<Point>();
		int n = (int) (Math.sqrt(shadowRaysNum)); // save the sqrt number in order to not do sqrt every time
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Point p = constructPoint(n, j, i, light, l);
				if (p != null)
					list.add(p);
			}
		}
		return list;
	}
}
