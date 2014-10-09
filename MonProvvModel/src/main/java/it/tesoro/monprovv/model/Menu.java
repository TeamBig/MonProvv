package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MENU")
public class Menu extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9154030918170870642L;

	@Id
	@Column(name = "ID_MENU")
	private Integer id;

	@Column(name = "DESCRIZIONE", length = 240)
	private String descrizione;

	@Column(name = "ID_PARENT_MENU")
	private Integer idParentMenu;

	@Column(name = "ID_FUNZIONE")
	private Integer idFunzione;

	@Column(name = "ORDINAMENTO")
	private Integer ordinamento;

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getIdParentMenu() {
		return idParentMenu;
	}

	public void setIdParentMenu(Integer idParentMenu) {
		this.idParentMenu = idParentMenu;
	}

	public Integer getIdFunzione() {
		return idFunzione;
	}

	public void setIdFunzione(Integer idFunzione) {
		this.idFunzione = idFunzione;
	}

	public Integer getOrdinamento() {
		return ordinamento;
	}

	public void setOrdinamento(Integer ordinamento) {
		this.ordinamento = ordinamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
