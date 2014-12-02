package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.Stato;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;



public class StatoPropertyEditor extends PropertyEditorSupport {

	private GestioneTipologicaFacade tipologicaFacade;
	
	public StatoPropertyEditor(GestioneTipologicaFacade tipologicaFacade) {
		super();
		this.tipologicaFacade = tipologicaFacade;
	}

	@Override
	public String getAsText() {
		Stato stato = (Stato)getValue();
		if (stato != null) {
			return stato.getId().toString();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.isBlank(text)){
			setValue(null);
		}else{
			setValue(tipologicaFacade.recuperaStatoById(Integer.parseInt(text)));
		}
	}

	
}
