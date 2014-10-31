package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.facade.GestioneNotificaFacade;
import it.tesoro.monprovv.web.utils.AlertUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = "/private/notifiche/elenco", method = RequestMethod.GET)
	public String elencoNotifiche(Model model) {
		
		model.addAttribute("notifiche", gestioneNotificaFacade.recuperaNotifichePersonali());
		
		return "notifiche";
	}
}
