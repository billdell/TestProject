package ots.com.test.ydata.pages;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("grid")
public class Grid {
	
	@XStreamAsAttribute
	public String label;

	@XStreamAsAttribute
	public String value;
	
	
	@XStreamAsAttribute
	public String rows;
	
	
	
	@XStreamAsAttribute
	public String wait ;
	
	@XStreamAsAttribute
	public String  waitElement;
	
	@XStreamAsAttribute
	public String implicitWait;
	
	
	           
            
	@XStreamImplicit(itemFieldName = "column")
    public List<Column> columns = new ArrayList<Column>();
	
	
	//**************************************************************************************************************************************

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getWait() {
		return wait;
	}

	public void setWait(String wait) {
		this.wait = wait;
	}

	public String getWaitElement() {
		return waitElement;
	}

	public void setWaitElement(String waitElement) {
		this.waitElement = waitElement;
	}

	public String getImplicitWait() {
		return implicitWait;
	}

	public void setImplicitWait(String implicitWait) {
		this.implicitWait = implicitWait;
	}
	

	//**************************************************************************************************************************************
	
	
	public String getColumnLocator(  String columnLabel ) {
		
		if ( columns == null ) return null ;  
		for  ( Column column : columns ){ 
			if ( column.getLabel().equals(columnLabel ) ) 
				return column.getLocator(); 
		}
		return null ; 
		
	}
	
	
public String getColumnAction(  String columnLabel ) {
		
		if ( columns == null ) return null ;  
		for  ( Column column : columns ){ 
			if ( column.getLabel().equals(columnLabel ) ) 
				return column.getAction(); 
		}
		return null ; 
		
	}
	
	
	
	public String getGridWaitType(){
	  return this.getWait();
	}
	public String getGridWaitElement(){
		  return this.getWaitElement();
	}
	
	public String getGridImplicitWait(){
		  return  this.getImplicitWait();
	}
	
	
	
	
	
	
	
	
	
	
	

}
