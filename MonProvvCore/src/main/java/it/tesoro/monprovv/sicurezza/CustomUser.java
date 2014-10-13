package it.tesoro.monprovv.sicurezza;

import it.tesoro.monprovv.model.Ruolo;
import it.tesoro.monprovv.model.Utente;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
 
	private static final long serialVersionUID = 6590143587838802555L;

	//private List<Ruolo> ruoliDaScegliere;
	//private String targetURL;
	private Date dataUltimoAccesso;
	private Utente utente;
	private Ruolo ruoloCorrente;

	public CustomUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

//	public List<Ruolo> getRuoliDaScegliere() {
//		return ruoliDaScegliere;
//	}
//
//	public void setRuoliDaScegliere(List<Ruolo> ruoliDaScegliere) {
//		this.ruoliDaScegliere = ruoliDaScegliere;
//	}
//
//	public String getTargetURL() {
//		return targetURL;
//	}
//
//	public void setTargetURL(String targetURL) {
//		this.targetURL = targetURL;
//	}

	public Date getDataUltimoAccesso() {
		return dataUltimoAccesso;
	}

	public void setDataUltimoAccesso(Date dataUltimoAccesso) {
		this.dataUltimoAccesso = dataUltimoAccesso;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Ruolo getRuoloCorrente() {
		return ruoloCorrente;
	}

	public void setRuoloCorrente(Ruolo ruoloCorrente) {
		this.ruoloCorrente = ruoloCorrente;
	}
}
