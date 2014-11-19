package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.AllegatoDto;
import it.tesoro.monprovv.dto.AssegnazioneDto;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;
import it.tesoro.monprovv.dto.IndirizzoEmailDto;
import it.tesoro.monprovv.dto.InserisciProvvedimentoDto;
import it.tesoro.monprovv.dto.Mail;
import it.tesoro.monprovv.dto.ProvvedimentoStampaDto;
import it.tesoro.monprovv.dto.RicercaProvvedimentoDto;
import it.tesoro.monprovv.dto.SalvaENotificaDto;
import it.tesoro.monprovv.dto.SollecitoDto;
import it.tesoro.monprovv.dto.UtenteDto;
import it.tesoro.monprovv.facade.GestioneNotificaFacade;
import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.facade.GestioneUtenteFacade;
import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.EmailExtra;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Notifica;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.ProvvedimentiParent;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.Storico;
import it.tesoro.monprovv.model.TipoAtto;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;
import it.tesoro.monprovv.model.Utente;
import it.tesoro.monprovv.service.MailService;
import it.tesoro.monprovv.service.ReportService;
import it.tesoro.monprovv.sicurezza.CustomUser;
import it.tesoro.monprovv.utils.Constants;
import it.tesoro.monprovv.utils.SicurezzaUtils;
import it.tesoro.monprovv.utils.StringUtils;
import it.tesoro.monprovv.web.utils.AlertUtils;
import it.tesoro.monprovv.web.utils.ProvvedimentiUtil;
import it.tesoro.monprovv.web.validators.ProvvedimentoValidator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SessionAttributes("provvedimentoInserisci")
@Controller
public class GestioneProvvedimentoController {

	protected static Logger logger = Logger.getLogger(GestioneProvvedimentoController.class);
	
	@Autowired
	private GestioneNotificaFacade gestioneNotificaFacade;
	
	@Autowired
	private AlertUtils alertUtils;
	
	@Autowired
	protected ProvvedimentoValidator provValidator;

	@Autowired
	private GestioneProvvedimentoFacade gestioneProvvedimentoFacade;
	
