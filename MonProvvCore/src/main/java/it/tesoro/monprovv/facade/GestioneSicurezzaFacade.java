package it.tesoro.monprovv.facade;

import it.tesoro.monprovv.cache.CacheService;
import it.tesoro.monprovv.dao.AssegnazioneDAO;
import it.tesoro.monprovv.dao.CondizioneDAO;
import it.tesoro.monprovv.dao.FunzioneDAO;
import it.tesoro.monprovv.dao.MenuDAO;
import it.tesoro.monprovv.dao.NotificaDAO;
import it.tesoro.monprovv.dao.ProvvedimentoDAO;
import it.tesoro.monprovv.dao.RuoloDAO;
import it.tesoro.monprovv.dao.RuoloFunzioneDAO;
import it.tesoro.monprovv.dao.RuoloUtenteDAO;
import it.tesoro.monprovv.dao.UtenteAstageDAO;
import it.tesoro.monprovv.dao.UtenteDAO;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Condizione;
import it.tesoro.monprovv.model.Funzione;
import it.tesoro.monprovv.model.Menu;
import it.tesoro.monprovv.model.Notifica;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Ruolo;
import it.tesoro.monprovv.model.RuoloFunzione;
import it.tesoro.monprovv.model.Utente;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("gestioneSicurezzaFacade")
public class GestioneSicurezzaFacade {
	
	@Value("#{config['oam.abilitato']}")
    private String oamAbilitato;
	
	@Value("#{config['oam.logoutUrl']}")
	private String oamLogoutUrl;

	@Autowired 
	private RuoloUtenteDAO ruoloUtenteDAO;
	
	@Autowired 
	private UtenteAstageDAO utenteAstageDAO;
	
	@Autowired 
	private UtenteDAO utenteDAO;
	
	@Autowired
	private RuoloFunzioneDAO ruoloFunzioneDAO;
	
	@Autowired
	private MenuDAO menuDAO;
	
	@Autowired 
	private RuoloDAO ruoloDAO;
	
	@Autowired 
	private FunzioneDAO funzioneDAO;
	
	@Autowired 
	private CondizioneDAO condizioneDAO;
	
	@Autowired 
	private AssegnazioneDAO assegnazioneDAO;
	
	@Autowired 
	private ProvvedimentoDAO provvedimentoDAO;

	@Autowired 
	private NotificaDAO notificaDAO;	
	
	@Autowired 
	private CacheService cacheService;
	
	private ObjectMapper mapper;
	
	public static class Headers {
		private String SSO_CF;
		private String SSO_USERNAME;
		private String SSO_NOME;
		private String SSO_COGNOME;
		private String SSO_EMAIL;
		
		public String getSSO_CF() {
			return SSO_CF;
		}
		public void setSSO_CF(String sSO_CF) {
			SSO_CF = sSO_CF;
		}
		
		public String getSSO_USERNAME() {
			return SSO_USERNAME;
		}
		public void setSSO_USERNAME(String sSO_USERNAME) {
			SSO_USERNAME = sSO_USERNAME;
		}
		public String getSSO_NOME() {
			return SSO_NOME;
		}
		public void setSSO_NOME(String sSO_NOME) {
			SSO_NOME = sSO_NOME;
		}
		public String getSSO_COGNOME() {
			return SSO_COGNOME;
		}
		public void setSSO_COGNOME(String sSO_COGNOME) {
			SSO_COGNOME = sSO_COGNOME;
		}
		public String getSSO_EMAIL() {
			return SSO_EMAIL;
		}
		public void setSSO_EMAIL(String sSO_EMAIL) {
			SSO_EMAIL = sSO_EMAIL;
		}
		
		
	}
	
	public Utente recuperaUtentePerCF(String codiceFiscale) {
		
		return utenteDAO.findAttivoByCodiceFiscale(codiceFiscale);
	}
	
	
	public List<RuoloFunzione> recuperaRuoliFunzione() {
		List<String> order = new ArrayList<String>();
		order.add("id asc");
		return ruoloFunzioneDAO.findAll(order);
	}

	public List<Condizione> recuperaCondizione() {
		return condizioneDAO.findAll();
	}	
	
	public String encodeJSON(HttpServletRequest request) {
		
		if (mapper == null)
			mapper = new ObjectMapper();
		
		Headers headers = new Headers();
		if ("N".equalsIgnoreCase(oamAbilitato)) {
			
			if (StringUtils.isBlank(request.getParameter("oamcf")))
				return null;
			
			headers.setSSO_CF(request.getParameter("oamcf"));
			headers.setSSO_USERNAME(request.getParameter("oamusername"));
		} else {
			if (StringUtils.isBlank(request.getHeader("SSO_CF")))
				return null;
			
			headers.setSSO_CF(request.getHeader("SSO_CF"));
			headers.setSSO_USERNAME(request.getHeader("SSO_USERNAME"));			
			headers.setSSO_NOME(request.getHeader("SSO_NOME"));
			headers.setSSO_COGNOME(request.getHeader("SSO_COGNOME"));
			headers.setSSO_EMAIL(request.getHeader("SSO_EMAIL"));			
			
		}
		
		String encoded = "";
		try {
			encoded = mapper.writeValueAsString(headers);
		} catch (JsonProcessingException e) {
			return null;
		}
		
		return encoded;
	}

	public Headers decodeJSON(String json) {
		
		if (mapper == null)
			mapper = new ObjectMapper();
		
		try {
			return mapper.readValue(json, Headers.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> getFirstLevelMenu() {
		
		List<Menu> firstLevelMenu = (List<Menu>)cacheService.getFromCache(CacheService.KEY_FIRST_LEVEL_MENU);
		if (firstLevelMenu == null) {
			firstLevelMenu =  menuDAO.findFirstLevelOrdered();
			cacheService.addToCache(CacheService.KEY_FIRST_LEVEL_MENU, firstLevelMenu);
		}
	
		return firstLevelMenu;
	}
	
	@SuppressWarnings("unchecked")
	public List<Funzione> getFunzioni() {
		List<Funzione> funzioni = (List<Funzione>)cacheService.getFromCache(CacheService.KEY_FUNZIONI);
		if (funzioni == null) {
			funzioni = funzioneDAO.findAll();
			cacheService.addToCache(CacheService.KEY_FUNZIONI, funzioni);
		}
		
		return funzioni;
	}
	
	
	public List<Ruolo> recuperaRuoli() {
		return ruoloDAO.findAll();
	}
	
	public Ruolo recuperaRuolo(String codice) {
		return ruoloDAO.findByCodiceRuolo(codice);
	}
	
	public Provvedimento recuperaProvvedimento(String id) {
		return provvedimentoDAO.findById(Integer.parseInt(id));
	}
	
	public Assegnazione recuperaAssegnazione(String id) {
		return assegnazioneDAO.findById(Integer.parseInt(id));
	}

	public Notifica recuperaNotifica(String id) {
		return notificaDAO.findById(Integer.parseInt(id));
	}

	
	public boolean checkLogout(String path) {
		if ("S".equals(oamAbilitato) && path.equals(oamLogoutUrl) ) {
			return true;
		}
		return false;
	}
	
}
