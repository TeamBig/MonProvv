package it.tesoro.monprovv.web.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DataPropertyEditor extends PropertyEditorSupport {

	public void setAsText(String value) {
		try {
			if (!StringUtils.isBlank(value)) {
				SimpleDateFormat sdp = new SimpleDateFormat("dd/MM/yyyy");
				sdp.setLenient(false);
				setValue(sdp.parse(value));
			} else {
				setValue(null);
			}
		} catch (ParseException e) {
			try {
				setValue(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1950"));
			} catch (ParseException e1) {
				setValue(null);
			}
		}
	}

	public String getAsText() {	
		Object o = getValue();
		if (o != null) {
			return new SimpleDateFormat("dd/MM/yyyy").format((Date) getValue());
		}
		return null;
	}

}
