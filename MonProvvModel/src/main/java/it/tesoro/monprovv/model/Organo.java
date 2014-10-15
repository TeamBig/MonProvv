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
@Table(name = "ORGANO")
public class Organo extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -88624704588322807L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_ORGANO"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID_ORGANO")
	private Integer id;

	@Column(name = "DENOMINAZIONE", length = 240)
	@NotNull
	private String denominazione;

	@Column(name = "DENOMINAZIONE_ESTESA", length = 2000)
	private String denominazioneEstesa;
	
	
	@Column(name = "FLAG_CONCERTANTE", length = 1)
	private String flagConcertante;
	
	public String getConcertante() {
		return ("S".equals(flagConcertante))?"Concertante":"Non Concertante" ;
	}

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

	public String getFlagConcertante() {
		return flagConcertante;
	}

	public void setFlagConcertante(String flagConcertante) {
		this.flagConcertante = flagConcertante;
	}

}
