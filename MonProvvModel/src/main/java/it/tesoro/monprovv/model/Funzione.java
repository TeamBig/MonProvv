package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FUNZIONE")
public class Funzione extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1355847811465277192L;

	@Id
	@Column(name = "ID_FUNZIONE")
	private Integer id;

	@Column(name = "DESCRIZIONE", length = 240)
	private String descrizione;

	@Column(name = "URL", length = 240)
	private String url;

	@Column(name = "FLG_LINKABLE", length = 1)
	private String linkable;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLinkable() {
		return linkable;
	}

	public void setLinkable(String linkable) {
		this.linkable = linkable;
	}


}
