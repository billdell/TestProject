package ots.com.test.ydata.steps;

import java.util.List;
import java.util.Map;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.pages.WebElementFacade;
import net.thucydides.core.steps.ScenarioSteps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ots.com.test.ydata.data.RecordField;
import ots.com.test.ydata.pages.OnePage;

public class UserSteps extends ScenarioSteps {

	public UserSteps(Pages pages){
		super(pages);
		System.out.println("UserSteps constructor is called") ; 
	}
	
	protected OnePage  onePage  ;
	
	public OnePage getPage() { 
		return this.onePage; 
		
	}
	
	
	
	@Step
	public void sendTab(String field) throws Exception{
		onePage.sendTab(field);
	}
	
	
	@Step
	public void sendRowTab(int row, String typeDetail, String field) throws Exception{
		onePage.sendRowTab(row , typeDetail, field);
	}
	@Step
	public void setField(String field , String value) throws Exception{
		onePage.setField(field, value );
	}
	
	@Step
	public void setFieldWithOutTab(String field , String value) throws Exception{
		onePage.setFieldWithOutTab(field, value );
	}
	
	@Step
	public void setFieldAndEnter(String field , String value) throws Exception{
		onePage.setFieldWithOutTab(field, value );
	}
	
	@Step
	public void clearField(String field) throws Exception{
		onePage.clearField(field);
	}
	
	@Step
	public void clearDropDownField(String field) throws Exception{
		onePage.clearDropDownField(field);
	}
	
	@Step
	public void setNumberTypeField(String field , String value) throws Exception{
		onePage.setNumberTypeField(field, value);
	}
	
