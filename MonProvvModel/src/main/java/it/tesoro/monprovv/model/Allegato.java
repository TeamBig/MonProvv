package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;
import it.tesoro.monprovv.util.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ALLEGATO")
public class Allegato extends AbstractCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7425231841601536226L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_ALLEGATO"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID_ALLEGATO")
	private Integer id;

	@ManyToOne(targetEntity=Assegnazione.class)
    @JoinColumn(name="ID_ASSEGNAZIONE", referencedColumnName="ID_ASSEGNAZIONE")
	@Valid
	private Assegnazione assegnazione;

	@ManyToOne(targetEntity=Provvedimento.class)
    @JoinColumn(name="ID_PROVVEDIMENTO", referencedColumnName="ID_PROVVEDIMENTO")
	@Valid
	private Provvedimento provvedimento;

	@Column(name = "NOMEFILE", length = 240)
	@NotNull
	private String nomefile;

	@Column(name = "CONTENUTO")
	private Blob contenuto;

	@Column(name = "DIMENSIONE")
	private Integer dimensione;

	@Column(name = "FLAG_VISIBILE", length = 1)
	private String flagVisibile;
	
	@Column(name = "DESCRIZIONE", length = 240)
	private String descrizione;
	
	@Transient
	public String getDimensioneAsText() throws IOException, SQLException {
	
		if (dimensione != null) {
			return StringUtils.convertBytesToKb(dimensione, true);
		}
		
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Assegnazione getAssegnazione() {
		return assegnazione;
	}

	public void setAssegnazione(Assegnazione assegnazione) {
		this.assegnazione = assegnazione;
	}

	public Provvedimento getProvvedimento() {
		return provvedimento;
	}

	public void setProvvedimento(Provvedimento provvedimento) {
		this.provvedimento = provvedimento;
	}

	public String getNomefile() {
		return nomefile;
	}

	public void setNomefile(String nomefile) {
		this.nomefile = nomefile;
	}

	public Blob getContenuto() {
		return contenuto;
	}

	public void setContenuto(Blob contenuto) {
		this.contenuto = contenuto;
	}

	public Integer getDimensione() {
		return dimensione;
	}

	public void setDimensione(Integer dimensione) {
		this.dimensione = dimensione;
	}
	
	public String getFlagVisibile() {
		return flagVisibile;
	}

	public void setFlagVisibile(String flagVisibile) {
		this.flagVisibile = flagVisibile;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
		Allegato other = (Allegato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}
