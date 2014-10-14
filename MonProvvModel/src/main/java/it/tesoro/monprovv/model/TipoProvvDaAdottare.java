package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TIPO_PROVV_DA_ADOTTARE")
public class TipoProvvDaAdottare extends AbstractCommonEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 3155052174053130863L;

	@Id
	@Column(name="ID_TIPO_PROVV_DA_ADOTTARE",unique=true, nullable=false)
	private Integer id;

	@Column(name = "DESCRIZIONE", length = 240)
	@NotNull
	private String descrizione;

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


}
