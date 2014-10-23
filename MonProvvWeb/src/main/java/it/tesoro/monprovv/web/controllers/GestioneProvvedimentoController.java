package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;
import it.tesoro.monprovv.dto.RicercaProvvedimentoDto;
import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;
import it.tesoro.monprovv.util.StringUtils;
import it.tesoro.monprovv.web.utils.ProvvedimentiUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class GestioneProvvedimentoController {

	protected static Logger logger = Logger.getLogger(GestioneProvvedimentoController.class);

	@Autowired
	private GestioneProvvedimentoFacade gestioneProvvedimentoFacade;

	@RequestMapping(value = { "/private/ricercaProv" } , method = RequestMethod.GET)
	public String init(Model model,	SecurityContextHolderAwareRequestWrapper request, @PagingAndSorting(tableId = "provvedimento") DisplayTagPagingAndSorting ps,@ModelAttribute("ricercaProvvedimenti") RicercaProvvedimentoDto provvedimento) {
		RicercaProvvedimentoDto dto = new RicercaProvvedimentoDto();
		model.addAttribute("ricercaProvvedimenti", dto);
		List<Provvedimento> listProvvedimenti = new ArrayList<Provvedimento>();
		if(ps!=null){
			if(StringUtils.isEmpty( provvedimento ) )
				listProvvedimenti = gestioneProvvedimentoFacade.initAllProvvedimenti(ps.getPage());
			else
				listProvvedimenti = gestioneProvvedimentoFacade.ricercaProvvedimenti(provvedimento, ps.getPage());
		} else {
			listProvvedimenti = gestioneProvvedimentoFacade.ricercaProvvedimenti(provvedimento, 1);
		}
		if(StringUtils.isEmpty( provvedimento ) )
			model.addAttribute("tableProvvedimentiSize", countAllProvvedimenti());
		else
			model.addAttribute("tableProvvedimentiSize", gestioneProvvedimentoFacade.countRicercaProvvedimenti(provvedimento));
		model.addAttribute("listaProvvedimenti", listProvvedimenti);
//		if(ps!=null){
//			listProvvedimenti = initAllProvvedimenti(ps.getPage());
//		} else {
//			listProvvedimenti = initAllProvvedimenti(1);
//		}
//		model.addAttribute("tableProvvedimentiSize", countAllProvvedimenti());
//		model.addAttribute("listaProvvedimenti", listProvvedimenti);
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
			if(StringUtils.isEmpty( provvedimento ) )
				listProvvedimenti = gestioneProvvedimentoFacade.initAllProvvedimenti(ps.getPage());
			else
				listProvvedimenti = gestioneProvvedimentoFacade.ricercaProvvedimenti(provvedimento, ps.getPage());
		} else {
			listProvvedimenti = gestioneProvvedimentoFacade.ricercaProvvedimenti(provvedimento, 1);
		}
		if(StringUtils.isEmpty( provvedimento ) )
			model.addAttribute("tableProvvedimentiSize", countAllProvvedimenti());
		else
			model.addAttribute("tableProvvedimentiSize", gestioneProvvedimentoFacade.countRicercaProvvedimenti(provvedimento));
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
			List<Assegnazione> listaAssegnazione = provvedimentoDettaglio.getAssegnazioneList();
//			model.addAttribute("listaAssegnazioneSize", listaAssegnazione.size());
			model.addAttribute("listaAssegnazione", listaAssegnazione);
			
			retVal = "provvedimentoDettaglio";
		}
		return retVal;
	}
	
	@RequestMapping(value = { "/private/ricercaProv/dettaglio/{idProvvedimento}" } , method = RequestMethod.POST)
	public String dettaglioSubmit(Model model,@PathVariable("idProvvedimento") int id, @RequestParam String action) {
		String retVal = "ricercaProv";
		if(StringUtils.isNotEmpty(id)){
			if(action.equals("Modifica")){
				retVal = "redirect:/private/ricercaProv/modifica/"+id;	
			}
			if(action.equals("Salva")){
				
			}
		}
		return retVal;
	}
	
	//***** MODIFICA PROVVEDIMENTO ******//
	@RequestMapping(value = { "/private/ricercaProv/modifica/{idProvvedimento}" } , method = RequestMethod.GET)
	public String modificaProvvedimento(Model model,@PathVariable("idProvvedimento") int id) {
		String retVal = "ricercaProv";
		if(StringUtils.isNotEmpty(id)){
			Provvedimento provvedimentoDettaglio = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
			model.addAttribute("provvedimentoDettaglio", provvedimentoDettaglio);
			List<Allegato> listaAllegati = provvedimentoDettaglio.getAllegatiList();
			model.addAttribute("tableAllegatiSize", listaAllegati.size());
			model.addAttribute("listaAllegati", listaAllegati);
			List<Assegnazione> listaAssegnazione = provvedimentoDettaglio.getAssegnazioneList();
//			model.addAttribute("listaAssegnazioneSize", listaAssegnazione.size());
			model.addAttribute("listaAssegnazione", listaAssegnazione);
			retVal = "provvedimentoModifica";
		}
		return retVal;
	}
	
	@RequestMapping(value={"/private/ricercaProv/downloadAllegato/{allegatoId}"}, method = RequestMethod.GET)
	public String downloadAllegato(Model model, @PathVariable("allegatoId") Integer allegatoId,HttpServletResponse response) {
		Allegato doc = gestioneProvvedimentoFacade.getAllegatoById(allegatoId);
		try {
			response.setHeader("Content-Disposition", "inline;filename=\""
					+ doc.getNomefile() + "\"");
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
	

	@RequestMapping(value={"/private/ricercaProv/modifica/inserisciAllegato"}, method = RequestMethod.POST)
	@ResponseBody
	public String inserisciAllegato(MultipartHttpServletRequest request) {
		try {
			Iterator<String> itr = request.getFileNames();
			MultipartFile file = request.getFile(itr.next());
			String desc = ((String[]) request.getParameterMap().get("descrizioneAllegato"))[0];
			String idProvvedimento = ((String[]) request.getParameterMap().get("idProvvedimento"))[0];
			Provvedimento provv = gestioneProvvedimentoFacade.ricercaProvvedimentoById(Integer.parseInt(idProvvedimento));
			Allegato allegato = new Allegato();
			if (file.getBytes().length > 0) {
				allegato.setNomefile(file.getOriginalFilename());
				allegato.setDescrizione(desc);
				allegato.setContenuto(new SerialBlob(file.getBytes()));
				allegato.setProvvedimento(provv);
				allegato.setDimensione((int)file.getSize());
			}
			Allegato retAllegato = gestioneProvvedimentoFacade.inserisciAllegato(allegato);
			return ProvvedimentiUtil.addRowTableAllegatiAjax(retAllegato);
		} catch (Exception e) {
			logger.error("Errore call Ajax inserimento del file allegato");
		}
		return null;
	}
	
	private List<Provvedimento> initAllProvvedimenti(Integer page) {
		return gestioneProvvedimentoFacade.initAllProvvedimenti(page);
	}
	private Integer countAllProvvedimenti() {
		return gestioneProvvedimentoFacade.countAllProvvedimenti();
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