	@Step
	public void checkField(String field ){
		try {
			onePage.checkField(field);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Step
	public void selectFromDropDown(String dropDownField , String value  ) throws Exception{
		onePage.selectFromDropDown(dropDownField , value );
		
	}
	
	
	
	@Step
	public void selectOptionFromDropdown(int row, String typeDetail, String field,  String value  ) throws Exception{
		WebElement dropdown = onePage.getRowWebElement( row , typeDetail, field );
		onePage.selectOptionFromDropdown(dropdown, value );
		
	}
	
	@Step
	public void setValueFromDropDown(String dropDownField , String value  ) throws Exception{
		
		onePage.setValueFromDropDown(dropDownField, value);
	}
	
	@Step
	public boolean searchChoiceFromDropDown(String dropDownField , String value  ) throws Exception{
		return onePage.searchChoiceFromDropDown(dropDownField , value );
		
	}
	
	
	//*******************************************************************************************************
	@Step
	public boolean   isElementPresent(String field) throws Exception {
		return onePage.isElementPresent(field);
	}
	
	@Step
	public boolean   isElementPresent(String by, String locator) throws Exception {
		return onePage.isElementPresent(by, locator);
	}
	
	@Step
	public boolean   elementClassHasAttributeHiden(String field) throws Exception {
		return onePage.elementClassHasAttributeHiden(field);
	}

	@Step
	public boolean   elementHasAttributeReadOnly(String field) throws Exception {
		return onePage.elementHasAttributeReadOnly(field);
	}

	@Step
	public boolean   elementIsVisible(String field) throws Exception {
		return onePage.elementIsVisible(field);
	}
	
	@Step
	public boolean   elementIsDisplayed(String field) throws Exception {
		return onePage.elementIsDisplayed(field);
	}
	
	@Step
	public boolean   rowElementIsDisplayed(String field) throws Exception {
		return onePage.rowElementIsDisplayed(field);
	}
	//****************************************************************************************************************************
	
	
	//****************************************************************************************************************************
	
	@Step
	public void  delete() throws Exception{
		onePage.delete();
		waitFor(1000).milliseconds();
		onePage.confirmDelete();
	}
	
	@Step
	public void cancel() throws Exception {
		onePage.cancel();
	}
	
	
	
	@Step
	public void save( ){
		onePage.save();
	}
	
	@Step
	public void clickSave( ){
		onePage.clickSave();
	}
	
	
	
	@Step
	public String  saveWithConfirmAlertPopUp( ){
		return onePage.saveWithConfirmAlertPopUp();
	}
	
	
	
	
	@Step
	public void  list( ) throws Exception{
		onePage.list();
	}
	
	
	
	
	//*****************************************************************************************************************************************
	private void checkAlertForMessage(String message)  {
		try{ 
			WebElementFacade alertSuccessWebElement =onePage.getAlertSuccessWebElement();
			alertSuccessWebElement.shouldBePresent();
			alertSuccessWebElement.shouldBeVisible();
			alertSuccessWebElement.shouldContainOnlyText(message);
		} catch(Exception ex){
			throw ex;
		}
	}
	
	
	
	@Step
	public void  checkForSuccessSave( ) throws Exception {
		waitFor(2000).milliseconds();
		checkAlertForMessage(onePage.SuccessSaveMessage);
		
	}
	
	@Step
	public void  checkForSuccessModify( ) throws Exception {
			checkAlertForMessage(onePage.SuccessModifyMessage);
	}
	
	
	@Step
	public void  checkForSuccessDelete( ) throws Exception {
			checkAlertForMessage(onePage.SuccessDeleteMessage);
			
	}
	
	
	
	@Step
	public void checkAlertMessage(String message) throws Exception{
			checkAlertForMessage(message);
				
		
		
	}
	
	@Step
	public void checkErrorMessage(String errorField) throws Exception{
		
			WebElementFacade errorWebElement =onePage.getErrorWebElement(errorField) ;
			errorWebElement.shouldBePresent();
			errorWebElement.shouldBeVisible();
			errorWebElement.shouldContainOnlyText(onePage.getErrorFieldMessage(errorField));
		
		//onePage.checkErrorField(message);
	}
	
	@Step
	public void checkRowErrorMessage(int row, String errorField) throws Exception{
		
			WebElementFacade errorWebElement =onePage.getRowErrorWebElement(row, errorField) ;
			errorWebElement.shouldBePresent();
			errorWebElement.shouldBeVisible();
			errorWebElement.shouldContainOnlyText(onePage.getErrorFieldMessage(errorField));
		
		//onePage.checkErrorField(message);
	}
	
	
	@Step
	public void checkFieldAttributes( String field , List<String> attributes) throws Exception{
			
				WebElementFacade webElementFacade =  onePage.getWebElementFacade(field);
				
				for (String attribute : attributes ){
					switch ( attribute )   {
						case"readonly":			{   Assert.assertTrue(  onePage.elementHasAttributeReadOnly(webElementFacade ) )  ; break;}
					case "disable":				{	Assert.assertTrue( 	onePage.elementHasAttributeDisable(webElementFacade) ) ; break;}
					case "hiden":				{	Assert.assertTrue( 	onePage.elementClassHasAttributeHiden(webElementFacade ) ); break;}
					case "visible":				{	Assert.assertTrue(	onePage.elementIsVisible(webElementFacade) ) ;break;}
					case "displayed":			{	Assert.assertTrue(	onePage.elementIsDisplayed(webElementFacade)) ; break;}
					case "enabled":				{	Assert.assertTrue(	onePage.elementIsEnabled(webElementFacade)) ; break;}
					
					case"not readonly":			{   Assert.assertFalse( onePage.elementHasAttributeReadOnly(webElementFacade ) )  ; break;}
					case "not disable":			{	Assert.assertFalse( onePage.elementHasAttributeDisable(webElementFacade) ) ; break;}
					case "not hiden":			{	Assert.assertFalse( onePage.elementClassHasAttributeHiden(webElementFacade ) ); break;}
					case "not visible":			{	Assert.assertFalse(	onePage.elementIsVisible(webElementFacade) ) ;break;}
					case "not displayed":		{	Assert.assertFalse(	onePage.elementIsDisplayed(webElementFacade)) ; break;}
					case "not enabled":			{	Assert.assertFalse(	onePage.elementIsEnabled(webElementFacade)) ; break;}
						
					}
				}
			
			
	}
	
	@Step
	public void checkRowField(String field , String attribute) throws Exception{
		
		System.out.println("check field: " + field);
		WebElementFacade rowWebElementFacade =  onePage.getRowWebElement(0, field);
		  
		switch ( attribute ){
			case "disable":			{	Assert.assertTrue( onePage.elementIsDisplayed(rowWebElementFacade) ); break;}
		}
		
	}
		
	@Step
	public void checkGroupFieldsAttributes( List<String>  fields , List<String> attributes) throws Exception{
			
				for (String field : fields ){
					checkFieldAttributes( field ,attributes  );
				}
	}
	
	@Step
	public void checkGroupRowFields(List<String>  fields , String attribute) throws Exception{
		for (String field : fields ){
			checkRowField( field ,attribute );
		}
	}
	
	@Step
	public Map<String,String> getValues() throws Exception{
		return onePage.getPageValues();
	}
	
	
	
	@Step
	public List< Map<String,String> >  getRowValues() throws Exception{
		return onePage.getRowValues("rows");
	}
	
	@Step
	public List< Map<String,String> >  getRowValues(String typeDetail) throws Exception{
		return onePage.getRowValues(typeDetail);
	}
	
	
	@Step
	public  WebElementFacade  getRowWebElementFacade( List<RecordField> searchCriteria  ) throws Exception{
		return onePage.getRowWebElementFacade("rows" , searchCriteria );
	}
	
	
	
	//***************************************************************************************************************************************
	@Step
	public void  clickElement( String elementKey) throws Exception {
		onePage.getWebElementFacade(elementKey).click();
	}
	//***************************************************************************************************************************************
	@Step
	public  WebElementFacade  getRowWebElementFacade( String typeDetails, List<RecordField> searchCriteria  ) throws Exception{
		return onePage.getRowWebElementFacade(typeDetails , searchCriteria );
	}
		
	//**********************************************************For New Rows ****************************************************************
	@Step
	public void  setRowField(int row, String field , String value  ) throws Exception {
		
		getPage().setRowField(row , field, value);
	}
	
	@Step
	public void  setRowField(int row, String typeDetails, String field , String value  ) throws Exception {
		
		getPage().setRowField(row , typeDetails, field, value);
	}
	
	@Step
	public void setRowFieldWithOutTab(int row, String typeDetails, String field , String value) throws Exception{
		onePage.setRowFieldWithOutTab(row, typeDetails, field, value );
	}
	
	@Step
	public void  setRowValueDropDown(int row, String field , String value  ) throws Exception {
		
		getPage().setRowValueDropDown(row , field, value);
	}
	
	@Step
	public void  setRowValueDropDown(int row, String typeDetails, String field , String value  ) throws Exception {
		
		getPage().setRowValueDropDown(row , typeDetails, field, value);
	}
	
	
	@Step
	public void  selectRowValueDropDown(int row, String field , String value  ) throws Exception {
		
		getPage().selectRowValueDropDown(row , field, value);
	}
	
	@Step
	public void  selectRowValueDropDown(int row, String typeDetail, String field , String value  ) throws Exception {
		
		getPage().selectRowValueDropDown(row , typeDetail, field, value);
	}
	
	@Step
	public void  checkRowValue(int row, String field   ) throws Exception {
		//System.out.println ( "checkRowValue is called") ; 
		getPage().checkRowValue(row , field );
	}
	
	@Step
	public void  checkRowValue(int row, String typeDetails, String field   ) throws Exception { 
		getPage().checkRowValue(row , typeDetails, field );
	}
	//**********************************************************For Saved Rows ****************************************************************
	@Step
	public void  setSavedRowField(int row, String field , String value  ) throws Exception {
		
		getPage().setSavedRowField(row , field, value);
	}
	
	@Step
	public void  setSavedRowField(int row, String typeDetails, String field , String value  ) throws Exception {
		
		
		getPage().setSavedRowField(row , typeDetails, field, value);
	}
	
	@Step
	public void  setSavedRowValueDropDown(int row, String field , String value  ) throws Exception {
		
		getPage().setSavedRowValueDropDown(row , field, value);
	}
	
	@Step
	public void  setSavedRowValueDropDown(int row, String typeDetails, String field , String value  ) throws Exception {
		
		getPage().setSavedRowValueDropDown(row , typeDetails, field, value);
	}
	
	@Step
	public void  selectSavedRowValueDropDown(int row, String field , String value  ) throws Exception {
		
		getPage().selectSavedRowValueDropDown(row , field, value);
	}
	
	@Step
	public void  selectSavedRowValueDropDown(int row, String typeDetails , String field , String value  ) throws Exception {
		
		getPage().selectSavedRowValueDropDown(row , typeDetails , field, value);
	}
	
	
	
	@Step
	public void  checkSavedRowValue(int row, String field   ) throws Exception {
		//System.out.println ( "checkRowValue is called") ; 
		getPage().checkSavedRowValue(row , field );
	}
	
	@Step
	public void  checkSavedRowValue(int row, String typeDetails, String field   ) throws Exception {
		
		getPage().checkSavedRowValue(row , typeDetails, field );
	}
	
	//*******************************************************************************************************************************************************
	
	@Step 
	public void scroll( String value){
		onePage.scroll( value);
	}
	
	//**********************************************************************************************************************************************************
	
	
	
	public boolean isInmateAvailable() {
		
		WebElement cancelButtonWebElement = this.getDriver().findElement(By.xpath(".//button[@data-bb-handler='cancel']"));
		
		List<WebElement> rows = this.getDriver().findElement(By.xpath(".//table[@id='dcinmatesgrid']"))
	   			   								.findElements(By.xpath(".//tr[@role='row'][contains(@class ,'ui-widget-content')]")) ;
		if (rows.size()>0){
			cancelButtonWebElement.click();
			return true;
		}
		cancelButtonWebElement.click();
		return false;
	}
	
}
