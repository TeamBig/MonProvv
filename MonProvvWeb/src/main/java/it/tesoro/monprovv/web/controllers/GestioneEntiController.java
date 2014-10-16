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
	public String elencoEnti(Model model, @PagingAndSorting(tableId = tableEntiUID) DisplayTagPagingAndSorting ps)  {
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
	public String  aggiungiEnte(Model model)  {
		
		return "entiHomeEnti";
	}
	
	@RequestMapping(value= {"/private/admin/enti/dettaglio"}, method = RequestMethod.GET)
	public String dettaglioEnte(Model model, String id)  {
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
	public String editEnte(@ModelAttribute("organoToEdit") Organo organo, 
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
	
}