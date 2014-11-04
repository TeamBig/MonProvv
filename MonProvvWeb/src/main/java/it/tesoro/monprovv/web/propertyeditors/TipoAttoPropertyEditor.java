package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.TipoAtto;
import it.tesoro.monprovv.utils.StringUtils;

import java.beans.PropertyEditorSupport;

public class TipoAttoPropertyEditor extends PropertyEditorSupport {

	private GestioneTipologicaFacade tipologicaFacade;
	
	public TipoAttoPropertyEditor(GestioneTipologicaFacade tipologicaFacade) {
		super();
		this.tipologicaFacade = tipologicaFacade;
	}

	@Override
	public String getAsText() {
		TipoAtto tipoAtto = (TipoAtto)getValue();
		if (tipoAtto != null) {
			return tipoAtto.getId().toString();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(!StringUtils.isEmpty(text)){
			setValue(tipologicaFacade.recuperaTipoAttoById(Integer.parseInt(text)));
		}
	}

	
}
