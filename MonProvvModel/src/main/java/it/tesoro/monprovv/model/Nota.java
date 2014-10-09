package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "NOTA")
public class Nota extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -906651506286446982L;

	@Column(name = "ID_NOTA")
	private Integer id;

	@Column(name = "ID_ASSEGNAZIONE")
	private Integer idAssegnazione;

	@Column(name = "TESTO")
	private Integer testo;

	@Column(name = "FLAG_VISIBILE", length = 1)
	private String flagVisibile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdAssegnazione() {
		return idAssegnazione;
	}

	public void setIdAssegnazione(Integer idAssegnazione) {
		this.idAssegnazione = idAssegnazione;
	}

	public Integer getTesto() {
		return testo;
	}

	public void setTesto(Integer testo) {
		this.testo = testo;
	}

	public String getFlagVisibile() {
		return flagVisibile;
	}

	public void setFlagVisibile(String flagVisibile) {
		this.flagVisibile = flagVisibile;
	}

}
