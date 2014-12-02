package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneEntiFacade;
import it.tesoro.monprovv.model.Organo;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;




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
		if(StringUtils.isBlank(text)){
			setValue(null);
		}else{
			setValue(gestioneEntiFacade.recuperaOrganoById(Integer.parseInt(text)));
		}
	}

}
