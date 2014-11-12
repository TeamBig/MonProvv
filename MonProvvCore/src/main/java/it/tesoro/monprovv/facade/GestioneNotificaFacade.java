package it.tesoro.monprovv.facade;



import java.util.List;

import it.tesoro.monprovv.dao.NotificaDAO;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Notifica;
import it.tesoro.monprovv.model.Utente;
import it.tesoro.monprovv.sicurezza.CustomUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("gestioneNotificaFacade")
public class GestioneNotificaFacade {

	@Autowired
	private NotificaDAO notificaDAO;
	
	
	public long conteggioNotifichePersonaliNonLette() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal() instanceof CustomUser) {
			
			Utente utente = ((CustomUser)auth.getPrincipal()).getUtente();
			
			return notificaDAO.countNonLetteByUtente(utente);
		}
		
		return 0;
	}
	
	public List<Notifica> recuperaNotifichePersonaliNonLette() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal() instanceof CustomUser) {
			
			Utente utente = ((CustomUser)auth.getPrincipal()).getUtente();
			
			return notificaDAO.findNotificheByUtente(utente, true);
		}
		
		return null;
	}
	
	public List<Notifica> recuperaNotifichePersonali() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal() instanceof CustomUser) {
			
			Utente utente = ((CustomUser)auth.getPrincipal()).getUtente();
			
			return notificaDAO.findNotificheByUtente(utente, false);
		}
		
		return null;
	}
	
	public Notifica recuperaNotifica(Integer id) {
		return notificaDAO.findById(id);
	}
	
	public void aggiornaNotifica(Notifica notifica) {
		notificaDAO.saveOrUpdate(notifica);
	}
	
	public Notifica recuperaNotificaAssegnazione(Assegnazione assegnazione) {
		return notificaDAO.findByAssegnazione(assegnazione.getProvvedimento().getId(), assegnazione.getOrgano().getId());
	}
}
