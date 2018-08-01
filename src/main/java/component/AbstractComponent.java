package component;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public abstract class AbstractComponent {
	protected Point point;
	protected Point screenshotPoint;
	protected Dimension dimension;
	protected String text;
	
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public Dimension getDimension() {
		return dimension;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Point getScreenshotPoint() {
		return screenshotPoint;
	}
	public void setScreenshotPoint(Point screenshotPoint) {
		this.screenshotPoint = screenshotPoint;
	}
}