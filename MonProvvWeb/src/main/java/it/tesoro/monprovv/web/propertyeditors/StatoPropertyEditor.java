package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.utils.StringUtils;

import java.beans.PropertyEditorSupport;

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
		if(!StringUtils.isEmpty(text)){
			setValue(tipologicaFacade.recuperaStatoById(Integer.parseInt(text)));
		}
	}

	
}
