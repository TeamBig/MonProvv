package it.tesoro.monprovv.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class IndirizzoEmailDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5129115084562296241L;
	
	private String nome;
	private String cognome;
	private String email;
	
	public IndirizzoEmailDto(){
		
	}
	
	public IndirizzoEmailDto(String nome, String cognome, String email){
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
	}
	
	public String getDescrizione(){
		String retval = "";
		retval = StringUtils.isNotEmpty( this.nome )?this.nome + " ":"";
		retval = retval + (StringUtils.isNotEmpty( this.cognome )?this.cognome + " ":"");
		if( StringUtils.isEmpty( retval ) ) {
			retval = this.email;
		}else{
			retval = retval + "(" + this.email + ")";
		}
		return retval;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
