package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneUtenteFacade;
import it.tesoro.monprovv.model.UtenteAstage;
import it.tesoro.monprovv.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class UtenteAstagePropertyEditor extends PropertyEditorSupport {

	private GestioneUtenteFacade gestioneUtenteFacade;
	
	public UtenteAstagePropertyEditor(GestioneUtenteFacade gestioneUtenteFacade) {
		super();
		this.gestioneUtenteFacade = gestioneUtenteFacade;
	}
	
	@Override
	public String getAsText() {
		UtenteAstage utenteAstage = (UtenteAstage)getValue();
		if (utenteAstage != null) {
			return utenteAstage.getId().toString();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(!StringUtils.isEmpty(text)){
			setValue(gestioneUtenteFacade.recuperaUtenteAstageById(Integer.parseInt(text)));
		}
	}

}
