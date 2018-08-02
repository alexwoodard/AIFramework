package learning;

import component.clickable.Clickable;

public class NearestNeighbor {

	public int computeScore(Clickable expected, Clickable actual) {
		int xDiff = Math.abs(expected.getPoint().x - actual.getPoint().x);
		int yDiff = Math.abs(expected.getPoint().y - actual.getPoint().y);
		int textDiff;
		if (expected.getText().equals(actual.getText())) {
			textDiff = 0;
		} else {
			textDiff = 1;
		}
		int sizeDiff = Math.abs(expected.getDimension().getHeight() * expected.getDimension().getWidth()
				- actual.getDimension().getHeight() * actual.getDimension().getWidth());
		int heightDiff = Math.abs(expected.getDimension().height - actual.getDimension().height);
		int widthDiff = Math.abs(expected.getDimension().width - actual.getDimension().width);

		return xDiff + yDiff + textDiff + sizeDiff + heightDiff + widthDiff;
	}
}
