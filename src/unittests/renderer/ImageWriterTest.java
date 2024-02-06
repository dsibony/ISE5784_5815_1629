/**
 * 
 */
package unittests.renderer;


import org.junit.jupiter.api.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * Tests the imageWriter class (for learning purposes)
 */
class ImageWriterTest {
	/**
	 * Test method for imageWriter
	 */
	@Test
	void testImageWriter() {
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
