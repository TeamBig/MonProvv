package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.Governo;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;


public class GovernoPropertyEditor extends PropertyEditorSupport {

	private GestioneTipologicaFacade tipologicaFacade;
	
	public GovernoPropertyEditor(GestioneTipologicaFacade tipologicaFacade) {
		super();
		this.tipologicaFacade = tipologicaFacade;
	}

	@Override
	public String getAsText() {
		Governo governo = (Governo)getValue();
		if (governo != null) {
			return governo.getId().toString();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.isBlank(text)){
			setValue(null);
		}else{
			setValue(tipologicaFacade.recuperaGovernoById(Integer.parseInt(text)));
		}
	}

}
