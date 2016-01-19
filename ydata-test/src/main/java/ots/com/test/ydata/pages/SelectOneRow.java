package ots.com.test.ydata.pages;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@XStreamAlias("selectrow")
public class SelectOneRow {
	
	@XStreamImplicit(itemFieldName = "column")
    public List<Column> columns = new ArrayList<Column>();

	//*************************************************************************
	
	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	//*************************************************************************
	
	

}
