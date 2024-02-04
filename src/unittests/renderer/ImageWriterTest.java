/**
 * 
 */
package unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * 
 */
class ImageWriterTest {

	@Test
	void test() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: regular image
		ImageWriter image = new ImageWriter("test", 800, 500);
		Color yellow = new Color(255, 255, 0);
		Color red = new Color(255, 0, 0);
		for (int i = 0; i < 800; i++) {
			for (int j = 0; j < 500; j++) {
				if (i % 50 == 0 || j % 50 == 0)
					image.writePixel(i, j, red);
				else
					image.writePixel(i, j, yellow);
			}
		}
		image.writeToImage();
	}

}
