package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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

	@OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name="ID_PARENT_MENU")
	@OrderBy("ordinamento")
	private List<Menu> secondLevelList;
	
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

	public List<Menu> getSecondLevelList() {
		return secondLevelList;
	}

	public void setSecondLevelList(List<Menu> secondLevelList) {
		this.secondLevelList = secondLevelList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
