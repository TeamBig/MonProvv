package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.dto.GestioneTipologicheDto;
import it.tesoro.monprovv.dto.IdDescrizioneDto;
import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.TipoAtto;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.utils.StringUtils;
import it.tesoro.monprovv.web.utils.AlertUtils;
import it.tesoro.monprovv.web.validators.TipologicheValidator;

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

@Controller
public class GestioneTipologicheController {

protected static Logger logger = Logger.getLogger(GestioneTipologicheController.class);
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AlertUtils alertUtils;	
	
	@Autowired
	private GestioneTipologicaFacade gestioneTipologicaFacade;
	
	@Autowired
	protected TipologicheValidator tipologicheValidator;

	@ModelAttribute("gestioneTipologiche")
	public GestioneTipologicheDto gestioneTipologicheDto(HttpServletRequest request, @RequestParam(required = false) Integer idSchelta) {
		GestioneTipologicheDto gestioneTipologiche = new GestioneTipologicheDto();
		caricaListaScelta(idSchelta, gestioneTipologiche);
		return gestioneTipologiche;
	}

	private void caricaListaScelta(Integer idSchelta,
			GestioneTipologicheDto gestioneTipologiche) {
		if( idSchelta != null ){
			gestioneTipologiche.setIdSchelta(idSchelta);
			switch (idSchelta){
			case 1: gestioneTipologiche.setGoverni(gestioneTipologicaFacade.initGoverno4Tipologiche());
					gestioneTipologiche.setTipiAtto(null);
					gestioneTipologiche.setTipiProvv(null);
					break;
			case 2: gestioneTipologiche.setGoverni(null);
					gestioneTipologiche.setTipiAtto(gestioneTipologicaFacade.initTipoAtto4Tipologiche());
					gestioneTipologiche.setTipiProvv(null);
					break;
			case 3: gestioneTipologiche.setGoverni(null);
					gestioneTipologiche.setTipiAtto(null);
					gestioneTipologiche.setTipiProvv(gestioneTipologicaFacade.initTipoProvvDaAdottare4Tipologiche());
					break;
			}
			
		}
	}
	
	private void init(Model model, GestioneTipologicheDto gestioneTipologiche) {
		
		List<IdDescrizioneDto> sceltas = new ArrayList<IdDescrizioneDto>();
		sceltas.add(new IdDescrizioneDto(1,"Governo"));
		sceltas.add(new IdDescrizioneDto(2,"Tipo Atto"));
		sceltas.add(new IdDescrizioneDto(3,"Provvedimento da Adottare"));
		model.addAttribute("sceltas", sceltas);
		
		model.addAttribute("gestioneTipologiche", gestioneTipologiche);
		
	}

	@RequestMapping(value= {"/private/admin/tipologiche"}, method = RequestMethod.GET)
	public String initGet(Model model,
			@ModelAttribute("gestioneTipologiche") GestioneTipologicheDto gestioneTipologiche)  {
		
		init(model, gestioneTipologiche);
		
		return "tipologicheHome";
	}
	
	@RequestMapping(value= {"/private/admin/tipologiche"}, method = RequestMethod.POST, params="changeTipologica")
	public String initPost(Model model,
			@ModelAttribute("gestioneTipologiche") GestioneTipologicheDto gestioneTipologiche)  {
		
		init(model, gestioneTipologiche);
		
		return "tipologicheHome";
	}
	
