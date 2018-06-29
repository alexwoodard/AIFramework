package clickablefinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BfsAlgorithm {

	public String search(PageComponent c, WebDriver driver) {
		String returnString = "";
		XpathSelectorBuilder xpathSelectorBuilder = new XpathSelectorBuilder();
		ArrayList<PageComponent> queue = new ArrayList<PageComponent>();
		queue.add(c);
		while (!queue.isEmpty()) {
			PageComponent pc = queue.remove(0);
			WebElement element = driver.findElement(By.xpath(pc.getSelector()));
			List<WebElement> children;
			List<WebElement> anchorChildren = element.findElements(By.xpath(".//a"));
			List<WebElement> buttonChildren = element.findElements(By.xpath(".//button"));
			children = buttonChildren;
			children.addAll(anchorChildren);
			for (WebElement child : children) {
				int componentSize = getComponentSize(child);
				String componentClass = child.getAttribute("class");
				/**
				 * ignore the footer the debug footer the support footer the
				 * footer nav items and the utility bar
				 */
				if (componentSize > 0 && child.isDisplayed() && child.isEnabled()) {

					String childSelector = xpathSelectorBuilder.generateXPATH(child, "");
					ArrayList<String> unwantedClasses = new ArrayList<>(
							Arrays.asList("footer", "utility-bar", "support-footer-number", "footernav-item"));

					if (!componentClass.contains("footernav-item") && !componentClass.contains("topnav-logo")
							&& !hasUnwantedAncestorClass(childSelector, driver, unwantedClasses)) {
						PageComponent childComponent = new PageComponent(childSelector, pc, componentClass);

						if (returnString.equals("")) {
							returnString = childSelector;
						} else {
							returnString += "," + childSelector;
						}

						System.out.println("[DEBUG] selector: " + childComponent.getSelector() + " with class(s): "
								+ childComponent.getCssClass());
					}
				}
			}
		}
		return returnString;
	}

	private int getComponentSize(WebElement component) {
		return component.getSize().height * component.getSize().width;
	}

	private Boolean hasUnwantedAncestorClass(String xPath, WebDriver driver, ArrayList<String> unwantedClasses) {
		while (xPath.length() > 1) {
			int endIndex = xPath.lastIndexOf("/");
			xPath = xPath.substring(0, endIndex);
			if (xPath.length() == 0) {
				break;
			}
			WebElement parentElement = driver.findElement(By.xpath(xPath));
			String parentClasses = parentElement.getAttribute("class");
			for (String unwantedClass : unwantedClasses) {
				if (parentClasses.contains(unwantedClass)) {
					return true;
				}
			}
		}
		return false;
	}
}