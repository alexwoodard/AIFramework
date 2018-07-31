package coverage.util.resize;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImageResizerTest {

	@Test
	public void test() throws IOException {
		ImageResizer ir = new ImageResizer();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		driver.manage().window().setSize(new Dimension(1000, 1000));
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		driver.close();
		driver.quit();
		FileUtils.copyFile(scrFile, new File("images/resizer/ImageResizerTest.png"));
		
		BufferedImage newImage = ir.resize("images/resizer/ImageResizerTest.png", 1000);
		System.out.println("[DEBUG] new image size: height - "+newImage.getHeight()+" width - "+newImage.getWidth());
		File outputfile = new File("images/resizer/ImageResizerTestNewImage.png");
		ImageIO.write(newImage, "png", outputfile);
		
	}

}
