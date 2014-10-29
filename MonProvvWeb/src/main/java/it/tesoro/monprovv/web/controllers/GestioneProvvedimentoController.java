package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;
import it.tesoro.monprovv.dto.RicercaProvvedimentoDto;
import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoAtto;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;
import it.tesoro.monprovv.util.StringUtils;
import it.tesoro.monprovv.web.utils.AlertUtils;
import it.tesoro.monprovv.web.utils.ProvvedimentiUtil;
import it.tesoro.monprovv.web.validators.EnteValidator;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GestioneProvvedimentoController {

	protected static Logger logger = Logger.getLogger(GestioneProvvedimentoController.class);

	@Autowired
	private GestioneProvvedimentoFacade gestioneProvvedimentoFacade;
	
	@Autowired
	private AlertUtils alertUtils;
	
	@Autowired
	protected EnteValidator enteValidator;

	@RequestMapping(value = { "/private/ricercaProv" } , method = RequestMethod.GET)
	public String init(Model model,	SecurityContextHolderAwareRequestWrapper request, @PagingAndSorting(tableId = "provvedfimento") DisplayTagPagingAndSorting ps,@ModelAttribute("ricercaProvvedimenti") RicercaProvvedimentoDto provvedimento) {
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
			caricaTabelleInferiore(model, provvedimentoDettaglio);			
			retVal = "provvedimentoDettaglio";
		}
		return retVal;
	}
	
//	@RequestMapping(value = { "/private/ricercaProv/dettaglio/{id:[\\d]+}/{name}" } , method = RequestMethod.GET)
//	public String dettaglioReturn(Model model,@PathVariable("id") int id, @PathVariable("name") String alert,@RequestParam(required = false) String action){
//		Provvedimento provvedimentoDettaglio = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
//		model.addAttribute("provvedimentoDettaglio", provvedimentoDettaglio);
//		caricaTabelleInferiore(model, provvedimentoDettaglio);			
//		if(StringUtils.isNotEmpty(alert)){
//			alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Aggiornamento Provvedimento effettuato con successo", false);
//		}
//		return "provvedimentoDettaglio";
//	}
	
//	@RequestMapping(value = { "/private/ricercaProv/dettaglio/{id:[\\d]+}/{name}" } , method = RequestMethod.POST)
//	public String dettaglioSubmitPost(Model model,@PathVariable("id") int id, @PathVariable("name") String alert,@RequestParam(required = false) String action) {
//		String retVal = "ricercaProv";
//		if(StringUtils.isNotEmpty(id)){
//			if(action.equals("Modifica")){
//				retVal = "redirect:/private/ricercaProv/modifica/"+id;	
//			}
//			if(action.equals("Salva")){
//				
//			}
//		}
//		return retVal;
//	}
	
	@RequestMapping(value = { "/private/ricercaProv/dettaglio/{idProvvedimento}" } , method = RequestMethod.POST)
	public String dettaglioSubmit(Model model,@PathVariable("idProvvedimento") int id, @RequestParam String action) {
		String retVal = "ricercaProv";
		if(StringUtils.isNotEmpty(id)){
			if(action.equals("Modifica")){
				retVal = "redirect:/private/ricercaProv/modifica/"+id;	
			}
			if(action.equals("Salva")){
				
			}
			if(action.equals("Annulla")){
				retVal= "redirect:/private/ricercaProv";
			}
			if(action.equals("noteallegati")){
				retVal= "redirect:/private/ricercaProv/noteAllegatiProv/"+id;
			}
		}
		return retVal;
	}
	
	//***** MODIFICA PROVVEDIMENTO ******//
	@RequestMapping(value = { "/private/ricercaProv/modifica/{idProvvedimento}" } , method = RequestMethod.GET)
	public String modificaProvvedimento(Model model,@PathVariable("idProvvedimento") int id) {
		String retVal = "ricercaProv";
		if(StringUtils.isNotEmpty(id)){
			Provvedimento provvedimentoModifica = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
			model.addAttribute("provvedimentoModifica", provvedimentoModifica);
			caricaTabelleInferiore(model, provvedimentoModifica);
			retVal = "provvedimentoModifica";
		}
		return retVal;
	}

	private void caricaTabelleInferiore(Model model,
			Provvedimento provvedimentoModifica) {
		List<Allegato> listaAllegati = provvedimentoModifica.getAllegatiList();
		model.addAttribute("listaAllegati", listaAllegati);
		List<Assegnazione> listaAssegnazione = provvedimentoModifica.getAssegnazioneList();
		model.addAttribute("listaAssegnazione", listaAssegnazione);
		Assegnazione assegnazioneNew = new Assegnazione();
		assegnazioneNew.setProvvedimento(provvedimentoModifica);
		model.addAttribute("assegnatarioNew", assegnazioneNew);
	}
	
	
	//SALVA MODIFICA PROVVEDIMENTO
	@RequestMapping(value = { "/private/ricercaProv/modifica" } , method = RequestMethod.POST)
	public String salvaModificaProvvedimento(Model model,@ModelAttribute("provvedimentoModifica") Provvedimento provvedimento,
			BindingResult errors, RedirectAttributes redirectAttributes
			) {
		Provvedimento provvAggiornato = gestioneProvvedimentoFacade.aggiornaProvvedimento(provvedimento);
		
		model.addAttribute("provvedimentoDettaglio", provvAggiornato);
		caricaTabelleInferiore(model,provvAggiornato);
		alertUtils.message(redirectAttributes, AlertUtils.ALERT_TYPE_SUCCESS, "Aggiornamento Provvedimento effettuato con successo", false);
		return "redirect:/private/ricercaProv/dettaglio/"+provvedimento.getId();
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
	

	@RequestMapping(value={"/private/ricercaProv/modifica/inserisciAllegato", "/private/ricercaProv/noteAllegatiProv/inserisciAllegato"}, method = RequestMethod.POST)
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
				allegato.setDescrizione(StringUtils.isEmpty(desc)?file.getOriginalFilename():desc);
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
	
	@RequestMapping(value={"/private/ricercaProv/modifica/deleteAllegato/{idAllegato}", "/private/ricercaProv/noteAllegatiProv/deleteAllegato/{idAllegato}"}, method = RequestMethod.GET)
	@ResponseBody
	public String deleteAllegato(@PathVariable("idAllegato") Integer idAllegato) {
		gestioneProvvedimentoFacade.eliminaAllegato(idAllegato);
		return null;
	}
	
	@RequestMapping(value={"/private/ricercaProv/dettaglio/inserisciAssegnatario"}, method = RequestMethod.GET)
	@ResponseBody
	public String inserisciAssegnatario(@RequestParam("provvedimento.id") String idProvvedimento,@RequestParam("organo") String idOrgano ) {
		Integer idProvv = Integer.parseInt(idProvvedimento);
		Integer idOrg = Integer.parseInt(idOrgano);
		
		Assegnazione assegnazione = gestioneProvvedimentoFacade.inserisciAssegnazione(idProvv,idOrg);
		return ProvvedimentiUtil.addRowTableAssegnatariAjax(assegnazione);
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
	
	@ModelAttribute("listaTipoAtto")
	private List<TipoAtto> initTipoAtto() {
		return gestioneProvvedimentoFacade.initTipoAtto();
	}
	
	@ModelAttribute("listaOrgani")
	private List<Organo> initOrgani() {
		return gestioneProvvedimentoFacade.initOrgani();
	}
}
