package coverage.util.resize.webelement.dimension;

import java.awt.image.BufferedImage;

import org.openqa.selenium.Dimension;

public class WebElementDimensionScaler {

	public Dimension scaleDimension(Dimension originalDimension,BufferedImage originalImage, BufferedImage newImage){
		double heightScalerNom = newImage.getHeight();
		double heightScalerDenom = originalImage.getHeight();
		double newHeightScaler = heightScalerNom/heightScalerDenom;
		
		double widthScalerNom = newImage.getWidth();
		double widthScalerDenom = originalImage.getWidth();
		double newWidthScaler = widthScalerNom/widthScalerDenom;
		
		int newWidth = (int) (originalDimension.width*newWidthScaler);
		int newHeight = (int) (originalDimension.height*newHeightScaler);
		return new Dimension(newWidth,newHeight);
	}
}