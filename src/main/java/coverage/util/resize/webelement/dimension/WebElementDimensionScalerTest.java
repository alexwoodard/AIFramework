package coverage.util.resize.webelement.dimension;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.openqa.selenium.Dimension;

public class WebElementDimensionScalerTest {

	@Test
	public void test() throws IOException {

		String originalImageLocation = "images/resizer/ImageResizerTest.png";
		String newImageLocation = "images/resizer/ImageResizerTestNewImage.png";

		File originalFile = new File(originalImageLocation);
		File newFile = new File(newImageLocation);

		BufferedImage originalImage = ImageIO.read(originalFile);
		BufferedImage newImage = ImageIO.read(newFile);
		
		WebElementDimensionScaler weds = new WebElementDimensionScaler();
		Dimension originalDimension = new Dimension(100, 100);
		Dimension newDimension = weds.scaleDimension(originalDimension, originalImage, newImage);
		
		System.out.println("[DEBUG] new height:"+newDimension.height+" new width:"+newDimension.width);
	}

}
