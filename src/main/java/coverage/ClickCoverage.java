package coverage;

import java.util.ArrayList;

import component.clickable.Clickable;

public class ClickCoverage {

	public ArrayList<Clickable> allClicks = new ArrayList<Clickable>();
	
	public ArrayList<Clickable> getAllClicks() {
		return allClicks;
	}

	public void addClick(Clickable clickable){
		allClicks.add(clickable);
	}
}
