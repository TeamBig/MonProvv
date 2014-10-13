package it.tesoro.monprovv.facade;

import it.tesoro.monprovv.dao.FunzioneDAO;
import it.tesoro.monprovv.dao.MenuDAO;
import it.tesoro.monprovv.dao.RuoloDAO;
import it.tesoro.monprovv.dao.RuoloFunzioneDAO;
import it.tesoro.monprovv.dao.RuoloUtenteDAO;
import it.tesoro.monprovv.dao.UtenteAstageDAO;
import it.tesoro.monprovv.dao.UtenteDAO;
import it.tesoro.monprovv.model.Funzione;
import it.tesoro.monprovv.model.Menu;
import it.tesoro.monprovv.model.Ruolo;
import it.tesoro.monprovv.model.RuoloFunzione;
import it.tesoro.monprovv.model.Utente;

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
	
	private ObjectMapper mapper;
	
	private List<Menu> firstLevelMenu;
	
	private List<Funzione> funzioni;
	
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
		
		return utenteDAO.findByCodiceFiscale(codiceFiscale);
	}
	
	
	public List<RuoloFunzione> recuperaRuoliFunzione() {
		return ruoloFunzioneDAO.findAll();
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
	
	public List<Menu> getFirstLevelMenu() {
		if (firstLevelMenu == null)
			refresh();
		
		return firstLevelMenu;
	}
	
	
	
	public void refresh() {
		
		firstLevelMenu = menuDAO.findFirstLevelOrdered();
		funzioni = funzioneDAO.findAll();
	}


	public List<Funzione> getFunzioni() {
		if (funzioni == null)
			refresh();
		return funzioni;
	}
	
	
	public List<Ruolo> recuperaRuoli() {
		return ruoloDAO.findAll();
	}
	
	public Ruolo recuperaRuolo(String codice) {
		return ruoloDAO.findByCodiceRuolo(codice);
	}
}
