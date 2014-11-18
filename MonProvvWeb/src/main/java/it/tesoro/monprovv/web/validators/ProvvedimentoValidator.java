package it.tesoro.monprovv.web.validators;

import java.text.SimpleDateFormat;

import it.tesoro.monprovv.dto.InserisciProvvedimentoDto;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.utils.StringUtils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProvvedimentoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}


	@Override
	public void validate(Object target, Errors errors) {
		if (target instanceof InserisciProvvedimentoDto) {
			InserisciProvvedimentoDto provv = (InserisciProvvedimentoDto) target;
			if (StringUtils.isEmpty(provv.getTipoGoverno())) {
				errors.rejectValue("tipoGoverno", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare il governo");
			}
			if (StringUtils.isEmpty(provv.getTipologia())) {
				errors.rejectValue("tipologia", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare la tipologia");
			}
			if (StringUtils.isEmpty(provv.getTipoAtto())) {
				errors.rejectValue("tipoAtto", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare il tipo atto");
			}
			if (StringUtils.isEmpty(provv.getDataAtto())) {
				errors.rejectValue("dataAtto", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare data atto");
			}else if("01/01/1950".equals( new SimpleDateFormat("dd/MM/yyyy").format(provv.getDataAtto()))){
				errors.rejectValue("appoDataFormat","generic.error.required" ,"Attenzione: formato 'Data atto' non corretto");
				provv.setDataAtto(null);
			}
			if (StringUtils.isEmpty(provv.getNumeroAtto())) {
				errors.rejectValue("numeroAtto", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare numero atto");
			}
			if (StringUtils.isEmpty(provv.getArt())) {
				errors.rejectValue("art", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare l'articolo");
			}
			if (StringUtils.isEmpty(provv.getComma())) {
				errors.rejectValue("comma", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare il comma");
			}
			if (StringUtils.isEmpty(provv.getTipoProvvDaAdottare())) {
				errors.rejectValue("tipoProvvDaAdottare", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare il provvedimento da adottare");
			}
			if (StringUtils.isEmpty(provv.getTitoloOggetto())) {
				errors.rejectValue("titoloOggetto", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare il titolo / oggetto");
			}
			if (StringUtils.isEmpty(provv.getDtTermineScadenza())) {
				errors.rejectValue("dataAtto", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare termine scadenza");
			}else if("01/01/1950".equals( new SimpleDateFormat("dd/MM/yyyy").format(provv.getDtTermineScadenza()))){
				errors.rejectValue("appoDataFormat","generic.error.required" ,"Attenzione: formato 'Termine Scadenza' non corretto");
				provv.setDtTermineScadenza(null);
			}
		}
		if (target instanceof Provvedimento) {
			Provvedimento provv = (Provvedimento) target;
			if (StringUtils.isEmpty(provv.getGoverno())) {
				errors.rejectValue("tipoGoverno", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare il governo");
			}
			if (StringUtils.isEmpty(provv.getTipoProvvedimento())) {
				errors.rejectValue("tipologia", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare la tipologia");
			}
			if (StringUtils.isEmpty(provv.getTipoAtto())) {
				errors.rejectValue("tipoAtto", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare il tipo atto");
			}
			if (StringUtils.isEmpty(provv.getDataAtto())) {
				errors.rejectValue("dataAtto", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare data atto");
			}else if("01/01/1950".equals( new SimpleDateFormat("dd/MM/yyyy").format(provv.getDataAtto()))){
				errors.rejectValue("appoDataFormat","generic.error.required" ,"Attenzione: formato 'Data atto' non corretto");
				provv.setDataAtto(null);
			}
			if (StringUtils.isEmpty(provv.getNumeroAtto())) {
				errors.rejectValue("numeroAtto", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare numero atto");
			}
			if (StringUtils.isEmpty(provv.getArticolo())) {
				errors.rejectValue("art", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare l'articolo");
			}
			if (StringUtils.isEmpty(provv.getComma())) {
				errors.rejectValue("comma", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare il comma");
			}
			if (StringUtils.isEmpty(provv.getTipoProvvDaAdottare())) {
				errors.rejectValue("tipoProvvDaAdottare", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare il provvedimento da adottare");
			}
			if (StringUtils.isEmpty(provv.getOggetto())) {
				errors.rejectValue("titoloOggetto", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare il titolo / oggetto");
			}
			if (StringUtils.isEmpty(provv.getTermineScadenza())) {
				errors.rejectValue("dataAtto", "generic.error.required",
						"Attenzione: \u00E8 necessario selezionare termine scadenza");
			}else if("01/01/1950".equals( new SimpleDateFormat("dd/MM/yyyy").format(provv.getTermineScadenza()))){
				errors.rejectValue("appoDataFormat","generic.error.required" ,"Attenzione: formato 'Termine Scadenza' non corretto");
				provv.setTermineScadenza(null);
			}
			
		}

	}

}
