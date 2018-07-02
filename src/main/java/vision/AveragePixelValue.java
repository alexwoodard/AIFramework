package vision;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class AveragePixelValue {
	private int average;

	public AveragePixelValue(WebElement element) throws AWTException {
		average = getAverage(element);
	}

	public int getAverage(WebElement element) throws AWTException {
		Robot robot = new Robot();
		Point location = element.getLocation();
		Dimension elementDimension = element.getSize();
		int rbgTotal = 0;
		int iterations = 0;
		for (int xPoint = location.x; xPoint < location.x + elementDimension.width; xPoint++) {
			for (int yPoint = location.y; yPoint < location.y + elementDimension.height; yPoint++) {
				Color pixelColor = robot.getPixelColor(xPoint, yPoint);
				int pixelViscosity = pixelColor.getBlue() + pixelColor.getGreen() + pixelColor.getRed();
				rbgTotal += pixelViscosity;
				iterations++;
			}
		}
		int pixelCount = elementDimension.height * elementDimension.width;
		int returnValue = rbgTotal / pixelCount;
		return returnValue;
	}

	public int getAverage() {
		return average;
	}
}
