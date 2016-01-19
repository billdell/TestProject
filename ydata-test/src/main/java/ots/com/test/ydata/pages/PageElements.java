package ots.com.test.ydata.pages;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class PageElements extends PageObject {
	
	public PageElements(WebDriver driver){
		super(driver);
		System.out.println( "PageElements Constructor is called ") ; 
	}
	
	
	/*Pages pages;
	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}*/
	
	
	
	
	public  PageWebElementsFromXml pageWebElementsFromXml = null;
	
	
	public void readWebElementsXmlFile(String xmlFile) throws  Exception{
		if ( pageWebElementsFromXml == null ) { 
			System.out.println( "readWebElementsXmlFile from pageElements is called") ; 
			pageWebElementsFromXml = ReadWebElementsFromXml.readWebElementsFromXmlasMap(xmlFile); 
		}
	}
	

	public void initializeWebElements(String xmlFile) throws Exception{
		System.out.println("initializeWebElements is called");
		readWebElementsXmlFile( xmlFile);
		initializeWebElements();
		System.out.println("initializeWebElements is ended");
		
	}
	
	
	public void initializeWebElements() throws Exception{
		System.out.println("initializeWebElements"); 
		for (Map.Entry<String,WebElementFromXml> entry : pageWebElementsFromXml.getWebElementsFromXmlMap().entrySet()) {
			WebElementFromXml webElementFromXml = entry.getValue();
			
			System.out.println( webElementFromXml.getKey()); 
				
				
			if ( !webElementFromXml.getInitialVisible() )  {
				System.out.println(" WebElement  "  +  webElementFromXml.getKey() + "   is not visible" );
				continue;
			}
			
			webElementFromXml.setWebElement(getWebElementFacade(webElementFromXml)) ; 
			
			
			}
	}
	
	
	public String getWebElementAction (String field) throws Exception{
		
		WebElementFromXml webElementFromXml  = pageWebElementsFromXml.getWebElementsFromXmlMap().get(field);
		String webElementFromXmlAction = webElementFromXml.getAction();
		
		return webElementFromXmlAction;
	}
	
	
	public  WebElementFacade getWebElementFacade( String field) throws Exception {
		
		if ( pageWebElementsFromXml  == null ) System.out.println( "pageWebElementsFromXml is null "); 
		if (pageWebElementsFromXml.getWebElementsFromXmlMap() == null ) System.out.println( "pageWebElementsFromXml.getWebElementsFromXmlMap() " ) ;
		WebElementFromXml webElementFromXml  = pageWebElementsFromXml.getWebElementsFromXmlMap().get(field);
		
		
		/*for ( Entry<String, WebElementFromXml> x:  pageWebElementsFromXml.getWebElementsFromXmlMap().entrySet() ){ 
			System.out.println( "key - > "	 + x.getKey() ) ; 
			System.out.println( "Value - > "	 + 	 x.getValue().getValue() ) ; 
		}
		*/
		
		
		 if (webElementFromXml==null) { 
			 System.out.println( "field : " + field + "       getWebElementFacade - > " +  "webElementFromXml is null ");  
			 return null;
		 }
		 
		return getWebElementFacade(webElementFromXml) ; 
	
	}

	
	private   WebElementFacade getWebElementFacade( WebElementFromXml  webElementFromXml) throws Exception {
		
		return getWebElementFacade (webElementFromXml.getBy()  ,webElementFromXml.getValue()  ) ; 
		
	
	}
	
	

	public WebElementFacade getWebElementFacade( String by , String locator ) throws Exception {
		WebElementFacade webElementFacade = null ; 
		switch( by ) {
			case "id":{ 
				webElementFacade = 	this.find(By.id(locator)) ; break;}
			case "className":{
				webElementFacade = 	this.find(By.className(locator)); break;}
			case "name":{
				webElementFacade = 	this.find(By.name(locator)); break;}
			case "xpath":{
				webElementFacade = 	this.find(By.xpath(locator)); break;}
			case "cssSelector":{
				webElementFacade = 	this.find(By.cssSelector(locator)); break;}
			}
		
		return webElementFacade;
		
	}
	
	public WebElementFromXml getWebElementFromXml( String field ) { 
		return 	this.pageWebElementsFromXml.getWebElementsFromXmlMap().get( field );
	}
	
	public  WebElementDetailsFromXml  getWebElementDetailsFromXml( String rowsType ){
		
		return this. pageWebElementsFromXml.getWebElementsDetailsFromXmlMap().get(rowsType); 
		
	}
	
	
	
	public Set<Entry<String, WebElementFromXml>> getSetMapEntries(){
		
		return  this.pageWebElementsFromXml.getWebElementsFromXmlMap().entrySet()  ; 
	
	}

	public PageWebElementsFromXml getPageWebElementsFromXml() {
		return pageWebElementsFromXml;
	}
	
	
	
	public Map<String, WebElementFromXml> getWebElementsFromXmlMap(){
		return pageWebElementsFromXml.getWebElementsFromXmlMap(); 
		
	}
	
	
	
	public  WebElementFacade getRowWebElement( int row, String typeDetails, String field  ) throws Exception{
		
		String xpathLocatorRowElement = this.pageWebElementsFromXml.getForNewRowWebElementXpath( row , typeDetails, field) ; 
		//System.out.println("getRowWebElement --> " + xpathLocatorRowElement);
		return this.findBy(xpathLocatorRowElement);
	}

	
	
	public  WebElementFacade getRowSavedWebElement( int row, String field  ) throws Exception{
		
		String xpathLocatorRowElement = this.pageWebElementsFromXml.getForSavedRowWebElementXpath( row , field) ; 
		System.out.println ( "getRowSavedWebElement - >   " +xpathLocatorRowElement  ) ; 
				
		//	return element (this.getDriver().findElement(By.xpath(xpathLocatorRowElement)) );	
			return this.findBy(xpathLocatorRowElement);
	}
	
	public  WebElementFacade getRowSavedWebElement( String typeDetails, String field  ) throws Exception{
		String xpathLocatorRowElement = this.pageWebElementsFromXml.getForSavedRowWebElementXpath( typeDetails , field) ; 
		return this.findBy(xpathLocatorRowElement);
	}
	
	
	public  WebElementFacade getRowSavedWebElement( int row, String typeDetails, String field  ) throws Exception{
		
		String xpathLocatorRowElement = this.pageWebElementsFromXml.getForSavedRowWebElementXpath( row , typeDetails, field) ; 
				
		//	return element (this.getDriver().findElement(By.xpath(xpathLocatorRowElement)) );	
			return this.findBy(xpathLocatorRowElement);
	}
	
	public WebElementFacade getForModifingSaveRowWebElementXpath( int row, String field  ) throws Exception{
		
		String xpathLocatorRowElement = this.pageWebElementsFromXml.getForModifingSavedRowWebElementXpath( row , field) ; 
		System.out.println( " getForModifingSaveRowWebElementXpath  - > " +  xpathLocatorRowElement); 
		return this.findBy(xpathLocatorRowElement);
	}


	public WebElementFacade getForSavedRowWebElementXpath(  String typeDetails, String field  ) throws Exception{
		
		String xpathLocatorRowElement = this.pageWebElementsFromXml.getForSavedRowWebElementXpath( typeDetails , field) ; 
		System.out.println( " getForSavedRowWebElementXpath  - > " +  xpathLocatorRowElement); 
		return this.findBy(xpathLocatorRowElement);
	}


public  WebElementFacade getRowWebElement( int row, String field  ) throws Exception{
	return this.getRowWebElement( row,  "rows", field  ) ; 	
}



public WebElementFacade getForModifingSaveRowWebElementXpath( int row, String typeDetails, String field  ) throws Exception{
	
	String xpathLocatorRowElement =  this.pageWebElementsFromXml.getForModifingSavedRowWebElementXpath( row , typeDetails, field) ; 
	System.out.println( " getForModifingSaveRowWebElementXpath  - > " +  xpathLocatorRowElement); 
	return this.findBy(xpathLocatorRowElement);
}

//************************************************************************************************************************************************************************************
public String getActionForRowWebElement ( String type , String field ) { 
	return this.pageWebElementsFromXml.getWebElementsDetailsFromXmlMap().get(type).getWebElementsFromXmlMap().get(field).getAction();
}
//************************************************************************************************************************************************************************************





}
