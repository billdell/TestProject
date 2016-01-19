package ots.com.test.ydata.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;




@XStreamAlias("WebElements")
public class PageWebElementsFromXml  {

	
	
	
	@XStreamImplicit(itemFieldName = "element")
	public List webElements = new ArrayList();
	
	
	
	@XStreamImplicit(itemFieldName = "webElementDetails")
	public List<WebElementDetailsFromXml> webElementDetailsList= new ArrayList<WebElementDetailsFromXml>();
	


Map<String,WebElementFromXml> webElementsFromXmlMap = new HashMap<String,WebElementFromXml >();
Map<String,WebElementDetailsFromXml> webElementsDetailsFromXmlMap = new HashMap<String,WebElementDetailsFromXml >();



public void initializeMaps(){
		
		webElementsFromXmlMap = new HashMap<String,WebElementFromXml >();
		if (!webElements.isEmpty()) { 
			Iterator<WebElementFromXml> iter = webElements.iterator();
			while(iter.hasNext()) {
			
				WebElementFromXml webElementFromXml = iter.next();
				webElementsFromXmlMap.put(webElementFromXml.key, webElementFromXml);
			}
				
		}
		
		webElementsDetailsFromXmlMap =  new HashMap<String,WebElementDetailsFromXml >();
		if ( webElementDetailsList  != null ){
		  	Iterator<WebElementDetailsFromXml> iterDetails   = webElementDetailsList.iterator(); 
		  	while (iterDetails.hasNext()) { 
				WebElementDetailsFromXml webElementDetailsFromXml = iterDetails.next(); 
				//********************************************************
				webElementDetailsFromXml.initializeMaps();
				//********************************************************
				webElementsDetailsFromXmlMap.put(webElementDetailsFromXml.getType(), webElementDetailsFromXml); 
						
		  	}
		}

	}

public Map<String, WebElementFromXml> getWebElementsFromXmlMap() {
	return webElementsFromXmlMap;
}

public Map<String, WebElementDetailsFromXml> getWebElementsDetailsFromXmlMap() {
	return webElementsDetailsFromXmlMap;
}
	

private String xpathRegExprlocator(int row, String xpath){

	Pattern p = Pattern.compile("(\\d)");
	Matcher m = p.matcher(xpath);
	String newXpath=m.replaceAll(Integer.toString(row));
	return newXpath;
}


//*********************************************************************************************************************************************
public String  getForNewRowWebElementXpath( int row, String typeDetails , String field  ){
	return xpathRegExprlocator( row , webElementsDetailsFromXmlMap.get(typeDetails).getWebElementsFromXmlMap().get(field).getValueForNewRow() ) ;
}

public String  getForSavedRowWebElementXpath( int row, String typeDetails , String field  ){
	  return xpathRegExprlocator( row , webElementsDetailsFromXmlMap.get(typeDetails).getWebElementsFromXmlMap().get(field).getValueForSavedRow() ) ;
}

public String  getForModifingSavedRowWebElementXpath( int row, String typeDetails , String field  ){
	return xpathRegExprlocator( row ,  webElementsDetailsFromXmlMap.get(typeDetails).getWebElementsFromXmlMap().get(field).getValueForModifingSavedRow() ) ;
}




public String  getForNewRowWebElementXpath( int row,  String field  ){
	return getForNewRowWebElementXpath(row, "rows" , field) ; 
}

public  String  getForSavedRowWebElementXpath( int row,  String field  ){
	return getForSavedRowWebElementXpath(row, "rows" , field) ; 
}



public  String  getForModifingSavedRowWebElementXpath( int row, String field  ){
	return getForModifingSavedRowWebElementXpath(row, "rows" , field) ;  
}

public String getForSavedRowWebElementXpath(String typeDetails , String field){
	
	return webElementsDetailsFromXmlMap.get(typeDetails).getWebElementsFromXmlMap().get(field).getValueForSavedRow();
}
//*********************************************************************************************************************************************


}