package it.tesoro.monprovv.dto;

import it.tesoro.monprovv.model.Stato;

public class RicercaProvvedimentoDto {

	private Stato cercaPerStato;

	public Stato getCercaPerStato() {
		return cercaPerStato;
	}

	public void setCercaPerStato(Stato cercaPerStato) {
		this.cercaPerStato = cercaPerStato;
	} 
}
