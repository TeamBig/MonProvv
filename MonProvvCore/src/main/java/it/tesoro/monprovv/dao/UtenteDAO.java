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
		String hql = "from Utente u where u.codiceFiscale = :codiceFiscale and flagAttivo = 'S' ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codiceFiscale", codiceFiscale);
		
		List<Utente> risultati =  findByHqlQuery(hql, params);
		if (!CollectionUtils.isEmpty(risultati)) {
			return risultati.get(0);
		}
		return null;
	}
	
	
	public List<Utente> findAttiviByOrgano(Integer idOrgano) {
		String hql = "from Utente u where u.organo.id = :idOrgano and u.flagAttivo = 'S' ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idOrgano", idOrgano);
		
		return findByHqlQuery(hql, params);
	}
	
	public List<Utente> findAttiviByRuolo(String codiceRuolo) {
		String hql = "select u from Utente u inner join u.ruoloUtenteList ru where ru.ruolo.codice = :codiceRuolo and u.flagAttivo = 'S' ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codiceRuolo", codiceRuolo);
		
		return findByHqlQuery(hql, params);
	}
}
