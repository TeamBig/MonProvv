package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="TIPO_PROVV_DA_ADOTTARE")
public class TipoProvvDaAdottare extends AbstractCommonEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 3155052174053130863L;
	
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_TIPO_PROVV_DA_ADOTTARE"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name="ID_TIPO_PROVV_DA_ADOTTARE",unique=true, nullable=false)
	private Integer id;

	@Column(name = "DESCRIZIONE", length = 240)
	@NotNull
	private String descrizione;
	
	@Column(name="FLAG_ATTIVO", length=1)
	private String flagAttivo;

	public TipoProvvDaAdottare(){
		this.flagAttivo = "S";
	}
	
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

	public String getFlagAttivo() {
		return flagAttivo;
	}

	public void setFlagAttivo(String flagAttivo) {
		this.flagAttivo = flagAttivo;
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
		TipoProvvDaAdottare other = (TipoProvvDaAdottare) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
