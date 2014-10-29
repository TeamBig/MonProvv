package it.tesoro.monprovv.web.controllers;

import java.util.List;

import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.util.StringUtils;
import it.tesoro.monprovv.web.utils.AlertUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NoteAllegatiProvvedimentoController {
	
	protected static Logger logger = Logger.getLogger(NoteAllegatiProvvedimentoController.class);
	
	@Autowired
	private GestioneProvvedimentoFacade gestioneProvvedimentoFacade;
	
	@Autowired
	private AlertUtils alertUtils;
	
	//***** MODIFICA PROVVEDIMENTO ******//
	@RequestMapping(value = { "/private/ricercaProv/noteAllegatiProv/{idProvvedimento}" } , method = RequestMethod.GET)
	public String modificaProvvedimento(Model model,@PathVariable("idProvvedimento") int id) {
		String retVal = "redirect:/private/ricercaProv/dettaglio/"+id;
//		if(StringUtils.isNotEmpty(id)){
//			Provvedimento provvedimento = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
//			model.addAttribute("provvedimento", provvedimento);
//			caricaTabelleInferiore(model, provvedimento);
//			retVal = "noteAllegatiProv";
//		}
		return retVal;
	}
	
	private void caricaTabelleInferiore(Model model, Provvedimento provvedimentoModifica) {
		List<Allegato> listaAllegati = provvedimentoModifica.getAllegatiList();
		model.addAttribute("listaAllegati", listaAllegati);
		List<Assegnazione> listaAssegnazione = provvedimentoModifica.getAssegnazioneList();
		model.addAttribute("listaAssegnazione", listaAssegnazione);
		Assegnazione assegnazioneNew = new Assegnazione();
		assegnazioneNew.setProvvedimento(provvedimentoModifica);
		model.addAttribute("assegnatarioNew", assegnazioneNew);
	}
	
}
