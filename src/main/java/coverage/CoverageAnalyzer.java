package coverage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import component.clickable.Clickable;
import coverage.util.resize.ImageResizer;
import coverage.util.resize.webelement.coordinates.WebElementCoordinatesScaler;
import coverage.util.resize.webelement.dimension.WebElementDimensionScaler;

public class CoverageAnalyzer {
	public void generateReport(ClickCoverage clickCoverage, BufferedImage originalImage, BufferedImage scaledImage) throws IOException {
		System.out.println("[DEBUG] Generating Code Coverage Report");
		ImageResizer ir = new ImageResizer();

		/**
		 * go through all clicks and highlight clicked area
		 */
		WebElementDimensionScaler weds = new  WebElementDimensionScaler();
		WebElementCoordinatesScaler wecs = new WebElementCoordinatesScaler();
		for (Clickable clickable : clickCoverage.allClicks) {
			Dimension scaledDimention = weds.scaleDimension(clickable.getDimension(), originalImage, scaledImage);
			Point scaledPoint = wecs.scalePoints(clickable.getPoint(), originalImage, scaledImage);

			for (int x = clickable.getPoint().x; x < clickable.getPoint().x + clickable.getDimension().width; x++) {
				for (int y = clickable.getPoint().y; y < clickable.getPoint().y + clickable.getDimension().height; y++) {
					try {
						scaledImage.setRGB(x, y, new Color(255, 0, 0).getRGB());
					} catch (Exception e) {
						System.out.println("[DEBUG] Issue highlighting pixel at x:" + x + " y:" + y);
					}
				}
			}
		}
		File outputfile = new File("heatmap.png");
		ImageIO.write(scaledImage, "png", outputfile);
	}
}