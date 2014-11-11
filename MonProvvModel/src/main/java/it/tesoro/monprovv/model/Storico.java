package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="STORICO")
public class Storico extends AbstractCommonEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5909453637196176298L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_STORICO"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID_STORICO")
	private Integer id;

	@Column(name = "TIPO_OPERAZIONE", length = 240)
	@NotNull
	private String tipoOperazione;

	@Column(name = "TIPO_ENTITA", length = 240)
	@NotNull
	private String tipoEntita;
	
	@Column(name = "ID_ENTITA")
	@NotNull
	private Integer idEntita;
	
	@ManyToOne(targetEntity = Utente.class)
	@JoinColumn(name = "ID_UTENTE_OPERAZIONE", referencedColumnName = "ID_UTENTE")
	@Valid
	@NotNull
	private Utente idUtenteOperazione;

	@Column(name = "DATA_OPERAZIONE")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dataOperazione;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoOperazione() {
		return tipoOperazione;
	}

	public void setTipoOperazione(String tipoOperazione) {
		this.tipoOperazione = tipoOperazione;
	}

	public String getTipoEntita() {
		return tipoEntita;
	}

	public void setTipoEntita(String tipoEntita) {
		this.tipoEntita = tipoEntita;
	}

	public Integer getIdEntita() {
		return idEntita;
	}

	public void setIdEntita(Integer idEntita) {
		this.idEntita = idEntita;
	}

	public Utente getIdUtenteOperazione() {
		return idUtenteOperazione;
	}

	public void setIdUtenteOperazione(Utente idUtenteOperazione) {
		this.idUtenteOperazione = idUtenteOperazione;
	}

	public Date getDataOperazione() {
		return dataOperazione;
	}

	public void setDataOperazione(Date dataOperazione) {
		this.dataOperazione = dataOperazione;
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
		Storico other = (Storico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
