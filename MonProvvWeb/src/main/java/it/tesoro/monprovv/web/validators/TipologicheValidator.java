package it.tesoro.monprovv.web.validators;

import it.tesoro.monprovv.dto.GestioneTipologicheDto;
import it.tesoro.monprovv.utils.StringUtils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TipologicheValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		GestioneTipologicheDto o = (GestioneTipologicheDto)target;
		switch (o.getIdSchelta()){
		case 1: if(StringUtils.isEmpty(o.getDenominazioneGoverno()))
					errors.rejectValue("denominazioneGoverno","generic.error.required" ,"Il campo 'Denominazione' \u00E8 obbligatorio");
				break;
		case 2: if(StringUtils.isEmpty(o.getCodiceTipoAtto()))
					errors.rejectValue("codiceTipoAtto","generic.error.required" ,"Il campo 'Codice' \u00E8 obbligatorio");
				if(StringUtils.isEmpty(o.getDescrizioneTipoAtto()))
					errors.rejectValue("descrizioneTipoAtto","generic.error.required" ,"Il campo 'Descrizione' \u00E8 obbligatorio");
				break;
		case 3: if(StringUtils.isEmpty(o.getDescrizioneTipoProv()))
					errors.rejectValue("descrizioneTipoProv","generic.error.required" ,"Il campo 'Descrizione' \u00E8 obbligatorio");
				break;
		}
		
	}
}
