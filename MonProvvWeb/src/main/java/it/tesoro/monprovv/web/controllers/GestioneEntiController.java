package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.CodiceDescrizioneDto;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;
import it.tesoro.monprovv.facade.GestioneEntiFacade;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.UnitaOrgAstage;
import it.tesoro.monprovv.util.StringUtils;
import it.tesoro.monprovv.web.utils.AlertUtils;
import it.tesoro.monprovv.web.validators.EnteValidator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@SessionAttributes("ricercaEnte")
public class GestioneEntiController {
	
	protected static Logger logger = Logger.getLogger(GestioneEntiController.class);
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AlertUtils alertUtils;	
	
	@Autowired 
	private GestioneEntiFacade gestioneEntiFacade;
	
	@Autowired
	protected EnteValidator enteValidator;
	
//	@Autowired
//	private CacheService cacheService;

	private final String tableEntiUID = "organo";
	
	@ModelAttribute("organoToEdit")
	public Organo organoToEdit(HttpServletRequest request, @RequestParam(required = false) Integer id) {
		if( id != null ){
			return gestioneEntiFacade.recuperaOrganoById(Integer.valueOf(id));
		}else{
			return new Organo();
		}
	}
	
	@ModelAttribute("ricercaEnte")
	public Organo ricercaEnte(HttpServletRequest request) {
		Organo retval = new Organo();
		retval.setFlagAttivo("S");
		return retval;
	}
	
	@RequestMapping(value= {"/private/admin/enti"}, method = RequestMethod.GET)
	public String initGet(Model model, 
			@ModelAttribute("ricercaEnte") Organo ricercaEnte,	
			@PagingAndSorting(tableId = tableEntiUID) DisplayTagPagingAndSorting ps)  {
		initModel(model, ricercaEnte, ps);
		return "entiHomeEnti";
	}

	private void initModel(Model model, Organo ricercaEnte, DisplayTagPagingAndSorting ps) {
		List<Organo> listaOrgani = gestioneEntiFacade.recuperaOrgano((ps != null)?ps.getPage():1, ricercaEnte);
		int tableRisultatiSize = gestioneEntiFacade.countOrgano(ricercaEnte);
		model.addAttribute("listaOrgani", listaOrgani);
		model.addAttribute("tableOrganiRisultatiSize", tableRisultatiSize);
	}
	
	@RequestMapping(value = "/private/admin/enti", method = RequestMethod.POST)
	public String initEntiPost(Model model, 
			@RequestParam(required = false) String buttonNew, 
			@RequestParam(required = false) String buttonFind, 
			@RequestParam(required = false) String buttonClean, 
			@ModelAttribute("ricercaEnte") Organo ricercaEnte,				
			HttpServletRequest request)  {
		String retval = "entiHomeEnti";
		
		if("clean".equals( buttonClean )){
			ricercaEnte = new Organo();
			ricercaEnte.setFlagAttivo("S");
			model.addAttribute("ricercaEnte",ricercaEnte);
		}
		
		if("new".equals( buttonNew )){
			retval = "redirect:/private/admin/enti/nuovo";
		}else{
			initModel(model, ricercaEnte, null);
		}
		return retval;
	}
	
//	@RequestMapping(value= {"/private/admin/enti/delete/{id}"}, method = RequestMethod.GET)
//	public String deleteGet(@PathVariable("id") int id, RedirectAttributes redirectAttributes)  {
	@RequestMapping(value= {"/private/admin/enti/delete"}, method = RequestMethod.GET)
	public String deleteGet(RedirectAttributes redirectAttributes, @RequestParam(required = false) String id)  {
		String retval = "redirect:/private/admin/enti";	
		gestioneEntiFacade.eliminazioneLogica(Integer.valueOf(id));
		alertUtils.message(redirectAttributes, AlertUtils.ALERT_TYPE_SUCCESS, "Cancellazione Ente effettuato con successo", false);	
		return retval;
	}
	
	@RequestMapping(value = "/private/admin/enti/nuovo", method = RequestMethod.GET)
	public String nuovoGet(@ModelAttribute("organoToEdit") Organo organoToEdit,
			Model model, 
			HttpServletRequest request)  {
		loadCombo4NewEnte(model, request);
		model.addAttribute("organoToEdit", new Organo());
		return "entiNewEnte";
	}
	
	@RequestMapping(value = "/private/admin/enti/nuovo", method = RequestMethod.POST)
	public String nuovoPost(@ModelAttribute("organoToEdit") Organo organoToEdit,
			Model model, 
			@RequestParam(required = false) String buttonSave,
			@RequestParam(required = false) String buttonCancel,
			BindingResult errors, 
			HttpServletRequest request)  {
		String retval = "redirect:/private/admin/enti";
		if("save".equals(buttonSave)){
			enteValidator.validate(organoToEdit, errors);
			if( !errors.hasErrors() ){
				if( "I".equals( organoToEdit.getFlgInternoEsterno() ) ) {
					//Interno
					organoToEdit.setDenominazione( organoToEdit.getUnitaOrgAstage().getNome() );
					organoToEdit.setDenominazioneEstesa( organoToEdit.getUnitaOrgAstage().getNomeEsteso() );
				}else{
					//Esterno
					organoToEdit.setUnitaOrgAstage(null);
				}
				organoToEdit.setFlagAttivo("S");
				gestioneEntiFacade.inserisciOrgano(organoToEdit);
				alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Inserimento Ente effettuato con successo", false);
				model.addAttribute("organoToEdit", organoToEdit );
				model.addAttribute("tableUtentiListOrganiRisultatiSize", 0);
				retval = "entiDettaglioEnte"; 							
			}else{
				for (FieldError f : errors.getFieldErrors()) {
					alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
				}
				loadCombo4NewEnte(model, request);
				model.addAttribute("organoToEdit", organoToEdit);
				retval = "entiNewEnte";
			}
		}else if("cancel".equals(buttonCancel)){
		}
		return retval;
	}

