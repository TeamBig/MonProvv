package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.TipoProvvedimento;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;



public class TipoProvvedimentoPropertyEditor extends PropertyEditorSupport {

	private GestioneTipologicaFacade tipologicaFacade;
	
	public TipoProvvedimentoPropertyEditor(GestioneTipologicaFacade tipologicaFacade) {
		super();
		this.tipologicaFacade = tipologicaFacade;
	}

	@Override
	public String getAsText() {
		TipoProvvedimento tipoProvvedimento = (TipoProvvedimento)getValue();
		if (tipoProvvedimento != null) {
			return tipoProvvedimento.getId().toString();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.isBlank(text)){
			setValue(null);
		}else{
			setValue(tipologicaFacade.recuperaTipoProvvedimentoById(Integer.parseInt(text)));
		}
	}

}
