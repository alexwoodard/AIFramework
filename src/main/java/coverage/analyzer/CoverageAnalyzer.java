package coverage.analyzer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import component.clickable.Clickable;
import coverage.ClickCoverage;
import coverage.util.resize.webelement.coordinates.WebElementCoordinatesScaler;

public class CoverageAnalyzer {
	public void generateReport(ClickCoverage clickCoverage, BufferedImage scaledImage) throws IOException {
		System.out.println("[DEBUG] Generating Code Coverage Report");
		/**
		 * go through all clicks and highlight clicked area
		 */
		WebElementCoordinatesScaler wecs = new WebElementCoordinatesScaler();
		for (Clickable clickable : clickCoverage.allClicks) {

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