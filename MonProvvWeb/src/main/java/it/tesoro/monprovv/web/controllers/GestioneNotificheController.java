package it.tesoro.monprovv.web.controllers;

import java.net.URISyntaxException;

import it.tesoro.monprovv.facade.GestioneNotificaFacade;
import it.tesoro.monprovv.model.Notifica;
import it.tesoro.monprovv.web.utils.AlertUtils;

import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GestioneNotificheController {

	protected static Logger logger = Logger.getLogger(GestioneNotificheController.class);
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AlertUtils alertUtils;	
	
	@Autowired
	private GestioneNotificaFacade gestioneNotificaFacade;


	@RequestMapping(value = "/private/notifiche/conteggio", method = RequestMethod.GET)
	@ResponseBody
	public long conteggioNotifiche() {
		return gestioneNotificaFacade.conteggioNotifichePersonaliNonLette();
	}
	
	@RequestMapping(value = "/private/notifiche/elencononlette", method = RequestMethod.GET)
	public String elencoNotificheNonLette(Model model) {
		
		model.addAttribute("notifiche", gestioneNotificaFacade.recuperaNotifichePersonaliNonLette());
		model.addAttribute("soloNonLette", "S");
		return "notifiche";
	}
	
	@RequestMapping(value = "/private/notifiche", method = RequestMethod.GET)
	public String redirectNotifiche(Model model) {
		
		return "redirect:/private/notifiche/elenco";
	}
	
	@RequestMapping(value = "/private/notifiche/elenco", method = RequestMethod.GET)
	public String elencoNotifiche(Model model) {
		
		model.addAttribute("notifiche", gestioneNotificaFacade.recuperaNotifichePersonali());
		return "notifiche";
	}
	
	
	@RequestMapping(value = "/private/notifiche/dettaglio", method = RequestMethod.GET)
	public String dettaglioNotifica(@RequestParam(value = "id", required = true) Integer idNotifica, Model model) {
		
		Notifica notifica = gestioneNotificaFacade.recuperaNotifica(idNotifica);
		
		if (notifica.getTipoNotifica().equals(Notifica.INFORMATIVA)) {
			// contrassegno come già letta
			notifica.setFlagLettura(Notifica.LETTA);
			
			gestioneNotificaFacade.aggiornaNotifica(notifica);
		}
		
		model.addAttribute("notifica", notifica);
		
		return "dettaglioNotifica";
	}
	
	@RequestMapping(value = "/private/notifiche/gestione", method = RequestMethod.GET)
	public String gestioneNotificaOperativa(@RequestParam(value = "id", required = true) Integer idNotifica, 
			Model model) {
		
		Notifica notifica = gestioneNotificaFacade.recuperaNotifica(idNotifica);
		
		
		// concateno id notifica a link operazione e faccio redirect
		URIBuilder uriBuilder = null;
		try {
			uriBuilder = new URIBuilder(notifica.getLinkOperazione());
			uriBuilder.addParameter("idNotifica", idNotifica.toString());
			return "redirect:" + uriBuilder.build().toString();
			
		} catch (URISyntaxException e) {
			alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, "Errore nella generazione della URI", false);
			return "dettaglioNotifica";
		}
	}
	
	@RequestMapping(value = "/private/notifiche/daleggere", method = RequestMethod.GET)
	public String segnaDaLeggere(@RequestParam(value = "id", required = true) Integer idNotifica, 
			Model model) {
		
		Notifica notifica = gestioneNotificaFacade.recuperaNotifica(idNotifica);
		if (notifica.getTipoNotifica().equals(Notifica.INFORMATIVA)) {
			notifica.setFlagLettura(Notifica.NON_LETTA);
			gestioneNotificaFacade.aggiornaNotifica(notifica);
		}

		return "redirect:/private/notifiche/elenco";
	}
}
