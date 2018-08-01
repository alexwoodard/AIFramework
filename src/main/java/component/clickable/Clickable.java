package component.clickable;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import component.AbstractComponent;

public class Clickable extends AbstractComponent{


	public Clickable(WebElement webElement) 
			throws AWTException, IOException{
		text = webElement.getText();
		point = webElement.getLocation();
		dimension = webElement.getSize();
	}
}