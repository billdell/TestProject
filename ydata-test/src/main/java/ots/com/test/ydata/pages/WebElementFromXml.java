

package ots.com.test.ydata.pages;

import net.thucydides.core.pages.WebElementFacade;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("element")
public class WebElementFromXml {

	@XStreamAlias("action")
	public
	String  action;
	
	
	@XStreamAlias("type")
	public
	String  type;
	
	
	@XStreamAlias("by")
	public
	String by=null;
	

	@XStreamAlias("value")
	public
	String value=null;
	
	
	@XStreamAlias("key")
	public
	String key=null;
	
	@XStreamAlias("initialVisible")
	public Boolean  initialVisible=false;
	
	//**********************************************************************************
	
	@XStreamAlias("valueForSavedRow")
	public String valueForSavedRow=null;
	
	@XStreamAlias("valueForNewRow")
	public String valueForNewRow=null;
	
	@XStreamAlias("valueForModifingSavedRow")
	public String valueForModifingSavedRow=null;

	
	//**********************************************************************************
	
	WebElementFacade webElement;
	
	public String getValueForSavedRow() {
		return valueForSavedRow;
	}

	public String getValueForNewRow() {
		return valueForNewRow;
	}

	public String getValueForModifingSavedRow() {
		return valueForModifingSavedRow;
	}

	public WebElementFacade getWebElement() {
		return webElement;
	}
	
	public void setWebElement(WebElementFacade webElement) {
		this.webElement = webElement;
	}


	//*****************************************
	
	public String getType() {
		return type;
	}
	
	public String getAction() {
		return action;
	}

	public String getBy() {
		return by;
	}

	public String getValue() {
		return value;
	}

	public String getKey() {
		return key;
	}

	public Boolean getInitialVisible() {
		if (  initialVisible == null ) initialVisible = true;
		return initialVisible ;
	}

}
