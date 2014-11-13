package it.tesoro.monprovv.dto;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.CollectionUtils;

import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Provvedimento;

public class ProvvedimentoStampaDto {
	
	private Provvedimento provvedimento; 
	
	public ProvvedimentoStampaDto(Provvedimento provvedimento) {
		this.provvedimento = provvedimento;
	}
	
	public String getGoverno() {
		return provvedimento.getGoverno().getDenominazione();
	}
	public String getTipologia() {
		return provvedimento.getTipoProvvedimento().getDescrizione();
	}
	
	public String getFonteInfo() {
		return provvedimento.getTipoAtto().getDescrizione() + " " 
				+ DateFormatUtils.format(provvedimento.getDataAtto(), "dd-MM-yyyy") + " " 
				+ "Nr. " + provvedimento.getNumeroAtto();
	}
	
	public String getArticolo() {
		return provvedimento.getArticolo();
	}
	
	public String getComma() {
		return provvedimento.getComma();
	}
	
	public String getProvvDaAdottare() {
		return provvedimento.getTipoProvvDaAdottare().getDescrizione();
	}
	
	public String getTitoloOgg() throws IOException, SQLException {
		return provvedimento.getOggettoAsText();
	}
	
	public String getStatoAtt() {
		return provvedimento.getStato().getDescrizione();
	}
	
	public String getCapofila() {
		return provvedimento.getOrganoCapofila().getDenominazione();
	}
	
	public String getProponente() {
		if (provvedimento.getOrganoConcertante() != null) {
			return provvedimento.getOrganoConcertante().getDenominazione();
		}
		return null;
	}
	
	public String getAssegnazione() {
		if (!CollectionUtils.isEmpty(provvedimento.getAssegnazioneList())) {
			
			String result = "";
			for (Assegnazione assegnazione : provvedimento.getAssegnazioneList() ) {
				result += assegnazione.getOrgano().getDenominazione() + "<br/>";
			}
			return result;
		}
		return null;
	}
	
	public String getId() {
		return provvedimento.getId().toString();
	}
}
