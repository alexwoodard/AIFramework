package clickablefinder;


import java.util.ArrayList;

public class PageComponent {
	private String selector;
	private PageComponent parent;
	private String cssClass;


	private ArrayList<PageComponent> children = new ArrayList<PageComponent>();
	
	public PageComponent(String selector, PageComponent parent, String cssClass){
		this.selector=selector;
		this.parent=parent;
		this.cssClass=cssClass;
	}
	
	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
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
