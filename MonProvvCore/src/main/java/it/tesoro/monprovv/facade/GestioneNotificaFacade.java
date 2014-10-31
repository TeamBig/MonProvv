package it.tesoro.monprovv.facade;



import java.util.List;

import it.tesoro.monprovv.dao.NotificaDAO;
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
	
	public List<Notifica> recuperaNotifichePersonali() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal() instanceof CustomUser) {
			
			Utente utente = ((CustomUser)auth.getPrincipal()).getUtente();
			
			return notificaDAO.findNotificheByUtente(utente);
		}
		
		return null;
	}
}
