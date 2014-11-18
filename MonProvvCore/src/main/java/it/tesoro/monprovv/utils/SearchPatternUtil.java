package it.tesoro.monprovv.utils;

import java.io.Serializable;

public class SearchPatternUtil implements Serializable {

	private static final long serialVersionUID = 1431920117984851089L;

	private String nomeCampo;
	private String pattern;
	private boolean preponi;
	private boolean postponi;
	private String operazione;
	private boolean customPattern;

	public SearchPatternUtil() {
	}

	public SearchPatternUtil(String nomeCampo, String pattern, boolean preponi,
			boolean postponi, boolean customPattern) {
		this.nomeCampo = nomeCampo;
		this.pattern = pattern;
		this.preponi = preponi;
		this.postponi = postponi;
		this.customPattern = customPattern;
	}

	public SearchPatternUtil(String nomeCampo, String pattern, boolean preponi,
			boolean postponi, String operazione) {
		this.nomeCampo = nomeCampo;
		this.pattern = pattern;
		this.preponi = preponi;
		this.postponi = postponi;
		this.operazione = operazione;
	}

	public SearchPatternUtil(String nomeCampo, String pattern, String operazione) {
		this.nomeCampo = nomeCampo;
		this.pattern = pattern;
		this.preponi = false;
		this.postponi = false;
		this.operazione = operazione;
	}

	public SearchPatternUtil(String nomeCampo, String operazione) {
		this.nomeCampo = nomeCampo;
		this.pattern = null;
		this.preponi = false;
		this.postponi = false;
		this.operazione = operazione;
	}
	
	public SearchPatternUtil(String nomeCampo, String pattern, boolean preponi,
			boolean postponi) {
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

	public String getOperazione() {
		return operazione;
	}

	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}

	public boolean isCustomPattern() {
		return customPattern;
	}

	public void setCustomPattern(boolean customPattern) {
		this.customPattern = customPattern;
	}

}
