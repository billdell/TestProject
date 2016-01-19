package ots.com.test.ydata.data;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("searchCriteria")
public class SearchCriteria {

	@XStreamImplicit(itemFieldName = "field")
    public List<RecordField> fields = new ArrayList<RecordField>();
	
	
	
	public List<RecordField> getFields() {
		return fields;
	}



	public String getSearchCriterio( String field){
		if (fields != null  ){ 
			for ( RecordField recordField :fields  ) { 
				if (recordField.getLabel().equals(field )) return recordField.getValue();
			}
			
		}
		return null;	
	}
	
	
}
