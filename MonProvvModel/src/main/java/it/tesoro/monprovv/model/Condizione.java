package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONDIZIONE")
public class Condizione extends AbstractCommonEntity implements Serializable {

	private static final long serialVersionUID = 8099384796885278220L;

	@Id
	@Column(name = "ID_CONDIZIONE")
	private Integer id;

	@Column(name = "ELEMENTO", length = 240)
	private String elemento;

	@Column(name = "TARGET", length = 240)
	private String target;
	
	@Column(name = "ESPRESSIONE", length = 500)
	private String espressione;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getElemento() {
		return elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}

	public String getEspressione() {
		return espressione;
	}

	public void setEspressione(String espressione) {
		this.espressione = espressione;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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
		Condizione other = (Condizione) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}
