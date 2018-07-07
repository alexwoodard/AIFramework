package crawler;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ignore.IgnoreResolver;

public class ClickerCrawler {
	public List<WebElement> getAllClickableElements(WebDriver driver) {
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
	public List<WebElement> getAllIgnorableClickableElements(WebDriver driver, ArrayList<String> ignoreSelectors) {
		List<WebElement> visibleIgnorableClickables = new ArrayList<WebElement>();
		List<WebElement> clickables = new ArrayList<WebElement>();
		clickables.addAll(driver.findElements(By.xpath(".//a")));
		clickables.addAll(driver.findElements(By.xpath(".//button")));
		IgnoreResolver ir = new IgnoreResolver(driver, ignoreSelectors);
		for (WebElement element : clickables) {
			if (elementIsVisible(element) && ir.ignoreComponent(element)) {
				visibleIgnorableClickables.add(element);
			}
		}
		return visibleIgnorableClickables;
	}
	
	public boolean elementIsVisible(WebElement element) {
		int componentSize = getComponentSize(element);
		if (componentSize > 0 && element.isDisplayed() && element.isEnabled()) {
			return true;
		}
		return false;
	}
	
	private int getComponentSize(WebElement component) {
		return component.getSize().height * component.getSize().width;
	}
}
