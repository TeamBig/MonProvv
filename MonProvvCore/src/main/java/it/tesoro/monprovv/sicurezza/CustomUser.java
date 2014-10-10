package it.tesoro.monprovv.sicurezza;

import it.tesoro.monprovv.model.Ruolo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
 
	private static final long serialVersionUID = 6590143587838802555L;

	private String codiceFiscale;
	private String nome;
	private String cognome;
	private List<Ruolo> ruoliDaScegliere;
	private String targetURL;
	private Date dataUltimoAccesso;
	private Integer idUtente;
	

	public CustomUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Ruolo> getRuoliDaScegliere() {
		return ruoliDaScegliere;
	}

	public void setRuoliDaScegliere(List<Ruolo> ruoliDaScegliere) {
		this.ruoliDaScegliere = ruoliDaScegliere;
	}

	public String getTargetURL() {
		return targetURL;
	}

	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}

	public Date getDataUltimoAccesso() {
		return dataUltimoAccesso;
	}

	public void setDataUltimoAccesso(Date dataUltimoAccesso) {
		this.dataUltimoAccesso = dataUltimoAccesso;
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

}
