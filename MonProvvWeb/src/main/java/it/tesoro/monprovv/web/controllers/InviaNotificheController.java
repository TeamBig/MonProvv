package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.dto.IndirizzoEmailDto;
import it.tesoro.monprovv.dto.SalvaENotificaDto;
import it.tesoro.monprovv.facade.GestioneUtenteFacade;
import it.tesoro.monprovv.model.Utente;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InviaNotificheController {

	protected static Logger logger = Logger.getLogger(InviaNotificheController.class);
	
	@Autowired 
	private GestioneUtenteFacade gestioneUtenteFacade;
	
	
	@RequestMapping(value={"/private/provvedimenti/ricerca/dettaglio/salvaeinvianotifica"}, method = RequestMethod.GET)
	public String getCronologiaAssegnatario(Model model,@RequestParam(required = false) String id) {		
		SalvaENotificaDto salvaenotifica = new SalvaENotificaDto();
		salvaenotifica.setIdProvvedimento(id);
		model.addAttribute("salvaenotifica", salvaenotifica);
		
		return "inviaNotifica";
	}
	
	@RequestMapping(value={"/private/provvedimenti/ricerca/salvaenotifica"}, method = RequestMethod.POST)
	public String salvaenotifica(Model model) {		
		
		model.addAttribute("salvaenotifica", model);
		
		return "inviaNotifica";
	}
	
	
	
	@RequestMapping(value= {"/private/provvedimenti/ricerca/autocomplateutentemail"}, method = RequestMethod.GET)
	@ResponseBody
	public List<IndirizzoEmailDto> autocomplateutentemail(@RequestParam("query") String query){
		List<Utente> dtos = gestioneUtenteFacade.recuperaUtenteiAttivi(query);
		List<IndirizzoEmailDto> retval = new ArrayList<IndirizzoEmailDto>();
		retval.add(new IndirizzoEmailDto(query, "", query));
		for(Utente tmp: dtos){
			retval.add(new IndirizzoEmailDto(tmp.getNome(), tmp.getCognome(), tmp.getEmail()));
		}
		return retval;
	}
	
}
