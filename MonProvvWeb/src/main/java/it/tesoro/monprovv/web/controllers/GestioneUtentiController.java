package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;
import it.tesoro.monprovv.facade.GestioneUtenteFacade;
import it.tesoro.monprovv.model.Utente;
import it.tesoro.monprovv.web.utils.AlertUtils;
import it.tesoro.monprovv.web.utils.StringUtils;
import it.tesoro.monprovv.web.validators.UtenteValidator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
		if (ps != null) {
			if(StringUtils.isEmpty( ricercaUtente.getCodiceFiscale() ) )
				listaUtenti =gestioneUtenteFacade.recupera(ps.getPage());
			else
				listaUtenti =gestioneUtenteFacade.recupera(ps.getPage(), ricercaUtente);
		} else {
			listaUtenti = gestioneUtenteFacade.recupera(1);
		}
		model.addAttribute("listaUtenti", listaUtenti);
		
		if(StringUtils.isEmpty( ricercaUtente.getCodiceFiscale() ) )
			model.addAttribute("tableRisultatiSize", gestioneUtenteFacade.count());
		else
			model.addAttribute("tableRisultatiSize", gestioneUtenteFacade.count(ricercaUtente));
		
		return "utentHomeUtenti";
	}
	
	@RequestMapping(value = "/private/admin/utenti", method = RequestMethod.POST)
	public String initPost(Model model, 
			@RequestParam(required = false) String buttonNew, 
			@RequestParam(required = false) String ricerca, 
			@RequestParam(required = false) String pulisci, 
			@ModelAttribute("ricercaUtente") Utente ricercaUtente,				
			HttpServletRequest request)  {
		String retval = "utentHomeUtenti";
		
		if("OK".equals( pulisci )){
			ricercaUtente = new Utente();
			model.addAttribute("ricercaUtente", ricercaUtente);
		}
		
		if("OK".equals( buttonNew )){
			loadCombo4NewUtente(model, request);
			model.addAttribute("utenteToEdit", new Utente());
			retval = "utenteNewUtente";
		}else if("OK".equals(ricerca)){
			List<Utente> listaUtenti = gestioneUtenteFacade.recupera(1, ricercaUtente);
			model.addAttribute("listaUtenti", listaUtenti);
			model.addAttribute("tableRisultatiSize", gestioneUtenteFacade.count(ricercaUtente));
		}else{
			model.addAttribute("listaEnti", gestioneUtenteFacade.recupera(1));
			model.addAttribute("tableRisultatiSize", gestioneUtenteFacade.count());
		}
		return retval;
	}

	private void loadCombo4NewUtente(Model model, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}
	
}
