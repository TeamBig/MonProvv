package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ASSEGNAZIONE")
public class Assegnazione extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6367134585044493233L;

	@Id
	@Column(name = "ID_ASSEGNAZIONE")
	private Integer id;

	@Column(name = "ID_PROVVEDIMENTO")
	private Integer idProvvedimento;

	@Column(name = "ID_ORGANO")
	private Integer idOrgano;

	@Column(name = "ID_STATO")
	private Integer idStato;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdProvvedimento() {
		return idProvvedimento;
	}

	public void setIdProvvedimento(Integer idProvvedimento) {
		this.idProvvedimento = idProvvedimento;
	}

	public Integer getIdOrgano() {
		return idOrgano;
	}

	public void setIdOrgano(Integer idOrgano) {
		this.idOrgano = idOrgano;
	}

	public Integer getIdStato() {
		return idStato;
	}

	public void setIdStato(Integer idStato) {
		this.idStato = idStato;
	}

}
