package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.util.ArrayList;
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
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="UTENTE")
public class Utente extends AbstractCommonEntity implements java.io.Serializable {

	private static final long serialVersionUID = -8894686735318320320L;
	// Fields

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_UTENTE"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name="ID_UTENTE",unique=true, nullable=false)
	private Integer id;
	
	@Column(name="EMAIL", length=240)
	@NotEmpty
	@Email
	private String email;
	
	
//	@OneToMany(mappedBy = "utente", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//	private List<UtenteRuolo> utenteRuoloList;
	
	@Column(name="NOME", length=100)
	private String nome;
	
	@Column(name="COGNOME", length=100)
	private String cognome;
	
	@Column(name="FLG_INT_EST", length=1)
	private String flagIntEst;
	
	
	@ManyToOne(targetEntity=UtenteAstage.class)
    @JoinColumn(name="UTENTE_ASTAGE", referencedColumnName="ID_UTENTE_ASTAGE")
	@Valid
	private UtenteAstage utenteAstage;
	
	
//	@Transient
//	private String pageAction;
	
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


	public UtenteAstage getUtenteAstage() {
		return utenteAstage;
	}


	public void setUtenteAstage(UtenteAstage utenteAstage) {
		this.utenteAstage = utenteAstage;
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