package coverage;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import component.clickable.Clickable;
import coverage.util.resize.ImageResizer;

public class CoverageAnalyzerTest {
	@Test
	public void test() throws AWTException, IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		driver.manage().window().setSize(new Dimension(1000,1000));
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("images/coverage/CoverageAnalyzerTest.png"));
		
		WebElement googleSubmitButton = driver.findElement(By.name("btnK"));
		Clickable clickable = new Clickable(googleSubmitButton);

		ImageResizer ir = new ImageResizer();
		BufferedImage newImage = ir.resize("images/coverage/CoverageAnalyzerTest.png", 1000);
		File outputfile = new File("images/coverage/CoverageAnalyzerTestNewImage.png");
		ImageIO.write(newImage, "png", outputfile);
		
		ClickCoverage cc = new ClickCoverage();
		cc.addClick(clickable);
		CoverageAnalyzer ca = new CoverageAnalyzer();
		try {
			String originalImageLocation = "images/coverage/CoverageAnalyzerTest.png";
			String newImageLocation = "images/coverage/CoverageAnalyzerTestNewImage.png";

			File originalFile = new File(originalImageLocation);
			File newFile = new File(newImageLocation);

			BufferedImage originalImage = ImageIO.read(originalFile);
			BufferedImage scaledImage = ImageIO.read(newFile);
			ca.generateReport(cc, originalImage, scaledImage);
		} catch (Exception e) {
			driver.close();
			driver.quit();
			e.printStackTrace();
		}
		driver.close();
		driver.quit();
	}
}