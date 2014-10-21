package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="RUOLO_FUNZIONE")
public class RuoloFunzione extends AbstractCommonEntity implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4772733585181781690L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_RUOLO_FUNZIONE"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name="ID_RUOLO_FUNZIONE",unique=true, nullable=false)
	private Integer id;

	@ManyToOne(targetEntity=Ruolo.class)
    @JoinColumn(name="ID_RUOLO", referencedColumnName="ID_RUOLO")
	@Valid
	@NotNull
	private Ruolo ruolo;
	
	@ManyToOne(targetEntity=Funzione.class)
    @JoinColumn(name="ID_FUNZIONE", referencedColumnName="ID_FUNZIONE")
	@Valid
	@NotNull
	private Funzione funzione;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public Funzione getFunzione() {
		return funzione;
	}

	public void setFunzione(Funzione funzione) {
		this.funzione = funzione;
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
		RuoloFunzione other = (RuoloFunzione) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
