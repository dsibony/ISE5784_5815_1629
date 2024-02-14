/**
 * 
 */
package unittests.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	/** Scene for the tests */
	private final Scene scene = new Scene("Test scene");
	/** Camera builder for the tests with triangles */
	private final Camera.Builder cameraBuilder = Camera.getBuilder()
			.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setRayTracer(new SimpleRayTracer(scene));

	/** Produce a picture of a sphere lighted by a spot light */
	@Test
	public void twoSpheres() {
		scene.geometries.add(
				new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE))
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
				.setKl(0.0004).setKq(0.0000006));

		cameraBuilder.setP0(new Point(0, 0, 1000)).setVpDistance(1000).setVpSize(150, 150)
				.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)).build().renderImage().writeToImage();
	}

	/** Produce a picture of a sphere lighted by a spot light */
	@Test
	public void twoSpheresOnMirrors() {
		scene.geometries.add(
				new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 50, 100)).setMaterial(
						new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(new Double3(0.5, 0, 0))),
				new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20))
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000))
						.setEmission(new Color(20, 20, 20)).setMaterial(new Material().setKr(1)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)).setEmission(new Color(20, 20, 20))
						.setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4))
				.setKl(0.00001).setKq(0.000005));

		cameraBuilder.setP0(new Point(0, 0, 10000)).setVpDistance(10000).setVpSize(2500, 2500)
				.setImageWriter(new ImageWriter("reflectionTwoSpheresMirrored", 500, 500)).build().renderImage()
				.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		scene.geometries.add(
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
				new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));
		scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)).setKl(4E-5)
				.setKq(2E-7));

		cameraBuilder.setP0(new Point(0, 0, 1000)).setVpDistance(1000).setVpSize(200, 200)
				.setImageWriter(new ImageWriter("refractionShadow", 600, 600)).build().renderImage().writeToImage();
	}

//	/**
//	 * Produce a complicated picture of spheres lighted by a spot light
//	 */
//	@Test
//	public void threeObjectsPicture() {
//		scene.geometries.add(
//				new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(GRAY))
//						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
//				new Sphere(new Point(0, -12, -50), 10d).setEmission(new Color(RED))
//						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
//				new Sphere(new Point(10, 15, 10), 15d).setEmission(new Color(YELLOW))
//						.setMaterial(new Material().setKd(0.1).setKs(0.3).setShininess(50).setKt(0.6)),
//				new Sphere(new Point(-10, 15, 10), 15d).setEmission(new Color(YELLOW))
//						.setMaterial(new Material().setKd(0.1).setKs(0.3).setShininess(50).setKt(0.6)),
//				new Triangle(new Point(-30, -37, -40), new Point(30, -37, -50), new Point(0, -25, -45))
//						.setEmission(new Color(ORANGE))
//						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.3)),
//				new Triangle(new Point(22, 28, 10), new Point(4, 32, 10), new Point(12, 37, 10))
//						.setEmission(new Color(165, 42, 42))
//						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.3)),
//				new Triangle(new Point(-22, 28, 10), new Point(-4, 32, 10), new Point(-12, 37, 10))
//						.setEmission(new Color(165, 42, 42))
//						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.3)));
//		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
//				.setKl(0.001).setKq(0.000006));
//
//		cameraBuilder.setP0(new Point(0, 0, 1000)).setVpDistance(1000).setVpSize(150, 150)
//				.setImageWriter(new ImageWriter("faceObjectsPicture", 500, 500)).build().renderImage()
//				.writeToImage();
//	}
	/**
	 * Produce a complicated picture of spheres lighted by a spot light (Bonus)
	 */
	@Test
	public void threeObjectsPicture() {
		scene.geometries.add(
				new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(GRAY))
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.35)),
				new Sphere(new Point(0, -12, -30), 10d).setEmission(new Color(RED))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				new Sphere(new Point(10, 15, -30), 15d).setEmission(new Color(YELLOW))
						.setMaterial(new Material().setKd(0.1).setKs(0.3).setShininess(50).setKt(0.4)),
				new Sphere(new Point(-10, 15, -30), 15d).setEmission(new Color(YELLOW))
						.setMaterial(new Material().setKd(0.1).setKs(0.3).setShininess(50).setKt(0.4)),
				new Sphere(new Point(100, 15, 50), 5d).setEmission(new Color(30, 30, 30))
						.setMaterial(new Material().setKd(0.1).setKs(0.3).setShininess(50).setKt(0.6)),
				new Sphere(new Point(120, 15, 50), 5d).setEmission(new Color(30, 30, 30))
						.setMaterial(new Material().setKd(0.1).setKs(0.3).setShininess(50).setKt(0.6)),
				new Triangle(new Point(-30, -37, -30), new Point(30, -37, -40), new Point(0, -25, -35))
						.setEmission(new Color(ORANGE))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.4)),
				new Triangle(new Point(22, 28, -30), new Point(4, 32, -30), new Point(12, 37, -30))
						.setEmission(new Color(165, 42, 42))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.4)),
				new Triangle(new Point(-22, 28, -30), new Point(-4, 32, -30), new Point(-12, 37, -30))
						.setEmission(new Color(165, 42, 42))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.4)),
				new Sphere(new Point(-200, 0, -30), 50d).setEmission(new Color(20, 20, 20))
						.setMaterial(new Material().setKr(1)),
				new Plane(new Point(-200, 0, -30), new Vector(-1.5, 0, -1)).setEmission(new Color(20, 20, 20))
						.setMaterial(new Material().setKr(1)));
		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-200, -100, 500), new Vector(-1, -1, -2))
				.setKl(0.001).setKq(0.000006));

		cameraBuilder.setP0(new Point(1002, 0, 668)).setVpDistance(800).setVpSize(150, 150)
				.setDirection(new Vector(-1.5, 0, -1), new Vector(0, 1, 0))
				.setImageWriter(new ImageWriter("complicateObjectsPicture", 1000, 1000)).build().renderImage()
				.writeToImage();
	}
}