	@RequestMapping(value= {"/private/admin/tipologiche"}, method = RequestMethod.POST, params="saveGoverno")
	public String saveGovernoPost(@ModelAttribute("gestioneTipologiche") GestioneTipologicheDto gestioneTipologiche,
			BindingResult errors, Model model)  {
		
		Governo governo = null;
		tipologicheValidator.validate(gestioneTipologiche, errors);
		
		if( !errors.hasErrors() ){			
			
			governo =  (StringUtils.isEmpty( gestioneTipologiche.getIdGoverno() ))? new Governo() : gestioneTipologicaFacade.recuperaGovernoById( gestioneTipologiche.getIdGoverno() );
			
			List<Governo> governiPrenseti = gestioneTipologicaFacade.recuperaGovernoByDenominazione(gestioneTipologiche.getDenominazioneGoverno());
			
			for( Governo tmp: governiPrenseti ){
				if( ! tmp.equals(governo) ){
					//Denominazione già presente per un altro id
					errors.rejectValue("denominazioneGoverno","generic.error.required" ,"La 'Denominazione' del Governo è già presente");
					break;
				}
			}
		}
		if( !errors.hasErrors() ){			
			
			governo.setDenominazione(gestioneTipologiche.getDenominazioneGoverno());
			
			gestioneTipologicaFacade.inserisciAggiornaGoverno(governo);
			
			if(StringUtils.isEmpty( gestioneTipologiche.getIdGoverno() )){
				alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Inserimento Governo effettuato con successo", false);	
			}else{
				alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Modifica Governo effettuato con successo", false);	
			}
			
			gestioneTipologiche.setGoverni(gestioneTipologicaFacade.initGoverno4Tipologiche());
			
		}else{			
			for (FieldError f : errors.getFieldErrors()) {
				alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
			}
		}
		
		init(model, gestioneTipologiche);
		
		return "tipologicheHome";
	}
	
	@RequestMapping(value= {"/private/admin/tipologiche"}, method = RequestMethod.POST, params="saveTipoAtto")
	public String saveTipoAttoPost(@ModelAttribute("gestioneTipologiche") GestioneTipologicheDto gestioneTipologiche,
			BindingResult errors, Model model)  {
		
		TipoAtto tipoAtto = null;
		tipologicheValidator.validate(gestioneTipologiche, errors);
		if( !errors.hasErrors() ){	
		
			tipoAtto = (StringUtils.isEmpty( gestioneTipologiche.getIdTipoAtto() ))? new TipoAtto() : gestioneTipologicaFacade.recuperaTipoAttoById( gestioneTipologiche.getIdTipoAtto() );
			tipoAtto.setCodice(gestioneTipologiche.getCodiceTipoAtto());
			tipoAtto.setDescrizione(gestioneTipologiche.getDescrizioneTipoAtto());
			
			List<TipoAtto> attiByCodice = gestioneTipologicaFacade.recuperaTipoAttoByoByCodice(gestioneTipologiche.getCodiceTipoAtto());
			for( TipoAtto tmp: attiByCodice ){
				if( ! tmp.equals(tipoAtto) ){
					//Descrizione già presente per un altro id
					errors.rejectValue("codiceTipoAtto","generic.error.required" ,"Il 'Codice' del Tipo Atto è già presente");
					break;
				}
			}
			
			List<TipoAtto> attiByDesc = gestioneTipologicaFacade.recuperaTipoAttoByDescrizione(gestioneTipologiche.getDescrizioneTipoAtto());
			for( TipoAtto tmp: attiByDesc ){
				if( ! tmp.equals(tipoAtto) ){
					//Descrizione già presente per un altro id
					errors.rejectValue("descrizioneTipoAtto","generic.error.required" ,"La 'Descrizione' del Tipo Atto è già presente");
					break;
				}
			}
			
		}
		if( !errors.hasErrors() ){	
			gestioneTipologicaFacade.inserisciAggiornaTipoAtto(tipoAtto);
			
			if(StringUtils.isEmpty( gestioneTipologiche.getIdTipoAtto() )){
				alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Inserimento Tipo Atto effettuato con successo", false);	
			}else{
				alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Modifica Tipo Atto effettuato con successo", false);	
			}
			
			gestioneTipologiche.setTipiAtto(gestioneTipologicaFacade.initTipoAtto4Tipologiche());
		
		}else{	
			for (FieldError f : errors.getFieldErrors()) {
				alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
			}
		}
		init(model, gestioneTipologiche);
		
		return "tipologicheHome";
	}
	
