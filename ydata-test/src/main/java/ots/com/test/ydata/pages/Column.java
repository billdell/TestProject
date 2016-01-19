package ots.com.test.ydata.pages;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("column")
public class Column {
	@XStreamAsAttribute
	public String label;

	@XStreamAsAttribute
	public String value;
	
	
	@XStreamAsAttribute
	public String by;
	
	@XStreamAsAttribute
	public String locator;
	
	@XStreamAsAttribute
	public String action;
	
	//****************************************Getters And Setters********************************************************************


	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}
	
	
	
	//************************************************************************************************************

	

}
