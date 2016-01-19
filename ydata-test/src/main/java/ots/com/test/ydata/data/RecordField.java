package ots.com.test.ydata.data;
import java.util.ArrayList;
import java.util.List;

import ots.com.test.ydata.pages.Response;
import ots.com.test.ydata.pages.Verify;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@XStreamAlias("field")
public class RecordField {
		@XStreamAsAttribute
		public String label;
	
		@XStreamAsAttribute
		public String value;
		
		@XStreamAsAttribute
		public String message;
		
		@XStreamAsAttribute
		public String type;
		
		@XStreamAsAttribute
		public String row;
		
		@XStreamAsAttribute
		public String checkValue;
		
		@XStreamAsAttribute
		public String scroll;
		
		@XStreamAsAttribute
		public String wait;
		
		@XStreamAsAttribute
		public String readOnly;
		
		@XStreamAlias("searchCriteria")
	    public SearchCriteria searchCriteria ;
		
		
		public String getModifyButton() {
			return modifyButton;
		}

		public void setModifyButton(String modifyButton) {
			this.modifyButton = modifyButton;
		}

		@XStreamAlias("response")
		public Response  response; 
		
		@XStreamImplicit(itemFieldName = "verify")
	    public List<Verify> verify = new ArrayList<Verify>();
		
		@XStreamAsAttribute
		public String modifyButton;
		/*@XStreamAsAttribute
		public String action;
	

		public String getAction() {
			return action;
		}*/

		public Response getResponse() {
			return response;
		}

		public void setResponse(Response response) {
			this.response = response;
		}
		
		public List<Verify> getVerify(){
			return verify;
		}
		
		public void setVerify(List<Verify> verify) {
			this.verify = verify;
		}
		
		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
		
		
		public String getMessage() {
			return message;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getType() {
			return type;
		}

		public String getCheckValue() {
			return checkValue;
		}
		
		public String getScroll() {
			return scroll;
		}
		
		public String getRow() {
			return row;
		}
		
		
		public String getWait() {
			return wait;
		}

		public String getReadOnly() {
			if ( readOnly == null ) return "false" ; 
			return readOnly;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		public SearchCriteria getSearchCriteria() {
			return searchCriteria;
		}

		public void setSearchCriteria(SearchCriteria searchCriteria) {
			this.searchCriteria = searchCriteria;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RecordField other = (RecordField) obj;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}
		
		
		
	
}
