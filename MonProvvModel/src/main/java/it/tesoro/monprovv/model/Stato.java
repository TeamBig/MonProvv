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
@Table(name="STATO")
public class Stato extends AbstractCommonEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4199594017235197118L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_STATO"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name="ID_STATO",unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "TIPO", length = 30)
	@NotNull
	private String tipo;

	@Column(name = "CODICE", length = 30)
	@NotNull
	private String codice;

	@Column(name = "DESCRIZIONE", length = 240)
	@NotNull
	private String descrizione;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
