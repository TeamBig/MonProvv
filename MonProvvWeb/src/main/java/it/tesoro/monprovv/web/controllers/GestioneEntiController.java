package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.CodiceDescrizioneDto;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;
import it.tesoro.monprovv.facade.GestioneEntiFacade;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.UnitaOrgAstage;
import it.tesoro.monprovv.web.utils.AlertUtils;
import it.tesoro.monprovv.web.utils.StringUtils;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
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
		return new Organo();
	}
	
	
	@RequestMapping(value= {"/private/admin/enti"}, method = RequestMethod.GET)
	public String initEntiGet(Model model, 
			@ModelAttribute("ricercaEnte") Organo ricercaEnte,	
			@PagingAndSorting(tableId = tableEntiUID) DisplayTagPagingAndSorting ps)  {
		
		List<Organo> listaOrgani = new ArrayList<Organo>();
		if (ps != null) {
			if(StringUtils.isEmpty( ricercaEnte.getDenominazione() ) )
				listaOrgani =gestioneEntiFacade.recuperaOrgano(ps.getPage());
			else
				listaOrgani =gestioneEntiFacade.recuperaOrgano(ps.getPage(), ricercaEnte);
		} else {
			listaOrgani = gestioneEntiFacade.recuperaOrgano(1);
		}
		model.addAttribute("listaOrgani", listaOrgani);
		
		if(StringUtils.isEmpty( ricercaEnte.getDenominazione() ) )
			model.addAttribute("tableOrganiRisultatiSize", gestioneEntiFacade.countOrgano());
		else
			model.addAttribute("tableOrganiRisultatiSize", gestioneEntiFacade.countOrgano(ricercaEnte));
		
		return "entiHomeEnti";
	}
	
	@RequestMapping(value = "/private/admin/enti", method = RequestMethod.POST)
	public String initEntiPost(Model model, 
			@RequestParam(required = false) String buttonNew, 
			@RequestParam(required = false) String ricerca, 
			@RequestParam(required = false) String pulisci, 
			@ModelAttribute("ricercaEnte") Organo ricercaEnte,				
			HttpServletRequest request)  {
		String retval = "entiHomeEnti";
		
		if("OK".equals( pulisci )){
			ricercaEnte = new Organo();
			model.addAttribute("ricercaEnte",ricercaEnte);
		}
		
		if("OK".equals( buttonNew )){
			loadCombo4NewEnte(model, request);
			model.addAttribute("organoToEdit", new Organo());
			retval = "entiNewEnte";
		}else if("OK".equals(ricerca)){
			List<Organo> listaOrgani = gestioneEntiFacade.recuperaOrgano(1, ricercaEnte);
			model.addAttribute("listaOrgani", listaOrgani);
			model.addAttribute("tableOrganiRisultatiSize", gestioneEntiFacade.countOrgano(ricercaEnte));
		}else{
			List<Organo> listaOrgani = gestioneEntiFacade.recuperaOrgano(1);
			model.addAttribute("listaOrgani", listaOrgani);
			model.addAttribute("tableOrganiRisultatiSize", gestioneEntiFacade.countOrgano());
		}
		return retval;
	}
	
	@RequestMapping(value = "/private/admin/enti/nuovo", method = RequestMethod.POST)
	public String nuovoPost(@ModelAttribute("organoToEdit") Organo organoToEdit,
			Model model, 
			@RequestParam(required = false) String buttonSave,
			@RequestParam(required = false) String buttonCancel,
			BindingResult errors, HttpServletRequest request)  {
		
		
		String retval = "redirect:/private/admin/enti";
		
		if("OK".equals(buttonSave)){
			
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
			
		}
		
		return retval;
	}

	private void loadCombo4NewEnte(Model model, HttpServletRequest request) {
		loadComboConcertante(model);
		
		List<CodiceDescrizioneDto> tipos = new ArrayList<CodiceDescrizioneDto>();
		tipos.add( new CodiceDescrizioneDto("I","Interno") );
		tipos.add( new CodiceDescrizioneDto("E","Esterno") ); 
		model.addAttribute("tipos", tipos );
		
//		List<UnitaOrgAstage> listaUo = (List<UnitaOrgAstage>)cacheService.getFromCache(CacheService.KEY_LISTA_UO);
//		if (listaUo == null)  {
//			listaUo = gestioneEntiFacade.recuperaUnitaOrgAstageNonUsati();
//			cacheService.addToCache(CacheService.KEY_LISTA_UO, listaUo);
//		}
//
//		model.addAttribute("organiInterni", listaUo );
	}
	
	@RequestMapping(value= {"/private/admin/enti/nuovo/autocompletamento"}, method = RequestMethod.GET)
	@ResponseBody
	public List<UnitaOrgAstage> autocompletamentoUo(@RequestParam("query") String query)  {
		
		List<UnitaOrgAstage> result = gestioneEntiFacade.recuperaUnitaOrgAstageNonUsati(query);
		return result;
		
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
			loadComboConcertante(model);
			organo.setFlgInternoEsterno((organo.getUnitaOrgAstage()==null)?"E":"I");
			model.addAttribute("organoToEdit", organo);
		}
		
		return retVal;
	}
	
	@RequestMapping(value= {"/private/admin/enti/edit"}, method = RequestMethod.POST)
	public String editEntePost(
							@ModelAttribute("organoToEdit") Organo organoToEdit, 
							Model model,
							@RequestParam(required = false) String buttonSave, 
							@RequestParam(required = false) String buttonCancel,
							BindingResult errors, HttpServletRequest request)  {
		
		String retVal = "entiDettaglioEnte"; 
		
		if("OK".equals( buttonSave )){
			
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
		
		
		if("OK".equals( buttonCancel )){
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