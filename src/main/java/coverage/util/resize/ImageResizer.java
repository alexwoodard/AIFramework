package coverage.util.resize;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageResizer {

	public BufferedImage resize(String screenshot, int newWidth) throws IOException{
		BufferedImage fullPageImage = ImageIO.read(new File(screenshot));
		int originalWidth = fullPageImage.getWidth();
		int originalHeight = fullPageImage.getHeight();
		System.out.println("[DEBUG] original image size: height - "+originalHeight+" width - "+originalWidth);
		System.out.println("[DEBUG] proposed width - "+newWidth);
		double nom = newWidth;
		double denom = originalWidth;
		double scaler = nom/denom;
		System.out.println("[DEBUG] scaler is: "+scaler);
		int newHeight = (int) (originalHeight*scaler);

		System.out.println("[DEBUG] new image size: height - "+newHeight+" width - "+newWidth);
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = newImage.createGraphics();
		g.drawImage(fullPageImage, 0, 0, newWidth, newHeight, null);
		g.dispose();
		return newImage;
	}
}
