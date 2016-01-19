package ots.com.test.ydata.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("row")
public class DetailRecord {
	
	@XStreamImplicit(itemFieldName = "field")
    public List<RecordField> fields = new ArrayList<RecordField>();
	
	@XStreamAsAttribute
	public String order;
	
	@XStreamAsAttribute
	public String action;
	
	
	
	@XStreamAlias("searchCriteria")
    public SearchCriteria searchCriteria ;
	
	//*************************************************************************************
	public Map<String, String > fieldsMap ; 
	public void initializeFieldsMap(){ 
		if ( fields != null )  { 
			fieldsMap  = new HashMap<String, String>(); 
			for(RecordField r : fields){ 
				fieldsMap.put( r.getLabel() , r.getValue()) ; 
			}
		}
	}

	public String getValue(String field){
		return fieldsMap.get(field); 
	}
	
	public void addField(String key , String value ){
		fieldsMap.put(key, value);
	}
	
	public void addRecordField(RecordField recordField){
		fields.add(recordField);
	}
	//*************************************************************************************
	
	public String getOrder() {
		return order;
	}


	public void setOrder(String order) {
		this.order = order;
	}


	public Map<String, String> getFieldsMap() {
		return fieldsMap;
	}


	public void setFieldsMap(Map<String, String> fieldsMap) {
		this.fieldsMap = fieldsMap;
	}


	public List<RecordField> getFields() {
		return fields;
	}

	public void setFields(List<RecordField> fields) {
		this.fields = fields;
	}

	public String getAction() {
		return action;
	}

	public SearchCriteria getSearchCriteria() {
		return searchCriteria;
	}

	
public   Map<String, String > getFieldsMapForCheckingValues(){
		
		Map<String, String >  fieldsMapForCheckingValues  = new HashMap<String, String>(); 
		 for(RecordField r : fields){ 
			 
					if ( r.getCheckValue()  == null  ||  r.getCheckValue().equals("true")  ) { 
						fieldsMapForCheckingValues.put( r.getLabel() , r.getValue()) ; 
					} 
					
		}
		
		 /*System.out.println (   "**********  testRecord.getFieldsMap().entrySet() ********* start ") ; 
		 for (  Entry<String , String>  entry :  fieldsMapForCheckingValues.entrySet() ) { 
				System.out.println (  "Key - > " + entry.getKey() + "    value : "  + "*"+ entry.getValue()+"*" ) ; 
				
			}
		 System.out.println (   "********** testRecord.getFieldsMap().entrySet() ********* end  ") ; 
		*/
		 
		 return fieldsMapForCheckingValues;
		
	}
	
	

}
