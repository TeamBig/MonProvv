package it.tesoro.monprovv.dto;

import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.TipoAtto;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;

import java.io.Serializable;
import java.util.List;

public class GestioneTipologicheDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4258410385413585122L;
	
	private Integer idSchelta;

	private List<Governo> governi;
	private List<TipoAtto> tipiAtto;
	private List<TipoProvvDaAdottare> tipiProvv;
	
	private Integer idGoverno;
	private String denominazioneGoverno;
	
	private Integer idTipoAtto;
	private String codiceTipoAtto;
	private String descrizioneTipoAtto;

	private Integer idTipoProv;
	private String descrizioneTipoProv;
	
	public Integer getIdSchelta() {
		return idSchelta;
	}

	public void setIdSchelta(Integer idSchelta) {
		this.idSchelta = idSchelta;
	}

	public List<Governo> getGoverni() {
		return governi;
	}

	public void setGoverni(List<Governo> governi) {
		this.governi = governi;
	}

	public List<TipoAtto> getTipiAtto() {
		return tipiAtto;
	}

	public void setTipiAtto(List<TipoAtto> tipiAtto) {
		this.tipiAtto = tipiAtto;
	}

	public List<TipoProvvDaAdottare> getTipiProvv() {
		return tipiProvv;
	}

	public void setTipiProvv(List<TipoProvvDaAdottare> tipiProvv) {
		this.tipiProvv = tipiProvv;
	}

	public Integer getIdGoverno() {
		return idGoverno;
	}

	public void setIdGoverno(Integer idGoverno) {
		this.idGoverno = idGoverno;
	}

	public String getDenominazioneGoverno() {
		return denominazioneGoverno;
	}

	public void setDenominazioneGoverno(String denominazioneGoverno) {
		this.denominazioneGoverno = denominazioneGoverno;
	}

	public Integer getIdTipoAtto() {
		return idTipoAtto;
	}

	public void setIdTipoAtto(Integer idTipoAtto) {
		this.idTipoAtto = idTipoAtto;
	}

	public String getCodiceTipoAtto() {
		return codiceTipoAtto;
	}

	public void setCodiceTipoAtto(String codiceTipoAtto) {
		this.codiceTipoAtto = codiceTipoAtto;
	}

	public String getDescrizioneTipoAtto() {
		return descrizioneTipoAtto;
	}

	public void setDescrizioneTipoAtto(String descrizioneTipoAtto) {
		this.descrizioneTipoAtto = descrizioneTipoAtto;
	}

	public Integer getIdTipoProv() {
		return idTipoProv;
	}

	public void setIdTipoProv(Integer idTipoProv) {
		this.idTipoProv = idTipoProv;
	}

	public String getDescrizioneTipoProv() {
		return descrizioneTipoProv;
	}

	public void setDescrizioneTipoProv(String descrizioneTipoProv) {
		this.descrizioneTipoProv = descrizioneTipoProv;
	}
	
}
