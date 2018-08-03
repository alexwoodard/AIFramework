package coverage.fullpage;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import chrome.ChromeDriverEx;
import coverage.util.resize.ImageResizer;

public class FullPageScreenshotBuilder {
	public void build(ChromeDriverEx driver, String imageSaveLocation, int imageWidth) throws Exception{
		File file = driver.getFullScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(imageSaveLocation));
		ImageResizer ir = new ImageResizer();
		BufferedImage resized = ir.resize(imageSaveLocation, imageWidth);
		File resizedOutputfile = new File(imageSaveLocation);
		ImageIO.write(resized, "png", resizedOutputfile);
	}
}