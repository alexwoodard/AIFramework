package component.clickable;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import component.AbstractComponent;
import coverage.util.ElementScrollIntoViewHelper;

public class Clickable extends AbstractComponent{


	public Clickable(WebElement webElement,  WebDriver driver, BufferedImage componentImage) 
			throws AWTException, IOException{
		image = componentImage;
		
//		this.imageName=imageName;
//		dimension = webElement.getSize();
		point = webElement.getLocation();
		text = webElement.getText();
//		size = dimension.height * dimension.width;
		x = point.x;
		y = point.y;
		href = webElement.getAttribute("href");
//		this.relativeX= relativeCoordinates[0];
//		this.relativeY= relativeCoordinates[1];
		
		ElementScrollIntoViewHelper esivh = new ElementScrollIntoViewHelper();

			/**
			 * scroll to the top of the page first then scroll the clickable
			 * into view
			 */
//			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//			File scrFile = webElement.getScreenshotAs(OutputType.FILE);
//			FileUtils.copyFile(scrFile, new File(imageName));
//			System.out.println("[DEBUG] Created component image: "+imageName);
			// Actions actions = new Actions(driver);
			// actions.moveToElement(webElement);
			// actions.perform();
			//
			// File scrFile =
			// ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// // Now you can do whatever you need to do with it, for example
			// copy somewhere
			// FileUtils.copyFile(scrFile, new File(imageName));
			//
			// componentImage = new BufferedImage(dimension.width,
			// dimension.height, BufferedImage.TYPE_INT_RGB);
			//
			// Robot robot = new Robot();
			// int relativeX = 0;
			// int relativeY = 0;
			// for (int xPoint = x; xPoint < x + dimension.width; xPoint++) {
			// for (int yPoint = 0; yPoint < dimension.height; yPoint++) {
			// Color pixelColor = robot.getPixelColor(xPoint, yPoint);
			// componentImage.setRGB(relativeX, relativeY, pixelColor.getRGB());
			// relativeY++;
			// }
			// relativeX++;
			// relativeY = 0;
			// }
			//
			// File outputfile = new File(imageName);
			// ImageIO.write(componentImage, "jpg", outputfile);
			//
			// System.out.println("[DEBUG] Set rgb values for component at x:" +
			// x + " y:" + y);
		
	}
}