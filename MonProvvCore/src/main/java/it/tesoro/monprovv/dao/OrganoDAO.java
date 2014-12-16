package it.tesoro.monprovv.dao;

import it.tesoro.monprovv.dao.common.AbstractCommonDAO;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.Ruolo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("organoDAO")
public class OrganoDAO extends AbstractCommonDAO<Organo>{
	
	
	
	
	public List<Organo> findAttiviConInseritoreByFlagConcertante(String flagConcertante, List<String> order) {
		
		String hql = "from Organo o where o.flagConcertante = :flagConcertante and o.flagAttivo = 'S' and "
				+ "exists (from Utente u where u.organo.id = o.id and "
				+ "exists (from RuoloUtente ru where ru.utente.id = u.id and (ru.ruolo.codice = '"+Ruolo.ROLE_INSERITORE+"') or (ru.ruolo.codice = '"+Ruolo.ROLE_INSERITORE_FINE_LAV+"'))  ) ";
				
		hql = addOrderBy(hql, order);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flagConcertante", flagConcertante);
		
		return findByHqlQuery(hql, params);
		
	}
	
	public List<Organo> findAttiviByFlagConcertante(String flagConcertante, List<String> order) {
		
		String hql = "from Organo o where o.flagConcertante = :flagConcertante and o.flagAttivo = 'S' ";
		hql = addOrderBy(hql, order);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flagConcertante", flagConcertante);
		
		return findByHqlQuery(hql, params);
		
	}

	public List<Organo> findAttivi(List<String> order) {
		
		String hql = "from Organo o where o.flagAttivo = 'S' ";
		hql = addOrderBy(hql, order);
		return findByHqlQuery(hql);
		
	}

}
