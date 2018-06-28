

import java.util.ArrayList;

public class PageComponent {
	private String selector;
	private PageComponent parent;

	private ArrayList<PageComponent> children = new ArrayList<PageComponent>();
	
	public PageComponent(String selector, PageComponent parent){
		this.selector=selector;
		this.parent=parent;
	}
	
	public PageComponent getParent() {
		return parent;
	}

	public void setParent(PageComponent parent) {
		this.parent = parent;
	}

	public ArrayList<PageComponent> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<PageComponent> children) {
		this.children = children;
	}


	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}
}
