package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ORGANO")
public class Organo extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -88624704588322807L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_ORGANO"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID_ORGANO")
	private Integer id;

	@Column(name = "DENOMINAZIONE", length = 240)
	@NotNull
	private String denominazione;

	@Column(name = "DENOMINAZIONE_ESTESA", length = 2000)
	private String denominazioneEstesa;
	
	@Column(name="FLAG_CONCERTANTE", length=1)
	private String flagConcertante;
	
	@Transient
	private String flgInternoEsterno;
	
	public String getConcertante() {
		return ("S".equals(flagConcertante))?"Concertante":"Non Concertante" ;
	}
	
	@ManyToOne(targetEntity=UnitaOrgAstage.class, cascade = CascadeType.ALL)
    @JoinColumn(name="ORGANIZATION_ID", referencedColumnName="ORGANIZATION_ID")
	private UnitaOrgAstage unitaOrgAstage;
	
	public String getTipo(){
		if(this.unitaOrgAstage != null){
			return "Interno";
		}else{
			return "Esterno";
		}
	}
	
	@OneToMany(targetEntity=Utente.class, fetch=FetchType.EAGER, mappedBy="organo")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Utente> utenteList;
	
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
		Organo other = (Organo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// Property accessors
	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getDenominazioneEstesa() {
		return denominazioneEstesa;
	}

	public void setDenominazioneEstesa(String denominazioneEstesa) {
		this.denominazioneEstesa = denominazioneEstesa;
	}

	public String getFlagConcertante() {
		return flagConcertante;
	}

	public void setFlagConcertante(String flagConcertante) {
		this.flagConcertante = flagConcertante;
	}

	public UnitaOrgAstage getUnitaOrgAstage() {
		return unitaOrgAstage;
	}

	public void setUnitaOrgAstage(UnitaOrgAstage unitaOrgAstage) {
		this.unitaOrgAstage = unitaOrgAstage;
	}

	public List<Utente> getUtenteList() {
		return utenteList;
	}

	public void setUtenteList(List<Utente> utenteList) {
		this.utenteList = utenteList;
	}

	public String getFlgInternoEsterno() {
		return flgInternoEsterno;
	}

	public void setFlgInternoEsterno(String flgInternoEsterno) {
		this.flgInternoEsterno = flgInternoEsterno;
	}

	
}
