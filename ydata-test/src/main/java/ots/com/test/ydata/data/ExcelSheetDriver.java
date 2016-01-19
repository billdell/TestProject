package ots.com.test.ydata.data;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class ExcelSheetDriver {

  static Sheet masterWrksheet;
  static Sheet detailsWrksheet;
  
  static List<TestRecord> testRecords = new ArrayList<TestRecord>(); 
  
  // create workbook name
  static Workbook wrkbook = null;
//  static Hashtable dict = new Hashtable();
  
  static Hashtable<Integer,String>masterDict = new Hashtable<Integer,String>();
  static Hashtable<Integer,String>detailsDict = new Hashtable<Integer,String>();
  
 // static Hashtable detailsDict = new Hashtable();
  
  //Create a Constructor
  public ExcelSheetDriver(){
	  
  }
  
  public ExcelSheetDriver(String excelSheetPath) throws BiffException, IOException {
    //Initialize the workbook
	  //****************************************************************************************************************************
	  readXls( excelSheetPath ); 
}
 
  
  public static void  readXls(String excelSheetPath ) throws BiffException, IOException{
	  	Class myClass = ExcelSheetDriver.class;
		ClassLoader loader = myClass.getClassLoader();
		URL myURL = loader.getResource(excelSheetPath);
		String path = myURL.getPath();
		wrkbook = Workbook.getWorkbook(new File(path));
		masterWrksheet = wrkbook.getSheet("master");
		detailsWrksheet = wrkbook.getSheet("details");
		  
  }
  
  public static int getMasterRowCount() {
    return masterWrksheet.getRows();
  }
  
  public static int getDetailsRowCount() {
	    return detailsWrksheet.getRows();
	  }
  

  
  public static String readMasterWrksheetCell(int column, int row) {
	    return masterWrksheet.getCell(column, row).getContents();
	  }
  
  public static String readDetailsWrksheetCell(int column, int row) {
	    return detailsWrksheet.getCell(column, row).getContents();
	  }

  
  public static void initMasterColumnDictionary() {
    for (int col = 0; col < masterWrksheet.getColumns(); col++) {
    	masterDict.put(col , readMasterWrksheetCell(col, 0) );
    	
    }
  }

  public static void  initDetailsColumnDictionary() {
	    for (int col = 0; col < detailsWrksheet.getColumns(); col++) {
	    	detailsDict.put(col  , readDetailsWrksheetCell(col, 0) );
	    }
	  }

  
  
  
  

  public static Map<String,TestRecord>   initializeTestRecords(){
		testRecords = new ArrayList<TestRecord>(); 
		initMasterColumnDictionary();
		initDetailsColumnDictionary();
		
		for (int rowCnt = 1; rowCnt < getMasterRowCount(); rowCnt++) {
		  TestRecord testRecord = new TestRecord(); 
		  
		  String tc= readMasterWrksheetCell(0, rowCnt); 
		  String test= readMasterWrksheetCell(1, rowCnt); 
		  testRecord.setTc(tc); 
		  testRecord.setTest(test); 
		  System.out.println ( tc) ; 
		  System.out.println ( test) ; 
		  
		  
		  for (int col = 2; col < masterWrksheet.getColumns(); col++) {
			  RecordField recordField = new RecordField();
			  String value = readMasterWrksheetCell(col,rowCnt );
			  recordField.setLabel(masterDict.get(col));
			  recordField.setValue(value);
			  testRecord.addRecordField(recordField);
			  //testRecord.addField(masterDict.get(col) , value);
		  }
		  testRecords.add(testRecord);
		  
		  //System.out.println (  readMasterWrksheetCell(2, rowCnt) )  ; 
	}
	  
	
	  
	  //*****************************************************************************
	 
	  for (TestRecord testRecord : testRecords){
		  
		  String  testRecordTC =  testRecord.getTc(); // Ποιο είναι το TestCase
		  
		  for (int rowCnt = 1; rowCnt < getDetailsRowCount(); rowCnt++) {
			 
			  String tc = readDetailsWrksheetCell(0,rowCnt ) ; // Για ποιο testcase αναφέρεται η συγκεκριμένη 
			  if ( !tc.equals(testRecordTC)) continue; 
			  // Εφόσον για το συγκεκριμένο TestCase υπάρχει detail
			  //**************************************************************************
			  String type = readDetailsWrksheetCell(1,rowCnt ) ; 	//Προσδιορίζουμε τον τύπο του detail 
			  String order = readDetailsWrksheetCell(2,rowCnt ) ; 	//Προσδιορίζουμε την σειρά του detail
			  // Δημιουργούμε το detail και αρχικοποιούμε το MapFields( label , value ) 
			  DetailRecord detailRecord  = new DetailRecord(); 
			  detailRecord.setOrder( order ) ; 
			  for (int col = 3; col < detailsWrksheet.getColumns(); col++) {
				  
				  RecordField recordField = new RecordField();
				  String value = readDetailsWrksheetCell(col,rowCnt );
				  recordField.setLabel(detailsDict.get(col));
				  recordField.setValue(value);
				 // System.out.println("label - > " + detailsDict.get(col) + " ------  value -> " + value );
				  detailRecord.addRecordField(recordField);
				 // detailRecord.addField(detailsDict.get(col) , value);
			  }
			  
			  //*****************************************************************************
			 
			 Boolean lbexists = false;
			  if (  testRecord.getRows().size() > 0 ){ 
				  for ( DetailRecords detailRecords : testRecord.getRows() ){
					  // Εαν για το συγκεκριμένο testRecord υπάρχουν και άλλα details του αυτού τύπου 
					  // σε αυτό τον τύπο details προσθέτουμε και το detail που φτιάξαμε προηγουένως
					  if (detailRecords.getType().equals(type)) { 
						  //System.out.println("Υπάρχει "); 
						  detailRecords.addDetailRecord(detailRecord);
						  lbexists = true;
						  break; 
					  }
				  }
			   }
			  // Εαν για το συγκεκριμένο testRecord ΔΕΝ  υπάρχουν  άλλα details του αυτού τύπου 
			  // δημιουργούμε έναν νέο  τύπο details στον οποίο  προσθέτουμε και το detail 
			  // που φτιάξαμε προηγουένως
		
			  if ( !lbexists ) {
				  //*******************************************************************
				 // System.out.println("Δεν υπάρχει "); 
				//*******************************************************************
				  DetailRecords detailRecords = new DetailRecords(); 
				  detailRecords.setType(type);
				  detailRecords.addDetailRecord(detailRecord);
				  testRecord.addDetailRecords(detailRecords);
			  }
			  
			  //*****************************************************************************
		  }
		  
	  }

	  /*for (TestRecord testRecord : testRecords){
		  testRecord.initializeFieldsMap(); 
	  }*/
	 
	  Map<String,TestRecord> testRecordMap = new HashMap<String,TestRecord >();
	  for (TestRecord t :testRecords ) { 
		 t.initializeFieldsMap(); 
		 testRecordMap.put(t.getTc() ,   t); 
	  }
	  return testRecordMap;
	  
}



}
  
  
  
  
