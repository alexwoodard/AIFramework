package ignore;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IgnoreResolver {
	
	ArrayList<IgnorableComponent> ignorableComponents = new ArrayList<>();
	
	public IgnoreResolver(WebDriver driver, ArrayList<String> ignorableSelectors){
		for(String ignorableSelector : ignorableSelectors){
			ArrayList<WebElement> ignorableElementArray = (ArrayList<WebElement>) driver.findElements(By.cssSelector(ignorableSelector));
			
			if(ignorableElementArray.size()>0){
				WebElement ignorableElement = ignorableElementArray.get(0);
				int top = ignorableElement.getLocation().y;
				int bottom = top+ignorableElement.getSize().height;
				ignorableComponents.add(new IgnorableComponent(top, bottom));
			}
		}
	}
	
	public boolean ignoreComponent(WebElement element){
		for(IgnorableComponent ic : ignorableComponents){
			int elementTop = element.getLocation().y;
			int elementBottom = elementTop + element.getSize().height;
			if(elementTop >=ic.getTop() && elementBottom <= ic.getBottom() ){
				System.out.println("Found ignorable spanning: y="+elementTop+" down to y="+elementBottom);
				return true;
			}
		}
		return false;
	}
}