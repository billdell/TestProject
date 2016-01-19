package ots.com.test.ydata.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocatorElement {
	String locator;
	String message;
	String by;
	
	
	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public LocatorElement(String locator , String message ) {
		this.locator= locator;
		this.message= message;
	}
	
	public LocatorElement(String locator , String message , String by ) {
		this.locator= locator;
		this.message= message;
		this.by = by;
	}
	
	public LocatorElement(String locator) {
		this.locator= locator;
	}
	
	
	public String getLocator() {
		System.out.println(locator);
		return locator;
	}
	
	
	public String getRowLocator(int row){
		
		Pattern p = Pattern.compile("(\\d)");
		Matcher m = p.matcher(locator);
		String rowLocator=m.replaceAll(Integer.toString(row));
		return rowLocator;		
	}
	
	
	public void setLocator(String locator) {
		this.locator = locator;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
