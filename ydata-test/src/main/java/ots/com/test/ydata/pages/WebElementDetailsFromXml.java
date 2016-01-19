package ots.com.test.ydata.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("webElementDetails")
public class WebElementDetailsFromXml {
	
	@XStreamImplicit(itemFieldName = "element")
	public List<WebElementFromXml> details  = new ArrayList<WebElementFromXml>();

	
	@XStreamAsAttribute
	public String type;
	
	@XStreamAsAttribute
	public String tab;
	
	@XStreamAsAttribute
	public String table;
	
	@XStreamAsAttribute
	public String prothema;
	
	
	//********************************************************************************************************
	
	
	public List<WebElementFromXml> getDetails() {
		return details;
	}

	public void setDetails(List<WebElementFromXml> details) {
		this.details = details;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getProthema() {
		return prothema;
	}

	public void setProthema(String prothema) {
		this.prothema = prothema;
	}
	//***************************************************************************************************************************************************************
	
	HashMap<String,WebElementFromXml > webElementsFromXmlMap = new HashMap<String,WebElementFromXml >();
	
	public void initializeMaps(){
		webElementsFromXmlMap = new HashMap<String,WebElementFromXml >();
		if (!details.isEmpty()) { 
			Iterator<WebElementFromXml> iter = details.iterator();
			while(iter.hasNext()) {
			
				WebElementFromXml webElementFromXml = iter.next();
				webElementsFromXmlMap.put(webElementFromXml.key, webElementFromXml);
			}
				
		}
		
	}

	public HashMap<String, WebElementFromXml> getWebElementsFromXmlMap() {
		return webElementsFromXmlMap;
	}
	//***************************************************************************************************************************************************************
	
	
	

}
