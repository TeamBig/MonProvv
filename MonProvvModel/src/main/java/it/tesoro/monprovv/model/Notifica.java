package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICA")
public class Notifica extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7136472204232788138L;

	@Id
	@Column(name = "ID_NOTIFICA")
	private Integer id;

	@Column(name = "ID_ORGANO_DESTINATARIO")
	private Integer idOrganoDestinatario;

	@Column(name = "ID_UTENTE_DESTINATARIO")
	private Integer idUtenteDestinatario;

	@Column(name = "ID_UTENTE_MITTORGANO")
	private Integer idUtenteMittorgano;

	@Column(name = "OGGETTO", length = 240)
	private String oggetto;

	@Column(name = "TESTO", length = 4000)
	private String testo;

	@Column(name = "LINK_OPERAZIONE", length = 240)
	private String linkOperazione;

	@Column(name = "FLAG_LETTURA", length = 1)
	private String flagLettura;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdOrganoDestinatario() {
		return idOrganoDestinatario;
	}

	public void setIdOrganoDestinatario(Integer idOrganoDestinatario) {
		this.idOrganoDestinatario = idOrganoDestinatario;
	}

	public Integer getIdUtenteDestinatario() {
		return idUtenteDestinatario;
	}

	public void setIdUtenteDestinatario(Integer idUtenteDestinatario) {
		this.idUtenteDestinatario = idUtenteDestinatario;
	}

	public Integer getIdUtenteMittorgano() {
		return idUtenteMittorgano;
	}

	public void setIdUtenteMittorgano(Integer idUtenteMittorgano) {
		this.idUtenteMittorgano = idUtenteMittorgano;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getLinkOperazione() {
		return linkOperazione;
	}

	public void setLinkOperazione(String linkOperazione) {
		this.linkOperazione = linkOperazione;
	}

	public String getFlagLettura() {
		return flagLettura;
	}

	public void setFlagLettura(String flagLettura) {
		this.flagLettura = flagLettura;
	}

}
