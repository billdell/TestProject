package ots.com.test.ydata.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@XStreamAlias("rows")
public class DetailRecords {
	
	@XStreamImplicit(itemFieldName = "row")
	public List<DetailRecord> rows  = new ArrayList<DetailRecord>();

	
	@XStreamAsAttribute
	public String type;
	
	
	public List<DetailRecord> getRows() {
		return rows;
	}

	public void setRows(List<DetailRecord> rows) {
		this.rows = rows;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	//**********************************************************************************************
	public void addDetailRecord(DetailRecord detailRecord){
		rows.add(detailRecord) ; 
	}
	//**********************************************************************************************
	public Map<String,  DetailRecord> rowsMap = new HashMap<String, DetailRecord>();
	public void initializeRowsMap(){ 
		rowsMap  = new HashMap<String, DetailRecord>(); 
		for(DetailRecord r : rows){ 
			rowsMap.put( r.getOrder() , r) ; 
		}	
	}
	
	public DetailRecord getRow( String order){
		return rowsMap.get(order); 
	}
	public DetailRecord getRow( int order){
		return rowsMap.get(order); 
	}
	
	public Map<String, DetailRecord> getRowsMap() {
		return rowsMap;
	}
	
	//**********************************************************************************************
	

}
