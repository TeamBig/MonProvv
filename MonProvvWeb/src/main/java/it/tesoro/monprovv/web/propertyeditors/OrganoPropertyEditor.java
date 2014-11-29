package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneEntiFacade;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.utils.StringUtils;

import java.beans.PropertyEditorSupport;

public class OrganoPropertyEditor extends PropertyEditorSupport {

	private GestioneEntiFacade gestioneEntiFacade;
	
	public OrganoPropertyEditor(GestioneEntiFacade gestioneEntiFacade) {
		super();
		this.gestioneEntiFacade = gestioneEntiFacade;
	}
	
	@Override
	public String getAsText() {
		Organo organo = (Organo)getValue();
		if (organo != null) {
			return organo.getId().toString();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.isNotEmpty(text)){
			setValue(gestioneEntiFacade.recuperaOrganoById(Integer.parseInt(text)));
		}
	}

}
