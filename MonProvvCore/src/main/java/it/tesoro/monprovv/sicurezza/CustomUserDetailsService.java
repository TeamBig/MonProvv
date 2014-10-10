package it.tesoro.monprovv.sicurezza;

import it.tesoro.monprovv.facade.GestioneSicurezzaFacade;
import it.tesoro.monprovv.facade.GestioneSicurezzaFacade.Headers;
import it.tesoro.monprovv.model.RuoloUtente;

import java.util.ArrayList;
import java.util.Date;
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
public class CustomUserDetailsService implements UserDetailsService {
	
	
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
			
		
		List<RuoloUtente> utenteRuoli = gestioneSicurezzaFacade.recuperaUtenteRuoloPerCF(oamCodiceFiscale);
		
		if (utenteRuoli == null || utenteRuoli.size() == 0)
			throw new UsernameNotFoundException("Utente non autorizzato");
		
		// gestionre authorities
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (int i = 0; i < utenteRuoli.size(); i++) {
			authorities.add(new SimpleGrantedAuthority(utenteRuoli.get(i).getRuolo().getCodice()));
		}
		
		// creazione utente
		CustomUser user = new CustomUser(oamUsername, "password", true, true, true, true, authorities);

		user.setIdUtente(utenteRuoli.get(0).getUtente().getId());
		user.setCodiceFiscale(utenteRuoli.get(0).getUtente().getCodiceFiscale()); // uguale a oamCodiceFiscale
		user.setNome(utenteRuoli.get(0).getUtente().getNome());
		user.setCognome(utenteRuoli.get(0).getUtente().getCognome());
		user.setDataUltimoAccesso(new Date());

		return user;
	}
}
