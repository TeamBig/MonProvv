package it.tesoro.monprovv.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractCommonEntity implements Serializable {
		
	private static final long serialVersionUID = 1188226918822687174L;
	
	@Column(name="VERSIONE")
	@Version
	private Integer versione;
	
	@Column(name="UTENTE_INSERIMENTO", updatable = false)
	private String utenteInserimento;

	@Column(name="DATA_INSERIMENTO", updatable = false)
	private Date dataInserimento;	

	@Column(name="DATA_AGGIORNAMENTO")
	private Date dataAggiornamento;

	@Column(name="UTENTE_AGGIORNAMENTO")
	private String utenteAggiornamento;
	
	public String getUtenteInserimento() {
		return utenteInserimento;
	}

	public void setUtenteInserimento(String utenteInserimento) {
		this.utenteInserimento = utenteInserimento;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	

	public String getUtenteAggiornamento() {
		return utenteAggiornamento;
	}

	public void setUtenteAggiornamento(String utenteAggiornamento) {
		this.utenteAggiornamento = utenteAggiornamento;
	}

	public Date getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setDataAggiornamento(Date dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public Integer getVersione() {
		return versione;
	}

	public void setVersione(Integer versione) {
		this.versione = versione;
	}

	
	public abstract Integer getId();

}
