package it.tesoro.monprovv.dto;

import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoAtto;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InserisciProvvedimentoDto {

	public InserisciProvvedimentoDto() {
		listaAssegnazione = new ArrayList<Assegnazione>();
		listaProvvedimenti = new ArrayList<Provvedimento>();
		idAssegnatariUpdList = new ArrayList<Integer>();
		idAllegatiUpdList = new ArrayList<Integer>();
	}

	public Provvedimento getProvvedimento() {
		Provvedimento provv = new Provvedimento();
		provv.setGoverno(this.getTipoGoverno());
		provv.setArticolo(this.getArt());
		provv.setComma(this.getComma());
		provv.setCollNormattiva(this.getCollNormattiva());
		provv.setDataAtto(this.getDataAtto());
		provv.setTermineScadenza(this.getDtTermineScadenza());
		provv.setNumeroAtto(Integer.valueOf(this.getNumeroAtto()));
		provv.setStato(this.getStatoDiAttuazione());
		provv.setTipoProvvDaAdottare(this.getTipoProvvDaAdottare());
		provv.setParere(this.getParere());
		provv.setOrganoCapofila(this.getOrganoCapofila());
		provv.setOrganoInseritore(this.getOrganoInseritore());
		provv.setTipoAtto(this.getTipoAtto());
		provv.setTipoProvvedimento(this.getTipologia());
		provv.setOrganoConcertante(this.getProponente());
		// provv.setOggetto(this.getTitoloOggetto());
		return provv;
	}

	private Stato statoDiAttuazione;
	private Governo tipoGoverno;
	private TipoProvvedimento tipologia;
	private String art;
	private String comma;
	private String titoloOggetto;
	private Date dtTermineScadenza;
	private String fonteNormativa;
	private String collNormattiva;
	private TipoProvvDaAdottare tipoProvvDaAdottare;
	private Organo organoCapofila;
	private Organo proponente;
	private Organo organoInseritore;
	private Organo assegnatario;
	private String parere;
	private Date dataAtto;
	private String numeroAtto;
	private TipoAtto tipoAtto;
	private String currentStep;
	private String stepSuccessivo;
	private List<Assegnazione> listaAssegnazione;
	private List<Integer> idAssegnatariUpdList;
	private List<Integer> idAllegatiUpdList;
	private List<Provvedimento> listaProvvedimenti;
	private String[] provvedimentiSelected;

	public Stato getStatoDiAttuazione() {
		return statoDiAttuazione;
	}

	public void setStatoDiAttuazione(Stato statoDiAttuazione) {
		this.statoDiAttuazione = statoDiAttuazione;
	}

	public Governo getTipoGoverno() {
		return tipoGoverno;
	}

	public void setTipoGoverno(Governo tipoGoverno) {
		this.tipoGoverno = tipoGoverno;
	}

	public TipoProvvedimento getTipologia() {
		return tipologia;
	}

	public void setTipologia(TipoProvvedimento tipologia) {
		this.tipologia = tipologia;
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

	public String getFonteNormativa() {
		return fonteNormativa;
	}

	public void setFonteNormativa(String fonteNormativa) {
		this.fonteNormativa = fonteNormativa;
	}

	public TipoProvvDaAdottare getTipoProvvDaAdottare() {
		return tipoProvvDaAdottare;
	}

	public void setTipoProvvDaAdottare(TipoProvvDaAdottare tipoProvvDaAdottare) {
		this.tipoProvvDaAdottare = tipoProvvDaAdottare;
	}

	public Organo getProponente() {
		return proponente;
	}

	public void setProponente(Organo proponente) {
		this.proponente = proponente;
	}

	public Organo getOrganoCapofila() {
		return organoCapofila;
	}

	public void setOrganoCapofila(Organo organoCapofila) {
		this.organoCapofila = organoCapofila;
	}

	public String getParere() {
		return parere;
	}

	public void setParere(String parere) {
		this.parere = parere;
	}

	public Date getDataAtto() {
		return dataAtto;
	}

	public void setDataAtto(Date dataAtto) {
		this.dataAtto = dataAtto;
	}

	public String getNumeroAtto() {
		return numeroAtto;
	}

	public void setNumeroAtto(String numeroAtto) {
		this.numeroAtto = numeroAtto;
	}

	public TipoAtto getTipoAtto() {
		return tipoAtto;
	}

	public void setTipoAtto(TipoAtto tipoAtto) {
		this.tipoAtto = tipoAtto;
	}

	public String getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(String currentStep) {
		this.currentStep = currentStep;
	}

	public String getStepSuccessivo() {
		return stepSuccessivo;
	}

	public void setStepSuccessivo(String stepSuccessivo) {
		this.stepSuccessivo = stepSuccessivo;
	}

	public Date getDtTermineScadenza() {
		return dtTermineScadenza;
	}

	public void setDtTermineScadenza(Date dtTermineScadenza) {
		this.dtTermineScadenza = dtTermineScadenza;
	}

	public List<Assegnazione> getListaAssegnazione() {
		return listaAssegnazione;
	}

	public void setListaAssegnazione(List<Assegnazione> listaAssegnazione) {
		this.listaAssegnazione = listaAssegnazione;
	}

	public List<Provvedimento> getListaProvvedimenti() {
		return listaProvvedimenti;
	}

	public void setListaProvvedimenti(List<Provvedimento> listaProvvedimenti) {
		this.listaProvvedimenti = listaProvvedimenti;
	}

	public String[] getProvvedimentiSelected() {
		return provvedimentiSelected;
	}

	public void setProvvedimentiSelected(String[] provvedimentiSelected) {
		this.provvedimentiSelected = provvedimentiSelected;
	}

	public String getCollNormattiva() {
		return collNormattiva;
	}

	public void setCollNormattiva(String collNormattiva) {
		this.collNormattiva = collNormattiva;
	}

	public Organo getOrganoInseritore() {
		return organoInseritore;
	}

	public void setOrganoInseritore(Organo organoInseritore) {
		this.organoInseritore = organoInseritore;
	}

	public Organo getAssegnatario() {
		return assegnatario;
	}

	public void setAssegnatario(Organo assegnatario) {
		this.assegnatario = assegnatario;
	}

	public List<Integer> getIdAllegatiUpdList() {
		return idAllegatiUpdList;
	}

	public void setIdAllegatiUpdList(List<Integer> idAllegatiUpdList) {
		this.idAllegatiUpdList = idAllegatiUpdList;
	}

	public List<Integer> getIdAssegnatariUpdList() {
		return idAssegnatariUpdList;
	}

	public void setIdAssegnatariUpdList(List<Integer> idAssegnatariUpdList) {
		this.idAssegnatariUpdList = idAssegnatariUpdList;
	}

}
