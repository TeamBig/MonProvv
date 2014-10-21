package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UNITA_ORG_ASTAGE")
public class UnitaOrgAstage extends AbstractCommonEntity implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5995817291213358570L;

	@Id
	@Column(name = "ORGANIZATION_ID",unique=true, nullable=false)
	private Integer id;

	@Column(name="CONTATORE",unique=true, nullable=false)
	private Integer contatore;
	
	@Column(name = "LIVELLO")
	private Integer livello;

	@Column(name = "NOME", length = 60)
	private String nome;

	@Column(name = "NOME_ESTESO", length = 400)
	private String nomeEsteso;

	@Column(name = "PROVINCIA", length = 30)
	private String provincia;

	@Column(name = "REGIONE", length = 30)
	private String regione;

	@Column(name = "ORG_ID_LIV1")
	private Integer orgIdLiv1;
	
	@Column(name = "NOME_ORG_LIV1", length = 60)
	private String nomeOrgLiv1;
	
	@Column(name = "ORG_ID_LIV2")
	private Integer orgIdLiv2;
	
	@Column(name = "NOME_ORG_LIV2", length = 60)
	private String nomeOrgLiv2;

	@Column(name = "ORG_ID_LIV3")
	private Integer orgIdLiv3;
	
	@Column(name = "NOME_ORG_LIV3", length = 60)
	private String nomeOrgLiv3;

	@Column(name = "ORG_ID_LIV4")
	private Integer orgIdLiv4;

	@Column(name = "NOME_ORG_LIV4", length = 60)
	private String nomeOrgLiv4;
	
	@Column(name = "ORG_ID_LIV5")
	private Integer orgIdLiv5;

	@Column(name = "NOME_ORG_LIV5", length = 60)
	private String nomeOrgLiv5;

	@Column(name = "ORG_ID_LIV6")
	private Integer orgIdLiv6;
	
	@Column(name = "NOME_ORG_LIV6", length = 60)
	private String nomeOrgLiv6;

	@Column(name = "NOTE", length = 100)
	private String note;

	@Column(name = "CENTR_PERIF", length = 1)
	private String centrPerif;

	@Column(name = "INIZIO_VALIDITA")
	private Date inizioValidita;

	@Column(name = "FINE_VALIDITA")
	private Date fineValidita;

	@Column(name = "FLAG_FISSO", length = 1)
	private String flagFisso;


	


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
		UnitaOrgAstage other = (UnitaOrgAstage) obj;
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

	public Integer getContatore() {
		return contatore;
	}

	public void setContatore(Integer contatore) {
		this.contatore = contatore;
	}

	public Integer getLivello() {
		return livello;
	}

	public void setLivello(Integer livello) {
		this.livello = livello;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeEsteso() {
		return nomeEsteso;
	}

	public void setNomeEsteso(String nomeEsteso) {
		this.nomeEsteso = nomeEsteso;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public Integer getOrgIdLiv1() {
		return orgIdLiv1;
	}

	public void setOrgIdLiv1(Integer orgIdLiv1) {
		this.orgIdLiv1 = orgIdLiv1;
	}

	public String getNomeOrgLiv1() {
		return nomeOrgLiv1;
	}

	public void setNomeOrgLiv1(String nomeOrgLiv1) {
		this.nomeOrgLiv1 = nomeOrgLiv1;
	}

	public Integer getOrgIdLiv2() {
		return orgIdLiv2;
	}

	public void setOrgIdLiv2(Integer orgIdLiv2) {
		this.orgIdLiv2 = orgIdLiv2;
	}

	public String getNomeOrgLiv2() {
		return nomeOrgLiv2;
	}

	public void setNomeOrgLiv2(String nomeOrgLiv2) {
		this.nomeOrgLiv2 = nomeOrgLiv2;
	}

	public Integer getOrgIdLiv3() {
		return orgIdLiv3;
	}

	public void setOrgIdLiv3(Integer orgIdLiv3) {
		this.orgIdLiv3 = orgIdLiv3;
	}

	public String getNomeOrgLiv3() {
		return nomeOrgLiv3;
	}

	public void setNomeOrgLiv3(String nomeOrgLiv3) {
		this.nomeOrgLiv3 = nomeOrgLiv3;
	}

	public Integer getOrgIdLiv4() {
		return orgIdLiv4;
	}

	public void setOrgIdLiv4(Integer orgIdLiv4) {
		this.orgIdLiv4 = orgIdLiv4;
	}

	public String getNomeOrgLiv4() {
		return nomeOrgLiv4;
	}

	public void setNomeOrgLiv4(String nomeOrgLiv4) {
		this.nomeOrgLiv4 = nomeOrgLiv4;
	}

	public Integer getOrgIdLiv5() {
		return orgIdLiv5;
	}

	public void setOrgIdLiv5(Integer orgIdLiv5) {
		this.orgIdLiv5 = orgIdLiv5;
	}

	public String getNomeOrgLiv5() {
		return nomeOrgLiv5;
	}

	public void setNomeOrgLiv5(String nomeOrgLiv5) {
		this.nomeOrgLiv5 = nomeOrgLiv5;
	}

	public Integer getOrgIdLiv6() {
		return orgIdLiv6;
	}

	public void setOrgIdLiv6(Integer orgIdLiv6) {
		this.orgIdLiv6 = orgIdLiv6;
	}

	public String getNomeOrgLiv6() {
		return nomeOrgLiv6;
	}

	public void setNomeOrgLiv6(String nomeOrgLiv6) {
		this.nomeOrgLiv6 = nomeOrgLiv6;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCentrPerif() {
		return centrPerif;
	}

	public void setCentrPerif(String centrPerif) {
		this.centrPerif = centrPerif;
	}

	public Date getInizioValidita() {
		return inizioValidita;
	}

	public void setInizioValidita(Date inizioValidita) {
		this.inizioValidita = inizioValidita;
	}

	public Date getFineValidita() {
		return fineValidita;
	}

	public void setFineValidita(Date fineValidita) {
		this.fineValidita = fineValidita;
	}

	public String getFlagFisso() {
		return flagFisso;
	}

	public void setFlagFisso(String flagFisso) {
		this.flagFisso = flagFisso;
	}


	
}
