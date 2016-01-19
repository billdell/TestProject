package ots.com.test.ydata.pages;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("verify")
public class Verify {
	
	@XStreamAsAttribute
	public String label;
	
	@XStreamAsAttribute
	public String kind;
	
	@XStreamAsAttribute
	public String row;
	
	@XStreamAsAttribute
	public String value;
	
	/*@XStreamImplicit(itemFieldName = "field")
    public List<RecordField> fields = new ArrayList<RecordField>();*/
	
	
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
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	
	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}
	
	
	/*public List<RecordField> getFields() {
		return fields;
	}

	public void setFields(List<RecordField> fields) {
		this.fields = fields;
	}*/
}
