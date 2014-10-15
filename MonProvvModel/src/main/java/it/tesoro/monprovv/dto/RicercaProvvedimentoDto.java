package it.tesoro.monprovv.dto;

import java.util.Date;

import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;

public class RicercaProvvedimentoDto {

	private Stato cercaPerStato;
	private Stato statoDiAttuazione;
	private String tipoGoverno;
	private TipoProvvedimento tipologia;
	private String art;
	private String comma;
	private String titoloOggetto;
	private Date dtTermineScadenzaDa;
	private Date dtTermineScadenzaA;
	private String fonteNormativa;
	private TipoProvvDaAdottare tipoProvvDaAdottare;

	public Stato getCercaPerStato() {
		return cercaPerStato;
	}

	public void setCercaPerStato(Stato cercaPerStato) {
		this.cercaPerStato = cercaPerStato;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String getComma() {
		return comma;
	}

	public void setComma(String comma) {
		this.comma = comma;
	}

	public String getTitoloOggetto() {
		return titoloOggetto;
	}

	public void setTitoloOggetto(String titoloOggetto) {
		this.titoloOggetto = titoloOggetto;
	}

	public Date getDtTermineScadenzaDa() {
		return dtTermineScadenzaDa;
	}

	public void setDtTermineScadenzaDa(Date dtTermineScadenzaDa) {
		this.dtTermineScadenzaDa = dtTermineScadenzaDa;
	}

	public Date getDtTermineScadenzaA() {
		return dtTermineScadenzaA;
	}

	public void setDtTermineScadenzaA(Date dtTermineScadenzaA) {
		this.dtTermineScadenzaA = dtTermineScadenzaA;
	}

	public String getFonteNormativa() {
		return fonteNormativa;
	}

	public void setFonteNormativa(String fonteNormativa) {
		this.fonteNormativa = fonteNormativa;
	}

	public String getTipoGoverno() {
		return tipoGoverno;
	}

	public void setTipoGoverno(String tipoGoverno) {
		this.tipoGoverno = tipoGoverno;
	}

	public Stato getStatoDiAttuazione() {
		return statoDiAttuazione;
	}

	public void setStatoDiAttuazione(Stato statoDiAttuazione) {
		this.statoDiAttuazione = statoDiAttuazione;
	}

	public TipoProvvedimento getTipologia() {
		return tipologia;
	}

	public void setTipologia(TipoProvvedimento tipologia) {
		this.tipologia = tipologia;
	}

	public TipoProvvDaAdottare getTipoProvvDaAdottare() {
		return tipoProvvDaAdottare;
	}

	public void setTipoProvvDaAdottare(TipoProvvDaAdottare tipoProvvDaAdottare) {
		this.tipoProvvDaAdottare = tipoProvvDaAdottare;
	}

}
