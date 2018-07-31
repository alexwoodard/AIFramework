package coverage.util.resize.webelement.dimension;

import java.awt.image.BufferedImage;

import org.openqa.selenium.Dimension;

public class WebElementDimensionScaler {

	public Dimension scaleDimension(Dimension originalDimension,BufferedImage originalImage, BufferedImage newImage){
		double newHeightScaler = newImage.getHeight()/originalImage.getHeight();
		double newWidthScaler = newImage.getWidth()/newImage.getWidth();
		return new Dimension(newWidth, newHeight);
	}
}