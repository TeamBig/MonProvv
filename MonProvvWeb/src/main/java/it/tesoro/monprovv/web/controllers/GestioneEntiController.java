package it.tesoro.monprovv.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class GestioneEntiController {

	protected static Logger logger = Logger.getLogger(GestioneEntiController.class);
	
	@RequestMapping(value= {"/private/admin/entiHome"}, method = RequestMethod.GET)
	public String caricaHomepagePrivata(Model model, SecurityContextHolderAwareRequestWrapper request)  {
		return "entiHomeEnti";
	}
	
}