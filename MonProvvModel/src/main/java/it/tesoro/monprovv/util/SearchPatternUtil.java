package it.tesoro.monprovv.util;

import java.io.Serializable;

public class SearchPatternUtil implements Serializable{

	private static final long serialVersionUID = 1431920117984851089L;
	
	private String nomeCampo;
	private String pattern;
	private boolean preponi;
	private boolean postponi;
	
	public SearchPatternUtil() {
	}
	public SearchPatternUtil(String nomeCampo, String pattern, boolean preponi, boolean postponi) {
		this.nomeCampo = nomeCampo;
		this.pattern = pattern;
		this.preponi = preponi;
		this.postponi = postponi;
	}
	
	public String getNomeCampo() {
		return nomeCampo;
	}
	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public boolean isPreponi() {
		return preponi;
	}
	public void setPreponi(boolean preponi) {
		this.preponi = preponi;
	}
	public boolean isPostponi() {
		return postponi;
	}
	public void setPostponi(boolean postponi) {
		this.postponi = postponi;
	}
}
