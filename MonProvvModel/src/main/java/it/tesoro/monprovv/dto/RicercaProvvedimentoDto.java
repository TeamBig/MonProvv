package it.tesoro.monprovv.dto;

import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;

import java.util.Date;

public class RicercaProvvedimentoDto {

	private Stato statoDiAttuazione;
	private Governo tipoGoverno;
	private TipoProvvedimento tipologia;
	private String art;
	private String comma;
	private String titoloOggetto;
	private Date dtTermineScadenzaDa;
	private Date dtTermineScadenzaA;
	private String fonteNormativa;
	private TipoProvvDaAdottare tipoProvvDaAdottare;
	private String[] ammUfficiCoinvolti;
	
	public boolean filtriImpostati(){
		if( statoDiAttuazione != null ){
			return true;
		}
		if( tipoGoverno != null ){
			return true;
		}
		if( tipologia != null ){
			return true;
		}
		if( art != null ){
			return true;
		}
		if( comma != null && (!"".equals(comma)) ){
			return true;
		}
		if( titoloOggetto != null && (!"".equals(titoloOggetto)) ){
			return true;
		}
		if( dtTermineScadenzaDa != null ){
			return true;
		}
		if( dtTermineScadenzaA != null ){
			return true;
		}
		if( fonteNormativa != null && (!"".equals(fonteNormativa)) ){
			return true;
		}
		if( tipoProvvDaAdottare != null ){
			return true;
		}
		if( ammUfficiCoinvolti != null ){
			return true;
		}
		return false;
	}
	
	public boolean filtriAvanzatiImpostati(){
		if( dtTermineScadenzaDa != null ){
			return true;
		}
		if( dtTermineScadenzaA != null ){
			return true;
		}
		if( fonteNormativa != null && (!"".equals(fonteNormativa)) ){
			return true;
		}
		if( tipoProvvDaAdottare != null ){
			return true;
		}
		if( ammUfficiCoinvolti != null && ammUfficiCoinvolti.length > 0){
			return true;
		}
		return false;
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

	public Governo getTipoGoverno() {
		return tipoGoverno;
	}

	public void setTipoGoverno(Governo tipoGoverno) {
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

	public String[] getAmmUfficiCoinvolti() {
		return ammUfficiCoinvolti;
	}

	public void setAmmUfficiCoinvolti(String[] ammUfficiCoinvolti) {
		this.ammUfficiCoinvolti = ammUfficiCoinvolti;
	}

}
