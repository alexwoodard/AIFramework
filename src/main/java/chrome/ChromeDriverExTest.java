package chrome;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeOptions;

import coverage.util.resize.ImageResizer;

public class ChromeDriverExTest {
	@Test
	public void test() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("disable-infobars");

		ChromeDriverEx driver = new ChromeDriverEx(options);

		driver.get("https://godaddy.com");

		synchronized (driver) {
			driver.wait(3000);
		}

		File file = driver.getFullScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(file, new File("fullpage.png"));

		driver.close();
		driver.quit();
		ImageResizer ir = new ImageResizer();
		BufferedImage resized = ir.resize("fullpage.png", 1000);

		File resizedOutputfile = new File("fullpage.png");
		ImageIO.write(resized, "png", resizedOutputfile);
	}
}