package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.model.TipoProvvedimento;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GestioneProvvedimentoController {

	protected static Logger logger = Logger.getLogger(GestioneProvvedimentoController.class);
	
	@Autowired
	private GestioneProvvedimentoFacade gestioneProvvedimentoFacade;
	

	@RequestMapping(value= {"/private/ricercaProv"}, method = RequestMethod.GET)
	public String init(Model model, SecurityContextHolderAwareRequestWrapper request)
	{
	   initDdl();
	   
	   return "ricercaProv";
	   
	}
	
	public void initDdl()
	{
	   List<TipoProvvedimento> listTipoProvv = gestioneProvvedimentoFacade.initDdl();
	   //return "ricercaprov";
	   
	}
}
