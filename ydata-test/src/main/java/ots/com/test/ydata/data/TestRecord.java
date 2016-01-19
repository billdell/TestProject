package ots.com.test.ydata.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@XStreamAlias("record")
public class TestRecord {
	
	@XStreamAsAttribute
	public String tc;

	@XStreamAsAttribute
	public String test;
	
	@XStreamImplicit(itemFieldName = "field")
    public List<RecordField> fields = new ArrayList<RecordField>();
	
	/*@XStreamAlias("rows")
	public DetailRecords rows ;*/
	
	@XStreamAlias("searchCriteria")
    public SearchCriteria searchCriteria ;
	
	@XStreamImplicit(itemFieldName = "rows")
    public List<DetailRecords>  rows= new ArrayList<DetailRecords>();

	
	//***************************************************************************************
	public Map<String, String > fieldsMap = new HashMap<String, String>();  ; 
	//public Map<String, String > rowsMap ; 
	
	
	//**************************************************************
	
	public   Map<String, String > getFieldsMapForCheckingValues(){
		
		Map<String, String >  fieldsMapForCheckingValues  = new HashMap<String, String>(); 
		 for(RecordField r : fields){ 
					if ( r.getCheckValue()  == null  ||  r.getCheckValue().equals("true")  ) 
						fieldsMapForCheckingValues.put( r.getLabel() , r.getValue()) ; 
		}
		
		 System.out.println (   "**********  testRecord.getFieldsMap().entrySet() ********* start ") ; 
		 for (  Entry<String , String>  entry :  fieldsMapForCheckingValues.entrySet() ) { 
				System.out.println (  "Key - > " + entry.getKey() + "    value : "  + "*"+ entry.getValue()+"*" ) ; 
				
			}
		 System.out.println (   "********** testRecord.getFieldsMap().entrySet() ********* end  ") ; 
		
		 
		 return fieldsMapForCheckingValues;
		
	}
	
	public void initializeFieldsMap(){ 
		fieldsMap  = new HashMap<String, String>(); 
		if (fields != null )  { 
			for(RecordField r : fields){ 
			//	System.out.println( "r.getLabel() - > " + r.getLabel() ); 
			//	System.out.println( "r.getValue() -> " + r.getValue() ); 
				
				fieldsMap.put( r.getLabel() , r.getValue()) ; 
			}
		}
		//**************************************************************
		if (rows != null ) { 
			//*******************************************************
			initializeRowsMap(); 
			//*******************************************************
			for (DetailRecords rowType : rows ) { 
				rowType.initializeRowsMap();
				for ( DetailRecord  row :  rowType.getRows() ) { 
					row.initializeFieldsMap(); 
				}
			}
		}
	}
	//**************************************************************
	
	
	
	public void addDetailRecords( DetailRecords detailRecords) { 
		 rows.add( detailRecords  ) ;
		 
	}
	
	public void addRecordField(RecordField recordField){
		fields.add(recordField);
	}
	
	//**************************************************************
	public Map<String, String> getFieldsMap() {
		return fieldsMap;
	}

	//*************************************************************************
	public void addField(String key , String value ){
		fieldsMap.put(key, value);
	}
	//*************************************************************************
	
	
	public String getValue(String field){
		return fieldsMap.get(field); 
	}
	//***************************************************************************************
	
	public Map<String,  DetailRecords> rowsMap = new HashMap<String, DetailRecords>();
	public void initializeRowsMap(){ 
		rowsMap  = new HashMap<String, DetailRecords>(); 
		for(DetailRecords r : rows){ 
			rowsMap.put( r.getType() , r) ; 
		}
		
	}
	
	public List<DetailRecords> getRows() {
		
		return rows;
	}

	public Map<String, DetailRecords> getRowsMap() {
		return rowsMap;
	}

	public DetailRecords getDetailRecords(String type){
		if ( rowsMap != null ) 
			return rowsMap.get(type); 
		else
			return null;
	}
	
	//***************************************************************************************
	public List<DetailRecord>  getDetailRecords(){
		if (getRows() != null )
			if (getRows().size() > 0 )	return getRows().get(0).getRows();
		else
			return null;
		return null;
	}
	
	public List<DetailRecord>  getRowTypeDetailRecords(String type ){
		 return rowsMap.get(type).getRows();
	}
	//***************************************************************************************
	
	public DetailRecord  getDetailRecord(String type , String order){
		return rowsMap.get(type).getRow(order); 
	}
	public DetailRecord  getDetailRecord(String order){
		return  rows.get(0).getRow(order); 
		
	}
	public DetailRecord  getDetailRecord(int order){
		return getDetailRecord(  String.valueOf(order) ) ; 
		
	}
	
	public DetailRecord  getDetailRecord(){
		return  rows.get(0).getRows().get(0); 
	}
	
	//***************************************************************************************
	
	public String getTc() {
		return tc;
	}


	public void setTc(String tc) {
		this.tc = tc;
	}


	public List<RecordField> getFields() {
		return fields;
	}
	
	public void setFields(List<RecordField> fields) {
		this.fields = fields;
	}
	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	//****************************************************************************************************
	public String getSearchCriterio( String field){
		if (searchCriteria != null  ){ 
			return searchCriteria.getSearchCriterio(field); 
		}
		return null;	
	}
	//****************************************************************************************************

	
	
}
