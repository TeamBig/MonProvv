package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.CodiceDescrizioneDto;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;
import it.tesoro.monprovv.facade.GestioneEntiFacade;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.web.utils.AlertUtils;
import it.tesoro.monprovv.web.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GestioneEntiController {
	
	protected static Logger logger = Logger.getLogger(GestioneEntiController.class);
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AlertUtils alertUtils;	
	
	@Autowired 
	private GestioneEntiFacade gestioneEntiFacade;

	private final String tableEntiUID = "tableEntiUID";
	
	@ModelAttribute("organoToEdit")
	public Organo organoToEdit(@RequestParam(required = false) Integer id) {
		Organo retval = null;
		if( id != null ){
			retval = gestioneEntiFacade.recuperaOrganoById(Integer.valueOf(id));
		}
		return retval;
	}
	
	@RequestMapping(value= {"/private/admin/enti"}, method = RequestMethod.GET)
	public String initEntiGet(Model model, @PagingAndSorting(tableId = tableEntiUID) DisplayTagPagingAndSorting ps)  {
		if (ps != null) {
			//String sortColumn = StringUtils.isNotEmpty(ps.getSortColumn()) ? ps.getSortColumn() : "DENOMINAZIONE";
			//String sortOrder = StringUtils.isNotEmpty(ps.getSortOrder()) ? ps.getSortOrder() : "ASC"; 
			List<Organo> organi = gestioneEntiFacade.recuperaOrgano(ps.getPage());
			model.addAttribute("listaOrgani", organi);
		} else {
			List<Organo> organi = gestioneEntiFacade.recuperaOrgano(1);
			model.addAttribute("tableOrganiRisultatiSize", organi.size());
			model.addAttribute("listaOrgani", organi);
		}
		return "entiHomeEnti";
	}
	
	@RequestMapping(value = "/private/admin/enti", method = RequestMethod.POST)
	public String initEntiPost(Model model, @RequestParam(required = false) String buttonNew)  {
		String retval = "entiHomeEnti";
		if("OK".equals( buttonNew )){
			
			List<CodiceDescrizioneDto> cdDto = new ArrayList<CodiceDescrizioneDto>();
			cdDto.add( new CodiceDescrizioneDto("S","Concertante") );
			cdDto.add( new CodiceDescrizioneDto("N","Non Concertante") ); 
			model.addAttribute("cdDto", cdDto );
			
			List<CodiceDescrizioneDto> tipos = new ArrayList<CodiceDescrizioneDto>();
			tipos.add( new CodiceDescrizioneDto("I","Interno") );
			tipos.add( new CodiceDescrizioneDto("E","Esterno") ); 
			model.addAttribute("tipos", tipos );
			
			model.addAttribute("organoToEdit", new Organo());
			retval = "entiNewEnte";
		}else{
			List<Organo> organi = gestioneEntiFacade.recuperaOrgano(1);
			model.addAttribute("tableOrganiRisultatiSize", organi.size());
			model.addAttribute("listaOrgani", organi);
		}
		return retval;
	}
	
	@RequestMapping(value= {"/private/admin/enti/dettaglio/{idOrgano}"}, method = RequestMethod.GET)
	public String dettaglioEnteGet(Model model, @PathVariable("idOrgano") String id)  {
		String retVal = "entiHomeEnti";
		if( StringUtils.isNotEmpty(id) ){
			Organo organo = gestioneEntiFacade.recuperaOrganoById(Integer.valueOf(id));
			model.addAttribute("organoToEdit", organo );
			model.addAttribute("tableUtentiListOrganiRisultatiSize", organo.getUtenteList().size());
			retVal = "entiDettaglioEnte";
		}
		return retVal;
	}
	
	@RequestMapping(value= {"/private/admin/enti/dettaglio"}, method = RequestMethod.POST)
	public String dettaglioEntePost(@ModelAttribute("organoToEdit") Organo organo, 
							Model model,
							@RequestParam(required = false) String buttonBack, 
							@RequestParam(required = false) String buttonModify)  {
		
		String retVal = "entiEditEnte";
		
		if("OK".equals(buttonBack)){
			retVal = "redirect:/private/admin/enti";
		}else if("OK".equals(buttonModify)){
			List<CodiceDescrizioneDto> cdDto = new ArrayList<CodiceDescrizioneDto>();
			cdDto.add( new CodiceDescrizioneDto("S","Concertante") );
			cdDto.add( new CodiceDescrizioneDto("N","Non Concertante") );
			model.addAttribute("cdDto", cdDto );
			model.addAttribute("organoToEdit", organo);
		}
		
		return retVal;
	}
	
	@RequestMapping(value= {"/private/admin/enti/edit"}, method = RequestMethod.POST)
	public String editEntePost(
							@ModelAttribute("organoToEdit") Organo organoToEdit, 
							Model model,
							@RequestParam(required = false) String buttonSave, 
							@RequestParam(required = false) String buttonCancel)  {
		
		String retVal = "entiDettaglioEnte"; 
		
		if("OK".equals( buttonSave )){
			Organo organo = gestioneEntiFacade.aggiornaOrgano(organoToEdit);
			model.addAttribute("organoToEdit", organo );
			model.addAttribute("tableUtentiListOrganiRisultatiSize", organo.getUtenteList().size());
		}
		
		
		if("OK".equals( buttonCancel )){
			model.addAttribute("organoToEdit", organoToEdit );
			model.addAttribute("tableUtentiListOrganiRisultatiSize", organoToEdit.getUtenteList().size());
		}
		
		return retVal;
	}
	
}