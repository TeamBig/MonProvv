package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.CodiceDescrizioneDto;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;
import it.tesoro.monprovv.dto.IdDescrizioneDto;
import it.tesoro.monprovv.dto.UtenteDto;
import it.tesoro.monprovv.facade.GestioneEntiFacade;
import it.tesoro.monprovv.facade.GestioneNotificaFacade;
import it.tesoro.monprovv.facade.GestioneUtenteFacade;
import it.tesoro.monprovv.model.Ruolo;
import it.tesoro.monprovv.model.Utente;
import it.tesoro.monprovv.model.UtenteAstage;
import it.tesoro.monprovv.utils.StringUtils;
import it.tesoro.monprovv.web.utils.AlertUtils;
import it.tesoro.monprovv.web.validators.UtenteValidator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("ricercaUtente")
public class GestioneUtentiController {

protected static Logger logger = Logger.getLogger(GestioneUtentiController.class);
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AlertUtils alertUtils;	
	
	@Autowired 
	private GestioneUtenteFacade gestioneUtenteFacade;
	
	@Autowired 
	private GestioneEntiFacade gestioneEntiFacade;
	
	@Autowired
	protected UtenteValidator utenteValidator;
	
	@Autowired
	private GestioneNotificaFacade gestioneNotificaFacade;

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
		if(ricercaUtente==null){
			ricercaUtente = new UtenteDto();
			ricercaUtente.setFlagAttivo("S");
		}
		return ricercaUtente;
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
		
		if( StringUtils.isNotEmpty( ricercaUtente.getCognome()) )
			model.addAttribute("filtriImpostati","true");
		
		if( StringUtils.isNotEmpty( ricercaUtente.getCodiceFiscale()) )
			model.addAttribute("filtriImpostati","true");
		
		if( StringUtils.isNotEmpty( ricercaUtente.getNome()) )
			model.addAttribute("filtriImpostati","true");
		
	}
	