	private void loadCombo4NewEnte(Model model, HttpServletRequest request) {
		loadComboConcertante(model);
		List<CodiceDescrizioneDto> tipos = new ArrayList<CodiceDescrizioneDto>();
		tipos.add( new CodiceDescrizioneDto("I","Interno") );
		tipos.add( new CodiceDescrizioneDto("E","Esterno") ); 
		model.addAttribute("tipos", tipos );
	}
	
	@RequestMapping(value= {"/private/admin/enti/nuovo/autocompletamento"}, method = RequestMethod.GET)
	@ResponseBody
	public List<UnitaOrgAstage> autocompletamentoUo(@RequestParam("query") String query)  {
		List<UnitaOrgAstage> result = gestioneEntiFacade.recuperaUnitaOrgAstageNonUsati(query);
		return result;
	}
	
	
//	@RequestMapping(value= {"/private/admin/enti/dettaglio/{id}"}, method = RequestMethod.GET)
//	public String dettaglioGet(Model model, @PathVariable("id") String id)  {
	@RequestMapping(value= {"/private/admin/enti/dettaglio"}, method = RequestMethod.GET)
	public String dettaglioGet(Model model, @RequestParam(required = false) String id)  {
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
	public String dettaglioPost(@ModelAttribute("organoToEdit") Organo organo, 
							Model model,
							@RequestParam(required = false) String buttonBack, 
							@RequestParam(required = false) String buttonModify)  {
		
		String retVal = "entiDettaglioEnte";
		if("back".equals(buttonBack)){
			retVal = "redirect:/private/admin/enti";
		}else if("modify".equals(buttonModify)){
			retVal = "redirect:/private/admin/enti/modifica?id="+organo.getId();
		}
		
		return retVal;
	}
	
//	@RequestMapping(value= {"/private/admin/enti/modifica/{id}"}, method = RequestMethod.GET)
//	public String modificaGet(Model model, @PathVariable("id") String id)  {
	@RequestMapping(value= {"/private/admin/enti/modifica"}, method = RequestMethod.GET)
	public String modificaGet(Model model, @RequestParam(required = false) String id)  {
		String retVal = "entiHomeEnti";
		if( StringUtils.isNotEmpty(id) ){
			Organo organo = gestioneEntiFacade.recuperaOrganoById(Integer.valueOf(id));
			loadComboConcertante(model);
			organo.setFlgInternoEsterno((organo.getUnitaOrgAstage()==null)?"E":"I");
			model.addAttribute("organoToEdit", organo);
			retVal = "entiEditEnte";
		}
		return retVal;
	}
	
	@RequestMapping(value= {"/private/admin/enti/modifica"}, method = RequestMethod.POST)
	public String modificaPost(
							@ModelAttribute("organoToEdit") Organo organoToEdit, 
							Model model,
							@RequestParam(required = false) String buttonSave, 
							@RequestParam(required = false) String buttonCancel,
							BindingResult errors, HttpServletRequest request)  {
		
		String retVal = "entiDettaglioEnte"; 
		
		if("save".equals( buttonSave )){
			
			enteValidator.validate(organoToEdit, errors);
			
			if( !errors.hasErrors() ){
				
				Organo organo = gestioneEntiFacade.aggiornaOrgano(organoToEdit);
				
				alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Aggiornamento Ente effettuato con successo", false);
				
				model.addAttribute("organoToEdit", organo );
				model.addAttribute("tableUtentiListOrganiRisultatiSize", organo.getUtenteList().size());
						
			}else{
				for (FieldError f : errors.getFieldErrors()) {
					alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
				}
				loadComboConcertante(model);
				model.addAttribute("organoToEdit", organoToEdit);
				retVal = "entiEditEnte";
			}
		
		}
		
		
		if("cancel".equals( buttonCancel )){
			Integer id = organoToEdit.getId();
			organoToEdit = new Organo();
			if( id != null ){
				organoToEdit = gestioneEntiFacade.recuperaOrganoById(Integer.valueOf(id));
			}
			model.addAttribute("organoToEdit", organoToEdit );
			model.addAttribute("tableUtentiListOrganiRisultatiSize", organoToEdit.getUtenteList().size());
		}
		
		return retVal;
	}

	private void loadComboConcertante(Model model) {
		List<CodiceDescrizioneDto> cdDto = new ArrayList<CodiceDescrizioneDto>();
		cdDto.add( new CodiceDescrizioneDto("S","Concertante") );
		cdDto.add( new CodiceDescrizioneDto("N","Non Concertante") );
		model.addAttribute("cdDto", cdDto );
	}
	
}