/**
 * 
 */
package unittests.special;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * 
 */
class BoundaryVolumeHierarchy {

	// Generate a point on the shell surface based on radius, theta, and layer
	private Point generatePoint(double radius, double theta, int layer) {
		double x = radius * Math.cos(theta);
		double y = radius * Math.sin(theta);
		double z = layer * 0.1; // Adjust height for each layer
		return new Point(x, y, z);
	}

	private final ImageWriter imageWriter = new ImageWriter("bvhTest", 800, 800);

	/** Scene of the tests */
	private final Scene scene = new Scene("Test scene");
	/** Camera builder of the tests */
	private final Camera.Builder camera = Camera.getBuilder().setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
			.setP0(new Point(0, 0, 1000)).setVpDistance(1000).setVpSize(200, 200).setImageWriter(imageWriter)
			.setRayTracer(new SimpleRayTracer(scene)).setMultithreading(3).setDebugPrint(0.1);

	private static Color color = new Color(0, 0, 200);
	private static final Material mat = new Material().setKd(0.5).setKs(0.5).setShininess(60);

	/**
	 * Produce a complicated picture that consists of ten shapes
	 */
	@Test
	public void theComplicatedPicture() {
		Geometries geometries = new Geometries();
		int pointsPerLayer = 360;
		int numLayers = 10;
		double radius = 50d;
		double thetaIncrement = 2 * Math.PI / pointsPerLayer;

		// Generate points for each layer
		for (int layer = 0; layer < numLayers; layer++) {
			double currentRadius = radius * (1 + layer * 0.1); // Adjust radius for each layer

			for (int i = 0; i < pointsPerLayer; i++) {
				double theta1 = i * thetaIncrement;
				double theta2 = (i + 1) * thetaIncrement;

				// Generate points for two adjacent vertices on the current layer
				Point p1 = generatePoint(currentRadius, theta1, layer);
				Point p2 = generatePoint(currentRadius, theta2, layer);

				// Generate points for two adjacent vertices on the next layer
				Point p3 = generatePoint(currentRadius + 0.1 * radius, theta1, layer + 1);
				Point p4 = generatePoint(currentRadius + 0.1 * radius, theta2, layer + 1);

				// Create triangles between these points
				geometries.add(new Triangle(p1, p2, p3).setEmission(color).setMaterial(mat));
				geometries.add(new Triangle(p2, p4, p3).setEmission(color).setMaterial(mat));
			}
		}
		scene.setGeometries(geometries);
		scene.lights.add(new PointLight(new Color(500, 500, 500), new Point(100, 0, -100)).setKq(0.000001));
		scene.geometries.setBoundaryRegionSwitch(true);
		scene.geometries.boundaryRegionSwitchAll(true);
		scene.geometries.KMeansClustering(20);
		camera.setRayTracer(new SimpleRayTracer(scene)).build().renderImage().printGrid(50, new Color(YELLOW))
				.writeToImage();
	}

	@Test
	public void BVHPicture() {
		Geometries geometries = new Geometries();
		int pointsPerLayer = 360;
		int numLayers = 10;
		double radius = 50d;
		double thetaIncrement = 2 * Math.PI / pointsPerLayer;

		// Generate points for each layer
		for (int layer = 0; layer < numLayers; layer++) {
			double currentRadius = radius * (1 + layer * 0.1); // Adjust radius for each layer

			for (int i = 0; i < pointsPerLayer; i++) {
				double theta1 = i * thetaIncrement;
				double theta2 = (i + 1) * thetaIncrement;

				// Generate points for two adjacent vertices on the current layer
				Point p1 = generatePoint(currentRadius, theta1, layer);
				Point p2 = generatePoint(currentRadius, theta2, layer);

				// Generate points for two adjacent vertices on the next layer
				Point p3 = generatePoint(currentRadius + 0.1 * radius, theta1, layer + 1);
				Point p4 = generatePoint(currentRadius + 0.1 * radius, theta2, layer + 1);

				geometries.add(new Sphere(p1, 3d).setEmission(color).setMaterial(mat));
				geometries.add(new Sphere(p2, 3d).setEmission(color).setMaterial(mat));
				geometries.add(new Sphere(p3, 3d).setEmission(color).setMaterial(mat));
				geometries.add(new Sphere(p4, 3d).setEmission(color).setMaterial(mat));
			}
		}
		camera.setImageWriter(new ImageWriter("BVHpic", 800, 800));
		scene.setGeometries(geometries);
		scene.lights.add(new PointLight(new Color(500, 500, 500), new Point(100, 0, -100)).setKq(0.000001));
//		scene.geometries.setBoundaryRegionSwitch(true);
//		scene.geometries.boundaryRegionSwitchAll(true);
//		scene.geometries.KMeansClustering(20);
		camera.setRayTracer(new SimpleRayTracer(scene)).build().renderImage().printGrid(50, new Color(YELLOW))
				.writeToImage();
	}
}