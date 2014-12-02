package it.tesoro.monprovv.web.propertyeditors;

import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;


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
		if(StringUtils.isBlank(text)){
			setValue(null);
		}else{
			setValue(tipologicaFacade.recuperaTipoProvvDaAdottareById(Integer.parseInt(text)));
		}
	}

}
