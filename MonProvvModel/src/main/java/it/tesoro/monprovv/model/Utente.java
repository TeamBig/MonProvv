package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="UTENTE")
public class Utente extends AbstractCommonEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1378680222191646421L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_UTENTE"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name="ID_UTENTE",unique=true, nullable=false)
	private Integer id;
	
	@Column(name="COGNOME", length=240)
	@NotNull
	@NotEmpty
	private String cognome;
	
	@Column(name="NOME", length=240)
	@NotNull
	@NotEmpty
	private String nome;
	
	@Transient
	private String nominativo;
	
	@Transient
	private Ruolo ruolo;
	
	@Transient
	private boolean amministratore;
	
	@Column(name="CODICE_FISCALE", length=20)
	@NotNull
	@NotEmpty
	@Pattern(regexp="^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$")
	private String codiceFiscale;
	
	@Column(name="EMAIL", length=240)
	@NotNull
	@NotEmpty
	@Email
	private String email;
	
	@Column(name="FLAG_INT_EST", length=1)
	@NotNull
	@NotEmpty
	private String flagIntEst;
	
	@Column(name="FLAG_ATTIVO", length=1)
	private String flagAttivo;
	
	@ManyToOne(targetEntity=Organo.class)
    @JoinColumn(name="ID_ORGANO", referencedColumnName="ID_ORGANO")
	@Valid
	private Organo organo;
	
	@Column(name = "DATA_NASCITA")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dataNascita;
	
	@Column(name="SESSO", length=1)
	@NotNull
	@NotEmpty
	private String sesso;

	@Transient
	private String organoDenominazione;
	
	@Transient
	private String organoDenominazioneInterni;
	
	@ManyToOne(targetEntity=UtenteAstage.class)
    @JoinColumn(name="ID_UTENTE_ASTAGE", referencedColumnName="ID_UTENTE_ASTAGE")
	@Valid
	private UtenteAstage utenteAstage;
	
	@OneToMany(mappedBy = "utente", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<RuoloUtente> ruoloUtenteList;

	public String getTipo(){
		return ("E".equals( this.flagIntEst )?"Esterno":"Interno" );
	}
	
	@AssertTrue
	public boolean isUtenteOK() {
		// ulteriori controlli di validita'
		return true;
	}

	// Constructors

	/** default constructor */
	public Utente() {
	}

	// Property accessors
	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		if(this.utenteAstage != null)
			return utenteAstage.getEmail();
		else
			return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFlagIntEst() {
		return flagIntEst;
	}

	public void setFlagIntEst(String flagIntEst) {
		this.flagIntEst = flagIntEst;
	}

	public String getNome() {
		if(this.utenteAstage != null)
			return utenteAstage.getNome();
		else
			return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		if(this.utenteAstage != null)
			return utenteAstage.getCognome();
		else
			return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public UtenteAstage getUtenteAstage() {
		return utenteAstage;
	}


	public void setUtenteAstage(UtenteAstage utenteAstage) {
		this.utenteAstage = utenteAstage;
	}

	public String getCodiceFiscale() {
		if(this.utenteAstage != null)
			return utenteAstage.getCodiceFiscale();
		else
			return codiceFiscale;
	}


	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}


	public Organo getOrgano() {
		return organo;
	}


	public void setOrgano(Organo organo) {
		this.organo = organo;
	}

	public String getOrganoDenominazione() {
		if(this.organo!=null)
			return organo.getDenominazione();
		else
			return organoDenominazione;
	}

	public void setOrganoDenominazione(String organoDenominazione) {
		this.organoDenominazione = organoDenominazione;
	}
	
	public List<RuoloUtente> getRuoloUtenteList() {
		return ruoloUtenteList;
	}


	public void setRuoloUtenteList(List<RuoloUtente> ruoloUtenteList) {
		this.ruoloUtenteList = ruoloUtenteList;
	}
	
	public String getNominativo() {
		return nominativo;
	}

	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}
	
	

	public String getOrganoDenominazioneInterni() {
		return organoDenominazioneInterni;
	}

	public void setOrganoDenominazioneInterni(String organoDenominazioneInterni) {
		this.organoDenominazioneInterni = organoDenominazioneInterni;
	}

	public String getFlagAttivo() {
		return flagAttivo;
	}

	public void setFlagAttivo(String flagAttivo) {
		this.flagAttivo = flagAttivo;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public Date getDataNascita() {
		if(this.utenteAstage != null)
			return utenteAstage.getDataNascita();
		else
			return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getSesso() {
		if(this.utenteAstage != null)
			return utenteAstage.getSesso();
		else
			return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public boolean isAmministratore() {
		return amministratore;
	}

	public void setAmministratore(boolean amministratore) {
		this.amministratore = amministratore;
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
		Utente other = (Utente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}