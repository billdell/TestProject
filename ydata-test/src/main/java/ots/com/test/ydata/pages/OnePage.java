package ots.com.test.ydata.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.pages.WebElementFacade;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import ots.com.test.ydata.data.RecordField;

public class OnePage extends PageObject{
	
	protected static PageElements pageElements ;  
	
	//protected ModalPageElements modalPageElements;
	//ModalPages modalPages; 
	
	//List<PageObject> modals = new ArrayList<PageObject>(); 
	
	
	
	
	public OnePage(WebDriver driver){
		super(driver);
		pageElements = new PageElements(driver) ;
		//modalPages = new ModalPages(driver) ; 
		//modalPages.setPage(this); 
		System.out.println( "OnePage is called " ) ; 
	}
	
	
	/*public ModalPages getModalPages() {
		return modalPages;
	}*/


	Pages pages;
	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	
	
	
	//********************************************************************************************************************
	int timeout = 10; // timeout in seconds 
	protected WebDriverWait wait = new WebDriverWait(this.getDriver(), timeout);
	
	protected ExpectedCondition<Boolean>  documentReadyStateCondition = new ExpectedCondition<Boolean>() 
			{ @Override public Boolean apply(WebDriver driver) {
				  return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
	};  
	
	
	
	//********************************************************************************************************************
	public  Map<String , LocatorElement >  errorLocatorElementMap = new  HashMap<>();
	public  Map<String, LocatorElement>    rowWebElementMap = new  HashMap<>();
	public String SuccessSaveMessage;
	public String SuccessModifyMessage;
	public String SuccessDeleteMessage;
	//********************************************************************************************************************
	
	//Τα webElements  του master της σελίδας 
	//protected Map<String,WebElementFromXml> webElements = null ;
	
	//protected PageWebElementsFromXml pageWebElementsFromXml = null;
	
	
	/*public void readWebElementsXmlFile(String xmlFile) throws FileNotFoundException{
		if ( pageWebElementsFromXml == null ) { 
			pageWebElementsFromXml = ReadWebElementsFromXml.readWebElementsFromXmlasMap(xmlFile); 
		}
	}
	*/
	

	public void initializeWebElements(String xmlFile) throws Exception{
		System.out.println("initializeWebElements is called");
		pageElements.readWebElementsXmlFile( xmlFile);
		//this.readWebElementsXmlFile( xmlFile);
		pageElements.initializeWebElements();
		System.out.println("initializeWebElements is ended");
		
	}
	
	/*public void initializeModalWebElements(String xmlFile) throws Exception{
		System.out.println("initializeModalWebElements is called");
		modalPageElements.readModalWebElementsXmlFile( xmlFile);
		modalPageElements.initializeModalWebElements();
		System.out.println("initializeModalWebElements is ended");
		
	}*/



	public  WebElementFacade getWebElementFacade( String field) throws Exception {
	
		return pageElements.getWebElementFacade(field); 
		
	}

	
	
	
	
	//******************************************************************************************************************************************************************************
	
	public boolean isElementPresent(String field)  throws Exception {
	    try {
	    	WebElementFacade webElementFacade =  pageElements.getWebElementFacade(field); 
	    	 if ( webElementFacade == null )  return false;
	    	return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	      return false;
	    } 
	}
	
	public boolean isElementPresent(String by , String locator)  throws Exception {
	    try {
	    	WebElementFacade webElementFacade =  pageElements.getWebElementFacade( by ,  locator); 
	    	 if ( webElementFacade == null )  return false;
	    	return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	      return false;
	    } 
	}
	
	
	public boolean isElementVisible(String by, String locator) throws Exception{
		try {
	    	WebElementFacade webElementFacade =  pageElements.getWebElementFacade( by ,  locator); 
	    	if (webElementFacade.isVisible())
	    		return true;
	    	
	    	return false;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	      return false;
	    } 
	}

	
	//******************************************************************************************************************************************************************************
	
	public boolean elementClassHasAttributeHiden(String field)  throws Exception {
	    	return	elementClassHasAttributeHiden(pageElements.getWebElementFacade(field)); 
	 }
	
	public boolean elementHasAttributeReadOnly(String field)  throws Exception {
	    	return ( elementHasAttributeReadOnly(pageElements.getWebElementFacade(field)) ) ;    	
	  
	}
	
	public boolean elementHasAttributeDisable(String field)  throws Exception {
	    	return ( elementHasAttributeDisable(pageElements.getWebElementFacade(field)) ) ;    	
	  
	}
	
	
	
	public boolean elementIsVisible(String field)  throws Exception {
	    	return elementIsVisible(pageElements.getWebElementFacade(field));
	  
	}
	
	public boolean elementIsDisplayed(String field)  throws Exception {
	    	return elementIsDisplayed(pageElements.getWebElementFacade(field)); 
	}
	
	public boolean rowElementIsDisplayed(String field)  throws Exception {
    	return elementIsDisplayed(pageElements.getRowWebElement(0, field)); 
}

	public boolean elementClassHasAttributeHiden(WebElementFacade webElementFacade) {
	    	return (webElementFacade.getAttribute("class").contains("hidden") ? true : false ) ;    	
	}
	
	public boolean elementHasAttributeReadOnly(WebElementFacade webElementFacade)  {
	    	return ( webElementFacade.getAttribute("readonly") == null  ? false : true) ;    	
	}
	
	public boolean elementHasAttributeDisable(WebElementFacade webElementFacade)  {
    	return ( webElementFacade.getAttribute("disabled") == null  ? false : true ) ;    	
	}
	
	
	public boolean elementIsVisible(WebElementFacade webElementFacade)  {
	    	return webElementFacade.isVisible();
	}
	public boolean elementIsDisplayed(WebElementFacade webElementFacade)  {
		
		return webElementFacade.isDisplayed();
	}
	
	public boolean elementIsEnabled(WebElementFacade webElementFacade)  {
		return webElementFacade.isEnabled();
	}

	
	
	//*******************************************************************************************************************************************************************************
	public void checkFieldAttributes( String field , List<String> attributes) throws Exception{
		
		try {
			WebElementFacade webElementFacade =  pageElements.getWebElementFacade(field);
		
		
			for (String attribute : attributes ){
				switch ( attribute )   {
					case"readonly":		{elementHasAttributeReadOnly(webElementFacade); break;}
					case "disable":			{elementHasAttributeDisable(webElementFacade); break;}
					case "hiden":				{elementClassHasAttributeHiden(webElementFacade); break;}
					case "visible":			{elementIsVisible(webElementFacade);break;}
					case "displayed":	{elementIsDisplayed(webElementFacade); break;}
					case "enabled":		{elementIsEnabled(webElementFacade); break;}
				}
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			throw ex;
		} 
		
	}
	//*******************************************************************************************************************************************************************************
	
	
	
	public String  getTextValue(String field ) throws Exception{
		try{ 
			
			
			//WebElementFromXml webElementFromXml = webElements.get(field);
			//WebElementFromXml webElementFromXml  = pageWebElementsFromXml.getWebElementsFromXmlMap().get( field );
			WebElementFromXml webElementFromXml  = pageElements.getWebElementFromXml(field); 
			
			WebElementFacade webElement = webElementFromXml.getWebElement();
			if (webElement == null )  webElement = pageElements.getWebElementFacade(field)  ; 
			if (webElement != null) { 
				if ( webElementFromXml.getType().equals("checkbox") ) { 
					WebElement fatherWebElement = webElement.findElement(By.xpath(".."));
					WebElement  checkbox = fatherWebElement.findElement(By.xpath(".//input[@type='checkbox']")); 
					return ( checkbox.getAttribute("checked") == null ? "false": "true" ) ; 
				}
				else 
					return webElement.getTextValue().replace("\u00a0",""); 
			}
			return "";
				
		} catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	
	
	

	//**************************************************************************************************************************************************************************************************
	public List<Map<String,String> >   getRowValues(String  rowsType){
		
		
		
		//WebElementDetailsFromXml details = pageWebElementsFromXml.getWebElementsDetailsFromXmlMap().get(rowsType); 
		
		WebElementDetailsFromXml details = pageElements.getWebElementDetailsFromXml(rowsType) ; 
		
		HashMap<String,WebElementFromXml > columns = details.getWebElementsFromXmlMap(); 
		WebElementFacade tabControlWebElement  =   this.findBy(details.getTab());
		WebElementFacade  tableWebElement =element (  tabControlWebElement.findBy(details.getTable()) ) ;
		
		List<WebElement> rows = tableWebElement.findElements(By.xpath("tbody//tr[@role='row'][@id]")); 
		
		List<Map<String,String> >  rowValues = new ArrayList<Map<String,String>>();
		
		Iterator<WebElement>  rowsIterator = rows.iterator(); 
		
		for (WebElement row : rows  ) { 
			
			Map<String,String> values = new HashMap<String, String>();
			for (Entry<String, WebElementFromXml> column : columns.entrySet()){
				
				if  ( column.getValue().getType().equals("icon") )  continue;
				
				WebElementFacade columnWebElement = element ( row.findElement(By.xpath (column.getValue().getValueForSavedRow())  ) ); 
		//		WebElementFacade columnWebElement  = row.findBy(column.getValue().getValueForSavedRow());
				
				String value = columnWebElement.getTextValue();
				if ( value == null || value.trim().equals("") ) value = columnWebElement.getAttribute("title");
				
				if  ( column.getValue().getType().equals("number") ) { 
					value = value.replace(".", "").replace(",", ".");
				}
				values.put(column.getKey(),  value ) ; 
				System.out.println( "key - > " + column.getKey() + "  value : " +  value) ; 
				
				//values.put(column.getKey(), columnWebElement.getTextValue()) ; 
			}
				
			rowValues.add(values);
			
		}
		
		return rowValues;
		
	}
	
	//**********************************************************Αναζήτηση της γραμμής ενός grid με βάση καποια κριτήρια αναζήτησης*****************************************************
	
	public WebElementFacade   getRowWebElementFacade( String rowsType , List<RecordField> searchCriteria  ) throws Exception{
		
		//WebElementDetailsFromXml details = pageWebElementsFromXml.getWebElementsDetailsFromXmlMap().get(rowsType); 
		WebElementDetailsFromXml details = pageElements.getWebElementDetailsFromXml(rowsType) ; 
		
		
		HashMap<String,WebElementFromXml > columns = details.getWebElementsFromXmlMap(); 
		
		for (RecordField recordField :searchCriteria ) { 
			WebElementFromXml column = columns.get(recordField.getLabel()); 
			if (column == null ) { 
				throw new Exception ("H στήλη : " + recordField.getLabel() + " δεν μπόρεσε να προσδιοριστεί στο σύνολο στηλών του grid με βάση το αντίστοιχο xml")  ; 
			}	
		}
		
		this.waitForPresenceOf(details.getTable()); 
		
		
		System.out.println("H στήλη μπόρεσε να προσδιοριστεί στο σύνολο στηλών του grid με βάση το αντίστοιχο xml"); 
		System.out.println("details.getTable() - > " + details.getTable()   ) ; 
		
		WebElementFacade tabControlWebElement  =   this.findBy(details.getTab());
		System.out.println( "FASE A1") ;
	
		WebElementFacade  tableWebElement =element (  tabControlWebElement.findBy(details.getTable()) ) ;
		System.out.println( "FASE A2") ;
		List<WebElement> rows = null;
		
		
		if(rowsType.equals("visitor"))
			rows= tableWebElement.findElements(By.xpath("//tr[contains(@id, 'row')]")); 
		else if(rowsType.equals("offenses") || rowsType.equals("decisions"))	
			rows= tableWebElement.findElements(By.xpath("//tr[starts-with(@id, 'row')]")); 
		else	
			rows= tableWebElement.findElements(By.xpath("//tr[@role='row'][@id]")); 
		
		System.out.println( "FASE A") ;
		
		for (WebElement row : rows  ) { 
			Boolean  rowFound = true;
			for (RecordField recordField :searchCriteria ) { 
				WebElementFromXml column = columns.get(recordField.getLabel()); 
				WebElementFacade columnWebElement = element ( row.findElement(By.xpath (column.getValueForSavedRow())  ) ); 

				System.out.println ("**********************************************************************************************") ; 
				
				System.out.println ("columnWebElement.getTextValue() - > " + columnWebElement.getTextValue() ) ;
				System.out.println ("recordField.getValue() - > " + recordField.getValue() ) ;
				
				System.out.println ("**********************************************************************************************") ;
				
				if ( !columnWebElement.getTextValue().equals(recordField.getValue()) )  { 
					 rowFound = false;
					break;
				}
				
			}
			if (rowFound ){ 
				
				System.out.println( "FASE B") ;
				return  element (row ) ; 
			}
		}
		System.out.println( "FASE C") ;
		return null;
	}
	
	
	//**************************************************************************************************************************************************************************************************
	
	public Map<String,String > getPageValues() throws Exception{
		Map<String,String> values = new HashMap<String, String>();
		System.out.println(" *********** getPageValues START  ***********");   
		
		
			
		//for (Map.Entry<String,WebElementFromXml> entry : pageWebElementsFromXml.getWebElementsFromXmlMap().entrySet()) {
		for (Map.Entry<String,WebElementFromXml> entry :pageElements.getSetMapEntries()) {
				
			String field = entry.getKey(); 
			String value = this.getTextValue(entry.getKey());
			
					
			if ( !value.isEmpty() ) { 
				System.out.println(field   + " :  " +  "*"+value+"*" + value.trim()+"*" ) ;
				
				values.put( field , value );
			}
		}
		System.out.println(" *********** getPageValues END  ***********");   
		return values;
	}
	
	//*******************************************************************************************************************************************************************************
	
	public void sendTab(String field ) throws Exception{
		WebElementFacade webElement  = pageElements.getWebElementFacade(field);
		sendTab(webElement);
	}
	
	public void sendRowTab(int row, String typeDetail, String field) throws Exception{
		//WebElementFacade webElement = getRowSavedWebElement( row , typeDetail, field );
		WebElementFacade webElement = getForSavedRowWebElementXpath( typeDetail, field );
		sendTab(webElement);
	}

	public void setField(String field , String value) throws Exception{
			//WebElementFacade webElement = pageWebElementsFromXml.getWebElementsFromXmlMap().get(field).getWebElement();
			
			WebElementFacade webElement  = pageElements.getWebElementFacade(field); 
			if (webElement == null )  webElement = pageElements.getWebElementFacade(field)  ; 
			setField(webElement , value ) ; 
		
	}
	public void setFieldWithOutTab(String field , String value) throws Exception{
		//WebElementFacade webElement = pageWebElementsFromXml.getWebElementsFromXmlMap().get(field).getWebElement();
		WebElementFacade webElement  = pageElements.getWebElementFacade(field); 
		
		if (webElement == null )  webElement = pageElements.getWebElementFacade(field)  ; 
		setFieldWithOutTab(webElement , value ) ; 
	
	}
	
	public void setFieldAndEnter(String field , String value) throws Exception{
		//WebElementFacade webElement = pageWebElementsFromXml.getWebElementsFromXmlMap().get(field).getWebElement();
		
		WebElementFacade webElement  = pageElements.getWebElementFacade(field); 
		if (webElement == null )  webElement = pageElements.getWebElementFacade(field)  ; 
		setFieldAndEnter(webElement , value ) ; 
	
}
	
	public void setNumberTypeField(String field , String value) throws Exception{
			String expression = "document.getElementById('" +  field +"').value='" + value + "';"; 
			((JavascriptExecutor)this.getDriver()).executeScript(expression );
		}
	
	public void checkField(String field ) throws Exception{
		//WebElementFacade webElement = pageWebElementsFromXml.getWebElementsFromXmlMap().get(field).getWebElement();
		WebElementFacade webElement  = pageElements.getWebElementFacade(field); 
		
		if (webElement == null )  webElement = pageElements.getWebElementFacade(field)  ; 
		checkField(webElement) ; 
		
	}

	
	//***********************************************************************************************************************************

	
	//********************************* Dropdown  που δεν δίνει την δυνατότητα  input  οποτε απλώς επιλέγουμε αυτό που θέλουμε**********************
	final String fromDcBranchChoisesXpathLocation = ".//ul[@class='chosen-results']/li";
	public void selectFromDropDown( String dropdown , String value ) throws Exception{
		//WebElementFacade  dropDownField = pageWebElementsFromXml.getWebElementsFromXmlMap().get(dropdown ).getWebElement();
		WebElementFacade  dropDownField  = pageElements.getWebElementsFromXmlMap().get(dropdown ).getWebElement();
		if (dropDownField == null ) dropDownField = pageElements.getWebElementFacade(dropdown)  ;
		selectFromDropDown(dropDownField , value ) ; 
		
	}
	//********************************* Dropdown  που  δίνει την δυνατότητα  input  με dataentry  οπότε στο αντίστοιχο input εισάγουμε την επιλογή value **********************
	final String inputDropDownXpathLocation = "div[@class='chosen-drop']//div[@class='chosen-search']//input";
	public void  setValueFromDropDown( String dropdown , String value) throws Exception{
		//WebElementFacade  dropDownField = pageWebElementsFromXml.getWebElementsFromXmlMap().get(dropdown ).getWebElement();
		WebElementFacade  dropDownField = pageElements.getWebElementsFromXmlMap().get(dropdown ).getWebElement();
		
		if (dropDownField == null ) dropDownField =pageElements.getWebElementFacade(dropdown)  ;
		setValueFromDropDown(dropDownField , value ) ; 
	}
	
	// *********************** Ανάκτηση όλων των επιλογών ενός dropdown και επιστρέφει αν βρεθεί η τιμή ή όχι ******************************************************
	
	public boolean searchChoiceFromDropDown( String dropdown , String value ) throws Exception{
		WebElementFacade  dropDownField  = pageElements.getWebElementsFromXmlMap().get(dropdown ).getWebElement();
		if (dropDownField == null ) dropDownField = pageElements.getWebElementFacade(dropdown)  ;
		return searchChoiceFromDropDown(dropDownField , value ) ; 
		
	}
	
	//*****************************************************************************************************************************************************************************************************
	
	//Χρήση του πλήκτρου Tab 
	public void sendTab(WebElementFacade webElement) throws Exception{
		webElement.getWrappedElement().sendKeys(Keys.CONTROL);
		webElement.getWrappedElement().sendKeys(Keys.TAB);
	}
	
	
	
	public   void setField(WebElementFacade webElement  , String value){
		webElement.click();
		webElement.clear();
		webElement.typeAndTab(value);
	}
	private  void setFieldWithOutTab(WebElementFacade webElement  , String value){
		webElement.click();
		webElement.clear();
		webElement.type(value);
	}
	
	
	private void setFieldAndEnter(WebElementFacade webElement  , String value){
		webElement.click();
		webElement.clear();
		webElement.typeAndEnter(value);
	}
	
	
	public  void checkField(WebElementFacade webElement ) throws Exception {
		if (webElement == null )
			System.out.println(" webElement  is  null  ");
		webElement.click();
	}
	
/*	private  void  setValueFromDropDown( WebElementFacade webElement  , String value){
		webElement.click();
		WebElementFacade  inputField  = element(   webElement.findElement(By.xpath(inputDropDownXpathLocation)) ) ;
		inputField.typeAndTab(value );
		
	}*/
	
	public void  setValueFromDropDown( WebElementFacade webElement  , String value){
		webElement.click();
		WebElementFacade  inputField  = element(   webElement.findElement(By.xpath(inputDropDownXpathLocation)) ) ;
		inputField.type(value );
		List<WebElement> choises = webElement.findElements(By.xpath(fromDcBranchChoisesXpathLocation)) ;
		if (choises.size()==1) 
			choises.get(0).click();
		else	{ 
			for( WebElement choise :choises ) {
				if (choise.getText().equals(value)) {
					choise.click();
					break;
				}
			}
		}
	}
	
	
/*	private  void  setValuesFromDropDown( WebElementFacade webElement  , String value){
		webElement.click();
		WebElementFacade  inputField  = element(   webElement.findElement(By.xpath(inputDropDownXpathLocation)) ) ;
		inputField.type(value );
		List<WebElement> choises = webElement.findElements(By.xpath(fromDcBranchChoisesXpathLocation)) ;
		if (choises.size()==1) 
			choises.get(0).click();
		else	{ 
			for( WebElement choise :choises ) {
				if (choise.getText().equals(value)) {
					choise.click();
					break;
				}
			}
		}
	}*/
	
	public void selectFromDropDown( WebElementFacade webElement , String value ){	
		webElement.click();
		List<WebElement> choises = webElement.findElements(By.xpath(fromDcBranchChoisesXpathLocation)) ;
		for( WebElement choise :choises ) {
			if (choise.getText().equals(value)) {
				choise.click();
				break;
			}
		}			
	}
	
	
	

	
	public void selectOptionFromDropdown(WebElement webElement, String option){
	
		WebElementFacade element = element(webElement) ;
		element.getWrappedElement().sendKeys(Keys.ENTER);
		
		List<WebElement> choises = webElement.findElements(By.tagName("option")) ;
		
		for( WebElement choise :choises ) {
			
			if (choise.getText().equals(option)) {				
				waitFor(1000).millisecond();
				choise.click();
				waitFor(1000).millisecond();
				break;
			}
		}
			
	}
	
	public boolean searchChoiceFromDropDown( WebElementFacade webElement , String value){
		webElement.click();
		List<WebElement> choises = webElement.findElements(By.xpath(fromDcBranchChoisesXpathLocation)) ;
		for( WebElement choise :choises ) {
			if (choise.getText().equals(value)) 
				return true;
		}
		return false;
	}
	
	
	public void clearField(String field) throws Exception{
		WebElementFacade webElement  = pageElements.getWebElementFacade(field); 
		webElement.clear();
	}
	
	public void clearDropDownField(String field) throws Exception{
		WebElementFacade webElement  = pageElements.getWebElementFacade(field);
		System.out.println("field: " + field + " | webElement: " + webElement.getValue());
		//WebElement webElement = this.getDriver().findElement(By.xpath("//div[@id='doctorId_chosen']//abbr[@class='search-choice-close']"));
		WebElement choiceCloseWebElement = webElement.findElement(By.xpath("//abbr[@class='search-choice-close']"));
		choiceCloseWebElement.click();
	}
	
	
	//********************************************************************************************************************************
	@FindBy(id="button-save")
	protected WebElementFacade saveButtonWebElement;
	
	@FindBy(id="button-delete")
	protected WebElementFacade deleteButtonWebElement;
	
	@FindBy(id="button-cancel")
	protected WebElementFacade cancelButtonWebElement;	
	
	@FindBy(id="button-list")
	protected WebElementFacade listButtonWebElement;
	
	
	
	

	public void save() {
		
	//	waitFor(1000).milliseconds();  
		//saveButtonWebElement.getWrappedElement().sendKeys(Keys.CONTROL);
		//saveButtonWebElement.getWrappedElement().sendKeys(Keys.ENTER);

	saveButtonWebElement.click();
	waitFor(1000).milliseconds();  
		//wait.until( documentReadyStateCondition ) ; 
	}
	
	public void clickSave(){
		saveButtonWebElement.getWrappedElement().sendKeys(Keys.CONTROL);
		saveButtonWebElement.getWrappedElement().sendKeys(Keys.ENTER);
	}
	  
	  
	public String  saveWithConfirmAlertPopUp() {
		  saveButtonWebElement.click();
		  this.waitFor(2000).milliseconds();
		  return confirmAlertPopUp(); 
	}
	
	public String confirmAlertPopUp(){
		Alert alert = this.getDriver().switchTo().alert();
		String alertMessage = alert.getText() ; 
		alert.accept();
		return alertMessage;
	}
	
	
	//Μετάβαση στο Ευρετήριο 
	public void list() throws Exception{
		listButtonWebElement.click();
		String xpathModalDialogLocator = "//div[@class='modal-dialog']//div[@class='modal-content']//div[@class='modal-body']" ; 
		String xpathModalDialogButtonAcceptLocator = "//div[@class='modal-dialog']//div[@class='modal-content']//div[@class='modal-footer']//button[@data-bb-handler='yesBtn']" ; 
		String xpathModalDialogButtonDenyLocator = "//div[@class='modal-dialog']//div[@class='modal-content']//div[@class='modal-footer']//button[@data-bb-handler='noBtn']" ;
		
		if ( 	this.isElementPresent( "xpath" ,xpathModalDialogLocator  ) ) 
			//System.out.println("Modal list is present "); 
			this.findBy(xpathModalDialogButtonAcceptLocator).click();
	}
	
	
	//Διαγραφή μια Εγγραφής
	 public void delete() {
		  deleteButtonWebElement.click();
		  waitFor(2000).milliseconds();
		  //this.confirmDelete();
		  /*WebDriverWait wait = new WebDriverWait(this.getDriver(), timeout);
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='modal-footer']")));
		  //WebElementFacade modalDialogWebElement = element ( this.getDriver().findElement(By.xpath(".//div[@class='modal-footer']")) ) ;
		  WebElementFacade modalDialogWebElement =  findBy(".//div[@class='modal-footer']") ;
		  
		  WebElementFacade  confirmButtonWebElement = element (  modalDialogWebElement.findElement(By.xpath(".//button[@data-bb-handler='confirm']") ) ) ; 
		  confirmButtonWebElement.click();*/
	 }
	
	 
	 public void cancel(){
		 cancelButtonWebElement.click();
		 waitFor(1000).millisecond();
		 confirmButtonWebElement.click();
	 }
		 
	 
	
	//Διαγραφή μια Εγγραφής
	/*public void delete() {
		deleteButtonWebElement.click();
		
		WebDriverWait wait = new WebDriverWait(this.getDriver(), timeout);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='modal-footer']")));
		//WebElementFacade modalDialogWebElement = element ( this.getDriver().findElement(By.xpath(".//div[@class='modal-footer']")) ) ;
		WebElementFacade modalDialogWebElement =  findBy(".//div[@class='modal-footer']") ;
		
		WebElementFacade  confirmButtonWebElement = element (  modalDialogWebElement.findElement(By.xpath(".//button[@data-bb-handler='confirm']") ) ) ; 
		confirmButtonWebElement.click();
	}*/
	
	
	//********************************************************************************************************************************
	

	
	//******************************************************************************************************************************************************************************
	
	// Επιστρέφει το WebElement για ενα πεδίο μια γραμμής η οποία είναι νέα γραμμή
	public  WebElementFacade getRowWebElement( int row, String field  ) throws Exception{
		
		return pageElements.getRowWebElement(row , field) ; 
		
		
	}
	
	public  WebElementFacade getRowWebElement( int row, String typeDetails, String field  ) throws Exception{
		
		return pageElements.getRowWebElement( row,  typeDetails,  field  ) ; 
		
	}

	// Επιστρέφει το WebElement για ενα πεδίο μια γραμμής η οποία είναι αποθηκευμένη  γραμμή
	private WebElementFacade getRowSavedWebElement( int row, String field  ) throws Exception{
		
		return pageElements.getRowSavedWebElement( row, field ) ; 
		
	}
	
	private WebElementFacade getRowSavedWebElement( int row, String typeDetails, String field  ) throws Exception{
		
		return pageElements.getRowSavedWebElement( row, typeDetails, field ) ; 
		
	}
	
	
	private WebElementFacade getForSavedRowWebElementXpath(String typeDetails, String field  ) throws Exception{
		
		return pageElements.getForSavedRowWebElementXpath(  typeDetails, field ) ; 
		
	}
	
	// Επιστρέφει το WebElement για ενα πεδίο μια γραμμής η οποία είναι αποθηκευμένη  γραμμή και είναι προς επεξεργασία
	private WebElementFacade getForModifingSaveRowWebElementXpath( int row, String field  ) throws Exception{
		
		return pageElements.getForModifingSaveRowWebElementXpath( row , field ) ; 
		
	}

	
	
	//****************************************************************************************************************************************************************
	private  void setRowField( WebElementFacade webElementFacade  ,  String value) throws Exception {
		
		setField( webElementFacade  , value);
	
	}
	
	private  void setRowFieldWithOutTab( WebElementFacade webElementFacade  ,  String value) throws Exception {
		
		setFieldWithOutTab( webElementFacade  , value);
	
	}
	
	
	private void setRowValueDropDown( WebElementFacade webElementFacade , String value) throws Exception{
	
		setValueFromDropDown(webElementFacade  , value);
		}

	private void  selectRowValueDropDown(WebElementFacade webElementFacade , String value) throws Exception{
		
			selectFromDropDown( webElementFacade  , value);
	}

	private void  checkRowValue(WebElementFacade webElementFacade ) throws Exception{
	
		checkField( webElementFacade  );
	
	}
	
	
	
	public WebElementFacade getForModifingSaveRowWebElementXpath( int row, String typeDetails, String field  ) throws Exception{
		
		return pageElements.getForModifingSaveRowWebElementXpath(  row,  typeDetails,  field  ) ; 
	
	}


	//*************************************************************  Για Νέες Εγγραφές - Rows  ***************************************************************************************************
	
	
	public void setRowField(int row, String field, String value) throws Exception {
			
			setRowField (getRowWebElement( row , field ) , value) ; 
			//setField(getRowWebElement( row , field ) , value);
		
	}
	
	public void setRowField(int row, String typeDetails, String field, String value) throws Exception {
		
		setRowField (getRowWebElement( row , typeDetails, field ) , value) ; 	
	}
	
	public void setRowFieldWithOutTab(int row, String typeDetails, String field, String value) throws Exception {
		
		setRowFieldWithOutTab (getRowWebElement( row , typeDetails, field ) , value) ; 	
	}
	
	public void setRowValueDropDown(int row, String field , String value) throws Exception{
			setRowValueDropDown(getRowWebElement( row , field ) , value );
			//	setValueFromDropDown(getRowWebElement( row , field ) , value);
	}
	
	public void setRowValueDropDown(int row, String typeDetails, String field , String value) throws Exception{
		setRowValueDropDown(getRowWebElement( row , typeDetails, field ) , value );
		//	setValueFromDropDown(getRowWebElement( row , field ) , value);
	}

	public void  selectRowValueDropDown(int row, String field , String value) throws Exception{
			selectRowValueDropDown(getRowWebElement( row , field ) , value) ; 
	}
	
	public void  selectRowValueDropDown(int row, String typeDetail,  String field , String value) throws Exception{
		selectRowValueDropDown(getRowWebElement( row , typeDetail, field ) , value) ; 
	}
	
	public void  checkRowValue(int row, String field ) throws Exception{
			
			checkRowValue( getRowWebElement( row , field )) ; 
			this.waitFor(1000).milliseconds();
			//checkField(getRowWebElement( row , field ) );
	}
	
	public void  checkRowValue(int row, String typeDetails, String field ) throws Exception{
		
		checkRowValue( getRowWebElement( row , typeDetails, field )) ;
		this.waitFor(1000).milliseconds();
	}
	
	
	//*************************************************************  Για  Μεταβολή Υπαρχουσών Εγγραφών  - Rows  ***************************************************************************************************
	
	public void setSavedRowField(int row, String field, String value) throws Exception {
			setRowField (getForModifingSaveRowWebElementXpath( row , field ) , value) ; 
	}
	
	public void setSavedRowField(int row, String typeDetails, String field, String value) throws Exception {
		setRowField (getForModifingSaveRowWebElementXpath( row , typeDetails, field ) , value) ; 
	}
	
	
	public void setSavedRowValueDropDown(int row, String field , String value) throws Exception{
			setRowValueDropDown(getForModifingSaveRowWebElementXpath( row , field ) , value );
		}

	
	public void setSavedRowValueDropDown(int row, String typeDetails, String field , String value) throws Exception{
		setRowValueDropDown(getForModifingSaveRowWebElementXpath( row , typeDetails, field ) , value );
}
	
	public void  selectSavedRowValueDropDown(int row, String field , String value) throws Exception{
			selectRowValueDropDown(getForModifingSaveRowWebElementXpath( row , field ) , value) ; 
	}
	
	public void  selectSavedRowValueDropDown(int row,  String typeDetails , String field , String value) throws Exception{
		selectRowValueDropDown(getForModifingSaveRowWebElementXpath( row , typeDetails , field ) , value) ; 
}

	
	public void  checkSavedRowValue(int row, String field ) throws Exception{
			
			checkRowValue( getForModifingSaveRowWebElementXpath( row , field )) ; 
	}
	
	//***********************************************************************************************************************************************************************************************************************
	
	/*public void saveItem(int row) throws Exception{
		LocatorElement locatorElement = rowWebElementMap.get("Αποθήκευση Γραμμής");
		String xpathLocatorRowElement = xpathRegExprlocator (row, locatorElement.getLocator());
		
		WebElementFacade saveItemButtonWebElement = element (this.getDriver().findElement(By.xpath(xpathLocatorRowElement)) );                                
		saveItemButtonWebElement.click();
	}*/
	
	
	
	
	//******************************************************************************************************************************************************************
	protected final String AlertSuccessWebElementXpathLocation = ".//div[@id='step-container']";
	
	public  WebElementFacade getAlertSuccessWebElement(){
		try{ 
			return element (  this.getDriver().findElement(By.xpath(AlertSuccessWebElementXpathLocation)) ) ;
		} catch(Exception ex){
			throw ex;
		}
	}
	
	//**************************************************************************************************************
	public  WebElementFacade getWebElement(String locator ) throws Exception {
		return this.findBy(locator ); 
		
	}
	public  WebElementFacade getWebElement( WebElementFacade parent , String locator ) throws Exception {
		return parent.findBy(locator ); 
		
	}
	//**************************************************************************************************************
	
	public  WebElementFacade getErrorWebElement(String errorField) throws Exception {
		try{ 
			LocatorElement locatorElement = errorLocatorElementMap.get(errorField);
			if ( locatorElement.getBy() == null )
				return element (this.getDriver().findElement(By.id(locatorElement.getLocator())));
			else if ( locatorElement.getBy().equals("xpath")  )
				return element (this.getDriver().findElement(By.xpath(locatorElement.getLocator())));
		} catch(Exception ex){
			throw ex;
		}
		return null;
	}
	
	public  WebElementFacade getRowErrorWebElement(int row, String errorField) throws Exception {
		try{ 
			LocatorElement locatorElement = errorLocatorElementMap.get(errorField);
			if ( locatorElement.getBy() == null )
				return element (this.getDriver().findElement(By.id(locatorElement.getRowLocator(row))));
			else if ( locatorElement.getBy().equals("xpath")  )
				return element (this.getDriver().findElement(By.xpath(locatorElement.getRowLocator(row))));
		} catch(Exception ex){
			throw ex;
		}
		return null;
	}
	
	public String getErrorFieldMessage(String errorField) throws Exception {
		try{ 
			LocatorElement locatorElement = errorLocatorElementMap.get(errorField);
			return  locatorElement.getMessage();
		} catch(Exception ex){
			throw ex;
		}
		
	}
	
	//******************************************************************************************************************************************************************
	
	 /*@FindBy(xpath=".//div[@class='modal-dialog']//*[@data-bb-handler='confirm']")
	  protected WebElementFacade confirmButtonWebElement;
	  public void confirmRemoveRow(){
		
		  confirmButtonWebElement.click();
		
	  }*/
	  
	  @FindBy(xpath=".//div[@class='modal-dialog']//*[@data-bb-handler='confirm']")
		 protected WebElementFacade confirmButtonWebElement;
		 
	public void confirmDelete(){
		  confirmButtonWebElement.click();
	 }
		 
		 
		 public void  checkSavedRowValue(int row, String typeDetails, String field ) throws Exception{
			  
			  checkRowValue( getForModifingSaveRowWebElementXpath( row , typeDetails, field )) ; 
			 }
		 
	//*************************Scroll Down/Up************************************************************************************************************

	public void scroll(String value){
		//για scroll down δίνουμε θετική τιμή
		//για scroll up δίνουμε αρνητική τιμή
		((JavascriptExecutor)this.getDriver()).executeScript("window.scrollBy(0," + value + ")");
		
	}
	//**********************Modal επιβεβαίσωης για άνοιγμα άλλης σελίδας χωρίς να αποθηκευτούν οι προηγούμενες αλλαγές***********************
	
	@FindBy(xpath=".//div[@class='modal-dialog']//*[@data-bb-handler='yesBtn']")
	protected WebElementFacade confirmModalPageButtonWebElement;
	
	@FindBy(xpath=".//div[@class='modal-dialog']")
	protected WebElementFacade confirmModalPageWebElement;
	
	public void confirmModalPage(){
		
		if(confirmModalPageWebElement.isPresent())
			confirmModalPageButtonWebElement.click();
	}
	
	//εναλλακτικά με @After
	
	public boolean isModalVisible(String message) throws Exception{
		try {
			waitFor(1000).milliseconds();
	    	WebElementFacade webElementFacade =  pageElements.getWebElementFacade("xpath",  ".//div[@class='bootbox-body']"); 
	    	if (webElementFacade.isVisible() && webElementFacade.getTextValue().equals(message))
	    		return true;
	    	
	    	return false;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	      return false;
	    } 
	}
	
	
	public void clickConfirmModalWithYes(){
		this.getDriver().findElement(By.xpath("//button[@data-bb-handler='yesBtn']")).click();
	}
	
	public void clickConfirmModalWithOk(){
		this.getDriver().findElement(By.xpath("//button[@data-bb-handler='okBtn']")).click();
	}


	public static PageElements getPageElements() {
		return pageElements;
	}
	
	public String xpathRegExprlocator(int row, String xpath){

		Pattern p = Pattern.compile("(\\d)");
		Matcher m = p.matcher(xpath);
		String newXpath=m.replaceAll(Integer.toString(row));
		return newXpath;
	}
	
	
	public String xpathRegExprlocator(String row, String xpath){

		Pattern p = Pattern.compile("(\\d)");
		Matcher m = p.matcher(xpath);
		String newXpath=m.replaceAll(row);
		return newXpath;
	}
}
