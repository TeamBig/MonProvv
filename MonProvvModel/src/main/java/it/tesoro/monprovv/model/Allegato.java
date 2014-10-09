package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ALLEGATO")
public class Allegato extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7425231841601536226L;

	@Id
	@Column(name = "ID_ALLEGATO")
	private Integer id;

	@Column(name = "ID_ASSEGNAZIONE")
	private Integer idAssegnazione;

	@Column(name = "ID_PROVVEDIMENTO")
	private Integer idProvvedimento;

	@Column(name = "NOMEFILE", length = 240)
	private String nomefile;

	@Column(name = "CONTENUTO")
	private Integer contenuto;

	@Column(name = "DIMENSIONE")
	private Integer dimensione;

	@Column(name = "MIMETYPE", length = 240)
	private String mimetype;

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

	public Integer getIdProvvedimento() {
		return idProvvedimento;
	}

	public void setIdProvvedimento(Integer idProvvedimento) {
		this.idProvvedimento = idProvvedimento;
	}

	public String getNomefile() {
		return nomefile;
	}

	public void setNomefile(String nomefile) {
		this.nomefile = nomefile;
	}

	public Integer getDimensione() {
		return dimensione;
	}

	public void setDimensione(Integer dimensione) {
		this.dimensione = dimensione;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public String getFlagVisibile() {
		return flagVisibile;
	}

	public void setFlagVisibile(String flagVisibile) {
		this.flagVisibile = flagVisibile;
	}

}
