package it.tesoro.monprovv.dao;

import it.tesoro.monprovv.dao.common.AbstractCommonDAO;
import it.tesoro.monprovv.model.Organo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("organoDAO")
public class OrganoDAO extends AbstractCommonDAO<Organo>{
	
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
