package it.tesoro.monprovv.dto;

public class SalvaENotificaDto {
	
	private String idProvvedimento;
	private String destinatari;
	private String oggetto;
	private String testo;
	
	public String getIdProvvedimento() {
		return idProvvedimento;
	}
	public void setIdProvvedimento(String idProvvedimento) {
		this.idProvvedimento = idProvvedimento;
	}
	public String getDestinatari() {
		return destinatari;
	}
	public void setDestinatari(String destinatari) {
		this.destinatari = destinatari;
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
	
	
}
