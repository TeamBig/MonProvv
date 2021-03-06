package it.tesoro.monprovv.dto;

import java.io.Serializable;

public class UtenteDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1309927040145303190L;

	private Integer id;
	
	private String cognome;
	
	private String nome;
	
	private String codiceFiscale;
	
	private String email;
	
	private String organo;
	
	private Integer idOrgano;

	private String flagAttivo;
	
	private String sesso;
	
	private String dataNascita;
	
	public UtenteDto(){
		this.flagAttivo = "S";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgano() {
		return organo;
	}

	public void setOrgano(String organo) {
		this.organo = organo;
	}

	public Integer getIdOrgano() {
		return idOrgano;
	}

	public void setIdOrgano(Integer idOrgano) {
		this.idOrgano = idOrgano;
	}
	
	public String getFlagAttivo() {
		return flagAttivo;
	}

	public void setFlagAttivo(String flagAttivo) {
		this.flagAttivo = flagAttivo;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	
}
