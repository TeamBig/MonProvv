package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.TipoProvvedimento;
import it.tesoro.monprovv.web.utils.StringUtils;

import java.beans.PropertyEditorSupport;

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
		if(!StringUtils.isEmpty(text)){
			setValue(tipologicaFacade.recuperaTipoProvvedimentoById(Integer.parseInt(text)));
		}
	}

}
