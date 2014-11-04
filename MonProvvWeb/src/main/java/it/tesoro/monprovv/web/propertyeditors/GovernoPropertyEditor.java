package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.utils.StringUtils;

import java.beans.PropertyEditorSupport;

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
		if(!StringUtils.isEmpty(text)){
			setValue(tipologicaFacade.recuperaGovernoById(Integer.parseInt(text)));
		}
	}

}
