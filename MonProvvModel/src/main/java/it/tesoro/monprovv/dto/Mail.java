package it.tesoro.monprovv.dto;

import java.io.Serializable;

public class Mail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4865336321496706545L;
	
	private String content;
	private String subject;
	private String destinatario;
	private boolean htmlFormat=false;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public boolean isHtmlFormat() {
		return htmlFormat;
	}
	public void setHtmlFormat(boolean htmlFormat) {
		this.htmlFormat = htmlFormat;
	}
	
}
