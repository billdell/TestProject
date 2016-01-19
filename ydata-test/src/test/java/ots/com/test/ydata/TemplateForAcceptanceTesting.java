package ots.com.test.ydata;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;

import ots.com.test.ydata.AbstractTest.CheckValues;
import ots.com.test.ydata.AbstractTest.RecordsXlsData;
import ots.com.test.ydata.AbstractTest.RecordsXmlData;
import ots.com.test.ydata.AbstractTest.TestCaseId;
import ots.com.test.ydata.data.ExcelSheetDriver;
import ots.com.test.ydata.data.ReadDataRecordsFromXml;
import ots.com.test.ydata.data.TestRecord;

@RunWith(ThucydidesRunner.class)
public class TemplateForAcceptanceTesting {
	
	
/*	@Managed(uniqueSession = true)
	public WebDriver webdriver;*/
	
	@Managed(uniqueSession = true, driver="chrome")
    public WebDriver webdriver;
	
	
	@ManagedPages
	public Pages pages;
	
	public static Map<String , TestRecord> testRecordsMap ; 
	public static  TestRecord testRecord ; 
	public  static ExcelSheetDriver xlsUtil ;
	 
	
	
	//oo
	
	public static List<String> visibleDisableFields = new ArrayList<>( );
	public static  List<String> visibleReadOnlyFields = new ArrayList<>( );
	public static List<String> visibleEnableFields = new ArrayList<>( );
	public static List<String> notVisibleFields = new ArrayList<>( );
	    
	
	public void checkValues() {
		// TODO Auto-generated method stub
		System.out.println("checkValues"); 
	}
		
    
    @BeforeClass
    public static void initialize(){
    	visibleDisableFields = new ArrayList<>( );
		visibleDisableFields.add("readonly");
		visibleDisableFields.add("disable");
		visibleDisableFields.add("not hiden");
		visibleDisableFields.add("visible");
		visibleDisableFields.add("displayed");
		
		
		visibleReadOnlyFields = new ArrayList<>( );
		visibleDisableFields.add("readonly");
		visibleDisableFields.add("not hiden");
		visibleDisableFields.add("visible");
		visibleDisableFields.add("displayed");
		
		visibleEnableFields = new ArrayList<>( );
		visibleEnableFields.add("not readonly");
		visibleEnableFields.add("not disable");
		visibleEnableFields.add("not hiden");
		visibleEnableFields.add("visible");
		visibleEnableFields.add("displayed");
		
		notVisibleFields = new ArrayList<>( );
		notVisibleFields.add("not visible");
		notVisibleFields.add("not displayed");
		
		   final String ChromeDriver = "D:/eclipse/chromedriver_win32/chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", ChromeDriver);
		
		
		//System.setProperty("webdriver.driver.chrome", "D:\\eclipse\\chromedriver_win32\\chromedriver.exe"); 
    }
    
     
	@Rule
	public ConfigurationRule  configurationRule  = new ConfigurationRule();
	
		
public  class ConfigurationRule  implements TestRule {
        public ConfigurationRule()
        {
        }
        @Override
		public Statement apply(final Statement base, final Description description)
		{
			 return new Statement()
	            {
	                @Override
	                public void evaluate() throws Throwable
	                {
	                
	                	TestCaseId  tcId  = description.getAnnotation(TestCaseId.class);
	                	testRecord = null;
	                	if (tcId != null ) {
	                    	testRecord = testRecordsMap.get(tcId.value()); 
	                    	System.out.println("tcId is not null ");
	                    }
	                    
	                   //********************
	                    base.evaluate();
	                   //********************
	                    CheckValues checkValues =  description.getAnnotation(CheckValues.class);
	                    
	                    if (checkValues!=null){
	                    		System.out.println("ΕΛΕΓΧΟΣ ΤΙΜΩΝ ");
	                    		checkValues(); 
	                    		
	               
	                    }
	                    
	                    
	                }

					
	            };
			
		}
        
        
      
}



	
	//****************************************************************************************************************************
	public    static class LoadDataRecordsRuleNew  implements TestRule
	
    {
		private  Class<?> className = null;
		public LoadDataRecordsRuleNew(Class<?>  className )
		
        {
        	this.className = className;
			System.out.println(" LoadDataRecordsRuleNew Constructor  is called");
			try {
				
				
			//if (  testRecordsMap == null ) {
					System.out.println( "testRecordsMap is null ") ; 
					if ( className.isAnnotationPresent(  RecordsXmlData.class) ) { 
						System.out.println("********************read from xml" ) ;
						Annotation annotation = (Annotation)  className.getAnnotation(RecordsXmlData.class);
						RecordsXmlData recordsData = (RecordsXmlData) annotation;
						testRecordsMap = ReadDataRecordsFromXml.readDataRecordsFromXmlAsMap(  recordsData.value() );
					} 
					else if ( className.isAnnotationPresent(  RecordsXlsData.class) )    {
						System.out.println("********************read from xls" ) ;
						Annotation annotation = (Annotation)  className.getAnnotation(RecordsXlsData.class);
						
						RecordsXlsData recordsData = (RecordsXlsData) annotation;
						System.out.println(recordsData.value()) ; 
						xlsUtil = new ExcelSheetDriver(recordsData.value());
						testRecordsMap = xlsUtil.initializeTestRecords(); 
					
												
					}
		//	}
			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
        
        }
		
		
		@Override
		public Statement apply(final Statement base, Description description) {
			// TODO Auto-generated method stub
			
			 return new Statement()
	            {
	                @Override
	                public void evaluate() throws Throwable
	                {
	                
	                    
	                   //********************
	                    base.evaluate();
	                   //********************
	                }
	            };
			
		}

	
	
       
}



	
	
	//****************************************************************************************************************************
	
	
	
}
