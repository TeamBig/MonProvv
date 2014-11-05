package it.tesoro.monprovv.dto;


public class AllegatoDto {
	private String id;
	private String nomefile;
	private String dimensione;
	private String descrizione;
	
	public AllegatoDto(){
		
	}
	
	public AllegatoDto(Integer id, String nomefile, String dimensione, String descrizione){
		this.id = Integer.toString(id);
		this.nomefile = nomefile;
		this.dimensione = dimensione;
		this.descrizione = descrizione;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomefile() {
		return nomefile;
	}
	public void setNomefile(String nomefile) {
		this.nomefile = nomefile;
	}
	public String getDimensione() {
		return dimensione;
	}
	public void setDimensione(String dimensione) {
		this.dimensione = dimensione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

//	public String getJSON() {
//		
//		String encoded = null;
//		try {
//			ObjectMapper mapper = new ObjectMapper();
//			encoded = mapper.writeValueAsString(this);
//		} catch (JsonProcessingException e) {
//			encoded = null;
//		}
//		
//		return encoded;
//		
////		String retval = "{\"id\":"+this.id+",\"nomefile\":\""+this.nomefile+"\",\"dimensione\":\""+this.dimensione+"\",\"descrizione\":\""+this.descrizione+"\"}";
////		return retval;
//	}
	
	
}
