package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;
import it.tesoro.monprovv.dto.RicercaProvvedimentoDto;
import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;
import it.tesoro.monprovv.web.utils.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		List<Provvedimento> listProvvedimenti = new ArrayList<Provvedimento>();
		if(ps!=null){
			listProvvedimenti = gestioneProvvedimentoFacade.ricercaProvvedimenti(provvedimento, ps.getPage());
		} else {
			listProvvedimenti = gestioneProvvedimentoFacade.ricercaProvvedimenti(provvedimento, 1);
		}
		model.addAttribute("tableProvvedimentiSize", listProvvedimenti.size());
		model.addAttribute("listaProvvedimenti", listProvvedimenti);

		return "ricercaProv";
	}
	//***** DETTAGLIO PROVVEDIMENTO ******//
	@RequestMapping(value = { "/private/ricercaProv/dettaglio/{idProvvedimento}" } , method = RequestMethod.GET)
	public String dettaglio(Model model,@PathVariable("idProvvedimento") int id) {
		String retVal = "ricercaProv";
		if(StringUtils.isNotEmpty(id)){
			Provvedimento provvedimentoDettaglio = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
			model.addAttribute("provvedimentoDettaglio", provvedimentoDettaglio);
			List<Allegato> listaAllegati = provvedimentoDettaglio.getAllegatiList();
			model.addAttribute("tableAllegatiSize", listaAllegati.size());
			model.addAttribute("listaAllegati", listaAllegati);
			
			retVal = "provvedimentoDettaglio";
		}
		return retVal;
	}
	
	@RequestMapping(value={"/private/ricercaProv/downloadAllegato/{allegatoId}"}, method = RequestMethod.GET)
	public String downloadAllegato(Model model, @PathVariable("allegatoId") Integer allegatoId,HttpServletResponse response) {
		Allegato doc = gestioneProvvedimentoFacade.getAllegatoById(allegatoId);
		try {
			response.setHeader("Content-Disposition", "inline;filename=\""
					+ doc.getNomefile()+"."+doc.getMimetype() + "\"");
			OutputStream out = response.getOutputStream();
			response.setContentType("application/octet-stream");
			response.setContentLength((int) doc.getContenuto().length());
			IOUtils.copy(doc.getContenuto().getBinaryStream(), out);
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

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
