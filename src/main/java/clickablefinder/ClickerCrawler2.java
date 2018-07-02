package clickablefinder;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickerCrawler2 {
	public void search(WebDriver driver) throws InterruptedException {
		String currentUrl = driver.getCurrentUrl();
		ArrayList<Point> points = new ArrayList<Point>();
		for (WebElement element : getAllClickableElements(driver)) {
			if (elementIsVisible(element)) {
				Point point = element.getLocation();
				points.add(point);
				System.out.println("[DEBUG] Added a point");
			}
		}
		for (Point p : points) {
			Boolean clicked = false;
			synchronized (driver) {
				driver.get(currentUrl);
				driver.wait(3000);
			}
			for (WebElement element : getAllClickableElements(driver)) {
				if (element.getLocation().x == p.x && element.getLocation().y == p.y) {
					element.click();
					System.out.println("[DEBUG] Clicked on " + p.x + " " + p.y);
					clicked = true;
					break;
				}
			}
			if (!clicked) {
				System.out.println("[DEBUG] Could not click on " + p.x + " " + p.y);
			}
			clicked = false;
		}
	}

	private List<WebElement> getAllClickableElements(WebDriver driver) {
		List<WebElement> visibleClickables = new ArrayList<WebElement>();
		List<WebElement> clickables = new ArrayList<WebElement>();
		clickables.addAll(driver.findElements(By.xpath(".//a")));
		clickables.addAll(driver.findElements(By.xpath(".//button")));
		for (WebElement element : clickables) {
			if (elementIsVisible(element)) {
				visibleClickables.add(element);
			}
		}
		return visibleClickables;
	}

	private boolean elementIsVisible(WebElement element) {
		int componentSize = getComponentSize(element);
		if (componentSize > 0 && element.isDisplayed() && element.isEnabled()) {
			System.out.println("Size: " + componentSize + " (x,y) (" + element.getLocation().x + ","
					+ element.getLocation().y + ")");
			return true;
		}
		return false;
	}

	private int getComponentSize(WebElement component) {
		return component.getSize().height * component.getSize().width;
	}
}