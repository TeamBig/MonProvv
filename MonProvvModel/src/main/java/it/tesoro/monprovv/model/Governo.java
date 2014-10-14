package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "GOVERNO")
public class Governo extends AbstractCommonEntity implements Serializable {

	private static final long serialVersionUID = 8694336139893958679L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_GOVERNO"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID_GOVERNO")
	private Integer id;

	@Column(name = "DENOMINAZIONE", length = 240)
	@NotNull
	private String denominazione;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

}
