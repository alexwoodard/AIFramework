package coverage.stitch;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.openqa.selenium.Dimension;

public class ScreenshotStitcher {

	/**
	 * 
	 * @param screenshots
	 * @param scrollHeight
	 *            the number of pixels scrolled down on each screenshot (this
	 *            could vary from image heights)
	 * @param d
	 * @return
	 * @throws Exception
	 */
	public BufferedImage stitch(ArrayList<BufferedImage> screenshots, int scrollHeight) throws Exception {
		int imageCount = screenshots.size();
		int newImageWidth = screenshots.get(0).getWidth();
		for (int i = 0; i < imageCount; i++) {
			if (screenshots.get(i).getWidth() != newImageWidth) {
				throw new Exception("[DEBUG all images must have the same width");
			}
		}

		int newImageHeight = (imageCount - 1) * scrollHeight + screenshots.get(imageCount - 1).getHeight();

		BufferedImage stichedImage = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = stichedImage.createGraphics();
		Color oldColor = g2.getColor();
		// fill background
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, newImageWidth, newImageHeight);
		// draw image
		g2.setColor(oldColor);

		for (int i = 0; i < imageCount; i++) {
			g2.drawImage(screenshots.get(i), 0, i * scrollHeight, newImageWidth - 1, i * scrollHeight + scrollHeight, 0,
					0, newImageWidth - 1, scrollHeight, null);
		}
		g2.dispose();

		return stichedImage;
	}
}