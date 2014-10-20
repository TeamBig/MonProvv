package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="UTENTE_ASTAGE")
public class UtenteAstage extends AbstractCommonEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -136481720437661744L;

	@Id
	@Column(name = "ID_UTENTE_ASTAGE")
	private Integer id;

	@Column(name = "LIVELLO", length = 10)
	private String livello;
	
	@Column(name = "CODICE_FISCALE", length = 16)
	private String codiceFiscale;

	@Column(name = "NOME", length = 100)
	private String nome;

	@Column(name = "COGNOME", length = 100)
	private String cognome;

	@Column(name = "SESSO", length = 1)
	private String sesso;

	@Column(name = "EMAIL", length = 500)
	private String email;
	
	@Column(name = "ASSEGNAZIONE_ID_UO")
	private Integer assegnazioneIdUo;

	@Column(name = "ASSEGNAZIONE_ID_DIP")
	private Integer assegnazioneIdDip;
	
	@Column(name = "ASSEGNAZIONE_NOME_DIP", length = 60)
	private String assegnazioneNomeDip;

	@Column(name = "ASSEGNAZIONE_NOME_UO", length = 60)
	private String assegnazioneNomeUo;

	@Column(name = "ASSEGNAZIONE_NOME_ESTESO_UO", length = 400)
	private String assegnazioneNomeEstesoUo;

	@Column(name = "DATA_NASCITA")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dataNascita;

	@Column(name = "ID_TIPO_PERSONA")
	private Integer idTipoPersona;
	
	@Column(name = "TIPO_PERSONA", length = 100)
	private String tipoPersona;
	
	@Column(name = "INDIRIZZO_RES", length = 500)
	private String indirizzoRes;

	@Column(name = "COMUNE_RES", length = 500)
	private String comuneRes;

	@Column(name = "CAP_RES", length = 5)
	private String capRes;

	@Column(name = "PAESE_RES", length = 1)
	private String paeseRes;

	@Column(name = "COMUNE_NAS", length = 500)
	private String comuneNas;

	@Column(name = "PROVINCIA_RES", length = 2)
	private String provinciaRes;
	
	@Column(name = "PROVINCIA_NAS", length = 2)
	private String provinciaNas;
	
	@Column(name = "PAESE_NAS", length = 1)
	private String paeseNas;
	
	@Column(name = "INDIRIZZO_UO", length = 500)
	private String indirizzoUo;

	@Column(name = "CAP_UO", length = 5)
	private String capUo;

	@Column(name = "COMUNE_UO", length = 500)
	private String comuneUo;

	@Column(name = "PROVINCIA_UO", length = 2)
	private String provinciaUo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLivello() {
		return livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
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

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAssegnazioneIdUo() {
		return assegnazioneIdUo;
	}

	public void setAssegnazioneIdUo(Integer assegnazioneIdUo) {
		this.assegnazioneIdUo = assegnazioneIdUo;
	}

	public Integer getAssegnazioneIdDip() {
		return assegnazioneIdDip;
	}

	public void setAssegnazioneIdDip(Integer assegnazioneIdDip) {
		this.assegnazioneIdDip = assegnazioneIdDip;
	}

	public String getAssegnazioneNomeDip() {
		return assegnazioneNomeDip;
	}

	public void setAssegnazioneNomeDip(String assegnazioneNomeDip) {
		this.assegnazioneNomeDip = assegnazioneNomeDip;
	}

	public String getAssegnazioneNomeUo() {
		return assegnazioneNomeUo;
	}

	public void setAssegnazioneNomeUo(String assegnazioneNomeUo) {
		this.assegnazioneNomeUo = assegnazioneNomeUo;
	}

	public String getAssegnazioneNomeEstesoUo() {
		return assegnazioneNomeEstesoUo;
	}

	public void setAssegnazioneNomeEstesoUo(String assegnazioneNomeEstesoUo) {
		this.assegnazioneNomeEstesoUo = assegnazioneNomeEstesoUo;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Integer getIdTipoPersona() {
		return idTipoPersona;
	}

	public void setIdTipoPersona(Integer idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getIndirizzoRes() {
		return indirizzoRes;
	}

	public void setIndirizzoRes(String indirizzoRes) {
		this.indirizzoRes = indirizzoRes;
	}

	public String getComuneRes() {
		return comuneRes;
	}

	public void setComuneRes(String comuneRes) {
		this.comuneRes = comuneRes;
	}

	public String getCapRes() {
		return capRes;
	}

	public void setCapRes(String capRes) {
		this.capRes = capRes;
	}

	public String getPaeseRes() {
		return paeseRes;
	}

	public void setPaeseRes(String paeseRes) {
		this.paeseRes = paeseRes;
	}

	public String getComuneNas() {
		return comuneNas;
	}

	public void setComuneNas(String comuneNas) {
		this.comuneNas = comuneNas;
	}

	public String getProvinciaRes() {
		return provinciaRes;
	}

	public void setProvinciaRes(String provinciaRes) {
		this.provinciaRes = provinciaRes;
	}

	public String getProvinciaNas() {
		return provinciaNas;
	}

	public void setProvinciaNas(String provinciaNas) {
		this.provinciaNas = provinciaNas;
	}

	public String getPaeseNas() {
		return paeseNas;
	}

	public void setPaeseNas(String paeseNas) {
		this.paeseNas = paeseNas;
	}

	public String getIndirizzoUo() {
		return indirizzoUo;
	}

	public void setIndirizzoUo(String indirizzoUo) {
		this.indirizzoUo = indirizzoUo;
	}

	public String getCapUo() {
		return capUo;
	}

	public void setCapUo(String capUo) {
		this.capUo = capUo;
	}

	public String getComuneUo() {
		return comuneUo;
	}

	public void setComuneUo(String comuneUo) {
		this.comuneUo = comuneUo;
	}

	public String getProvinciaUo() {
		return provinciaUo;
	}

	public void setProvinciaUo(String provinciaUo) {
		this.provinciaUo = provinciaUo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UtenteAstage other = (UtenteAstage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
