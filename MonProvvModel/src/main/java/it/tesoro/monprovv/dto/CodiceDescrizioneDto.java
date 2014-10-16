package it.tesoro.monprovv.dto;

public class CodiceDescrizioneDto {
	private String codice;
	private String descrizione;
	
	public CodiceDescrizioneDto(){
		
	}
	
	public CodiceDescrizioneDto(String codice, String descrizione){
		this.codice = codice;
		this.descrizione = descrizione;
	}
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
}
