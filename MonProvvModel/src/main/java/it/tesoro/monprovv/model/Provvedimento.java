package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "PROVVEDIMENTO")
public class Provvedimento extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3895683101409100101L;

	public Provvedimento getProvvedimentoToUpdate(Provvedimento prov) {
		this.setArticolo(prov.getArticolo());
		this.setComma(prov.getComma());
		this.setGoverno(prov.getGoverno());
		this.setCollNormattiva(prov.getCollNormattiva());
		this.setDataAtto(prov.getDataAtto());
		this.setNoteInterne(prov.getNoteInterne());
		this.setNumeroAtto(prov.getNumeroAtto());
		this.setOggetto(prov.getOggetto());
		this.setParere(prov.getParere());
		this.setStato(prov.getStato());
		this.setTermineScadenza(prov.getTermineScadenza());
		this.setSenzaTermine(prov.isSenzaTermine());
		this.setTipoAtto(prov.getTipoAtto());
		this.setTipoProvvDaAdottare(prov.getTipoProvvDaAdottare());
		this.setTipoProvvedimento(prov.getTipoProvvedimento());
		this.setOrganoConcertante(prov.getOrganoConcertante());
		this.setOrganoParere(prov.getOrganoParere());
		this.setVersione(prov.getVersione());
		return this;
	}

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_PROVVEDIMENTO"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID_PROVVEDIMENTO")
	private Integer id;

	@Column(name = "FONTE_NORMATIVA", length = 240)
	private String fonteNormativa;

	@Column(name = "ARTICOLO", length = 60)
	private String articolo;

	@Column(name = "COMMA", length = 60)
	private String comma;

	@Column(name = "OGGETTO")
	private Clob oggetto;

	@Column(name = "PARERE", length = 4000)
	private String parere;

	@ManyToOne(targetEntity = TipoProvvedimento.class)
	@JoinColumn(name = "ID_TIPO_PROVVEDIMENTO", referencedColumnName = "ID_TIPO_PROVVEDIMENTO")
	@Valid
	@NotNull
	private TipoProvvedimento tipoProvvedimento;

	@ManyToOne(targetEntity = TipoProvvDaAdottare.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TIPO_PROVV_DA_ADOTTARE", referencedColumnName = "ID_TIPO_PROVV_DA_ADOTTARE")
	@Valid
	@NotNull
	private TipoProvvDaAdottare tipoProvvDaAdottare;

	@ManyToOne(targetEntity = Governo.class)
	@JoinColumn(name = "ID_GOVERNO", referencedColumnName = "ID_GOVERNO")
	@Valid
	@NotNull
	private Governo governo;

	@Column(name = "TERMINE_SCADENZA")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date termineScadenza;

	@ManyToOne(targetEntity = Stato.class)
	@JoinColumn(name = "ID_STATO", referencedColumnName = "ID_STATO")
	@Valid
	private Stato stato;

	@ManyToOne(targetEntity = Organo.class)
	@JoinColumn(name = "ID_ORGANO_INSERITORE", referencedColumnName = "ID_ORGANO")
	@Valid
	private Organo organoInseritore;

	@ManyToOne(targetEntity = Organo.class)
	@JoinColumn(name = "ID_ORGANO_CAPOFILA", referencedColumnName = "ID_ORGANO")
	@Valid
	private Organo organoCapofila;

	@ManyToOne(targetEntity = Organo.class)
	@JoinColumn(name = "ID_ORGANO_CONCERTANTE", referencedColumnName = "ID_ORGANO")
	@Valid
	private Organo organoConcertante;

	@Column(name = "DATA_ATTO")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dataAtto;

	@Column(name = "NUMERO_ATTO")
	private Integer numeroAtto;

	@Column(name = "COLL_NORMATTIVA")
	private String collNormattiva;

	@Column(name = "NOTE_INTERNE", length = 4000)
	private String noteInterne;

	@ManyToOne(targetEntity = TipoAtto.class)
	@JoinColumn(name = "ID_TIPO_ATTO", referencedColumnName = "ID_TIPO_ATTO")
	@Valid
	private TipoAtto tipoAtto;

	@OneToMany(targetEntity = ProvvedimentiParent.class, fetch = FetchType.EAGER, mappedBy = "provvedimento")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ProvvedimentiParent> provvedimentiParent;

	@OneToMany(targetEntity = Allegato.class, fetch = FetchType.EAGER, mappedBy = "provvedimento")
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<Allegato> allegatiList;

	@OneToMany(targetEntity = Assegnazione.class, fetch = FetchType.EAGER, mappedBy = "provvedimento")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Assegnazione> assegnazioneList;

//	@ManyToOne
//	@NotFound(action = NotFoundAction.IGNORE)
//	@JoinColumn(name = "ID_ORGANO_PARERE", insertable = false, updatable = false)
//	private Organo organoParere;
	
	@Column(name = "ID_ORGANO_PARERE")
	private Integer organoParere;
	
	@Transient
	private String desOrganoParere;
	
//	@Column(name = "FLG_SENZA_TERMINE", length = 1)
//	private String flgSenzaTermine;

	@Transient
	private boolean senzaTermine;
	
	@Transient
	private List<Integer> idAllegatiDelList;

	@Transient
	private List<Integer> idAllegatiUpdList;

	@Transient
	private String motivazioneRichiesta;
	
	@Transient
	private String oggettoSollecito;
	
	@Transient
	private String testoSollecito;
	
	@Transient
	private String idAssegnatarioSollecito;
	
	@Transient
	private String testoNotaAssegnazione;
	
	@Transient
	private Integer idNotaAssegnazione;
	
	@Transient
	private String motivazioneRifiuto;
	
	@Transient
	private String appoDataFormat4dataAtto;
	
	@Transient
	private String appoDataFormat4dtTermineScadenza;

	@Transient
	public String getOggettoAsText() throws IOException, SQLException {

		if (oggetto != null) {
			return IOUtils.toString(oggetto.getCharacterStream());
		}

		return null;
	}

	@Transient
	private String[] provvedimentiParentSelected;
	
	@Transient
	private List<Provvedimento> provvedimentiParentSel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFonteNormativa() {
		if (StringUtils.isEmpty(fonteNormativa)) {
			fonteNormativa = tipoAtto.getDescrizione() + " n. " + numeroAtto
					+ " del " + DateFormatUtils.format(dataAtto, "dd-MM-yyyy")
					+ " art. " + articolo + " comma " + comma;
		}
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

	public Clob getOggetto() {
		return oggetto;
	}

	public void setOggetto(Clob oggetto) {
		this.oggetto = oggetto;
	}

	public String getParere() {
		return parere;
	}

	public void setParere(String parere) {
		this.parere = parere;
	}

	public TipoProvvedimento getTipoProvvedimento() {
		return tipoProvvedimento;
	}

	public void setTipoProvvedimento(TipoProvvedimento tipoProvvedimento) {
		this.tipoProvvedimento = tipoProvvedimento;
	}

	public Date getTermineScadenza() {
		return termineScadenza;
	}

	public void setTermineScadenza(Date termineScadenza) {
		this.termineScadenza = termineScadenza;
		if( StringUtils.isEmpty(termineScadenza) ){
			this.setSenzaTermine(true);
		}
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Organo getOrganoInseritore() {
		return organoInseritore;
	}

	public void setOrganoInseritore(Organo organoInseritore) {
		this.organoInseritore = organoInseritore;
	}

	public Organo getOrganoCapofila() {
		return organoCapofila;
	}

	public void setOrganoCapofila(Organo organoCapofila) {
		this.organoCapofila = organoCapofila;
	}

	public List<ProvvedimentiParent> getProvvedimentiParent() {
		return provvedimentiParent;
	}

	public void setProvvedimentiParent(
			List<ProvvedimentiParent> provvedimentiParent) {
		this.provvedimentiParent = provvedimentiParent;
	}

	public Organo getOrganoConcertante() {
		return organoConcertante;
	}

	public void setOrganoConcertante(Organo organoConcertante) {
		this.organoConcertante = organoConcertante;
	}

	public TipoProvvDaAdottare getTipoProvvDaAdottare() {
		return tipoProvvDaAdottare;
	}

	public void setTipoProvvDaAdottare(TipoProvvDaAdottare tipoProvvDaAdottare) {
		this.tipoProvvDaAdottare = tipoProvvDaAdottare;
	}

	public Governo getGoverno() {
		return governo;
	}

	public void setGoverno(Governo governo) {
		this.governo = governo;
	}

	public List<Allegato> getAllegatiList() {
		return allegatiList;
	}

	public void setAllegatiList(List<Allegato> allegatiList) {
		this.allegatiList = allegatiList;
	}

	public List<Assegnazione> getAssegnazioneList() {
		return assegnazioneList;
	}

	public void setAssegnazioneList(List<Assegnazione> assegnazioneList) {
		this.assegnazioneList = assegnazioneList;
	}

	public Date getDataAtto() {
		return dataAtto;
	}

	public void setDataAtto(Date dataAtto) {
		this.dataAtto = dataAtto;
	}

	public Integer getNumeroAtto() {
		return numeroAtto;
	}

	public void setNumeroAtto(Integer numeroAtto) {
		this.numeroAtto = numeroAtto;
	}

	public String getCollNormattiva() {
		return collNormattiva;
	}

	public void setCollNormattiva(String collNormattiva) {
		this.collNormattiva = collNormattiva;
	}

	public String getNoteInterne() {
		return noteInterne;
	}

	public void setNoteInterne(String noteInterne) {
		this.noteInterne = noteInterne;
	}

	public TipoAtto getTipoAtto() {
		return tipoAtto;
	}

	public void setTipoAtto(TipoAtto tipoAtto) {
		this.tipoAtto = tipoAtto;
	}

	public List<Integer> getIdAllegatiDelList() {
		return idAllegatiDelList;
	}

	public void setIdAllegatiDelList(List<Integer> idAllegatiDelList) {
		this.idAllegatiDelList = idAllegatiDelList;
	}

	public List<Integer> getIdAllegatiUpdList() {
		return idAllegatiUpdList;
	}

	public void setIdAllegatiUpdList(List<Integer> idAllegatiUpdList) {
		this.idAllegatiUpdList = idAllegatiUpdList;
	}

	public String getMotivazioneRichiesta() {
		return motivazioneRichiesta;
	}

	public void setMotivazioneRichiesta(String motivazioneRichiesta) {
		this.motivazioneRichiesta = motivazioneRichiesta;
	}

	public String[] getProvvedimentiParentSelected() {
		return provvedimentiParentSelected;
	}

	public void setProvvedimentiParentSelected(
			String[] provvedimentiParentSelected) {
		this.provvedimentiParentSelected = provvedimentiParentSelected;
	}

	public String getOggettoSollecito() {
		return oggettoSollecito;
	}

	public void setOggettoSollecito(String oggettoSollecito) {
		this.oggettoSollecito = oggettoSollecito;
	}

	public String getTestoSollecito() {
		return testoSollecito;
	}

	public void setTestoSollecito(String testoSollecito) {
		this.testoSollecito = testoSollecito;
	}
	
	public String getIdAssegnatarioSollecito() {
		return idAssegnatarioSollecito;
	}

	public void setIdAssegnatarioSollecito(String idAssegnatarioSollecito) {
		this.idAssegnatarioSollecito = idAssegnatarioSollecito;
	}

	public Integer getOrganoParere() {
		return organoParere;
	}

	public void setOrganoParere(Integer organoParere) {
		this.organoParere = organoParere;
	}

	public String getDesOrganoParere() {
		return desOrganoParere;
	}

	public void setDesOrganoParere(String desOrganoParere) {
		this.desOrganoParere = desOrganoParere;
	}

	public String getTestoNotaAssegnazione() {
		return testoNotaAssegnazione;
	}

	public void setTestoNotaAssegnazione(String testoNotaAssegnazione) {
		this.testoNotaAssegnazione = testoNotaAssegnazione;
	}

	public Integer getIdNotaAssegnazione() {
		return idNotaAssegnazione;
	}

	public void setIdNotaAssegnazione(Integer idNotaAssegnazione) {
		this.idNotaAssegnazione = idNotaAssegnazione;
	}
	
	public String getMotivazioneRifiuto() {
		return motivazioneRifiuto;
	}

	public void setMotivazioneRifiuto(String motivazioneRifiuto) {
		this.motivazioneRifiuto = motivazioneRifiuto;
	}

	public List<Provvedimento> getProvvedimentiParentSel() {
		return provvedimentiParentSel;
	}

	public void setProvvedimentiParentSel(List<Provvedimento> provvedimentiParentSel) {
		this.provvedimentiParentSel = provvedimentiParentSel;
	}
	
//	public String getFlgSenzaTermine() {
//		return flgSenzaTermine;
//	}
//
//	public void setFlgSenzaTermine(String flgSenzaTermine) {
//		this.flgSenzaTermine = flgSenzaTermine;
//	}
	
	public boolean isSenzaTermine() {
		return this.senzaTermine; //(this.termineScadenza==null);// ("S".equals(this.flgSenzaTermine));
	}

	public void setSenzaTermine(boolean senzaTermine) {
		this.senzaTermine = senzaTermine;//this.flgSenzaTermine = (senzaTermine)?"S":"N";
	}

	

	public String getAppoDataFormat4dataAtto() {
		return appoDataFormat4dataAtto;
	}

	public void setAppoDataFormat4dataAtto(String appoDataFormat4dataAtto) {
		this.appoDataFormat4dataAtto = appoDataFormat4dataAtto;
	}

	public String getAppoDataFormat4dtTermineScadenza() {
		return appoDataFormat4dtTermineScadenza;
	}

	public void setAppoDataFormat4dtTermineScadenza(
			String appoDataFormat4dtTermineScadenza) {
		this.appoDataFormat4dtTermineScadenza = appoDataFormat4dtTermineScadenza;
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
		Provvedimento other = (Provvedimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
