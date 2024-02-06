/**
 * 
 */
package unittests.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import geometries.Geometries;
import primitives.Point;
import renderer.Camera;

/**
 * Helper class (used in the geometries and camera integration tests)
 */
public class IntegrationUtil {
	/**
	 * finds the number of intersections of camera rays given the geometries, the
	 * number of x and y pixels, then compares with the expected outcome (asserts if they're equal) 
	 *
	 * @param expectedNum - the expected number of intersections with the geometry
	 * @param camera      - the camera
	 * @param geo         - the geometries used for the intersections
	 * @param nX          - number of pixels in a row
	 * @param nY          - number of pixels in a column
	 * @param arg		  - the string meant to be sent when the number of intersections doesnt match the expected one	 */
	public static void assertIntersections(int expectedNum, Camera camera, Geometries geo, int nX, int nY, String arg) {
		int num = 0;
		for (int i = 0; i < nY; i++) {
			for (int j = 0; j < nX; j++) {
				List<Point> list = geo.findIntersections(camera.constructRay(3, 3, i, j));
				if (list != null)
					num = num + list.size();
			}
		}
		assertEquals(expectedNum, num, arg);
	}
}