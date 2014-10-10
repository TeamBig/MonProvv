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
@Table(name = "PROVVEDIMENTI_PARENT")
public class ProvvedimentiParent extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -345631099906840341L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_PROVVEDIMENTI_PARENT"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID_PROVVEDIMENTI_PARENT")
	private Integer id;

	@ManyToOne(targetEntity=Provvedimento.class)
	@JoinColumn(name="ID_PROVVEDIMENTO", referencedColumnName="ID_PROVVEDIMENTO")
	@Valid
	@NotNull
	private Provvedimento provvedimento;

	@ManyToOne(targetEntity=Provvedimento.class)
	@JoinColumn(name="ID_PROVVEDIMENTO_COLLEGATO", referencedColumnName="ID_PROVVEDIMENTO")
	@Valid
	@NotNull
	private Provvedimento provvedimentoCollegato;

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

	public Provvedimento getProvvedimentoCollegato() {
		return provvedimentoCollegato;
	}

	public void setProvvedimentoCollegato(Provvedimento provvedimentoCollegato) {
		this.provvedimentoCollegato = provvedimentoCollegato;
	}

}
