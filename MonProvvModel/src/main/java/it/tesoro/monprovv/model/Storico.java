package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;


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
	
	@Column(name = "ID_UTENTE_OPERAZIONE")
	private Integer idUtenteOperazione;

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

	public Integer getIdUtenteOperazione() {
		return idUtenteOperazione;
	}

	public void setIdUtenteOperazione(Integer idUtenteOperazione) {
		this.idUtenteOperazione = idUtenteOperazione;
	}

	public Date getDataOperazione() {
		return dataOperazione;
	}

	public void setDataOperazione(Date dataOperazione) {
		this.dataOperazione = dataOperazione;
	}
	
	
	
}
