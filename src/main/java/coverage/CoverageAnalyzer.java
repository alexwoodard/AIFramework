package coverage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import component.clickable.Clickable;

public class CoverageAnalyzer {
	public void generateReport(ClickCoverage clickCoverage, BufferedImage fullPageImage) throws IOException {
		System.out.println("[DEBUG] Generating Code Coverage Report");

		// BufferedImage coverageImage = new
		// BufferedImage(fullPageImage.getWidth(),fullPageImage.getHeight(),BufferedImage.TYPE_INT_ARGB);
		/**
		 * go through all clicks and highlight clicked area
		 */
		for (Clickable clickable : clickCoverage.allClicks) {
			BufferedImage componentImage = clickable.getImage();
			Point clickablePoint = clickable.getPoint();
			int componentWidth = componentImage.getWidth();
			int componentHeight = componentImage.getHeight();

			System.out
					.println("Full image height: " + fullPageImage.getHeight() + " width: " + fullPageImage.getWidth());

			for (int x = clickablePoint.x; x < clickablePoint.x + componentWidth; x++) {
				for (int y = clickablePoint.y; y < clickablePoint.y + componentHeight; y++) {
					try {
						fullPageImage.setRGB(x, y, new Color(255, 0, 0).getRGB());
					} catch (Exception e) {
						System.out.println("[DEBUG] Issue highlighting pixel at x:" + x + " y:" + y);
					}
				}
			}
		}
		File outputfile = new File("heatmap.png");
		ImageIO.write(fullPageImage, "png", outputfile);
	}
}