package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;
import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "NOTA")
public class Nota extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -906651506286446982L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_NOTA"))
	@GeneratedValue(generator = "generator")
	@Column(name = "ID_NOTA")
	@Id
	private Integer id;

	@ManyToOne(targetEntity=Assegnazione.class)
    @JoinColumn(name="ID_ASSEGNAZIONE", referencedColumnName="ID_ASSEGNAZIONE")
	@Valid
	@NotNull
	private Assegnazione assegnazione;

	@Column(name = "TESTO")
	private Clob testo;

	@Column(name = "FLAG_VISIBILE", length = 1)
	private String flagVisibile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Assegnazione getAssegnazione() {
		return assegnazione;
	}

	public void setAssegnazione(Assegnazione assegnazione) {
		this.assegnazione = assegnazione;
	}

	public Clob getTesto() {
		return testo;
	}

	public void setTesto(Clob testo) {
		this.testo = testo;
	}

	public String getFlagVisibile() {
		return flagVisibile;
	}

	public void setFlagVisibile(String flagVisibile) {
		this.flagVisibile = flagVisibile;
	}

	

}
