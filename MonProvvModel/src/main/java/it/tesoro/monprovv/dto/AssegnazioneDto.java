package it.tesoro.monprovv.dto;

public class AssegnazioneDto {
	private Integer id;
	private String nomeAssegnatario;

	public AssegnazioneDto() {
	}

	public AssegnazioneDto(Integer id, String nomeAssegnatario) {
		this.id = id;
		this.nomeAssegnatario = nomeAssegnatario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeAssegnatario() {
		return nomeAssegnatario;
	}

	public void setNomeAssegnatario(String nomeAssegnatario) {
		this.nomeAssegnatario = nomeAssegnatario;
	}

}
