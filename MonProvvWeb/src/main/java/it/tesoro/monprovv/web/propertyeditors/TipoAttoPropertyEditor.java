package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.TipoAtto;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;



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
		if(StringUtils.isBlank(text)){
			setValue(null);
		}else{
			setValue(tipologicaFacade.recuperaTipoAttoById(Integer.parseInt(text)));
		}
	}

	
}
