package ots.com.test.ydata.data;

import java.util.ArrayList;
import java.util.List;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;



@XStreamAlias("records")
public class DataRecords {

	@XStreamImplicit(itemFieldName = "record")
public List<TestRecord> records  = new ArrayList<TestRecord>();

	public List<TestRecord> getRecords() {
		return records;
	}

	public void setRecords(List<TestRecord> records) {
		this.records = records;
	}
	
	
}