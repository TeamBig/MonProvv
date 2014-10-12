package it.tesoro.monprovv.sicurezza;

import it.tesoro.monprovv.facade.GestioneSicurezzaFacade;
import it.tesoro.monprovv.facade.GestioneSicurezzaFacade.Headers;
import it.tesoro.monprovv.model.Ruolo;
import it.tesoro.monprovv.model.Utente;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private GestioneSicurezzaFacade gestioneSicurezzaFacade;
	
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		Headers headers = gestioneSicurezzaFacade.decodeJSON(username);
		
		if (headers == null)
			throw new UsernameNotFoundException("Username e cf non disponibili in request");
		
		String oamCodiceFiscale = headers.getSSO_CF();
		
		Utente utente = gestioneSicurezzaFacade.recuperaUtentePerCF(oamCodiceFiscale);
		
		if (utente == null || CollectionUtils.isEmpty(utente.getRuoloUtenteList()))
			throw new UsernameNotFoundException("Utente non autorizzato");
		
		// gestionre authorities
		Ruolo ruoloCorrente = null;
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (int i = 0; i < utente.getRuoloUtenteList().size(); i++) {
			authorities.add(new SimpleGrantedAuthority(utente.getRuoloUtenteList().get(i).getRuolo().getCodice()));
			
			if (   (!utente.getRuoloUtenteList().get(i).getRuolo().getCodice().equals(Ruolo.ROLE_USER)) 
				&& (!utente.getRuoloUtenteList().get(i).getRuolo().getCodice().equals(Ruolo.ROLE_ADMIN)) ) {
				ruoloCorrente = utente.getRuoloUtenteList().get(i).getRuolo();
			}
		
		}
		
		// creazione utente
		CustomUser user = new CustomUser(oamCodiceFiscale, "password", true, true, true, true, authorities);

		user.setUtente(utente);
		user.setDataUltimoAccesso(new Date());
		user.setRuoloCorrente(ruoloCorrente);

		return user;
	}
}
