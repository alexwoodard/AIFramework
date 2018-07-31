package coverage.util;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementScrollIntoViewHelper {

	public int[] scroll(WebDriver driver, WebElement element) {
		/**
		 * not selenium coordinates top left coordinates on the visible browser
		 */
		int[] relativeCoordinates = new int[2];

		/**
		 * 1) Get viewport size 2) Move to the top of the page 3) Get the
		 * location of the element 4) Get dimension of the element
		 */
		Dimension viewportDimension = driver.manage().window().getSize(); // 1
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); // 2
		Point elementPoint = element.getLocation(); // 3
		Dimension elementDimension = element.getSize();
		/**
		 * check if the element is fully within view if so then just return
		 * relativeCoordinates as the absolute coordinates
		 */
		relativeCoordinates[0] = elementPoint.x;
		if (elementPoint.getY() + elementDimension.height <= viewportDimension.height) {
			relativeCoordinates[1] = elementPoint.y;
		} else {
			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			actions.perform();
			/**
			 * find the distance below the element
			 */
			int bodyHeight = driver.findElement(By.xpath(".//body")).getSize().height;
			int distanceBelowElement = bodyHeight - (elementPoint.y + elementDimension.height);
			/**
			 * if the distanceBelowElement is less then the viewport height, the
			 * element is at the top of the screen
			 */
			if (distanceBelowElement <= viewportDimension.height) {
				relativeCoordinates[1] = 0;
			} else {
				int yValue = viewportDimension.height - distanceBelowElement - elementDimension.height;
				relativeCoordinates[1] = yValue;
			}
		}

		return relativeCoordinates;

	}
}