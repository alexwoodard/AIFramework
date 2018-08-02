package coverage.stitch;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScreenshotStitcherTest {

	@Test
	public void test() throws Exception {
		ArrayList<BufferedImage> screenshotImages = new ArrayList<BufferedImage>();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.godaddy.com/websites/website-builder");
		driver.manage().window().setSize(new Dimension(1000,1000));
		
		int pageHeight = driver.findElement(By.xpath("//body")).getSize().height;
		int numberOfScrolls = pageHeight / 1000;
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		for(int i = 1; i <= numberOfScrolls; i++){
			jse.executeScript("scroll(0, "+1000*i+");");
			String screenshotName = "Screenshot"+i;

			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("images/stitcher/"+screenshotName+".png"));
			screenshotImages.add(ImageIO.read(screenshot));
		}
		BufferedImage stiched = new ScreenshotStitcher().stitch(screenshotImages, 1000);

		File outputfile = new File("images/stitcher/stichedImage.png");
		ImageIO.write(stiched, "png", outputfile);
		driver.close();
		driver.quit();

	}

}
