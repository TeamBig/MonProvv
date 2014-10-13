package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private String descrizione;

	@ManyToOne(targetEntity=Menu.class)
    @JoinColumn(name="ID_PARENT_MENU", referencedColumnName="ID_MENU")
	private Menu parentMenu;

	@ManyToOne(targetEntity=Funzione.class)
    @JoinColumn(name="ID_FUNZIONE", referencedColumnName="ID_FUNZIONE")
	@Valid
	private Funzione funzione;

	@Column(name = "ORDINAMENTO")
	private Integer ordinamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public Funzione getFunzione() {
		return funzione;
	}

	public void setFunzione(Funzione funzione) {
		this.funzione = funzione;
	}

	public Integer getOrdinamento() {
		return ordinamento;
	}

	public void setOrdinamento(Integer ordinamento) {
		this.ordinamento = ordinamento;
	}



}
