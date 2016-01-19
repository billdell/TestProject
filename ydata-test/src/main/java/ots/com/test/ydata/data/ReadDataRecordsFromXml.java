package ots.com.test.ydata.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ots.com.test.ydata.pages.ReadWebElementsFromXml;
import ots.com.test.ydata.pages.Response;
import ots.com.test.ydata.pages.SelectOneRow;

import com.thoughtworks.xstream.XStream;

public class ReadDataRecordsFromXml {
	
static public List<TestRecord>   readDataRecordsFromXmlAsList(String xmlFile) throws FileNotFoundException{
		
		Class myClass = ReadWebElementsFromXml.class;
		ClassLoader loader = myClass.getClassLoader();
		URL myURL = loader.getResource(xmlFile);
		String path = myURL.getPath();
		
		
		//FileReader reader = new FileReader(path); 
		InputStream inputStream = new FileInputStream(path);
		
		
		//FileReader reader = new FileReader("C:/adia.xml"); 
		//FileReader reader = new FileReader(xmlFile); 
		XStream xstream = new XStream();
		xstream.processAnnotations(DataRecords.class);
	  	xstream.processAnnotations(RecordField.class);
	  	xstream.processAnnotations(TestRecord.class);
	 	xstream.processAnnotations(DetailRecord.class);
	  	xstream.processAnnotations(DetailRecords.class);
	  	xstream.processAnnotations(SearchCriteria.class);
	  	//*************************************************************************************
	  	xstream.processAnnotations(Response.class);
	  	xstream.processAnnotations(SelectOneRow.class);
	  	
	  	//*************************************************************************************
	  	
	  	List<TestRecord> testRecordList = new ArrayList<TestRecord>(); 
	  	//DataRecords  dataRecords = (DataRecords) xstream.fromXML(reader);
	  	DataRecords  dataRecords = (DataRecords) xstream.fromXML(inputStream);
	  	
	  	Iterator<TestRecord> iter = dataRecords.getRecords().iterator();
	  	while(iter.hasNext()) {
	  	
	  		TestRecord  testRecord = iter.next();
	  		testRecord.initializeFieldsMap() ; 
	  		testRecordList.add(testRecord) ;
	  			
	  	}
	  	return testRecordList;
		
}
//********************************************************************************************************************************************************
static public Map<String,TestRecord>   readDataRecordsFromXmlAsMap(String xmlFile) throws FileNotFoundException{
	
	List<TestRecord> testRecordList  = readDataRecordsFromXmlAsList( xmlFile);
	Map<String,TestRecord> testRecordMap = new HashMap<String,TestRecord >();
	
	for (TestRecord t :testRecordList ) { 
		testRecordMap.put(t.getTc() ,   t); 
	}
	return testRecordMap;
}


//********************************************************************************************************************************************************

/*static public Map<String,TestRecord>   readDataRecordsFromXmlAsMap(String xmlFile) throws FileNotFoundException{
	
	Class myClass = ReadWebElementsFromXml.class;
	ClassLoader loader = myClass.getClassLoader();
	URL myURL = loader.getResource(xmlFile);
	String path = myURL.getPath();
	FileReader reader = new FileReader(path); 
	
	//FileReader reader = new FileReader("C:/adia.xml"); 
	//FileReader reader = new FileReader(xmlFile); 
	
	
	XStream xstream = new XStream();
	xstream.processAnnotations(DataRecords.class);
  	xstream.processAnnotations(RecordField.class);
  	xstream.processAnnotations(TestRecord.class);
  	xstream.processAnnotations(DetailRecord.class);
  	xstream.processAnnotations(DetailRecords.class);
  	
  	
 
  	List<TestRecord> testRecordList = new ArrayList<TestRecord>(); 
  	
  	Map<String,TestRecord> testRecordMap = new HashMap<String,TestRecord >();
  	
  	DataRecords  dataRecords = (DataRecords) xstream.fromXML(reader);
  	
  
  	Iterator<TestRecord> iter = dataRecords.getRecords().iterator();
  	while(iter.hasNext()) {
  		
  	
  		TestRecord  testRecord = iter.next();
  		testRecord.initializeFieldsMap();
  		
  		//List<RecordField> recordFields = testRecord.getFields();
  		testRecordMap.put(testRecord.getTc() , testRecord); 
  		
  		//testRecordList.add(testRecord) ;
  			
  	}
  	return testRecordMap;
  	
  	//List<String[]>  invoices= new ArrayList<String[]>();
	List<List<String>> invoices = new ArrayList<List<String>>();
	invoices.add(new ArrayList<String>(Arrays.asList("ΑΠΟΔ" , "12" ))) ;
	invoices.add(new ArrayList<String>(Arrays.asList("ΑΠΟΔ" , "34" ))) ;
	invoices.add(new ArrayList<String>(Arrays.asList("ΑΠΟΔ" , "56" ))) ;
	invoices.add(new ArrayList<String>(Arrays.asList("ΑΠΟΔ" , "67" ))) ;
	
	invoices.get(0).get(0); //O Τύπος
	invoices.get(0).get(1);//Ο Κωδικός
	
	
	for(List<String>  invoice : invoices  ){
		invoice.get(0); //O Τύπος
		invoice.get(1); //Ο Κωδικός
	}
	
	
	
	
}*/



}
