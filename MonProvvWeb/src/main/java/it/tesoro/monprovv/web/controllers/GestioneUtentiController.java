package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.CodiceDescrizioneDto;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;
import it.tesoro.monprovv.dto.IdDescrizioneDto;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public Utente ricercaUtente(HttpServletRequest request) {
		return new Utente();
	}
	
	@RequestMapping(value= {"/private/admin/utenti"}, method = RequestMethod.GET)
	public String initGet(Model model, 
			@ModelAttribute("ricercaUtente") Utente ricercaUtente,	
			@PagingAndSorting(tableId = tableUtenteUID) DisplayTagPagingAndSorting ps)  {
		
		List<Utente> listaUtenti = new ArrayList<Utente>();
		int tableRisultatiSize = 0;
		
		if(StringUtils.isEmpty( ricercaUtente.getCodiceFiscale() ) ){
			//Find All
			listaUtenti =gestioneUtenteFacade.recupera( (ps != null)? ps.getPage() : 1);	
			tableRisultatiSize = gestioneUtenteFacade.count();
			
		}else{
			//Ho impostato un filtro
			listaUtenti =gestioneUtenteFacade.recupera( ((ps != null)? ps.getPage() : 1), ricercaUtente);	
			tableRisultatiSize = gestioneUtenteFacade.count(ricercaUtente);
		
		}
		
		model.addAttribute("listaUtenti", listaUtenti);
		model.addAttribute("tableRisultatiSize", tableRisultatiSize);	
		
		
		return "utenteHomeUtente";
	}
	
	@RequestMapping(value = "/private/admin/utenti", method = RequestMethod.POST)
	public String initPost(Model model, 
			@RequestParam(required = false) String buttonNew, 
			@RequestParam(required = false) String buttonFind, 
			@RequestParam(required = false) String buttonClean, 
			@ModelAttribute("ricercaUtente") Utente ricercaUtente,				
			HttpServletRequest request)  {
		String retval = "utenteHomeUtente";
		
		if("clean".equals( buttonClean )){
			ricercaUtente = new Utente();
			model.addAttribute("ricercaUtente", ricercaUtente);
		}
		
		if("new".equals( buttonNew )){
			
			retval = "redirect:/private/admin/utenti/nuovo";
			
		}else if("find".equals(buttonFind)){
			List<Utente> listaUtenti = gestioneUtenteFacade.recupera(1, ricercaUtente);
			model.addAttribute("listaUtenti", listaUtenti);
			model.addAttribute("tableRisultatiSize", gestioneUtenteFacade.count(ricercaUtente));
			
		}else{
			model.addAttribute("listaEnti", gestioneUtenteFacade.recupera(1));
			model.addAttribute("tableRisultatiSize", gestioneUtenteFacade.count());
			
		}
		return retval;
	}

	private void loadCombo4NewUtente(Model model) {
		List<CodiceDescrizioneDto> tipos = new ArrayList<CodiceDescrizioneDto>();
		tipos.add( new CodiceDescrizioneDto("I","Interno") );
		tipos.add( new CodiceDescrizioneDto("E","Esterno") ); 
		model.addAttribute("tipos", tipos );
	}
	
	@RequestMapping(value= {"/private/admin/utenti/nuovo/autocomporganoesterno"}, method = RequestMethod.GET)
	@ResponseBody
	public List<IdDescrizioneDto> autocompletamentoUo(@RequestParam("query") String query)  {
		List<IdDescrizioneDto> result = gestioneUtenteFacade.recuperaOrganiEsterni(query);
		return result;
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

				}
				
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
	
	
}
