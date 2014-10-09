package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="RUOLO_UTENTE")
public class RuoloUtente extends AbstractCommonEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7884939203299221849L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_RUOLO_UTENTE"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name="ID_RUOLO_UTENTE",unique=true, nullable=false)
	private Integer id;

	@ManyToOne(targetEntity=Utente.class)
    @JoinColumn(name="UTENTE", referencedColumnName="ID_UTENTE")
	@Valid
	private Utente utente;

	@ManyToOne(targetEntity=Ruolo.class)
    @JoinColumn(name="RUOLO", referencedColumnName="ID_RUOLO")
	@Valid
	private Ruolo ruolo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

}
