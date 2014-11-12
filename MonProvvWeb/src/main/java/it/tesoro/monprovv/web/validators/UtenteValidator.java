package it.tesoro.monprovv.web.validators;

import it.tesoro.monprovv.model.Utente;
import it.tesoro.monprovv.utils.StringUtils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UtenteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Utente utente = (Utente)target;
		
		if(StringUtils.isEmpty( utente.getFlagIntEst() )){
			errors.rejectValue("flagIntEst","generic.error.required" ,"Attenzione: \u00E8 necessario selezionare il tipo di utente");
		}else{
			
			if("I".equals( utente.getFlagIntEst() )){
				
				if(StringUtils.isEmpty(utente.getNominativo()))
					errors.rejectValue("cognome","generic.error.required" ,"Attenzione: \u00E8 necessario inserire un nominativo");
				else
				if(StringUtils.isEmpty(utente.getUtenteAstage()))
					errors.rejectValue("cognome","generic.error.required" ,"Attenzione: \u00E8 necessario inserire un nominativo valido");
				
			}else{
				
				if(StringUtils.isEmpty(utente.getCognome()))
					errors.rejectValue("cognome","generic.error.required" ,"Attenzione: \u00E8 necessario inserire il Cognome per l'utente");
				
				if(StringUtils.isEmpty(utente.getNome()))
					errors.rejectValue("nome","generic.error.required" ,"Attenzione: \u00E8 necessario inserire il Nome per l'utente");
				
				if(StringUtils.isEmpty(utente.getCodiceFiscale()))
					errors.rejectValue("codiceFiscale","generic.error.required" ,"Attenzione: \u00E8 necessario inserire il Codice Fiscale per l'utente");
				
				if(StringUtils.isEmpty(utente.getEmail()))
					errors.rejectValue("email","generic.error.required" ,"Attenzione: \u00E8 necessario inserire l'indirizzo E-Mail per l'utente");
				else if(!StringUtils.mailSyntaxCheck(utente.getEmail()))
					errors.rejectValue("email","generic.error.required" ,"Attenzione: \u00E8 necessario inserire un indirizzo E-Mail valido per l'utente");
				
				
			}
			
		}
		
	}

}
