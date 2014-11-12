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
			errors.rejectValue("flgInternoEsterno","generic.error.required" ,"Attenzione: \u00E8 necessario selezionare il tipo di Ente");
		}else{
			
			 if( "I".equals( organo.getFlgInternoEsterno() ) ) {
				 //Interna
				 if( organo.getUnitaOrgAstage() == null ){
					errors.rejectValue("unitaOrgAstage","generic.error.required" ,"Attenzione: \u00E8 necessario selezionare un Ente Interno");
				}
			 }else{
				 //Esterna
				 if( StringUtils.isEmpty( organo.getDenominazione() )){
						errors.rejectValue("denominazione","generic.error.required" ,"Attenzione: \u00E8 necessario inserire una Denominazione");
				 }
			 }
			
		}
		
		if( StringUtils.isEmpty( organo.getFlagConcertante() )){
			errors.rejectValue("flagConcertante","generic.error.required" ,"Attenzione: \u00E8 necessario indicare se l'Ente è concertante o no");
		}
		
		
	}

}
