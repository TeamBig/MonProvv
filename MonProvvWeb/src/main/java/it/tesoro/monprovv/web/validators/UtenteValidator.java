package it.tesoro.monprovv.web.validators;

import it.tesoro.monprovv.model.Utente;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UtenteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Utente utente = (Utente)target;
	}

}
