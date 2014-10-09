package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ORGANO")
public class Organo extends AbstractCommonEntity implements Serializable {

	@Column(name = "ID_ORGANO")
	private Integer id;

	@Column(name = "DENOMINAZIONE", length = 240)
	private String denominazione;

	@Column(name = "DENOMINAZIONE_ESTESA", length = 2000)
	private String denominazioneEstesa;

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

	public String getDenominazioneEstesa() {
		return denominazioneEstesa;
	}

	public void setDenominazioneEstesa(String denominazioneEstesa) {
		this.denominazioneEstesa = denominazioneEstesa;
	}

}
