package clicker;

import java.util.ArrayList;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import component.Clickable;
import crawler.ClickerCrawler;
import learning.NearestNeighbor;

public class Clicker {
	ClickerCrawler clickerCrawler = new ClickerCrawler();
	public void search(WebDriver driver) throws InterruptedException {
		String urlUnderTest = driver.getCurrentUrl();
		ArrayList<Clickable> clickablesUnderTest = new ArrayList<Clickable>();
		for (WebElement element : clickerCrawler.getAllClickableElements(driver)) {
			if (clickerCrawler.elementIsVisible(element)) {
				clickablesUnderTest.add(new Clickable(element));
			}
		}

		NearestNeighbor nearestNeighbor = new NearestNeighbor();
		for (Clickable p : clickablesUnderTest) {
			int nearestNeighborScore = Integer.MAX_VALUE;
			WebElement nearestNeighborElement = null;
			Boolean clicked = false;
			synchronized (driver) {
				driver.get(urlUnderTest);
				driver.wait(3000);
			}
			for (WebElement element : clickerCrawler.getAllClickableElements(driver)) {
				Clickable clickable = new Clickable(element);
				int neighborScore = nearestNeighbor.computeScore(p, clickable);
				if (neighborScore < nearestNeighborScore) {
					nearestNeighborScore = neighborScore;
					nearestNeighborElement = element;
				}
			}
			if (nearestNeighborElement != null) {
				nearestNeighborElement.click();
				clicked = true;
			}
			if (!clicked) {
				System.out.println("[DEBUG] Could not click on " + nearestNeighborElement.getLocation().x + " "
						+ nearestNeighborElement.getLocation().y);
			}else{
//				System.out.println("[DEBUG] Clicked on " + nearestNeighborElement.getLocation().x + " "
//						+ nearestNeighborElement.getLocation().y);
			}
			clicked = false;
		}
	}
}