package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.cache.CacheService;
import it.tesoro.monprovv.facade.GestioneSicurezzaFacade;
import it.tesoro.monprovv.model.Ruolo;
import it.tesoro.monprovv.sicurezza.CustomPermissionEvaluator;
import it.tesoro.monprovv.sicurezza.CustomRequestHeaderAuthenticationFilter;
import it.tesoro.monprovv.sicurezza.CustomUser;
import it.tesoro.monprovv.web.utils.AlertUtils;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GestioneSicurezzaController {
	
	protected static Logger logger = Logger.getLogger(GestioneSicurezzaController.class);
	
	
	@Value("#{config['oam.abilitato']}")
	private String oamAbilitato;
	
	@Value("#{config['oam.logoutUrl']}")
	private String oamLogoutUrl;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private AlertUtils alertUtils;
	
	@Autowired 
	private GestioneSicurezzaFacade gestioneSicurezzaFacade;
	
	@Autowired
	@Qualifier("permissionEvaluator")
	private CustomPermissionEvaluator permissioneEvaluator;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	@Qualifier("oamFilter")
	private CustomRequestHeaderAuthenticationFilter oamFilter;
		
	@Autowired
	@Qualifier("config")
	private Properties config;
	

	@PostConstruct
	private void init() {
	}
	
	@RequestMapping(value="/public/logout")
	public String logout(Model model)  {
		alertUtils.message(model, AlertUtils.ALERT_TYPE_INFO, "Logout effettuato", false);
		return "logout";
	}

	@RequestMapping(value="/public/403")
	public String accessoNegato(Model model, HttpServletRequest request)  {
		
		Object securityException = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		
		if (securityException instanceof UsernameNotFoundException) {
			
			String message = "Errore in fase di login: " + ((UsernameNotFoundException)securityException).getMessage();
			
			alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, message, false);
			return "403";	
		} else {
			alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, "403.messaggio", true);
			return "403";
		}
	}
	
	@RequestMapping(value="/public/404")
	public String paginaNonTrovata(Model model)  {
		alertUtils.message(model, AlertUtils.ALERT_TYPE_WARNING, "404.messaggio", true);
	    return "404";
	}

	@RequestMapping(value="/public/500")
	public String errore500(Model model)  {
		alertUtils.message(model, AlertUtils.ALERT_TYPE_WARNING, "500.messaggio", true);
	    return "500";
	}
	
	@RequestMapping(value="/public/timeout")
	public String timeoutSessione(Model model)  {
		alertUtils.message(model, AlertUtils.ALERT_TYPE_WARNING, "timeout.messaggio", true);
		return "timeout";
	}

	
	@RequestMapping(value="/public/refresh", method = RequestMethod.GET)
	public String refreshSingleton(Model model)  {
		permissioneEvaluator.refreshAlberoPermessi();
		cacheService.invalidateCache();
		
		alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Refresh effettuato con successo", false);
		
		return "refresh";
	}
	

	@RequestMapping(value= "/", method = RequestMethod.GET)
	public String caricaHomepage(Model model)  {
	
		String redirectUrl = null;
		
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomUser) {
			redirectUrl = "/private";
		} else {
			redirectUrl = "/public";
		}
		
		return "redirect:" + redirectUrl;
	}	
	
	@RequestMapping(value= {"/private/home", "/private"}, method = RequestMethod.GET)
	public String caricaHomepagePrivata(Model model, SecurityContextHolderAwareRequestWrapper request)  {

		
		if (   request.isUserInRole(Ruolo.ROLE_CONSULTATORE) 
			|| request.isUserInRole(Ruolo.ROLE_LETTORE)
			|| request.isUserInRole(Ruolo.ROLE_INSERITORE) ) {
			return "redirect:/private/provvedimenti/ricerca";
		}
		
		if ( request.isUserInRole(Ruolo.ROLE_ADMIN) ) {
			return "redirect:/private/admin/enti";
		}
		
		return "home";
	}
	
	@RequestMapping(value= {"/public/home", "/public"}, method = RequestMethod.GET)
	public String caricaHomepagePubblica(Model model)  {
		
		return "redirect:/private";
	}
	
	
//	@RequestMapping(value="/private/sceltaRuolo", method = RequestMethod.GET)
//	public String sceltaRuolo(Model model)  {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
//		PdaUser user = (PdaUser)auth.getPrincipal();
//		
//		if (user.getRuoloAttivo() != null) {
//			return "redirect:/private/home";
//		}
//		
//		
//		model.addAttribute("ruoliDaScegliere", user.getRuoliDaScegliere());
//		model.addAttribute("ruoloScelto", new Ruolo());
//		
//		return "sceltaRuolo";
//	}
//	
//	@RequestMapping(value="/private/sceltaRuolo", method = RequestMethod.POST)
//	public String assegnaRuolo(@ModelAttribute("ruoloScelto") Ruolo ruoloScelto, BindingResult result, Model model)  {
//		
//		if (ruoloScelto.getCodice() == null) {
//			result.rejectValue("codice", "sceltaRuolo.erroreScelta");
//			
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
//			PdaUser user = (PdaUser)auth.getPrincipal();
//			
//			model.addAttribute("ruoliDaScegliere", user.getRuoliDaScegliere());
//			return "sceltaRuolo";
//		}
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
//		PdaAuthentication pdaAuth = new PdaAuthentication(auth);
//		pdaAuth.addAuthority(new SimpleGrantedAuthority(ruoloScelto.getCodice()));
//		
//		SecurityContextHolder.getContext().setAuthentication(pdaAuth);
//	     
//		PdaUser user = (PdaUser)auth.getPrincipal();
//		int numNuoveNotifiche=0;
//		List <UtenteRuoloNotifica> notificheUtente=gestioneSicurezzaFacade.findNotificheUtente(ruoloScelto.getCodice(),user.getIdUtente());
//			for (UtenteRuoloNotifica notifica:notificheUtente){
//				user.getNotificheUtente().add(notifica);
//				if (!notifica.isNotificaLetta()){
//					numNuoveNotifiche++;
//				}
//		}
//		user.setNumNuoveNotifiche(numNuoveNotifiche);
//
//		String targetURL = user.getTargetURL();
//		user.setTargetURL(null);
//
//		// assegno il ruolo attivo
//		user.setRuoloAttivo(gestioneSicurezzaFacade.recuperaRuolo(ruoloScelto.getCodice()));
//		
//	    return "redirect:" + targetURL; 
//	}
	

}
