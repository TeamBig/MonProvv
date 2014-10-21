package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;
import java.sql.Clob;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PROVVEDIMENTO")
public class Provvedimento extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3895683101409100101L;

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

	@ManyToOne(targetEntity=TipoProvvedimento.class)
    @JoinColumn(name="ID_TIPO_PROVVEDIMENTO", referencedColumnName="ID_TIPO_PROVVEDIMENTO")
	@Valid
	@NotNull
	private TipoProvvedimento tipoProvvedimento;

	@ManyToOne(targetEntity=TipoProvvDaAdottare.class, fetch = FetchType.EAGER)
    @JoinColumn(name="ID_TIPO_PROVV_DA_ADOTTARE", referencedColumnName="ID_TIPO_PROVV_DA_ADOTTARE")
	@Valid
	@NotNull
	private TipoProvvDaAdottare tipoProvvDaAdottare;
	
	@ManyToOne(targetEntity=Governo.class)
    @JoinColumn(name="ID_GOVERNO", referencedColumnName="ID_GOVERNO")
	@Valid
	@NotNull
	private Governo governo;
	
	@Column(name = "TERMINE_SCADENZA")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date termineScadenza;

	@ManyToOne(targetEntity=Stato.class)
    @JoinColumn(name="ID_STATO", referencedColumnName="ID_STATO")
	@Valid
	private Stato stato;

	@ManyToOne(targetEntity=Organo.class)
    @JoinColumn(name="ID_ORGANO_INSERITORE", referencedColumnName="ID_ORGANO")
	@Valid
	private Organo organoInseritore;
	
	@ManyToOne(targetEntity=Organo.class)
    @JoinColumn(name="ID_ORGANO_CAPOFILA", referencedColumnName="ID_ORGANO")
	@Valid
	private Organo organoCapofila;
	
	@ManyToOne(targetEntity=Organo.class)
    @JoinColumn(name="ID_ORGANO_CONCERTANTE", referencedColumnName="ID_ORGANO")
	@Valid
	private Organo organoConcertante;
	
	@OneToMany(targetEntity=ProvvedimentiParent.class, fetch=FetchType.LAZY, mappedBy="provvedimento")
	private List<ProvvedimentiParent> provvedimentiParent;
	
	@OneToMany(targetEntity=Allegato.class, fetch=FetchType.EAGER, mappedBy="provvedimento")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Allegato> allegatiList;
	
	@OneToMany(targetEntity=Assegnazione.class, fetch=FetchType.EAGER, mappedBy="provvedimento")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Assegnazione> assegnazioneList;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setProvvedimentiParent(List<ProvvedimentiParent> provvedimentiParent) {
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
