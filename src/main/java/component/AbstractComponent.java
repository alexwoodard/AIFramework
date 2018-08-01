package component;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public abstract class AbstractComponent {
	protected Point point;
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
	protected Dimension dimension;
	protected String text;
}