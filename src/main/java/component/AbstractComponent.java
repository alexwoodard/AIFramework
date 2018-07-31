package component;

import java.awt.image.BufferedImage;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public abstract class AbstractComponent {
	protected BufferedImage image;
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	protected String imageName;

//	protected Dimension dimension;
	protected Point point;
	protected String text;
//	protected int size;
	protected int x;
	protected int y;
	protected String href;
//	protected int relativeX;
//	protected int relativeY;
	
//	public int getRelativeX() {
//		return relativeX;
//	}
//
//	public void setRelativeX(int relativeX) {
//		this.relativeX = relativeX;
//	}
//
//	public int getRelativeY() {
//		return relativeY;
//	}
//
//	public void setRelativeY(int relativeY) {
//		this.relativeY = relativeY;
//	}
//
//	protected Dimension getDimension() {
//		return dimension;
//	}
//
	public Point getPoint() {
		return point;
	}

	protected String getText() {
		return text;
	}

//	protected int getSize() {
//		return size;
//	}
//
	protected int getX() {
		return x;
	}

	protected int getY() {
		return y;
	}

	protected String getHref() {
		return href;
	}

	protected String getImageName() {
		return imageName;
	}
}
