package it.tesoro.monprovv.dao;

import java.util.List;

import it.tesoro.monprovv.dao.common.AbstractCommonDAO;
import it.tesoro.monprovv.model.Utente;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("utenteDAO")
public class UtenteDAO extends AbstractCommonDAO<Utente> {

	public Utente findByCodiceFiscale(String codiceFiscale) {
		
		List<Utente> risultati = findByProperty("codiceFiscale", codiceFiscale);
		if (!CollectionUtils.isEmpty(risultati)) {
			return risultati.get(0);
		}
		return null;
		
	}
}