	@Autowired 
	private GestioneUtenteFacade gestioneUtenteFacade;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = { "/private/provvedimenti/ricerca" } , method = RequestMethod.GET)
	public String init(Model model,	SecurityContextHolderAwareRequestWrapper request, @PagingAndSorting(tableId = "provvedimento") DisplayTagPagingAndSorting ps,@ModelAttribute("ricercaProvvedimenti") RicercaProvvedimentoDto provvedimento) {
		RicercaProvvedimentoDto dto = new RicercaProvvedimentoDto();
		model.addAttribute("ricercaProvvedimenti", dto);
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
	
	@RequestMapping(value = { "/private/provvedimenti/ricerca" } , method = RequestMethod.POST, params="annulla")
	public String resetRicercaProvvedimenti(Model model){
		return "redirect:/private/provvedimenti/ricerca";
	}
	
	@RequestMapping(value = { "/private/provvedimenti/ricerca" } , method = RequestMethod.POST)
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
	@RequestMapping(value = { "/private/provvedimenti/ricerca/dettaglio" } , method = RequestMethod.GET)
	public String dettaglio(Model model,@RequestParam(required = false) Integer id) {
		String retVal = "ricercaProv";
		if(StringUtils.isNotEmpty(id)){
			Provvedimento provvedimentoDettaglio = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
			model.addAttribute("provvedimentoDettaglio", provvedimentoDettaglio);
			caricaTabelleInferiore(model, provvedimentoDettaglio);			
			retVal = "provvedimentoDettaglio";
		}
		return retVal;
	}
	
	@RequestMapping(value = "/private/provvedimenti", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:/private/provvedimenti/ricerca";
	}
	
	// **************** modifica -> dettaglio provvedimento ******//
	
	@RequestMapping(value = "/private/provvedimenti/ricerca/dettaglio", method = RequestMethod.POST, params="modifica" )
	public String gestioneModificaDettaglioProvv(@RequestParam(required = false) Integer id, Model model, @ModelAttribute("provvedimentoDettaglio") Provvedimento provvedimentoDettaglio) {
		
		return "redirect:/private/provvedimenti/ricerca/modifica?id="+id;
	}
	
	// **************** note allegati dettaglio provvedimento ******//
	
	@RequestMapping(value = "/private/provvedimenti/ricerca/dettaglio", method = RequestMethod.POST, params="noteAllegati" )
	public String gestioneNoteAllegatiDettaglioProvv(@RequestParam(required = false) Integer id, Model model, @ModelAttribute("provvedimentoDettaglio") Provvedimento provvedimentoDettaglio) {
		
		return "redirect:/private/provvedimenti/ricerca/noteAllegatiProv?id="+id;
	}
	
	// **************** annulla / indietro dettaglio provvedimento ******//
	
	@RequestMapping(value = "/private/provvedimenti/ricerca/dettaglio", method = RequestMethod.POST, params= "annulla")
	public String gestioneAnnullaDettaglioProvv(@RequestParam(required = false) Integer id, Model model, @ModelAttribute("provvedimentoDettaglio") Provvedimento provvedimentoDettaglio) {
		
		return "redirect:/private/provvedimenti/ricerca";
	}
	
	@RequestMapping(value = "/private/provvedimenti/ricerca/dettaglio", method = RequestMethod.POST, params= "indietro" )
	public String gestioneIndietroDettaglioProvv(@RequestParam(required = false) Integer id, Model model, @ModelAttribute("provvedimentoDettaglio") Provvedimento provvedimentoDettaglio) {
		
		return "redirect:/private/provvedimenti/ricerca";
	}
	
	// **************** cambio stato dettaglio provvedimento ******//
	
	@RequestMapping(value = "/private/provvedimenti/ricerca/dettaglio", method = RequestMethod.POST, params="cambioStato" )
	public String gestioneCambioStatoDettaglioProvv(@RequestParam(required = false) Integer id, Model model, @ModelAttribute("provvedimentoDettaglio") Provvedimento provvedimentoDettaglio) {
	
		Provvedimento provvRec = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
		provvRec.setStato(provvedimentoDettaglio.getStato());
		model.addAttribute("provvedimentoDettaglio", provvRec);
		caricaTabelleInferiore(model, provvRec);
		
		return "provvedimentoDettaglio";
	}
	
	// **************** salva dettaglio provvedimento ******//
	
	@RequestMapping(value = "/private/provvedimenti/ricerca/dettaglio", method = RequestMethod.POST, params="salvaDettaglio" )
	public String gestioneSalvataggioDettaglioProvv(@RequestParam(required = false) Integer id, Model model, @ModelAttribute("provvedimentoDettaglio") Provvedimento provvedimentoDettaglio) {
	
		Provvedimento provvRec = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
		provvRec.setStato(provvedimentoDettaglio.getStato());
		gestioneProvvedimentoFacade.aggiornaProvvedimento(provvRec);
		model.addAttribute("provvedimentoDettaglio", provvRec);
		caricaTabelleInferiore(model, provvRec);
		
		alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Salvataggio effettuato con successo.", false);
		
		return "provvedimentoDettaglio";
	}
	
	@RequestMapping(value = "/private/provvedimenti/ricerca/dettaglio", method = RequestMethod.POST, params="inviaSollecito" )
	public String gestioneSalvaENotifica(
			@RequestParam(required = false) Integer id, 
			Model model, 
			@ModelAttribute("provvedimentoDettaglio") Provvedimento provvedimentoDettaglio){
		
		Provvedimento provvRec = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
		provvRec.setStato(provvedimentoDettaglio.getStato());
		model.addAttribute("provvedimentoDettaglio", provvRec);
		caricaTabelleInferiore(model, provvRec);
		
		if( StringUtils.isEmpty(provvedimentoDettaglio.getOggettoSollecito()) || StringUtils.isEmpty(provvedimentoDettaglio.getTestoSollecito()) ){
			alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, "Sollecito non inviato, inserire sia l'oggetto che il testo del sollecito.", false);
		}else{
			SollecitoDto sollecitoDto = new SollecitoDto(provvedimentoDettaglio.getOggettoSollecito(), provvedimentoDettaglio.getTestoSollecito(), provvedimentoDettaglio.getIdAssegnatarioSollecito());
			gestioneProvvedimentoFacade.inserisciInviaSolleciti( sollecitoDto );
		}
		
		alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Sollecito inviato con successo.", false);
		
		return "provvedimentoDettaglio";
		
	}
	
	// **************** accettazione assegnazione ******//
	
	@RequestMapping(value = "/private/provvedimenti/ricerca/dettaglio", method = RequestMethod.POST, params="accettaAssegnazione" )
	public String accettazioneAssegnazione(@RequestParam(required = true) Integer id, @RequestParam(required = false) Integer idNotifica, 
				Model model, @ModelAttribute("provvedimentoDettaglio") Provvedimento provvedimentoDettaglio) throws Exception {
	
		Provvedimento provvedimento = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
		
		Assegnazione assegnazione = SicurezzaUtils.assegnazioneCorrente(provvedimento.getAssegnazioneList());
		if (assegnazione == null) {
			throw new Exception("Impossibile determinare l'assegnazione");
		}
		Notifica notifica = null;
		if (idNotifica == null) {
			// recupera notifica
			notifica = gestioneNotificaFacade.recuperaNotificaAssegnazione(assegnazione);
		} else {
			notifica = gestioneNotificaFacade.recuperaNotifica(idNotifica);
		}
		
		gestioneProvvedimentoFacade.aggiornaAssegnazione(assegnazione, notifica, true, null);
		
		
		return dettaglio(model, id);
	}
	
	@RequestMapping(value = "/private/provvedimenti/ricerca/dettaglio", method = RequestMethod.POST, params="rifiutaAssegnazione" )
	public String rifiutaAssegnazione(@RequestParam(required = true) Integer id, @RequestParam(required = false) Integer idNotifica, 
				Model model, @ModelAttribute("provvedimentoDettaglio") Provvedimento provvedimentoDettaglio) throws Exception {
	
		Provvedimento provvedimento = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
		
		Assegnazione assegnazione = SicurezzaUtils.assegnazioneCorrente(provvedimento.getAssegnazioneList());
		if (assegnazione == null) {
			throw new Exception("Impossibile determinare l'assegnazione");
		}
		Notifica notifica = null;
		if (idNotifica == null) {
			// recupera notifica
			notifica = gestioneNotificaFacade.recuperaNotificaAssegnazione(assegnazione);
		} else {
			notifica = gestioneNotificaFacade.recuperaNotifica(idNotifica);
		}
		
		gestioneProvvedimentoFacade.aggiornaAssegnazione(assegnazione, notifica, false, provvedimentoDettaglio.getMotivazioneRifiuto());
		
		return dettaglio(model, id);
	}
	
	@RequestMapping(value = "/private/provvedimenti/ricerca/dettaglio", method = RequestMethod.POST, params="fineLavorazione" )
	public String fineLavorazione(@RequestParam(required = true) Integer id, Model model, @ModelAttribute("provvedimentoDettaglio") Provvedimento provvedimentoDettaglio) throws Exception {
	
		Provvedimento provvedimento = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
		
		Assegnazione assegnazione = SicurezzaUtils.assegnazioneCorrente(provvedimento.getAssegnazioneList());
		if (assegnazione == null) {
			throw new Exception("Impossibile determinare l'assegnazione");
		}
		
		gestioneProvvedimentoFacade.aggiornaFineLavorazioneAssegnazione(assegnazione);
		
		alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Lavorazione del provvedimento completata.", false);
		
		return dettaglio(model, id);
	}
	
	// *************************** SALVA E INVIA NOTIFICA *************************** //
	@RequestMapping(value = "/private/provvedimenti/ricerca/dettaglio", method = RequestMethod.POST, params="salvaenotifica" )
	public String gestioneSalvaENotifica(
										RedirectAttributes redirectAttributes, 
										@RequestParam(required = false) Integer id, 
										Model model, 
										@ModelAttribute("provvedimentoDettaglio") Provvedimento provvedimentoDettaglio,
										@RequestParam(required = false) List<String> tokenfieldemail,
										@RequestParam(required = false) String oggetto,
										@RequestParam(required = false) String testo
			) {
		
		
		
		Provvedimento provvRec = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
		provvRec.setStato(provvedimentoDettaglio.getStato());
		gestioneProvvedimentoFacade.aggiornaProvvedimento(provvRec);
		model.addAttribute("provvedimentoDettaglio", provvRec);
		caricaTabelleInferiore(model, provvRec);
		
		//Invio email
		if( StringUtils.isNotEmpty( oggetto ) || StringUtils.isNotEmpty( testo ) ){
			
			
			UtenteDto criteria = null;
			EmailExtra emailExtra = null;
			Mail mail = null;
			for(String indirizzo: tokenfieldemail){
				//Verifico che l'indizizzo email sia stato inserito correttamente
				if(StringUtils.mailSyntaxCheck(indirizzo)){
					mail = new Mail();
					mail.setDestinatario(indirizzo);
					mail.setSubject(oggetto);
					mail.setContent(testo);
					mail.setHtmlFormat(false);
					
					gestioneProvvedimentoFacade.invioMail( mail );
					
					criteria = new UtenteDto();
					criteria.setEmail(indirizzo);

					if(gestioneUtenteFacade.countEmailExtra(indirizzo)==0
						&&	gestioneUtenteFacade.count(criteria)==0
							){
						emailExtra = new EmailExtra();
						emailExtra.setEmailExtra(indirizzo);
						gestioneUtenteFacade.inserisciEmailExtra(emailExtra);
					}
				}
			}
		}

		alertUtils.message(redirectAttributes, AlertUtils.ALERT_TYPE_SUCCESS, "Salvataggio e Notifica effettuati con successo.", false);
		
		return "redirect:/private/provvedimenti/ricerca";//"provvedimentoDettaglio";
	}
	
	@RequestMapping(value={"/private/provvedimenti/ricerca/dettaglio/salvaeinvianotifica"}, method = RequestMethod.GET)
	public String salvaeinvianotifica(Model model,@RequestParam(required = false) String id) {		
		SalvaENotificaDto salvaenotifica = new SalvaENotificaDto();
		salvaenotifica.setIdProvvedimento(id);
		model.addAttribute("salvaenotifica", salvaenotifica);
		
		return "inviaNotifica";
	}
	
	@RequestMapping(value= {"/private/provvedimenti/ricerca/autocomplateutentemail"}, method = RequestMethod.GET)
	@ResponseBody
	public List<IndirizzoEmailDto> autocomplateutentemail(@RequestParam("query") String query){
		List<Utente> utenti = gestioneUtenteFacade.recuperaUtenti4Notifica(query);
		List<IndirizzoEmailDto> retval = new ArrayList<IndirizzoEmailDto>();
		
		retval.add(new IndirizzoEmailDto(query, "", query));
		
		for(Utente tmp: utenti){
			retval.add(new IndirizzoEmailDto(tmp.getNome(), tmp.getCognome(), tmp.getEmail()));
		}
		
		List<EmailExtra> emailExtras = gestioneUtenteFacade.recuperaEmailExtra4Notifica(query);
		for(EmailExtra tmp: emailExtras){
			retval.add(new IndirizzoEmailDto("", "", tmp.getEmailExtra()));
		}
		
		return retval;
	}
	
	// *************************** FINE SALVA E INVIA NOTIFICA *************************** //
	
	// **************** richiesta Assegnazione ******//
	
	@RequestMapping(value = "/private/provvedimenti/ricerca/dettaglio", method = RequestMethod.POST, params = "richiediAssegnazione")
	public String gestioneRichiestaAssegnazione(@RequestParam(required = false) Integer id	, Model model, @ModelAttribute("provvedimentoDettaglio") Provvedimento provvedimentoDettaglio) {
	
		Integer idOrgano = ((CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtente().getOrgano().getId();
		
		gestioneProvvedimentoFacade.inserisciRichiestaAssegnazione(id, idOrgano, provvedimentoDettaglio.getMotivazioneRichiesta());		
		
		alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Richiesta inviata con successo.", false);
		
		return dettaglio(model, id);
	}
	
	
	@RequestMapping(value = "/private/provvedimenti/confermaassegnazione", method = RequestMethod.GET)
	//@PreAuthorize("hasPermission(#idAss, 'confermaAssegnazione')")	
	public String confermaAssegnazione(@RequestParam(required = true, value = "id") Integer idAss, @RequestParam(required = true, value = "idNotifica") Integer idNotifica, Model model) {
		
		Assegnazione assegnazione = gestioneProvvedimentoFacade.recuperaAssegnazioneById(idAss);
		Notifica notifica = gestioneNotificaFacade.recuperaNotifica(idNotifica);
		
		model.addAttribute("assegnazione", assegnazione);
		model.addAttribute("notifica", notifica);
		
		return "confermaAssegnazione";
	}
	
	@RequestMapping(value = "/private/provvedimenti/confermaassegnazione", method = RequestMethod.POST, params = "accettaRichiestaAssegnazione")
	//@PreAuthorize("hasPermission(#idAss, 'confermaAssegnazione')")	
	public String confermaRichiestaAssegnazione(@RequestParam(required = true, value = "id") Integer idAss, @RequestParam(required = true, value = "idNotifica") Integer idNotifica, Model model) {
		
		Assegnazione assegnazione = gestioneProvvedimentoFacade.recuperaAssegnazioneById(idAss);
		Notifica notifica = gestioneNotificaFacade.recuperaNotifica(idNotifica);
		
		model.addAttribute("assegnazione", assegnazione);
		model.addAttribute("notifica", notifica);
		
		gestioneProvvedimentoFacade.aggiornaRichiestaAssegnazione(assegnazione, true, notifica);
		
		alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Operazione conclusa con successo.", false);
		
		return "confermaAssegnazione";
	}	
	
	@RequestMapping(value = "/private/provvedimenti/confermaassegnazione", method = RequestMethod.POST, params = "rifiutaRichiestaAssegnazione")
	//@PreAuthorize("hasPermission(#idAss, 'confermaAssegnazione')")	
	public String rifiutaRichiestaAssegnazione(@RequestParam(required = true, value = "id") Integer idAss, @RequestParam(required = true, value = "idNotifica") Integer idNotifica, Model model) {
		
		Assegnazione assegnazione = gestioneProvvedimentoFacade.recuperaAssegnazioneById(idAss);
		Notifica notifica = gestioneNotificaFacade.recuperaNotifica(idNotifica);
		
		model.addAttribute("assegnazione", assegnazione);
		model.addAttribute("notifica", notifica);
		
		gestioneProvvedimentoFacade.aggiornaRichiestaAssegnazione(assegnazione, false, notifica);
		
		alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Operazione conclusa con successo.", false);
		
		return "confermaAssegnazione";
	}	
	
	
	//***** MODIFICA PROVVEDIMENTO ******//
	@RequestMapping(value = { "/private/provvedimenti/ricerca/modifica" } , method = RequestMethod.GET)
	public String modificaProvvedimento(Model model,@RequestParam(required = false) Integer id) {
		String retVal = "ricercaProv";
		if(StringUtils.isNotEmpty(id)){
			Provvedimento provvedimentoModifica = gestioneProvvedimentoFacade.ricercaProvvedimentoById(id);
			List<Provvedimento> listProvSel = new ArrayList<Provvedimento>();
			for(ProvvedimentiParent pr : provvedimentoModifica.getProvvedimentiParent()){
				listProvSel.add(pr.getProvvedimentoCollegato());
			}
			provvedimentoModifica.setProvvedimentiParentSel(listProvSel);
			model.addAttribute("listaProvvedimenti", gestioneProvvedimentoFacade.initAllProvvedimenti(1));
			
			List<Integer> idAllegatiList = new ArrayList<Integer>();
			for( Allegato tmp : provvedimentoModifica.getAllegatiList() ){
				idAllegatiList.add(tmp.getId());
			}
			provvedimentoModifica.setIdAllegatiUpdList( idAllegatiList );
			
			model.addAttribute("provvedimentoModifica", provvedimentoModifica);
			caricaTabelleInferiore(model, provvedimentoModifica);
			retVal = "provvedimentoModifica";
		}
		return retVal;
	}

	private void caricaTabelleInferiore(Model model, Provvedimento provvedimentoModifica) {
		model.addAttribute("listaProvvedimentiPregressi", provvedimentoModifica.getProvvedimentiParent());
		
		List<Allegato> listaAllegati = provvedimentoModifica.getAllegatiList();
		model.addAttribute("listaAllegati", listaAllegati);
		List<Assegnazione> listaAssegnazione = provvedimentoModifica.getAssegnazioneList();
		model.addAttribute("listaAssegnazione", listaAssegnazione);
		Assegnazione assegnazioneNew = new Assegnazione();
		assegnazioneNew.setProvvedimento(provvedimentoModifica);
		model.addAttribute("assegnatarioNew", assegnazioneNew);
	}
	
	
	//SALVA MODIFICA PROVVEDIMENTO
	
	// **************** annulla button -> indietro dettaglio provvedimento ******//
	@RequestMapping(value = "/private/provvedimenti/ricerca/modifica", method = RequestMethod.POST, params="annullaIndietroDettaglio" )
	public String gestioneAnnullaModificaProvv(Model model,@ModelAttribute("provvedimentoModifica") Provvedimento provvedimento) {
		
		return "redirect:/private/provvedimenti/ricerca/dettaglio?id="+provvedimento.getId();
	}
	
	@RequestMapping(value = { "/private/provvedimenti/ricerca/modifica" } , method = RequestMethod.POST)
	public String salvaModificaProvvedimento(Model model,@ModelAttribute("provvedimentoModifica") Provvedimento provvedimento,
			BindingResult errors, RedirectAttributes redirectAttributes
			) {
		
		provValidator.validate(provvedimento, errors);
		if( !errors.hasErrors() ){
			Provvedimento provvAggiornato = gestioneProvvedimentoFacade.aggiornaProvvedimento(provvedimento);

			ProvvedimentiUtil.gestioneSalvaAllegati(provvedimento, provvAggiornato, gestioneProvvedimentoFacade);
			 
			model.addAttribute("provvedimentoDettaglio", provvAggiornato);
			caricaTabelleInferiore(model,provvAggiornato);
			alertUtils.message(redirectAttributes, AlertUtils.ALERT_TYPE_SUCCESS, "Aggiornamento Provvedimento effettuato con successo", false);
			return "redirect:/private/provvedimenti/ricerca/dettaglio?id="+provvedimento.getId();
		} else {
			for (FieldError f : errors.getFieldErrors()) {
				alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
			}
			Provvedimento provvedimentoModifica = gestioneProvvedimentoFacade.ricercaProvvedimentoById(provvedimento.getId());
			List<Provvedimento> listProvSel = new ArrayList<Provvedimento>();
			for(ProvvedimentiParent pr : provvedimentoModifica.getProvvedimentiParent()){
				listProvSel.add(pr.getProvvedimentoCollegato());
			}
			provvedimentoModifica.setProvvedimentiParentSel(listProvSel);
			model.addAttribute("listaProvvedimenti", gestioneProvvedimentoFacade.initAllProvvedimenti(1));
			
			List<Integer> idAllegatiList = new ArrayList<Integer>();
			for( Allegato tmp : provvedimentoModifica.getAllegatiList() ){
				idAllegatiList.add(tmp.getId());
			}
			provvedimentoModifica.setIdAllegatiUpdList( idAllegatiList );
			
			model.addAttribute("provvedimentoModifica", provvedimento);
			caricaTabelleInferiore(model, provvedimento);
			
//			Provvedimento provvedimentoModifica = gestioneProvvedimentoFacade.ricercaProvvedimentoById(provvedimento.getId());			
//			model.addAttribute("listaProvvedimenti", provvedimentoModifica.getProvvedimentiParent());
//			
//			List<Integer> idAllegatiList = new ArrayList<Integer>();
//			for( Allegato tmp : provvedimentoModifica.getAllegatiList() ){
//				idAllegatiList.add(tmp.getId());
//			}
//			provvedimentoModifica.setIdAllegatiUpdList( idAllegatiList );
//			
//			model.addAttribute("provvedimentoModifica", provvedimento);
//			caricaTabelleInferiore(model, provvedimentoModifica);
		}
		return "provvedimentoModifica";
	}
	
	@RequestMapping(value={"/private/provvedimenti/ricerca/downloadAllegato"}, method = RequestMethod.GET)
	public String downloadAllegato(Model model, @RequestParam(required = false) String id, HttpServletResponse response) {
		if(StringUtils.isNotEmpty(id)){
			Allegato doc = gestioneProvvedimentoFacade.getAllegatoById(Integer.parseInt(id));
			try {
				response.setHeader("Content-Disposition", "attachment;filename=\""
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
		}
		return null;
	}
	

	@RequestMapping(value={"/private/provvedimenti/ricerca/modifica/inserisciAllegato", "/private/provvedimenti/ricerca/noteAllegatiProv/inserisciAllegato"}, method = RequestMethod.POST
			, produces = MediaType.TEXT_PLAIN_VALUE)//APPLICATION_JSON_VALUE)
	@ResponseBody
	public String inserisciAllegato(MultipartHttpServletRequest request) {
		String retval = null;
		try {
			Iterator<String> itr = request.getFileNames();
			MultipartFile file = request.getFile(itr.next());
			String desc = ((String[]) request.getParameterMap().get("descrizioneAllegato"))[0];
			//String idProvvedimento = ((String[]) request.getParameterMap().get("idProvvedimento"))[0];
			//Provvedimento provv = gestioneProvvedimentoFacade.ricercaProvvedimentoById(Integer.parseInt(idProvvedimento));
			Allegato allegato = new Allegato();
			if (file.getBytes().length > 0) {
				allegato.setNomefile(file.getOriginalFilename());
				allegato.setDescrizione(StringUtils.isEmpty(desc)?file.getOriginalFilename():desc);
				allegato.setContenuto(Hibernate.createBlob(file.getBytes()));
				//allegato.setProvvedimento(provv);
				allegato.setDimensione((int)file.getSize());
			}
			Allegato retAllegato = gestioneProvvedimentoFacade.inserisciAllegato(allegato);
			ProvvedimentiUtil.addRowTableAllegatiAjax(retAllegato);
			AllegatoDto dto = new AllegatoDto(retAllegato.getId(), retAllegato.getNomefile(), StringUtils.convertBytesToKb(retAllegato.getDimensione(),true), retAllegato.getDescrizione());
			
			String encoded = null;
			try {
				ObjectMapper mapper = new ObjectMapper();
				encoded = mapper.writeValueAsString(dto);
			} catch (JsonProcessingException e) {
				encoded = null;
			}
			
			retval = encoded;
			
		} catch (Exception e) {
			logger.error("Errore call Ajax inserimento del file allegato");
		}
		return retval;
	}
	
	@RequestMapping(value={"/private/provvedimenti/ricerca/modifica/deleteAllegato/{idAllegato}", "/private/provvedimenti/ricerca/noteAllegatiProv/deleteAllegato/{idAllegato}"}, method = RequestMethod.GET)
	@ResponseBody
	public String deleteAllegato(@PathVariable("idAllegato") Integer idAllegato) {
		gestioneProvvedimentoFacade.eliminaAllegato(idAllegato);
		return null;
	}
	
	@RequestMapping(value={"/private/provvedimenti/ricerca/deleteAssegnatario/{idAllegato}"}, method = RequestMethod.GET)
	@ResponseBody
	public AssegnazioneDto deleteAssegnatario(@PathVariable("idAllegato") Integer id) {
		Assegnazione asseDeleted = gestioneProvvedimentoFacade.eliminaAssegnatario(id);
		return new AssegnazioneDto(asseDeleted.getOrgano().getId());
	}
	
	@RequestMapping(value={"/private/provvedimenti/ricerca/inserisciAssegnatario"}, method = RequestMethod.GET)
	@ResponseBody
	public String inserisciAssegnatario(@RequestParam("provvedimento.id") String idProvvedimento,@RequestParam("organo") String idOrgano ) {
		Integer idProvv = Integer.parseInt(idProvvedimento);
		Integer idOrg = Integer.parseInt(idOrgano);
		AssegnazioneDto assRic = new AssegnazioneDto();
		assRic.setIdOrgano(idOrg);
		assRic.setIdProvvedimento(idProvv);
		
		if(StringUtils.isEmpty(gestioneProvvedimentoFacade.recuperaAssegnazioneByProvvOrgano(assRic))){
			Assegnazione assegnazione = gestioneProvvedimentoFacade.inserisciAssegnazione(idProvv,idOrg);
			return ProvvedimentiUtil.addRowTableAssegnatariAjax(assegnazione,false);
		}
		return null;
	}
	
	/* GESTIONE INSERIMENTO PROVVEDIMENTO */
	
	@RequestMapping(value = { "/private/provvedimenti/ricerca/nuovo" } , method = RequestMethod.GET)
	public String apriNuovoProvvedimento(Model model,@RequestParam(value="currentStep", required=false) String idStep,@RequestParam(value="stepSuccessivo", required=false) String stepSuccessivo, @ModelAttribute("provvedimentoInserisci") InserisciProvvedimentoDto provvedimento,
			@RequestParam(required = false) String action,
			BindingResult errors,SessionStatus status){
		//pulisciInserimento(model,status);
		gestioneInserimento(model,idStep,stepSuccessivo,provvedimento,action);	
		status.setComplete();
		return "provvedimentoInserisci";
	}
	
	@RequestMapping(value = { "/private/provvedimenti/ricerca/nuovo" } , method = RequestMethod.POST, params="indietroPagina")
	public String gestioneIndietroDaNuovoProvvedimento(Model model){

		return "redirect:/private/provvedimenti/ricerca";
	}
	
	@RequestMapping(value = { "/private/provvedimenti/ricerca/nuovo" } , method = RequestMethod.POST)
	public String apriNuovoProvvedinto(Model model,@RequestParam(value="currentStep", required=false) String idStep,@RequestParam(value="stepSuccessivo", required=false) String stepSuccessivo, @ModelAttribute("provvedimentoInserisci") InserisciProvvedimentoDto provvedimento,
			@RequestParam(required = false) String[] provvedimentiSelected,@RequestParam(required = false) String _provvedimentiSelected,@RequestParam(required = false) String action,
			RedirectAttributes redirectAttributes,BindingResult errors,SessionStatus status){
		provValidator.validate(provvedimento, errors);
		if( !errors.hasErrors() ){
			gestioneInserimento(model,idStep,stepSuccessivo,provvedimento,action);
			gestioneTabelleAllAss(model,provvedimento);
		} else {
			for (FieldError f : errors.getFieldErrors()) {
				alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, f);
			}
			model.addAttribute("currentStep", provvedimento.getCurrentStep());
			model.addAttribute("stepSuccessivo", provvedimento.getStepSuccessivo());
			gestioneTabelleAllAss(model,provvedimento);
			return "provvedimentoInserisci";
		}
		if(action.equals(Constants.SALVA)){
			pulisciInserimento(model,status);
			alertUtils.message(redirectAttributes, AlertUtils.ALERT_TYPE_SUCCESS, "Inserimento Provvedimento effettuato con successo", false);
			return "redirect:/private/provvedimenti/ricerca";
		}
		return "provvedimentoInserisci";
	}
	
	private void gestioneTabelleAllAss(Model model,InserisciProvvedimentoDto provvedimento){
		List<Assegnazione> listaAssegnazione = new ArrayList<Assegnazione>();
		if(provvedimento.getIdAssegnatariUpdList().size()>0){
			listaAssegnazione = gestioneProvvedimentoFacade.getListaAssegnazioneInserimento(provvedimento.getIdAssegnatariUpdList());
		}
		model.addAttribute("listaAssegnazione", listaAssegnazione);
		
		List<Allegato> listaAllegati = new ArrayList<Allegato>();
		if(provvedimento.getIdAllegatiUpdList().size()>0){
			listaAllegati = gestioneProvvedimentoFacade.getListaAllegatiInserimento(provvedimento.getIdAllegatiUpdList());
		}
		model.addAttribute("listaAllegati", listaAllegati);
	}
	
	private void gestioneInserimento(Model model, String idStep,String stepSuccessivo,InserisciProvvedimentoDto provvedimento, String action){
		CustomUser principal = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(StringUtils.isEmpty(action)){
			action = "";
		}
		if(StringUtils.isEmpty(provvedimento.getCurrentStep())){
			provvedimento.setCurrentStep("1");
			provvedimento.setStepSuccessivo("2");
		}
		if(action.equals(Constants.AVANTI)){
			provvedimento.setCurrentStep(getNextStep(idStep,false));
			provvedimento.setStepSuccessivo(getNextStep(idStep,true));
		}
		if(action.equals(Constants.INDIETRO)){
			provvedimento.setCurrentStep(getPrevStep(idStep));
			provvedimento.setStepSuccessivo(idStep);
		}
		if(action.equals(Constants.SALVA)){
			gestioneProvvedimentoFacade.inserisciProvvedimento(provvedimento);
			return;
		}
		if(provvedimento.getCurrentStep().equals("1")){
			model.addAttribute("titolo", "Inserimento Provvedimento");
		}
		if(provvedimento.getCurrentStep().equals("2")){
			model.addAttribute("titolo", "Nomina capofila provvedimento");
			if(provvedimento.getOrganoCapofila()==null){
				provvedimento.setOrganoCapofila(principal.getUtente().getOrgano());
			}
		}
		if(provvedimento.getCurrentStep().equals("3")){
			model.addAttribute("titolo", "Assegnatari");
			List<Organo> listaOrganiAssegnatari = initOrgani();
			if(listaOrganiAssegnatari.contains(principal.getUtente().getOrgano()))
				listaOrganiAssegnatari.remove(principal.getUtente().getOrgano());
			if(listaOrganiAssegnatari.contains(provvedimento.getOrganoCapofila()))
				listaOrganiAssegnatari.remove(provvedimento.getOrganoCapofila());
			model.addAttribute("listaOrganiAssegnatari",listaOrganiAssegnatari);
			model.addAttribute("listaAssegnazione", provvedimento.getListaAssegnazione());
			model.addAttribute("assegnatarioNew", new Assegnazione());
		}
		if(provvedimento.getCurrentStep().equals("4") && !action.equals(Constants.SALVA)){
			model.addAttribute("titolo", "Associa provvedimenti pregressi");
			model.addAttribute("stepSuccessivo", "salvataggio");
			provvedimento.setListaProvvedimenti(gestioneProvvedimentoFacade.initAllProvvedimenti(1));
			model.addAttribute("listaProvvedimenti", provvedimento.getListaProvvedimenti());
		}
		model.addAttribute("currentStep", provvedimento.getCurrentStep());
		model.addAttribute("stepSuccessivo", provvedimento.getStepSuccessivo());
	}
	
	private String getNextStep(String step,boolean plus){
		Integer idStep = null;
		if(plus){
			idStep = Integer.valueOf(step)+2;
		} else {
			idStep = Integer.valueOf(step)+1;	
		}
		
		return idStep.toString();
	}
	private String getPrevStep(String step){
		Integer idStep = Integer.valueOf(step)-1;
		return idStep.toString();
	}

	@RequestMapping(value={"/private/provvedimenti/ricerca/addAssegnatario"}, method = RequestMethod.GET)
	@ResponseBody
	public AssegnazioneDto inserisciAssegnatario(@RequestParam(required = false) String assegnatario) {
		Integer idOrg = Integer.parseInt(assegnatario);
		
		Assegnazione assegnazione = gestioneProvvedimentoFacade.inserisciAssegnazione(idOrg);
		return new AssegnazioneDto(assegnazione.getId(),assegnazione.getOrgano().getDenominazione(),assegnazione.getOrgano().getId());
	}
	
	private void pulisciInserimento(Model model,SessionStatus status)  {
		status.setComplete();
		model.addAttribute("provvedimentoInserisci", new InserisciProvvedimentoDto());
	}
	
	/* FINE GESTIONE INSERIMENTO PROVVEDIMENTO */
	
	@RequestMapping(value={"/private/provvedimenti/ricerca/dettaglio/modalCronologia"}, method = RequestMethod.GET)
	public String getCronologiaAssegnatario(Model model,@RequestParam(required = false) String id) {		
		Integer idAss = Integer.parseInt(id);
		List<Storico> listaStorico = gestioneProvvedimentoFacade.getCronologiaAssegnatario(idAss);
		model.addAttribute("listaStorico", listaStorico);
		return "cronologiaModifiche";
	}
	
	@RequestMapping(value={"/private/provvedimenti/motivazionerifiuto"}, method = RequestMethod.GET)
	public String getCronologiaAssegnatario(@RequestParam(required = true, value = "id") Integer idAssegnazione, Model model) {		
		Assegnazione assegnazione = gestioneProvvedimentoFacade.recuperaAssegnazioneById(idAssegnazione);
		model.addAttribute("assegnazione", assegnazione);
		return "motivazioneRifiuto";
	}
	
	// ESPORTAZIONE IN EXCEL
	@RequestMapping(value = "/private/provvedimenti/ricerca", method = RequestMethod.POST, params = "exportXls")
	public String esportaProvvedimentiXls(@ModelAttribute("ricercaProvvedimenti") RicercaProvvedimentoDto cercaProvvedimento, HttpServletResponse response){
	
		List<ProvvedimentoStampaDto> provvedimenti = gestioneProvvedimentoFacade.recuperaProvvedimentiPerExport(cercaProvvedimento);
		
		ByteArrayOutputStream baos = null;
		try {
			baos = reportService.generaReport(Constants.TIPO_XLS, "ExportXls", null, provvedimenti);
			
		} catch (Exception e) {
			logger.error("Errore nella generazione del report delle autorizzazioni uffici registri " + ExceptionUtils.getStackTrace(e));
		}
		if (baos != null) {
			response.reset();
			response.setContentType("application/force-download");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Disposition","attachment; filename=\"Provvedimenti_"+ DateFormatUtils.format(new Date(), "yyyyMMdd") + ".xls\"");
			
			try {
				response.getOutputStream().write(baos.toByteArray());
				response.setContentLength(baos.size());
				
				response.flushBuffer();
				} catch (IOException ioe) {
					logger.error("Errore nel download del report: " + ExceptionUtils.getStackTrace(ioe));
				}
			} else {
				response.setStatus(500);
			}
		
		return null;
	}	
	// FINE ESPORTAZIONE IN EXCEL	
	
	
	@ModelAttribute("provvedimentoInserisci")
	private InserisciProvvedimentoDto initDtoInserisciProv(){
		return new InserisciProvvedimentoDto();
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
	
	@ModelAttribute("listaTipoAtto")
	private List<TipoAtto> initTipoAtto() {
		return gestioneProvvedimentoFacade.initTipoAtto();
	}
	
	@ModelAttribute("listaOrgani")
	private List<Organo> initOrgani() {
		return gestioneProvvedimentoFacade.initOrgani();
	}
	
	@ModelAttribute("listaProponente")
	private List<Organo> initProponenteInserimento() {
		return gestioneProvvedimentoFacade.initProponente();
	}

	@ModelAttribute("listaOrganoCapofila")
	private List<Organo> initOrganoCapofila() {
		return gestioneProvvedimentoFacade.initOrgani();
	}
}
