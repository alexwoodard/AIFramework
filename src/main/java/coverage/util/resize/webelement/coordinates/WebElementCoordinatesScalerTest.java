package coverage.util.resize.webelement.coordinates;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.openqa.selenium.Point;

public class WebElementCoordinatesScalerTest {

	@Test
	public void test() throws IOException {
		String originalImageLocation = "images/resizer/ImageResizerTest.png";
		String newImageLocation = "images/resizer/ImageResizerTestNewImage.png";
		
		File originalFile = new File(originalImageLocation);
		File newFile = new File(newImageLocation);

		BufferedImage originalImage = ImageIO.read(originalFile);
		BufferedImage newImage = ImageIO.read(newFile);
		
		WebElementCoordinatesScaler wecs = new WebElementCoordinatesScaler();
		
		Point originalPoint = new Point(100, 100);
		Point newPoint = wecs.scalePoints(originalPoint, originalImage, newImage);
		System.out.println("[DEBUG] new point x: "+newPoint.x+ " new point y: "+newPoint.y);
	}

}
