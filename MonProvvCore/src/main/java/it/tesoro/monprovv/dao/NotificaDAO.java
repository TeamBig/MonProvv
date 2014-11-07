package it.tesoro.monprovv.dao;

import it.tesoro.monprovv.dao.common.AbstractCommonDAO;
import it.tesoro.monprovv.model.Notifica;
import it.tesoro.monprovv.model.Utente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("notificaDAO")
public class NotificaDAO extends AbstractCommonDAO<Notifica> {

	private final static String HQL_NOTIFICHE_PER_UTENTE = "from Notifica as n where n.utenteDestinatario.id = :idUtenteDestinatario or n.organoDestinatario.id = :idOrganoDestinatario ";
	
	public long countNonLetteByUtente(Utente utente) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idUtenteDestinatario", utente.getId());
		params.put("idOrganoDestinatario", utente.getOrgano().getId());
		return countByHqlQuery(HQL_NOTIFICHE_PER_UTENTE, params);
	}
	
	
	public List<Notifica> findNotificheByUtente(Utente utente) {
		
		String hql = "select n " + HQL_NOTIFICHE_PER_UTENTE;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idUtenteDestinatario", utente.getId());
		params.put("idOrganoDestinatario", utente.getOrgano().getId());
		
		return findByHqlQuery(hql, params);
	}
}
 
