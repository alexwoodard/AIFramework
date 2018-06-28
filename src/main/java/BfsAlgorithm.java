

import java.util.ArrayList;
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
			List<WebElement> children = element.findElements(By.xpath("*"));
			for (WebElement child : children) {
				int componentSize = getComponentSize(child);
				String componentID = child.getAttribute("id");
				String componentClass = child.getAttribute("class");
				if (componentSize > 0 && child.isDisplayed() && child.isEnabled() && !componentID.equals("debug-footer")
						&& !componentClass.equals("footer")) {
					String childSelector = xpathSelectorBuilder.generateXPATH(child, "");
					PageComponent childComponent = new PageComponent(childSelector, pc);
					pc.getChildren().add(childComponent);
					queue.add(childComponent);
					if(returnString.equals("")){
						returnString = childSelector;
					}else{
						returnString+=","+childSelector;
					}
					System.out.println("[DEBUG] selector: " + childSelector + " with size: " + componentSize);
				}
			}
		}
		return returnString;
	}

	private int getComponentSize(WebElement component) {
		return component.getSize().height * component.getSize().width;
	}
}