package it.tesoro.monprovv.dao;

import java.util.List;

import it.tesoro.monprovv.dao.common.AbstractCommonDAO;
import it.tesoro.monprovv.model.Stato;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("statoDAO")
public class StatoDAO extends AbstractCommonDAO<Stato>{
	
	public Stato findByCodice(String codice) {
		List<Stato> stati = findByProperty("codice", codice);
		if (!CollectionUtils.isEmpty(stati)) {
			return stati.get(0);
		}
		return null;
	}

}
