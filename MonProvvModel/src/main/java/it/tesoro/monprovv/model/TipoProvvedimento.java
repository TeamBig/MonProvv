package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_PROVVEDIMENTO")
public class TipoProvvedimento extends AbstractCommonEntity implements java.io.Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -937731898009712122L;

	@Id
	@Column(name="ID_TIPO_PROVVEDIMENTO",unique=true, nullable=false)
	private Integer id;

	@Column(name = "CODICE", length = 30)
	private String codice;
	
	@Column(name = "DESCRIZIONE", length = 240)
	private String descrizione;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


}
