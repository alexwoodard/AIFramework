package component;

import java.awt.AWTException;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import vision.AveragePixelValue;

public class Clickable {
	
	public Clickable(WebElement webElement){
		dimension = webElement.getSize();
		point = webElement.getLocation();
		text = webElement.getText();
		size = dimension.height*dimension.width;
		x = point.x;
		y = point.y;
		href = webElement.getAttribute("href");
	
		try {
			averagePixelValue = new AveragePixelValue(webElement);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Dimension dimension;
	private Point point;
	private String text;
	private int size;
	private int x;
	private int y;
	private String href;
	private AveragePixelValue averagePixelValue;
	
	public Dimension getDimension() {
		return dimension;
	}
	public Point getPoint() {
		return point;
	}
	public String getText() {
		return text;
	}
	public int getSize() {
		return size;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String getHref() {
		return href;
	}
	public int getAveragePixelValue() {
		return averagePixelValue.getAverage();
	}
}
