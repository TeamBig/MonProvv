package it.tesoro.monprovv.dao;

import it.tesoro.monprovv.dao.common.AbstractCommonDAO;
import it.tesoro.monprovv.model.Notifica;
import it.tesoro.monprovv.model.Ruolo;
import it.tesoro.monprovv.model.Utente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("notificaDAO")
public class NotificaDAO extends AbstractCommonDAO<Notifica> {

	private final static String HQL_NOTIFICHE_PER_UTENTE = "from Notifica as n where (n.utenteDestinatario.id = :idUtenteDestinatario or n.organoDestinatario.id = :idOrganoDestinatario) ";
	
	public long countNonLetteByUtente(Utente utente) {
		
		String hql = HQL_NOTIFICHE_PER_UTENTE + " and n.flagLettura = 'N'";
		
		// escludo le notifiche operative
		if (	(utente.getRuoloPrincipale() != null) 
			&& (Ruolo.ROLE_LETTORE.equals(utente.getRuoloPrincipale().getCodice()) || Ruolo.ROLE_CONSULTANTE.equals(utente.getRuoloPrincipale().getCodice()) )  ) {
			hql += " and n.tipoNotifica = 'I'";
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idUtenteDestinatario", utente.getId());
		params.put("idOrganoDestinatario", utente.getOrgano().getId());
		return countByHqlQuery(hql, params);
	}
	
	
	public List<Notifica> findNotificheByUtente(Utente utente, boolean soloNonLette) {
		
		String hql = "select n " + HQL_NOTIFICHE_PER_UTENTE;
		if (soloNonLette) {
			hql += " and n.flagLettura = 'N'";
		}
		
		// escludo le notifiche operative
		if (	(utente.getRuoloPrincipale() != null) 
			&& (Ruolo.ROLE_LETTORE.equals(utente.getRuoloPrincipale().getCodice()) || Ruolo.ROLE_CONSULTANTE.equals(utente.getRuoloPrincipale().getCodice()) )  ) {
			hql += " and n.tipoNotifica = 'I'";
		}
		
		hql += " order by n.dataInserimento desc";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idUtenteDestinatario", utente.getId());
		params.put("idOrganoDestinatario", utente.getOrgano().getId());
		
		return findByHqlQuery(hql, params);
	}
	
	public Notifica findByAssegnazione(Integer idProvvedimento, Integer idOrganoDestinatario) {
		// url /private/provvedimenti/ricerca/dettaglio?id=3
		
		
		String hql = "from Notifica as n where n.flagLettura = 'N' and n.tipoNotifica = 'O' and n.linkOperazione = :linkOperazione and n.organoDestinatario.id = :idOrganoDestinatario";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("linkOperazione", "/private/provvedimenti/ricerca/dettaglio?id=" + idProvvedimento);
		params.put("idOrganoDestinatario", idOrganoDestinatario);
		List<Notifica> result = findByHqlQuery(hql, params);
		
		if (!CollectionUtils.isEmpty(result)) {
			return result.get(0);
		}
		
		return null;
	}
	
}
 
