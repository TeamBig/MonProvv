package it.tesoro.monprovv.dao;

import java.util.List;

import it.tesoro.monprovv.dao.common.AbstractCommonDAO;
import it.tesoro.monprovv.model.RuoloUtente;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component("ruoloUtenteDAO")
public class RuoloUtenteDAO extends AbstractCommonDAO<RuoloUtente> {

	@SuppressWarnings("unchecked")
	public List<RuoloUtente> findByCodiceFiscale(String codiceFiscale) {
		
		Criteria criteria = newCriteria().createCriteria("utente", "ut")
				.add( Restrictions.eq("codiceFiscale", codiceFiscale) ).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
		return criteria.list();
		
	}
}
