package learning;

import component.Clickable;

public class KNearest {

	public int computeScore(Clickable expected, Clickable actual){
		int averagePixelDiff = Math.abs(expected.getAveragePixelValue() - actual.getAveragePixelValue());
		int xDiff = Math.abs(expected.getX() - actual.getX());
		int yDiff = Math.abs(expected.getY() - actual.getY());
		int textDiff;
		if(expected.getText().equals(actual.getText())){
			textDiff = 0;
		}else{
			textDiff = 1;
		}
		int sizeDiff = Math.abs(expected.getSize() - actual.getSize());
		int heightDiff = Math.abs(expected.getDimension().height - actual.getDimension().height);
		int widthDiff = Math.abs(expected.getDimension().width - actual.getDimension().width);
		return -1;
	}
}
