package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.web.utils.StringUtils;

import java.beans.PropertyEditorSupport;

public class TipoProvvDaAdottarePropertyEditor extends PropertyEditorSupport {

	private GestioneTipologicaFacade tipologicaFacade;
	
	public TipoProvvDaAdottarePropertyEditor(GestioneTipologicaFacade tipologicaFacade) {
		super();
		this.tipologicaFacade = tipologicaFacade;
	}

	@Override
	public String getAsText() {
		TipoProvvDaAdottare tipoProvvDaAdottare = (TipoProvvDaAdottare)getValue();
		if (tipoProvvDaAdottare != null) {
			return tipoProvvDaAdottare.getId().toString();
		}
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(!StringUtils.isEmpty(text)){
			setValue(tipologicaFacade.recuperaTipoProvvDaAdottareById(Integer.parseInt(text)));
		}
	}

}
