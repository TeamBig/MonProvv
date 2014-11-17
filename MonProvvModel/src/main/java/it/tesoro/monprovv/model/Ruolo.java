package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="RUOLO")
public class Ruolo extends AbstractCommonEntity implements java.io.Serializable {

	private static final long serialVersionUID = 2334105001244493370L;
	// Fields

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_INSERITORE = "ROLE_INSERITORE";
	public static final String ROLE_LETTORE = "ROLE_LETTORE";
	public static final String ROLE_CONSULTANTE = "ROLE_CONSULTANTE";
	
	@Id
	@Column(name="ID_RUOLO",unique=true, nullable=false)
	@NotNull
	private Integer id;

	@Column(name="CODICE",nullable=false,length=60)
	@NotNull
	private String codice;
	
	@Column(name="DESCRIZIONE",nullable=false,length=240)
	@NotNull
	private String descrizione;
	

	// Constructors

	/** default constructor */
	public Ruolo() {
	}

	/** minimal constructor */
	public Ruolo(Integer id, String descrizione, String codice) {
		this.id = id;
		this.descrizione = descrizione;
		this.codice=codice;
	}


	// Property accessors
	
	public String getDescrizione() {
		return this.descrizione;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
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
		Ruolo other = (Ruolo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}