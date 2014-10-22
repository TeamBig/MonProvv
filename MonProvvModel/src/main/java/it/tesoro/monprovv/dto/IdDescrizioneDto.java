package it.tesoro.monprovv.dto;

import java.io.Serializable;

public class IdDescrizioneDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1914648798651508196L;
	
	private Integer id;
	private String descrizione;
	
	public IdDescrizioneDto(){
		
	}
	
	public IdDescrizioneDto(Integer id, String descrizione){
		this.id = id;
		this.descrizione = descrizione;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
}
