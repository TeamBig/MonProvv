package it.tesoro.monprovv.dao;

import java.util.List;

import it.tesoro.monprovv.dao.common.AbstractCommonDAO;
import it.tesoro.monprovv.model.Notifica;
import it.tesoro.monprovv.model.Utente;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component("notificaDAO")
public class NotificaDAO extends AbstractCommonDAO<Notifica> {

	
	public long countNonLetteByUtente(Utente utente) {
		
		Criteria criteria = newCriteria()
				.createAlias("organoDestinatario", "organoDestinatario")
				.createAlias("utenteDestinatario", "utenteDestinatario")
				.add(Restrictions.eq("utenteDestinatario.id", utente.getId()))
				.add(Restrictions.eq("organoDestinatario.id", utente.getOrgano().getId()))
				.add(Restrictions.eq("flagLettura", "N"))
				.setProjection(Projections.rowCount());
						
		return (Long)criteria.uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Notifica> findNotificheByUtente(Utente utente) {
		Criteria criteria = newCriteria()
				.createAlias("organoDestinatario", "organoDestinatario")
				.createAlias("utenteDestinatario", "utenteDestinatario")
				.add(Restrictions.eq("utenteDestinatario.id", utente.getId()))
				.add(Restrictions.eq("organoDestinatario.id", utente.getOrgano().getId()))
				.addOrder(Order.desc("dataInserimento"))
				.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				
						
		return criteria.list();
		
	}
}
 