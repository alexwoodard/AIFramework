package component.clickable;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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

import coverage.ClickCoverage;
import coverage.CoverageAnalyzer;
import coverage.util.ElementScrollIntoViewHelper;

public class CreateScreenshotTest {
	@Test
	public void test() throws AWTException, IOException, InterruptedException {
		WebDriver driver = new ChromeDriver();
		ElementScrollIntoViewHelper esivh = new ElementScrollIntoViewHelper();
		driver.get("https://www.google.com");
		driver.manage().window().setSize(new Dimension(1000, 1000));
		synchronized (driver) {
			driver.wait(5000);
		}
		WebElement googleSubmitButton = driver.findElement(By.name("btnK"));
		int[] relativeCoordinates = esivh.scroll(driver, googleSubmitButton);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("images/CreateScreenshotTest.png"));

		BufferedImage fullPageImage = ImageIO.read(new File("./images/CreateScreenshotTest.png"));
		double ratio = (double) fullPageImage.getWidth() / fullPageImage.getHeight();
		int height = (int) (1000 / ratio);
		int width = (int) (1000 * ratio);

		BufferedImage newImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		Graphics g = newImage.createGraphics();
		g.drawImage(fullPageImage, 0, 0, width, height, null);
		g.dispose();

		BufferedImage componentImage = fullPageImage.getSubimage(relativeCoordinates[0], relativeCoordinates[1],
				googleSubmitButton.getSize().width, googleSubmitButton.getSize().height);
		File outputfile = new File("images/CreateScreenshotTest.png");
		ImageIO.write(componentImage, "png", outputfile);

		Clickable clickable = new Clickable(googleSubmitButton, driver, componentImage);
		
		ClickCoverage cc = new ClickCoverage();
		cc.addClick(clickable);
		CoverageAnalyzer ca = new CoverageAnalyzer();
		ca.generateReport(cc, newImage);
		driver.close();
		driver.quit();
	}
}