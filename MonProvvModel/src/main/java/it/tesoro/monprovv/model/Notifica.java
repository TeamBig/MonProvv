package it.tesoro.monprovv.model;

import it.tesoro.monprovv.model.common.AbstractCommonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "NOTIFICA")
public class Notifica extends AbstractCommonEntity implements Serializable {

	public static final String OPERATIVA = "O";
	public static final String INFORMATIVA = "I";
	
	public static final String LETTA = "S";
	public static final String NON_LETTA = "N";

	private static final long serialVersionUID = 7136472204232788138L;

	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQU_ID_NOTIFICA"))
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID_NOTIFICA")
	private Integer id;

	@ManyToOne(targetEntity=Organo.class)
    @JoinColumn(name="ID_ORGANO_DESTINATARIO", referencedColumnName="ID_ORGANO")
	@Valid
	private Organo organoDestinatario;

	@ManyToOne(targetEntity=Utente.class)
    @JoinColumn(name="ID_UTENTE_DESTINATARIO", referencedColumnName="ID_UTENTE")
	@Valid
	private Utente utenteDestinatario;

	@ManyToOne(targetEntity=Utente.class)
    @JoinColumn(name="ID_UTENTE_MITTORGANO", referencedColumnName="ID_UTENTE")
	@Valid
	@NotNull
	private Utente utenteMittente;

	@ManyToOne(targetEntity=Utente.class)
    @JoinColumn(name="ID_UTENTE_OPERATORE", referencedColumnName="ID_UTENTE")
	private Utente utenteOperatore;	
	
	@Column(name = "TIPO_NOTIFICA", length = 1)
	@NotNull
	private String tipoNotifica;

	@Column(name = "OGGETTO", length = 240)
	@NotNull
	private String oggetto;

	@Column(name = "TESTO", length = 4000)
	@NotNull
	private String testo;

	@Column(name = "LINK_OPERAZIONE", length = 240)
	@NotNull
	private String linkOperazione;

	@Column(name = "FLAG_LETTURA", length = 1)
	private String flagLettura;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Organo getOrganoDestinatario() {
		return organoDestinatario;
	}

	public void setOrganoDestinatario(Organo organoDestinatario) {
		this.organoDestinatario = organoDestinatario;
	}

	public Utente getUtenteDestinatario() {
		return utenteDestinatario;
	}

	public void setUtenteDestinatario(Utente utenteDestinatario) {
		this.utenteDestinatario = utenteDestinatario;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getLinkOperazione() {
		return linkOperazione;
	}

	public void setLinkOperazione(String linkOperazione) {
		this.linkOperazione = linkOperazione;
	}

	public String getFlagLettura() {
		return flagLettura;
	}

	public void setFlagLettura(String flagLettura) {
		this.flagLettura = flagLettura;
	}

	public String getTipoNotifica() {
		return tipoNotifica;
	}

	public void setTipoNotifica(String tipoNotifica) {
		this.tipoNotifica = tipoNotifica;
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
		Notifica other = (Notifica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Utente getUtenteMittente() {
		return utenteMittente;
	}

	public void setUtenteMittente(Utente utenteMittente) {
		this.utenteMittente = utenteMittente;
	}

}
