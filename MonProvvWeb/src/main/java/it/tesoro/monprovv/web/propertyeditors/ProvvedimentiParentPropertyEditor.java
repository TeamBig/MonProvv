package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.Provvedimento;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;


public class ProvvedimentiParentPropertyEditor extends PropertyEditorSupport {

	private GestioneTipologicaFacade tipologicaFacade;
	
	public ProvvedimentiParentPropertyEditor(GestioneTipologicaFacade tipologicaFacade) {
		super();
		this.tipologicaFacade = tipologicaFacade;
	}

	@Override
	public String getAsText() {
		Provvedimento provvedimento = (Provvedimento)getValue();
		if (provvedimento != null) {
			return provvedimento.getId().toString();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.isBlank(text)){
			setValue(null);
		}else{
			setValue(tipologicaFacade.recuperaProvvedimentoById(Integer.parseInt(text)));
		}
	}


}