	@RequestMapping(value= {"/private/admin/tipologiche"}, method = RequestMethod.POST, params="saveTipoProv")
	public String saveTipoProvPost(@ModelAttribute("gestioneTipologiche") GestioneTipologicheDto gestioneTipologiche,
			BindingResult errors, Model model)  {
		
		TipoProvvDaAdottare tipoProvvDaAdottare = null;
		tipologicheValidator.validate(gestioneTipologiche, errors);
		if( !errors.hasErrors() ){	
		
			tipoProvvDaAdottare =  (StringUtils.isEmpty( gestioneTipologiche.getIdTipoProv() ))? new TipoProvvDaAdottare() : gestioneTipologicaFacade.recuperaTipoProvvDaAdottareById( gestioneTipologiche.getIdTipoProv() );
			tipoProvvDaAdottare.setDescrizione(gestioneTipologiche.getDescrizioneTipoProv());
		
			List<TipoProvvDaAdottare> tipoProvByDesc = gestioneTipologicaFacade.recuperaTipoProvvDaAdottareByDescrizione(gestioneTipologiche.getDescrizioneTipoProv());
			for( TipoProvvDaAdottare tmp: tipoProvByDesc ){
				if( ! tmp.equals(tipoProvvDaAdottare) ){
					//Descrizione già presente per un altro id
					errors.rejectValue("descrizioneTipoProv","generic.error.required" ,"La 'Descrizione' del Tipo Provvedimento da Adottare è già presente");
					break;
				}
			}
			
		}
		
		if( !errors.hasErrors() ){	
			gestioneTipologicaFacade.inserisciAggiornaTipoProvvDaAdottare(tipoProvvDaAdottare);
			
			if(StringUtils.isEmpty( gestioneTipologiche.getIdTipoProv() )){
				alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Inserimento Tipo Provvedimento da Adottare effettuato con successo", false);	
			}else{
				alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Modifica Tipo Provvedimento da Adottare effettuato con successo", false);	
			}
			
			gestioneTipologiche.setTipiProvv(gestioneTipologicaFacade.initTipoProvvDaAdottare4Tipologiche());
		
		}else{
			for (FieldError f : errors.getFieldErrors()) {
				alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
			}
		}
		init(model, gestioneTipologiche);
		
		return "tipologicheHome";
	}
	
	@RequestMapping(value= {"/private/admin/tipologiche/governo/cambiastato"}, method = RequestMethod.GET)
	public String deleteGovernoGet(@RequestParam(required = false) Integer id, Model model)  {
		if(StringUtils.isNotEmpty( id )){
			Governo o = gestioneTipologicaFacade.recuperaGovernoById( id );
			if( "N".equals( o.getFlagAttivo() ) )
				o.setFlagAttivo("S");
			else
				o.setFlagAttivo("N");
			gestioneTipologicaFacade.inserisciAggiornaGoverno(o);
			alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Cancellazione Governo effettuato con successo", false);	
		}
		GestioneTipologicheDto dto = new GestioneTipologicheDto();
		caricaListaScelta(1, dto);
		init(model, dto);
		
		return "tipologicheHome";
	}
	
	@RequestMapping(value= {"/private/admin/tipologiche/tipoatto/cambiastato"}, method = RequestMethod.GET)
	public String deleteTipoAttoGet(@RequestParam(required = false) Integer id, Model model)  {
		if(StringUtils.isNotEmpty( id )){
			TipoAtto o = gestioneTipologicaFacade.recuperaTipoAttoById( id );
			if( "N".equals( o.getFlagAttivo() ) )
				o.setFlagAttivo("S");
			else
				o.setFlagAttivo("N");
			gestioneTipologicaFacade.inserisciAggiornaTipoAtto(o);
			alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Cancellazione Tipo Atto effettuato con successo", false);	
		}
		GestioneTipologicheDto dto = new GestioneTipologicheDto();
		caricaListaScelta(2, dto);
		init(model, dto);
		
		return "tipologicheHome";
	}
	
	@RequestMapping(value= {"/private/admin/tipologiche/tipiprovv/cambiastato"}, method = RequestMethod.GET)
	public String deleteTipoProvvGet(@RequestParam(required = false) Integer id, Model model)  {
		if(StringUtils.isNotEmpty( id )){
			TipoProvvDaAdottare o = gestioneTipologicaFacade.recuperaTipoProvvDaAdottareById( id );
			if( "N".equals( o.getFlagAttivo() ) )
				o.setFlagAttivo("S");
			else
				o.setFlagAttivo("N");
			gestioneTipologicaFacade.inserisciAggiornaTipoProvvDaAdottare(o);
			alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Cancellazione Tipo Provvedimento da Adottare effettuato con successo", false);	
		}
		GestioneTipologicheDto dto = new GestioneTipologicheDto();
		caricaListaScelta(3, dto);
		init(model, dto);
		
		return "tipologicheHome";
	}
}
