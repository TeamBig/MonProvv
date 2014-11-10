package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.utils.StringUtils;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;

public class ClobPropertyEditor extends PropertyEditorSupport {

	private GestioneTipologicaFacade tipologicaFacade;
	
	public ClobPropertyEditor(GestioneTipologicaFacade tipologicaFacade) {
		super();
		this.tipologicaFacade = tipologicaFacade;
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
			setValue(tipologicaFacade.creaClob(text));
		}
	}

}
