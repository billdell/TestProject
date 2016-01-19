package ots.com.test.ydata.pages;

import java.util.ArrayList;
import java.util.List;

import ots.com.test.ydata.data.DetailRecord;
import ots.com.test.ydata.data.RecordField;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("response")
public class Response {
	
	@XStreamAsAttribute
	public String kind;

	
	@XStreamAsAttribute
	public String value;
	
	
	@XStreamAsAttribute
	public String selectRow;
	
	
	@XStreamAsAttribute
	public String message;
	
	@XStreamAsAttribute
	public String button;
	
	@XStreamImplicit(itemFieldName = "field")
    public List<RecordField> fields = new ArrayList<RecordField>();
	
	
	@XStreamImplicit(itemFieldName = "selectrow")
    public List<SelectOneRow> rows = new ArrayList<SelectOneRow>();

	@XStreamAlias(value = "row")
	DetailRecord detailRow; 
	
	
	//********************************************************************************************************
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<SelectOneRow> getRows() {
		return rows;
	}

	public void setRows(List<SelectOneRow> rows) {
		this.rows = rows;
	}
	
	public List<RecordField> getFields() {
		return fields;
	}

	public void setFields(List<RecordField> fields) {
		this.fields = fields;
	}

	public String getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(String selectRow) {
		this.selectRow = selectRow;
	}

	
	//********************************************************************************************************
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	//********************************************************************************************************
	public DetailRecord getDetailRow() {
		return detailRow;
	}

	public void setDetailRow(DetailRecord detailRow) {
		this.detailRow = detailRow;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}
	
	//********************************************************************************************************
	
}
