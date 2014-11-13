package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneUtenteFacade;
import it.tesoro.monprovv.model.Ruolo;
import it.tesoro.monprovv.utils.StringUtils;

import java.beans.PropertyEditorSupport;

public class RuoloPropertyEditor extends PropertyEditorSupport {

	private GestioneUtenteFacade gestioneUtenteFacade;
	
	public RuoloPropertyEditor(GestioneUtenteFacade gestioneUtenteFacade) {
		super();
		this.gestioneUtenteFacade = gestioneUtenteFacade;
	}
	
	@Override
	public String getAsText() {
		Ruolo ruolo = (Ruolo)getValue();
		if (ruolo != null) {
			return ruolo.getId().toString();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(!StringUtils.isEmpty(text)){
			setValue(gestioneUtenteFacade.recuperaRuoloById(Integer.parseInt(text)));
		}
	}

}
