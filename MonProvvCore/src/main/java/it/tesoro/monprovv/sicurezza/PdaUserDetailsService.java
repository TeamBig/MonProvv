package it.tesoro.monprovv.sicurezza;

import it.tesoro.monprovv.facade.GestioneSicurezzaFacade;
import it.tesoro.monprovv.facade.GestioneSicurezzaFacade.Headers;
import it.tesoro.monprovv.model.Ruolo;
import it.tesoro.monprovv.model.UtenteRuolo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component("userDetailsService")
public class PdaUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private GestioneSicurezzaFacade gestioneSicurezzaFacade;
	
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		String oamCodiceFiscale = null;
		String oamUsername = null;

		Headers headers = gestioneSicurezzaFacade.decodeJSON(username);
		
		if (headers == null)
			throw new UsernameNotFoundException("Username e cf non disponibili in request");
		
		oamCodiceFiscale = headers.getSSO_CF();
		oamUsername = headers.getSSO_USERNAME();
			
		
		List<UtenteRuolo> utenteRuoli = gestioneSicurezzaFacade.recuperaUtenteRuoloPerCF(oamCodiceFiscale);
		
		if (utenteRuoli == null || utenteRuoli.size() == 0)
			throw new UsernameNotFoundException("Utente non autorizzato");
		
		// controllo validita del ruolo su reginde
		boolean isAmministratore=false;
		int indiceRuoloAmministratore = -1;
		int indiceRuoloPdaAttivo = 0;
		for (int i = 0; i < utenteRuoli.size(); i++) {
			if ((utenteRuoli.get(i).isRuoloValido()) 
					&& (utenteRuoli.get(i).getRuolo().isVisible()) ) {
				
				if (utenteRuoli.get(i).getRuolo().getCodice().equalsIgnoreCase(Ruolo.ROLE_ADMIN)) {
					isAmministratore = true;
					indiceRuoloAmministratore = i;
				} else {
				
					boolean ruoloValido = true;
					try {
						Soggetto soggetto = soggettoService.dettaglioSoggettoPerCodiceFiscale(oamCodiceFiscale);
						if (soggetto == null) {
							ruoloValido = false;
						}
						if ((soggetto != null) && ( !soggetto.getStatus().getDescr().equalsIgnoreCase("attivo") ) ) {
							ruoloValido = false;
						}
						
					} catch (WebServiceException wse) {
						ruoloValido = false;
					}
					
					if (!ruoloValido) {
						utenteRuoli.get(i).setRuoloValido("N");
					} else {
						indiceRuoloPdaAttivo = i;
					}
				}
			}
		}
		
		
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		//ArrayList<Ruolo> ruoliDaScegliere = new ArrayList<Ruolo>();
		
		for (int i = 0; i < utenteRuoli.size(); i++) {
			if (utenteRuoli.get(i).isRuoloValido()) {
				authorities.add(new SimpleGrantedAuthority(utenteRuoli.get(i).getRuolo().getCodice()));
			}
		}
		
		// aggiungo il ruolo standard anonimo 
		//authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		
//		if (ruoliDaScegliere.size() == 1) {
//			authorities.add(new SimpleGrantedAuthority(ruoliDaScegliere.get(0).getCodice()));
//		}
		
		//UserDetails user = new User(nome, "password", true, true, true, true, authorities);
		CustomUser user = null;

		// caso amministratore
//		if (ruoliDaScegliere.size() > 1 ) {
//			for (int i=0;i<ruoliDaScegliere.size();i++){
//				if (ruoliDaScegliere.get(i).getCodice().equalsIgnoreCase(Ruolo.ROLE_ADMIN)){
//					indiceRuoloAmministratore=i;
//					isAmministratore=true;
//					authorities.add(new SimpleGrantedAuthority(ruoliDaScegliere.get(indiceRuoloAmministratore).getCodice()));
//					break;
//				}
//			}
//			user=new PdaUser(oamUsername, "password", true, true, true, true, authorities);
//			user.setRuoloAttivo(gestioneSicurezzaFacade.recuperaRuolo(ruoliDaScegliere.get(indiceRuoloAmministratore).getCodice()));
//			//user.setRuoliDaScegliere(ruoliDaScegliere);
//		} else {
//			// il ruolo e' gia' stato assegnato perche' unico
//			user=new PdaUser(oamUsername, "password", true, true, true, true, authorities);
//			user.setRuoloAttivo(gestioneSicurezzaFacade.recuperaRuolo(ruoliDaScegliere.get(0).getCodice()));
//		}


		user=new CustomUser(oamUsername, "password", true, true, true, true, authorities);

		int indiceRuoloAttivo = -1;
		if (isAmministratore) {
			indiceRuoloAttivo = indiceRuoloAmministratore;
		} else {
			indiceRuoloAttivo = indiceRuoloPdaAttivo;
		}

		user.setRuoloAttivo(utenteRuoli.get(indiceRuoloAttivo).getRuolo());
		
		
		
		user.setIdUtente(utenteRuoli.get(0).getUtente().getId());
		user.setCodiceFiscale(utenteRuoli.get(0).getUtente().getUtenteEsterno().getCodiceFiscale()); // uguale a oamCodiceFiscale
		user.setNome(utenteRuoli.get(0).getUtente().getUtenteEsterno().getNome());
		user.setCognome(utenteRuoli.get(0).getUtente().getUtenteEsterno().getCognome());
		user.setDataUltimoAccesso(utenteRuoli.get(0).getUtente().getDataUltimoAccesso());

		int numNuoveNotifiche=0;
//		if (ruoliDaScegliere.size()==1 || (ruoliDaScegliere.size()>1 && isAmministratore)){
//			int indiceRuoloRicercaNotifiche=0;
//			if (isAmministratore){
//				indiceRuoloRicercaNotifiche=indiceRuoloAmministratore;
//			}
//			
//			List <UtenteRuoloNotifica> notificheUtente=gestioneSicurezzaFacade.findNotificheUtente(ruoliDaScegliere.get(indiceRuoloRicercaNotifiche).getCodice(),utenteRuoli.get(indiceRuoloRicercaNotifiche).getUtente().getId());
//			for (UtenteRuoloNotifica notifica:notificheUtente){
//				user.getNotificheUtente().add(notifica);
//				if (!notifica.isNotificaLetta()){
//					numNuoveNotifiche++;
//				}
//			}
//
//		}
		
		List <UtenteRuoloNotifica> notificheUtente = gestioneSicurezzaFacade.findNotificheUtente(utenteRuoli.get(indiceRuoloAttivo).getRuolo().getCodice(), utenteRuoli.get(indiceRuoloAttivo).getUtente().getId());
		for (UtenteRuoloNotifica notifica:notificheUtente){
			user.getNotificheUtente().add(notifica);
			if (!notifica.isNotificaLetta()){
				numNuoveNotifiche++;
			}
		}		
		
		user.setNumNuoveNotifiche(numNuoveNotifiche);
		return user;
	}
}
