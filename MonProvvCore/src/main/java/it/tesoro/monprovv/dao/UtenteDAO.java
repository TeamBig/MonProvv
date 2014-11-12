package it.tesoro.monprovv.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
	public List<Utente> findAttiviByOrgano(Integer idOrgano) {
		String hql = "from Utente u where u.organo.id = :idOrgano and flagAttivo = 'S' ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idOrgano", idOrgano);
		
		return findByHqlQuery(hql, params);
	}
}
