package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;

public class ClobPropertyEditor extends PropertyEditorSupport {

	
	public ClobPropertyEditor() {
		super();
	}

	@Override
	public String getAsText() {
		Clob text = (Clob)getValue();
		if (text != null) {
			try {
				return StringUtils.convertClobToString(text);
			} catch (IOException e) {
				setValue(null);
			} catch (SQLException e) {
				setValue(null);
			}
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(!StringUtils.isEmpty(text)){
			try {
				setValue(StringUtils.convertStringToClob(text));
			} catch (SQLException e) {
				setValue(null);
			}
		}
	}

}
