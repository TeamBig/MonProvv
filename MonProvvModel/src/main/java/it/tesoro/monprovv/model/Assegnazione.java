package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;

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
@Table(name="ASSEGNAZIONE")
public class Assegnazione extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6367134585044493233L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_ASSEGNAZIONE"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID_ASSEGNAZIONE")
	private Integer id;

	@ManyToOne(targetEntity=Provvedimento.class)
    @JoinColumn(name="ID_PROVVEDIMENTO", referencedColumnName="ID_PROVVEDIMENTO")
	@Valid
	@NotNull
	private Provvedimento provvedimento;
	
	@ManyToOne(targetEntity=Organo.class)
    @JoinColumn(name="ID_ORGANO", referencedColumnName="ID_ORGANO")
	@Valid
	@NotNull
	private Organo organo;

	@ManyToOne(targetEntity=Stato.class)
    @JoinColumn(name="ID_STATO", referencedColumnName="ID_STATO")
	@Valid
	@NotNull
	private Stato stato;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Provvedimento getProvvedimento() {
		return provvedimento;
	}

	public void setProvvedimento(Provvedimento provvedimento) {
		this.provvedimento = provvedimento;
	}

	public Organo getOrgano() {
		return organo;
	}

	public void setOrgano(Organo organo) {
		this.organo = organo;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}
	
	

}
