package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;
import it.tesoro.monprovv.dto.RicercaProvvedimentoDto;
import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GestioneProvvedimentoController {

	protected static Logger logger = Logger.getLogger(GestioneProvvedimentoController.class);

	@Autowired
	private GestioneProvvedimentoFacade gestioneProvvedimentoFacade;

	@RequestMapping(value = { "/private/ricercaProv" } , method = RequestMethod.GET)
	public String init(Model model,	SecurityContextHolderAwareRequestWrapper request, @PagingAndSorting(tableId = "provvedimento") DisplayTagPagingAndSorting ps) {
		RicercaProvvedimentoDto dto = new RicercaProvvedimentoDto();
		model.addAttribute("ricercaProvvedimenti", dto);
		List<Provvedimento> listProvvedimenti = new ArrayList<Provvedimento>();
		if(ps!=null){
			listProvvedimenti = initAllProvvedimenti(ps.getPage());
		} else {
			listProvvedimenti = initAllProvvedimenti(1);
		}
		model.addAttribute("tableProvvedimentiSize", listProvvedimenti.size());
		model.addAttribute("listaProvvedimenti", listProvvedimenti);
		return "ricercaProv";
	}
	
//	@ModelAttribute("ricercaProvvedimenti")
//	public RicercaProvvedimentoDto ricercaProvvedimentiDto(){
//		return new RicercaProvvedimentoDto();
//	}
	
	@RequestMapping(value = { "/private/ricercaProv" } , method = RequestMethod.POST)
	public String processRegistration(Model model, 
			@ModelAttribute("ricercaProvvedimenti") RicercaProvvedimentoDto provvedimento,
			@PagingAndSorting(tableId = "provvedimento") DisplayTagPagingAndSorting ps
			) {

		System.out.println("RICERCA");
		model.addAttribute("tipoGoverno", provvedimento.getTipoGoverno());

		return "ricercaProv";
	}
	//***** DETTAGLIO PROVVEDIMENTO ******//
//	@RequestMapping(value = { "/private/provvedimentoDettaglio" } , method = RequestMethod.GET)
//	public String init(Model model,	SecurityContextHolderAwareRequestWrapper request, @PagingAndSorting(tableId = "provvedimento") DisplayTagPagingAndSorting ps) {
//		RicercaProvvedimentoDto ricerca = new RicercaProvvedimentoDto();
//		model.addAttribute("ricercaProvvedimenti", ricerca);
//		List<Provvedimento> listProvvedimenti = new ArrayList<Provvedimento>();
//		if(ps!=null){
//			listProvvedimenti = initAllProvvedimenti(ps.getPage());
//		} else {
//			listProvvedimenti = initAllProvvedimenti(1);
//		}
//		model.addAttribute("tableProvvedimentiSize", listProvvedimenti.size());
//		model.addAttribute("listaProvvedimenti", listProvvedimenti);
//		return "ricercaProv";
//	}
	

	private List<Provvedimento> initAllProvvedimenti(Integer page) {
		return gestioneProvvedimentoFacade.initAllProvvedimenti(page);
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
