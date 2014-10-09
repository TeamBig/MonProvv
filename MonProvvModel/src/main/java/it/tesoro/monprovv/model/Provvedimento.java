package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PROVVEDIMENTO")
public class Provvedimento extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3895683101409100101L;

	@Column(name = "ID_PROVVEDIMENTO")
	private Integer id;

	@Column(name = "TIPOLOGIA", length = 240)
	private String tipologia;

	@Column(name = "FONTE_NORMATIVA", length = 240)
	private String fonteNormativa;

	@Column(name = "ARTICOLO", length = 60)
	private String articolo;

	@Column(name = "COMMA", length = 60)
	private String comma;

	@Column(name = "OGGETTO")
	private Integer oggetto;

	@Column(name = "PARERE", length = 4000)
	private String parere;

	@Column(name = "ID_TIPO_PROVVEDIMENTO")
	private Integer idTipoProvvedimento;

	@Column(name = "TERMINE_SCADENZA")
	private Date termineScadenza;

	@Column(name = "ID_STATO")
	private Integer idStato;

	@Column(name = "ID_ORGANO_INSERITORE")
	private Integer idOrganoInseritore;

	@Column(name = "ID_ORGANO_CAPOFILA")
	private Integer idOrganoCapofila;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getFonteNormativa() {
		return fonteNormativa;
	}

	public void setFonteNormativa(String fonteNormativa) {
		this.fonteNormativa = fonteNormativa;
	}

	public String getArticolo() {
		return articolo;
	}

	public void setArticolo(String articolo) {
		this.articolo = articolo;
	}

	public String getComma() {
		return comma;
	}

	public void setComma(String comma) {
		this.comma = comma;
	}

	public Integer getOggetto() {
		return oggetto;
	}

	public void setOggetto(Integer oggetto) {
		this.oggetto = oggetto;
	}

	public String getParere() {
		return parere;
	}

	public void setParere(String parere) {
		this.parere = parere;
	}

	public Integer getIdTipoProvvedimento() {
		return idTipoProvvedimento;
	}

	public void setIdTipoProvvedimento(Integer idTipoProvvedimento) {
		this.idTipoProvvedimento = idTipoProvvedimento;
	}

	public Date getTermineScadenza() {
		return termineScadenza;
	}

	public void setTermineScadenza(Date termineScadenza) {
		this.termineScadenza = termineScadenza;
	}

	public Integer getIdStato() {
		return idStato;
	}

	public void setIdStato(Integer idStato) {
		this.idStato = idStato;
	}

	public Integer getIdOrganoInseritore() {
		return idOrganoInseritore;
	}

	public void setIdOrganoInseritore(Integer idOrganoInseritore) {
		this.idOrganoInseritore = idOrganoInseritore;
	}

	public Integer getIdOrganoCapofila() {
		return idOrganoCapofila;
	}

	public void setIdOrganoCapofila(Integer idOrganoCapofila) {
		this.idOrganoCapofila = idOrganoCapofila;
	}

}
