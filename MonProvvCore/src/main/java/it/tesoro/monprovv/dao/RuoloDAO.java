package it.tesoro.monprovv.dao;

import java.util.List;

import it.tesoro.monprovv.dao.common.AbstractCommonDAO;
import it.tesoro.monprovv.model.Ruolo;

import org.springframework.stereotype.Component;

@Component("ruoloDAO")
public class RuoloDAO extends AbstractCommonDAO<Ruolo> {
	
	public Ruolo findByCodiceRuolo(String codice) {
		
		List<Ruolo> ruoli = findByProperty("codice", codice);
		if (ruoli != null) {
			return ruoli.get(0);
		}
		return null;
		
	}
	
}