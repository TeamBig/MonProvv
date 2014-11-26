package it.tesoro.monprovv.web.validators;

import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.utils.StringUtils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EnteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Organo organo = (Organo)target;
		
		
		
		if(StringUtils.isEmpty( organo.getFlgInternoEsterno() )){
			errors.rejectValue("flgInternoEsterno","interno.esterno.obbligatorio" ,"Attenzione: \u00E8 necessario selezionare il tipo di Organo");
		}else{
			
			 if( "I".equals( organo.getFlgInternoEsterno() ) ) {
				 //Interna
				 if( organo.getUnitaOrgAstage() == null ){
					errors.rejectValue("unitaOrgAstage","organo.area.stage.obbligatorio" ,"Attenzione: \u00E8 necessario selezionare un Organo Interno");
				}
			 }else{
				 //Esterna
				 if( StringUtils.isEmpty( organo.getDenominazione() )){
						errors.rejectValue("denominazione","denominazione.organo.esterno.obbiligatorio" ,"Attenzione: \u00E8 necessario inserire una Denominazione");
				 }
			 }
			
		}
		
		if( StringUtils.isEmpty( organo.getFlagConcertante() )){
			errors.rejectValue("flagConcertante","flag.concertante.obbligatorio" ,"Attenzione: \u00E8 necessario indicare se l'Organo è concertante o no");
		}
		
		
	}

}
