package coverage.util.resize.webelement.coordinates;

import java.awt.image.BufferedImage;

import org.openqa.selenium.Point;

public class WebElementCoordinatesScaler {

	public Point scalePoints(Point originalPoint, BufferedImage originalImage, BufferedImage newImage){
		
		
//		int originalX = originalPoint.x;
//		int originalY = originalPoint.y;
		double xScalerNom = newImage.getWidth();
		double xScalerDenom = originalImage.getWidth();
		double xScaler = xScalerNom/xScalerDenom;

		double yScalerNom = newImage.getHeight();
		double yScalerDenom = originalImage.getHeight();
		double yScaler = yScalerNom/yScalerDenom;
		
		int newX = (int) (originalPoint.x*xScaler);
		int newY = (int) (originalPoint.y*yScaler);

		Point newPoint = new Point(newX, newY);
		return newPoint;
		
	}
}