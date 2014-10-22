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
import it.tesoro.monprovv.web.utils.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
		model.addAttribute("tableProvvedimentiSize", countAllProvvedimenti());
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
	
	
	@RequestMapping(value={"/private/ricercaProv/modifica/inserisciAllegato"}, method = RequestMethod.GET)
	public String inserisciAllegato(HttpServletRequest request) {
		
//		request.getFileNames();
		return "";
	}
	
//	 @RequestMapping(value="/upload", method = RequestMethod.POST)
//	    public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
//	 
//	        //1. build an iterator
//	         Iterator<String> itr =  request.getFileNames();
//	         MultipartFile mpf = null;
//	 
//	         //2. get each file
//	         while(itr.hasNext()){
//	 
//	             //2.1 get next MultipartFile
//	             mpf = request.getFile(itr.next()); 
//	             System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());
//	 
//	             //2.2 if files > 10 remove the first from the list
//	             if(files.size() >= 10)
//	                 files.pop();
//	 
//	             //2.3 create new fileMeta
//	             fileMeta = new FileMeta();
//	             fileMeta.setFileName(mpf.getOriginalFilename());
//	             fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
//	             fileMeta.setFileType(mpf.getContentType());
//	 
//	             try {
//	                fileMeta.setBytes(mpf.getBytes());
//	 
//	                 // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)            
//	                 FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("D:/temp/files/"+mpf.getOriginalFilename()));
//	                 
//	 
//	            } catch (IOException e) {
//	                // TODO Auto-generated catch block
//	                e.printStackTrace();
//	            }
//	             //2.4 add to files
//	             files.add(fileMeta);
//	         }
//	        // result will be like this
//	        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
//	        return files;
//	    }

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
