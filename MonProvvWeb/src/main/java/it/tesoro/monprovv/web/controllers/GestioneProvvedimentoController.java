package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.dto.RicercaProvvedimentoDto;
import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GestioneProvvedimentoController {

	protected static Logger logger = Logger.getLogger(GestioneProvvedimentoController.class);

	@Autowired
	private GestioneProvvedimentoFacade gestioneProvvedimentoFacade;

	@RequestMapping(value = { "/private/ricercaProv" }, method = RequestMethod.GET)
	public String init(Model model,	SecurityContextHolderAwareRequestWrapper request) {
		initStatiDiAttuazione();
		RicercaProvvedimentoDto ricerca = new RicercaProvvedimentoDto();
		model.addAttribute("ricercaProvvedimenti", ricerca);

		return "ricercaProv";
	}

	@ModelAttribute("listaStatoDiAttuazione")
	private List<Stato> initStatiDiAttuazione() {
		return gestioneProvvedimentoFacade.initStato();
	}
	
	@ModelAttribute("listaGoverno")
	private List<Governo> initGoverno() {
		return gestioneProvvedimentoFacade.initGoverno();
	}

	@ModelAttribute("listaTipologia")
	private List<TipoProvvedimento> initTipologia() {
		return gestioneProvvedimentoFacade.initTipologia();
	}
	
	@ModelAttribute("listaTipoProvvDaAdottare")
	private List<TipoProvvDaAdottare> initTipoProvvDaAdottare() {
		return gestioneProvvedimentoFacade.initTipoProvvDaAdottare();
	}
}
