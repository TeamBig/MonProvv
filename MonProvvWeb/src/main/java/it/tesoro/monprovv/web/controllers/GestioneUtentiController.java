package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.CodiceDescrizioneDto;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;
import it.tesoro.monprovv.dto.IdDescrizioneDto;
import it.tesoro.monprovv.dto.UtenteDto;
import it.tesoro.monprovv.facade.GestioneUtenteFacade;
import it.tesoro.monprovv.model.Utente;
import it.tesoro.monprovv.util.StringUtils;
import it.tesoro.monprovv.web.utils.AlertUtils;
import it.tesoro.monprovv.web.validators.UtenteValidator;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GestioneUtentiController {

protected static Logger logger = Logger.getLogger(GestioneUtentiController.class);
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AlertUtils alertUtils;	
	
	@Autowired 
	private GestioneUtenteFacade gestioneUtenteFacade;
	
	@Autowired
	protected UtenteValidator utenteValidator;

	private final String tableUtenteUID = "utente";
	
	@ModelAttribute("utenteToEdit")
	public Utente utenteToEdit(HttpServletRequest request, @RequestParam(required = false) Integer id) {
		if( id != null ){
			return gestioneUtenteFacade.recuperaUtenteById(Integer.valueOf(id));
		}else{
			return new Utente();
		}
	}
	
	@ModelAttribute("ricercaUtente")
	public UtenteDto ricercaUtente(HttpServletRequest request, @ModelAttribute("ricercaUtente") UtenteDto ricercaUtente) {
		if(ricercaUtente!=null)
			return ricercaUtente;
		else
			return new UtenteDto();
	}
	
	@RequestMapping(value= {"/private/admin/utenti"}, method = RequestMethod.GET)
	public String initGet(Model model, 
			@ModelAttribute("ricercaUtente") UtenteDto ricercaUtente,	
			@PagingAndSorting(tableId = tableUtenteUID) DisplayTagPagingAndSorting ps)  {
		initModel(model, ricercaUtente, ps);	
		return "utenteHomeUtente";
	}

	private void initModel(Model model, UtenteDto ricercaUtente, DisplayTagPagingAndSorting ps) {
		List<Utente> listaUtenti = gestioneUtenteFacade.recupera( ((ps != null)? ps.getPage() : 1), ricercaUtente);
		int tableRisultatiSize = gestioneUtenteFacade.count(ricercaUtente);
		model.addAttribute("listaUtenti", listaUtenti);
		model.addAttribute("tableRisultatiSize", tableRisultatiSize);
	}
	
	@RequestMapping(value = "/private/admin/utenti", method = RequestMethod.POST)
	public String initPost(Model model, 
			@RequestParam(required = false) String buttonNew, 
			@RequestParam(required = false) String buttonFind, 
			@RequestParam(required = false) String buttonClean, 
			@ModelAttribute("ricercaUtente") UtenteDto ricercaUtente,				
			HttpServletRequest request)  {
		String retval = "utenteHomeUtente";
		
		if("clean".equals( buttonClean )){
			ricercaUtente = new UtenteDto();
			model.addAttribute("ricercaUtente", ricercaUtente);
		}
		
		if("new".equals( buttonNew )){
			retval = "redirect:/private/admin/utenti/nuovo";
		}else {
			initModel(model, ricercaUtente, null);	
		}
		return retval;
	}

	private void loadCombo4NewUtente(Model model) {
		List<CodiceDescrizioneDto> tipos = new ArrayList<CodiceDescrizioneDto>();
		tipos.add( new CodiceDescrizioneDto("I","Interno") );
		tipos.add( new CodiceDescrizioneDto("E","Esterno") ); 
		model.addAttribute("tipos", tipos );
	}
	
	@RequestMapping(value= {"/private/admin/utenti/nuovo/autocomporganoesterno", "/private/admin/utenti/modifica/{1}/autocomporganoesterno"}, method = RequestMethod.GET)
	@ResponseBody
	public List<IdDescrizioneDto> autocompletamentoUo(@RequestParam("query") String query){
		List<IdDescrizioneDto> result = gestioneUtenteFacade.recuperaOrganiEsterni(query);
		return result;
	}
	
	@RequestMapping(value= {"/private/admin/utenti/nuovo/autocomputenteinterno"}, method = RequestMethod.GET)
	@ResponseBody
	public List<UtenteDto> autocompletamentoUtenteInterno(@RequestParam("query") String query){
		List<UtenteDto> result = gestioneUtenteFacade.recuperaUtentiInterni(query);
		return result;
	}
	
	@RequestMapping(value= {"/private/admin/utenti/delete/{id}"}, method = RequestMethod.GET)
	public String deleteGet(@PathVariable("id") int id, RedirectAttributes redirectAttributes)  {
		String retval = "redirect:/private/admin/utenti";	
		gestioneUtenteFacade.eliminazioneLogica(id);
		alertUtils.message(redirectAttributes, AlertUtils.ALERT_TYPE_SUCCESS, "Cancellazione Utente effettuato con successo", false);	
		return retval;
	}
	
	@RequestMapping(value = "/private/admin/utenti/nuovo", method = RequestMethod.GET)
	public String nuovoGet(@ModelAttribute("utenteToEdit") Utente utenteToEdit,
			Model model,
			HttpServletRequest request)  {
		loadCombo4NewUtente(model);
		model.addAttribute("utenteToEdit", new Utente());
		return "utenteNewUtente";	
	}
	
	@RequestMapping(value = "/private/admin/utenti/nuovo", method = RequestMethod.POST)
	public String nuovoPost(@ModelAttribute("utenteToEdit") Utente utenteToEdit,
			Model model, 
			@RequestParam(required = false) String buttonSave,
			@RequestParam(required = false) String buttonCancel,
			BindingResult errors, 
			HttpServletRequest request)  {
	
		String retval = "redirect:/private/admin/utenti";	
		if("save".equals(buttonSave)){
			utenteValidator.validate(utenteToEdit, errors);
			if( !errors.hasErrors() ){			
				if( "I".equals( utenteToEdit.getFlagIntEst() ) ) {
					//Interno
				}else{
					//Esterno
					utenteToEdit.setUtenteAstage(null);
				}				
				utenteToEdit.setFlagAttivo("S");
				gestioneUtenteFacade.inserisciUtente(utenteToEdit);				
				alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Inserimento Utente effettuato con successo", false);				
				model.addAttribute("utenteToEdit", utenteToEdit );				
				retval = "utenteDettUtente"; 										
			}else{
				for (FieldError f : errors.getFieldErrors()) {
					alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
				}
				loadCombo4NewUtente(model);
				model.addAttribute("utenteToEdit", utenteToEdit);
				retval = "utenteNewUtente";
			}
		}else if("cancel".equals(buttonCancel)){
		}
		return retval;
	}
	
	@RequestMapping(value= {"/private/admin/utenti/dettaglio/{id}"}, method = RequestMethod.GET)
	public String dettaglioGet(Model model, @PathVariable("id") String id)  {
		String retVal = "utenteHomeUtente";
		if( StringUtils.isNotEmpty(id) ){
			Utente utente = gestioneUtenteFacade.recuperaUtenteById(Integer.valueOf(id));
			model.addAttribute("utenteToEdit", utente );
			retVal = "utenteDettUtente";
		}
		return retVal;
	}
	
	@RequestMapping(value= {"/private/admin/utenti/dettaglio"}, method = RequestMethod.POST)
	public String dettaglioPost(@ModelAttribute("utenteToEdit") Utente utente, 
							Model model,
							@RequestParam(required = false) String buttonBack, 
							@RequestParam(required = false) String buttonModify)  {
		
		String retVal = "utenteDettUtente";
		if("back".equals(buttonBack)){
			retVal = "redirect:/private/admin/utenti";
		}else if("modify".equals(buttonModify)){
			retVal = "redirect:/private/admin/utenti/modifica/"+utente.getId();
		}
		
		return retVal;
	}
	
	@RequestMapping(value= {"/private/admin/utenti/modifica/{id}"}, method = RequestMethod.GET)
	public String modificaGet(Model model, @PathVariable("id") String id)  {
		String retVal = "utenteHomeUtente";
		if( StringUtils.isNotEmpty(id) ){
			Utente utente = gestioneUtenteFacade.recuperaUtenteById(Integer.valueOf(id));
			model.addAttribute("utenteToEdit", utente );
			retVal = "utenteEditUtente";
		}
		return retVal;
	}
	
	@RequestMapping(value= {"/private/admin/utenti/modifica"}, method = RequestMethod.POST)
	public String modificaPost(@ModelAttribute("utenteToEdit") Utente utente,
			Model model,
			@RequestParam(required = false) String buttonSave, 
			@RequestParam(required = false) String buttonCancel,
			BindingResult errors, HttpServletRequest request
			)  {
		String retVal = "utenteDettUtente";
		
		if("save".equals( buttonSave )){
			
			utenteValidator.validate(utente, errors);
			
			if( !errors.hasErrors() ){
				
				Utente newUtente = gestioneUtenteFacade.aggiornaUtente(utente);
				
				alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Aggiornamento Utente effettuato con successo", false);
				
				model.addAttribute("utenteToEdit", newUtente );
						
			}else{
				for (FieldError f : errors.getFieldErrors()) {
					alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
				}
				model.addAttribute("utenteToEdit", utente);
				retVal = "utenteEditUtente";
			}
		
		}
		
		if("cancel".equals( buttonCancel )){
			model.addAttribute("utenteToEdit", gestioneUtenteFacade.recuperaUtenteById(utente.getId()));
		}
		
		return retVal;
	}
	
}
