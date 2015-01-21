package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneEntiFacade;
import it.tesoro.monprovv.model.UnitaOrgAstage;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;

public class UnitaOrgAstagePropertyEditor extends PropertyEditorSupport {

	private GestioneEntiFacade gestioneEntiFacade;
	
	public UnitaOrgAstagePropertyEditor(GestioneEntiFacade gestioneEntiFacade) {
		super();
		this.gestioneEntiFacade = gestioneEntiFacade;
	}

	@Override
	public String getAsText() {
		UnitaOrgAstage unitaOrgAstage = (UnitaOrgAstage)getValue();
		if (unitaOrgAstage != null) {
			return unitaOrgAstage.getId().toString();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.isBlank(text)){
			setValue(null);
		}else{
			setValue(gestioneEntiFacade.recuperaUnitaOrgAstageById(Integer.parseInt(text)));
		}
	}

}