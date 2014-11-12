package it.tesoro.monprovv.dto;

public class SollecitoDto {

	private String oggettoSollecito;

	private String testoSollecito;

	private String idAssegnatarioSollecito;

	public SollecitoDto() {
		super();
	}

	public SollecitoDto(String oggettoSollecito, String testoSollecito,
			String idAssegnatarioSollecito) {
		super();
		this.oggettoSollecito = oggettoSollecito;
		this.testoSollecito = testoSollecito;
		this.idAssegnatarioSollecito = idAssegnatarioSollecito;
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
	
	
	
}