//	@RequestMapping(value = "/private/admin/utenti", method = RequestMethod.POST)
//	public String initPost(Model model, 
//			@RequestParam(required = false) String buttonNew, 
//			@RequestParam(required = false) String buttonFind, 
//			@RequestParam(required = false) String buttonClean, 
//			@ModelAttribute("ricercaUtente") UtenteDto ricercaUtente,				
//			HttpServletRequest request)  {
//		String retval = "utenteHomeUtente";
//		
//		if("clean".equals( buttonClean )){
//			ricercaUtente = new UtenteDto();
//			ricercaUtente.setFlagAttivo("S");
//			model.addAttribute("ricercaUtente", ricercaUtente);
//		}
//		
//		if("new".equals( buttonNew )){
//			retval = "redirect:/private/admin/utenti/nuovo";
//		}else {
//			initModel(model, ricercaUtente, null);	
//		}
//		return retval;
//	}
	
	@RequestMapping(value = "/private/admin/utenti", method = RequestMethod.POST, params="buttonNew")
	public String initPostButtonNew(Model model, 
			@ModelAttribute("ricercaUtente") UtenteDto ricercaUtente,				
			HttpServletRequest request)  {
		String retval = "redirect:/private/admin/utenti/nuovo";
		return retval;
	}
	
	@RequestMapping(value = "/private/admin/utenti", method = RequestMethod.POST, params="buttonFind")
	public String initPostButtonFind(Model model, 
			@ModelAttribute("ricercaUtente") UtenteDto ricercaUtente,				
			HttpServletRequest request)  {
		String retval = "utenteHomeUtente";
		initModel(model, ricercaUtente, null);	
		return retval;
	}
	
	@RequestMapping(value = "/private/admin/utenti", method = RequestMethod.POST, params="buttonClean")
	public String initPostButtonClean(Model model,
			@ModelAttribute("ricercaUtente") UtenteDto ricercaUtente,				
			HttpSession session, SessionStatus status)  {
		status.setComplete();
        session.removeAttribute("ricercaUtente");
		return "redirect:/private/admin/utenti";
	}

	private void loadCombo4NewUtente(Model model) {
		List<CodiceDescrizioneDto> tipos = new ArrayList<CodiceDescrizioneDto>();
		tipos.add( new CodiceDescrizioneDto("I","Interno") );
		tipos.add( new CodiceDescrizioneDto("E","Esterno") ); 
		model.addAttribute("tipos", tipos );
		
		List<CodiceDescrizioneDto> sessos = new ArrayList<CodiceDescrizioneDto>();
		sessos.add( new CodiceDescrizioneDto("M","Maschile") );
		sessos.add( new CodiceDescrizioneDto("F","Femminile") ); 
		model.addAttribute("sessos", sessos );
		
		List<Ruolo> ruoli = new ArrayList<Ruolo>();
		for( Ruolo tmp: gestioneUtenteFacade.recuperaListaRuoli() ){
			if( !(Ruolo.ROLE_USER.equals( tmp.getCodice() ) || Ruolo.ROLE_ADMIN.equals( tmp.getCodice() ) ) ){
				ruoli.add(tmp);
			}
		}
		model.addAttribute("ruoli", ruoli );
		
		List<IdDescrizioneDto> organiEsterni = gestioneUtenteFacade.recuperaOrganiEsterni("");
		model.addAttribute("organiEsterni", organiEsterni );
		
	}
	
	@RequestMapping(value= {
			"/private/admin/utenti/nuovo/autocomporganoesterno", 
			"/private/admin/utenti/modifica/{1}/autocomporganoesterno",
			"/private/admin/utenti/modifica/autocomporganoesterno"}, method = RequestMethod.GET)
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
	
	
//	@RequestMapping(value= {"/private/admin/utenti/delete/{id}"}, method = RequestMethod.GET)
//	public String deleteGet(@PathVariable("id") int id, RedirectAttributes redirectAttributes)  {
	@RequestMapping(value= {"/private/admin/utenti/delete"}, method = RequestMethod.GET)
	public String deleteGet(@RequestParam(required = false) String id, RedirectAttributes redirectAttributes)  {
		String retval = "redirect:/private/admin/utenti";	
		gestioneUtenteFacade.eliminazioneLogica(Integer.valueOf(id));
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
	
//	@RequestMapping(value = "/private/admin/utenti/nuovo", method = RequestMethod.POST)
//	public String nuovoPost(@ModelAttribute("utenteToEdit") Utente utenteToEdit,
//			Model model, 
//			@RequestParam(required = false) String buttonSave,
//			@RequestParam(required = false) String buttonCancel,
//			BindingResult errors, 
//			HttpServletRequest request)  {
//	
//		String retval = "redirect:/private/admin/utenti";	
//		if("save".equals(buttonSave)){
//			utenteValidator.validate(utenteToEdit, errors);
//			if( !errors.hasErrors() ){			
//				if( "I".equals( utenteToEdit.getFlagIntEst() ) ) {
//					//Interno
//				}else{
//					//Esterno
//					utenteToEdit.setUtenteAstage(null);
//				}				
//				utenteToEdit.setFlagAttivo("S");
//				gestioneUtenteFacade.inserisciUtente(utenteToEdit);				
//				alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Inserimento Utente effettuato con successo", false);				
//				model.addAttribute("utenteToEdit", utenteToEdit );				
//				retval = "utenteDettUtente"; 										
//			}else{
//				for (FieldError f : errors.getFieldErrors()) {
//					alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
//				}
//				loadCombo4NewUtente(model);
//				model.addAttribute("utenteToEdit", utenteToEdit);
//				retval = "utenteNewUtente";
//			}
//		}else if("cancel".equals(buttonCancel)){
//		}
//		return retval;
//	}
	
	@RequestMapping(value = "/private/admin/utenti/nuovo", method = RequestMethod.POST, params="buttonSave")
	public String nuovoPostButtonSave(@ModelAttribute("utenteToEdit") @Valid Utente utenteToEdit,
			BindingResult errors, Model model)  {
	
		String retval = "redirect:/private/admin/utenti";
		if( "E".equals( utenteToEdit.getFlagIntEst() ) ) {
			//Esterno
			utenteToEdit.setUtenteAstage(null);
			if(StringUtils.isNotEmpty( utenteToEdit.getOrganoUteEsterno() )){
				utenteToEdit.setOrgano(gestioneEntiFacade.recuperaOrganoById(utenteToEdit.getOrganoUteEsterno()));
			}else{
				utenteToEdit.setOrgano(null);
			}
		}		
		
		if(StringUtils.isNotEmpty(utenteToEdit.getCodiceFiscale())){
			Utente u = gestioneUtenteFacade.recuperaUtenteByCodiceFiscale(utenteToEdit.getCodiceFiscale());
			if( u!=null ){
				errors.rejectValue("codiceFiscale","codice.fiscale.gia.presente" ,"E' gi\u00E0 presente un utente per il Codice Fiscale inserito");
			}else if( "E".equals( utenteToEdit.getFlagIntEst() ) ){
				UtenteAstage ua = gestioneUtenteFacade.recuperaUtenteAstageByCodiceFiscale(utenteToEdit.getCodiceFiscale());
				if( ua!=null ){
					errors.rejectValue("codiceFiscale","codice.fiscale.utente.interno" ,"Il Codice Fiscale inserito corrisponde ad un utente interno");
				}
			}
		}
		
		utenteValidator.validate(utenteToEdit, errors);
		if( !errors.hasErrors() ){					
			utenteToEdit.setFlagAttivo("S");
			gestioneUtenteFacade.inserisciUtente(utenteToEdit);				
			alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Inserimento Utente effettuato con successo", false);								
			retval = "utenteDettUtente"; 										
		}else{
			for (FieldError f : errors.getFieldErrors()) {
				alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
			}
			loadCombo4NewUtente(model);
			retval = "utenteNewUtente";
		}

		model.addAttribute("utenteToEdit", utenteToEdit);
		return retval;
	}
	
	@RequestMapping(value = "/private/admin/utenti/nuovo", method = RequestMethod.POST, params="buttonCancel")
	public String nuovoPostButtonCancel(@ModelAttribute("utenteToEdit") Utente utenteToEdit,
			Model model, 
			BindingResult errors, 
			HttpServletRequest request)  {
	
		String retval = "redirect:/private/admin/utenti";	
		return retval;
	}
	
//	@RequestMapping(value= {"/private/admin/utenti/dettaglio/{id}"}, method = RequestMethod.GET)
//	public String dettaglioGet(Model model, @PathVariable("id") String id)  {
	@RequestMapping(value= {"/private/admin/utenti/dettaglio"}, method = RequestMethod.GET)
	public String dettaglioGet(Model model, @RequestParam(required = false) String id)  {
		String retVal = "utenteHomeUtente";
		if( StringUtils.isNotEmpty(id) ){
			Utente utente = gestioneUtenteFacade.recuperaUtenteById(Integer.valueOf(id));
			model.addAttribute("utenteToEdit", utente );
			retVal = "utenteDettUtente";
		}
		return retVal;
	}
	
//	@RequestMapping(value= {"/private/admin/utenti/dettaglio"}, method = RequestMethod.POST)
//	public String dettaglioPost(@ModelAttribute("utenteToEdit") Utente utente, 
//							Model model,
//							@RequestParam(required = false) String buttonBack, 
//							@RequestParam(required = false) String buttonModify)  {
//		
//		String retVal = "utenteDettUtente";
//		if("back".equals(buttonBack)){
//			retVal = "redirect:/private/admin/utenti";
//		}else if("modify".equals(buttonModify)){
//			retVal = "redirect:/private/admin/utenti/modifica?id="+utente.getId();
//		}
//		
//		return retVal;
//	}
	
	@RequestMapping(value= {"/private/admin/utenti/dettaglio"}, method = RequestMethod.POST, params="buttonBack")
	public String dettaglioPostButtonBack(@ModelAttribute("utenteToEdit") Utente utente, 
							Model model)  {
		
		String retVal = "redirect:/private/admin/utenti";
		return retVal;
	}
	
	@RequestMapping(value= {"/private/admin/utenti/dettaglio"}, method = RequestMethod.POST, params="buttonModify")
	public String dettaglioPostButtonModify(@ModelAttribute("utenteToEdit") Utente utente, 
							Model model)  {
		
		String retVal = "redirect:/private/admin/utenti/modifica?id="+utente.getId();
		return retVal;
	}
//	@RequestMapping(value= {"/private/admin/utenti/modifica/{id}"}, method = RequestMethod.GET)
//	public String modificaGet(Model model, @PathVariable("id") String id)  {
	@RequestMapping(value= {"/private/admin/utenti/modifica"}, method = RequestMethod.GET)
	public String modificaGet(Model model, @RequestParam(required = false) String id)  {
		String retVal = "utenteHomeUtente";
		if( StringUtils.isNotEmpty(id) ){
			loadCombo4NewUtente(model);
			Utente utente = gestioneUtenteFacade.recuperaUtenteById(Integer.valueOf(id));
			model.addAttribute("utenteToEdit", utente );
			retVal = "utenteEditUtente";
		}
		return retVal;
	}
	
//	@RequestMapping(value= {"/private/admin/utenti/modifica"}, method = RequestMethod.POST)
//	public String modificaPost(@ModelAttribute("utenteToEdit") Utente utente,
//			Model model,
//			@RequestParam(required = false) String buttonSave, 
//			@RequestParam(required = false) String buttonCancel,
//			BindingResult errors, HttpServletRequest request
//			)  {
//		String retVal = "utenteDettUtente";
//		
//		if("save".equals( buttonSave )){
//			
//			utenteValidator.validate(utente, errors);
//			
//			if( !errors.hasErrors() ){
//				
//				Utente newUtente = gestioneUtenteFacade.aggiornaUtente(utente);
//				
//				alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Aggiornamento Utente effettuato con successo", false);
//				
//				model.addAttribute("utenteToEdit", newUtente );
//						
//			}else{
//				for (FieldError f : errors.getFieldErrors()) {
//					alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
//				}
//				model.addAttribute("utenteToEdit", utente);
//				retVal = "utenteEditUtente";
//			}
//		
//		}
//		
//		if("cancel".equals( buttonCancel )){
//			model.addAttribute("utenteToEdit", gestioneUtenteFacade.recuperaUtenteById(utente.getId()));
//		}
//		
//		return retVal;
//	}
	
	@RequestMapping(value= {"/private/admin/utenti/modifica"}, method = RequestMethod.POST, params="buttonSave")
	public String modificaPostButtonSave(@ModelAttribute("utenteToEdit") @Valid Utente utente,
			BindingResult errors, RedirectAttributes redirectAttributes, Model model)  {
		
		String retVal = "utenteDettUtente";

		utenteValidator.validate(utente, errors);

		if( !errors.hasErrors() ){

			Utente newUtente = gestioneUtenteFacade.aggiornaUtente(utente);

			alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Aggiornamento Utente effettuato con successo", false);

			model.addAttribute("utenteToEdit", newUtente );

		}else{
			for (FieldError f : errors.getFieldErrors()) {
				alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
			}
			loadCombo4NewUtente(model);
			model.addAttribute("utenteToEdit", utente);
			retVal = "utenteEditUtente";
		}
		
		return retVal;
	}
	
	@RequestMapping(value= {"/private/admin/utenti/modifica"}, method = RequestMethod.POST, params="buttonCancel")
	public String modificaPostButtonCancel(@ModelAttribute("utenteToEdit") Utente utente,
			Model model,
			BindingResult errors, HttpServletRequest request
			)  {
		String retVal = "utenteDettUtente";
		
		model.addAttribute("utenteToEdit", gestioneUtenteFacade.recuperaUtenteById(utente.getId()));
		
		return retVal;
	}

}
