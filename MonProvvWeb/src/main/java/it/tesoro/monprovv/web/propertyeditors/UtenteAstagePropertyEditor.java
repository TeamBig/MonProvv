package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneUtenteFacade;
import it.tesoro.monprovv.model.UtenteAstage;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;

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
		if(StringUtils.isBlank(text)){
			setValue(null);
		}else{
			setValue(gestioneUtenteFacade.recuperaUtenteAstageById(Integer.parseInt(text)));
		}
	}

}
