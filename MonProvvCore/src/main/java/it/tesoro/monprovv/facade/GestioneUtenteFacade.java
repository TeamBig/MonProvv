package it.tesoro.monprovv.facade;



import it.tesoro.monprovv.dao.EmailExtraDAO;
import it.tesoro.monprovv.dao.OrganoDAO;
import it.tesoro.monprovv.dao.RuoloDAO;
import it.tesoro.monprovv.dao.RuoloUtenteDAO;
import it.tesoro.monprovv.dao.UtenteAstageDAO;
import it.tesoro.monprovv.dao.UtenteDAO;
import it.tesoro.monprovv.dto.IdDescrizioneDto;
import it.tesoro.monprovv.dto.UtenteDto;
import it.tesoro.monprovv.model.EmailExtra;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.Ruolo;
import it.tesoro.monprovv.model.RuoloUtente;
import it.tesoro.monprovv.model.Utente;
import it.tesoro.monprovv.model.UtenteAstage;
import it.tesoro.monprovv.utils.SearchPatternUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("gestioneUtenteFacade")
public class GestioneUtenteFacade {

	@Autowired
	private UtenteDAO utenteDAO;
	
	@Autowired
	private OrganoDAO organoDAO;
	
	@Autowired
	private UtenteAstageDAO utenteAstageDAO;
	
	@Autowired 
	private EmailExtraDAO emailExtraDAO;
	
	@Autowired 
	private RuoloDAO ruoloDAO;
	
	@Autowired 
	private RuoloUtenteDAO ruoloUtenteDAO;
	
	public Utente recuperaUtenteById(Integer id) {
		Utente utente = null;
		if(id!=null){
			utente = utenteDAO.findById(id);
			recuperaRuoli(utente);
			if("E".equals(utente.getFlagIntEst())){
				utente.setOrganoUteEsterno( utente.getOrgano().getId() );
			}
		}
		return utente;
	}

	private void recuperaRuoli(Utente utente) {
		List<RuoloUtente> ru = ruoloUtenteDAO.findByProperty("utente.id", utente.getId());
		for(RuoloUtente tmp: ru){
			if( tmp.getRuolo().getCodice().equals( Ruolo.ROLE_ADMIN ) ){
				utente.setAmministratore(true);
			}else if( ! tmp.getRuolo().getCodice().equals( Ruolo.ROLE_USER ) ){
				utente.setRuolo(tmp.getRuolo());
			}
		}
	}

//	public List<Utente> recupera(int page) {
//		List<String> order = new ArrayList<String>();
//		order.add("cognome");
//		order.add("nome");
//		return utenteDAO.findAll(page, order);
//	}

	public List<Utente> recupera(int page, UtenteDto criteria) {
		List<String> order = new ArrayList<String>();
		order.add("cognome");
		order.add("nome");
		List<SearchPatternUtil> searchPatternObjects = popolaCriteria(criteria);
		return utenteDAO.findByPattern(searchPatternObjects, page, order);
	}
	
	public List<Utente> recuperaUtenti4Notifica(String param) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("param", "%"+param.toUpperCase()+"%");
		String hql = "from Utente u where upper("
				+ "concat(u.cognome , ' ' , u.nome , ' ' , u.cognome , ' ' , u.email)"
				+ ") like :param and u.flagAttivo = 'S' "
				+ "order by cognome, nome asc";
		List<Utente> utenti = utenteDAO.findByHqlQueryNumeroRecord(hql, params, 10);
		
		return utenti;
		
	}
	
	public List<EmailExtra> recuperaEmailExtra4Notifica(String param) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("param", "%"+param.toUpperCase()+"%");
		String hql = "from EmailExtra u where upper("
				+ "u.emailExtra"
				+ ") like :param and u.emailExtra not in (select s.email from Utente s where s.flagAttivo = 'S') "
				+ "order by emailExtra asc";
		return emailExtraDAO.findByHqlQueryNumeroRecord(hql, params, 10);
	}
	
	public int countEmailExtra(String param) {
		List<SearchPatternUtil> searchPatternObjects = new ArrayList<SearchPatternUtil>();
		SearchPatternUtil pattern = new SearchPatternUtil();
		pattern.setNomeCampo( "emailExtra" );
		pattern.setPattern(param);
		pattern.setPreponi(false);
		pattern.setPostponi(false);
		searchPatternObjects.add(pattern);
		return emailExtraDAO.countByPattern(searchPatternObjects);
	}
	
	public void inserisciEmailExtra(EmailExtra emailExtra) {
		emailExtraDAO.save(emailExtra);
	}

	private List<SearchPatternUtil> popolaCriteria(UtenteDto criteria) {
		List<SearchPatternUtil> searchPatternObjects = new ArrayList<SearchPatternUtil>();
		SearchPatternUtil pattern = null;
		if(criteria.getNome() != null){
			pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "nome" );
			pattern.setPattern(criteria.getNome());
			pattern.setPreponi(true);
			pattern.setPostponi(true);
			searchPatternObjects.add(pattern);
		}
		if(criteria.getCognome() != null){
			pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "cognome" );
			pattern.setPattern(criteria.getCognome());
			pattern.setPreponi(true);
			pattern.setPostponi(true);
			searchPatternObjects.add(pattern);
		}
		if(criteria.getCodiceFiscale() != null){
			pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "codiceFiscale" );
			pattern.setPattern(criteria.getCodiceFiscale());
			pattern.setPreponi(true);
			pattern.setPostponi(true);
			searchPatternObjects.add(pattern);
		}
		if(criteria.getEmail() != null){
			pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "email" );
			pattern.setPattern(criteria.getEmail());
			pattern.setPreponi(false);
			pattern.setPostponi(false);
			searchPatternObjects.add(pattern);
		}
		pattern = new SearchPatternUtil();
		pattern.setNomeCampo( "flagAttivo" );
		pattern.setPattern(criteria.getFlagAttivo());
		pattern.setPreponi(false);
		pattern.setPostponi(false);
		searchPatternObjects.add(pattern);
		return searchPatternObjects;
	}

//	public int count() {
//		return utenteDAO.countAll();
//	}

	public int count(UtenteDto criteria) {
		List<SearchPatternUtil> searchPatternObjects = popolaCriteria(criteria);
		return utenteDAO.countByPattern(searchPatternObjects);
	}

	public List<IdDescrizioneDto> recuperaOrganiEsterni(String denominazione) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("denominazione", "%"+denominazione.toUpperCase()+"%");
		String hql = "from Organo u where upper(u.denominazione) like :denominazione and u.unitaOrgAstage is null and u.flagAttivo = 'S' order by denominazione asc";
		List<Organo> organi = organoDAO.findByHqlQueryNumeroRecord(hql, params, 10);
		List<IdDescrizioneDto> retval = new ArrayList<IdDescrizioneDto>();
		for(Organo tmp: organi){
			retval.add( new IdDescrizioneDto( tmp.getId(), tmp.getDenominazione() ) ); 
		}
		return retval;
		
	}
	
	public List<UtenteDto> recuperaUtentiInterni(String denominazione) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("denominazione", "%"+denominazione.toUpperCase()+"%");
		String hql = "from UtenteAstage u where upper("
				+ "concat(u.cognome , ' ' , u.nome , ' ' , u.cognome , ' ' , u.codiceFiscale)"
				+ ") like :denominazione "
				+ "and u.id not in (select ute.utenteAstage.id from Utente ute where ute.utenteAstage is not null and ute.flagAttivo = 'S') "
				+ "order by cognome, nome asc";
		List<UtenteAstage> utenteAstage = utenteAstageDAO.findByHqlQueryNumeroRecord(hql, params, 10);
		
		List<UtenteDto> retval = new ArrayList<UtenteDto>();
		UtenteDto ele = null;
		List<SearchPatternUtil> parametri = new ArrayList<SearchPatternUtil>();
		parametri.add(new SearchPatternUtil("unitaOrgAstage", "is not null"));
		parametri.add(new SearchPatternUtil("flagAttivo", "= 'S'"));
		List<Organo> organi = organoDAO.findByProperty(parametri);
		
		for(UtenteAstage tmp: utenteAstage){
			ele = new UtenteDto();
			ele.setId( tmp.getId() );
			ele.setNome( tmp.getNome() );
			ele.setCognome( tmp.getCognome() );
			ele.setCodiceFiscale( tmp.getCodiceFiscale() );
			ele.setEmail( tmp.getEmail() );
			ele.setSesso( tmp.getSesso() );
			
			ele.setDataNascita( DateFormatUtils.format(tmp.getDataNascita(), "dd/MM/yyyy"));
			
			boolean flgTrovato = false;
			for (int i = 6; i > 0; i--) {
				try {
					Method method = tmp.getUo().getClass().getMethod("getOrgIdLiv" + i, new Class[] {});
					Integer orgId = (Integer)method.invoke(tmp.getUo(), new Object[] {});
					for (Organo organo : organi) {
						if (organo.getUnitaOrgAstage().getId().equals(orgId)) {
							ele.setOrgano(organo.getDenominazione());
							ele.setIdOrgano(organo.getId());
							flgTrovato = true;
							break;
						}
					}
				} catch (SecurityException e) {
				} catch (NoSuchMethodException e) {
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				} catch (InvocationTargetException e) {
				}
				if(flgTrovato){
					break;
				}
			}

			retval.add(ele);
		}
		return retval;
		
	}

	public void inserisciUtente(Utente utente) {
		if(utente.getUtenteAstage()!=null){
			UtenteAstage utenteAstage = utenteAstageDAO.findById(utente.getUtenteAstage().getId());
			utente.setUtenteAstage(utenteAstage);
		}
		utenteDAO.save(utente);
		RuoloUtente ru = new RuoloUtente();
		ru.setUtente(utente);
		ru.setRuolo(ruoloDAO.findByCodiceRuolo(Ruolo.ROLE_USER));
		ruoloUtenteDAO.save(ru);
		if(utente.isAmministratore()){
			ru = new RuoloUtente();
			ru.setUtente(utente);
			ru.setRuolo(ruoloDAO.findByCodiceRuolo(Ruolo.ROLE_ADMIN));
			ruoloUtenteDAO.save(ru);
		}
		if(utente.getRuolo()!=null){
			ru = new RuoloUtente();
			ru.setUtente(utente);
			ru.setRuolo(ruoloDAO.findByCodiceRuolo(utente.getRuolo().getCodice()));
			ruoloUtenteDAO.save(ru);
		}
	}

	public Object recuperaUtenteAstageById(int id) {
		return utenteAstageDAO.findById(id);
	}
	
	public Utente recuperaUtenteByCodiceFiscale(String codiceFiscale) {
		return utenteDAO.findAttivoByCodiceFiscale(codiceFiscale);
	}
	
	public UtenteAstage recuperaUtenteAstageByCodiceFiscale(String codiceFiscale) {
		return utenteAstageDAO.findByCodiceFiscale(codiceFiscale);
	}

	public Utente aggiornaUtente(Utente utente) {
		
		List<RuoloUtente> newRuoloUtenteList = new ArrayList<RuoloUtente>();
		RuoloUtente admin = null;
		RuoloUtente user = null;
		RuoloUtente other = null;
		for(RuoloUtente tmp: utente.getRuoloUtenteList()){
			if( tmp.getRuolo().getCodice().equals(Ruolo.ROLE_USER) ){
				user = tmp;
			}else if( tmp.getRuolo().getCodice().equals(Ruolo.ROLE_ADMIN) ){
				admin = tmp;
			}else{
				other = tmp;
			}
		}
		
		newRuoloUtenteList.add(user);
		
		if( utente.isAmministratore() && admin==null ){
			//Inserisco il profilo amministratore
			RuoloUtente ru = new RuoloUtente();
			ru.setUtente(utente);
			ru.setRuolo(ruoloDAO.findByCodiceRuolo(Ruolo.ROLE_ADMIN));
			ruoloUtenteDAO.save(ru);
			newRuoloUtenteList.add(ru);
		}else if( utente.isAmministratore() && admin!=null ){
			newRuoloUtenteList.add(admin);
		}else if( (!utente.isAmministratore()) && admin!=null ){
			ruoloUtenteDAO.delete(admin);
		}
		
		if( utente.getRuolo()!=null && other!=null && (!utente.getRuolo().equals(other.getRuolo().getCodice())) ){
			other.setRuolo(ruoloDAO.findByCodiceRuolo(utente.getRuolo().getCodice()));
			ruoloUtenteDAO.merge(other);
			newRuoloUtenteList.add(other);
		}else if( utente.getRuolo()!=null && other!=null && utente.getRuolo().equals(other.getRuolo().getCodice()) ){
			newRuoloUtenteList.add(other);
		}else if( utente.getRuolo()!=null && other==null ){
			RuoloUtente ru = new RuoloUtente();
			ru.setUtente(utente);
			ru.setRuolo(ruoloDAO.findByCodiceRuolo(utente.getRuolo().getCodice()));
			ruoloUtenteDAO.save(ru);
			newRuoloUtenteList.add(ru);
		}
		
		utente.setRuoloUtenteList(newRuoloUtenteList);

		
//		Utente utenteDB = recuperaUtenteById(utente.getId());
//		if( utenteDB.isAmministratore() && (!utente.isAmministratore()) ){
//			//Elimino il profilo amministratore
//			List<RuoloUtente> rus = ruoloUtenteDAO.findByProperty("utente.id", utente.getId());
//			for(RuoloUtente tmp: rus){
//				if( tmp.getRuolo().getCodice().equals(Ruolo.ROLE_ADMIN) )
//					ruoloUtenteDAO.delete(tmp);
//			}
//		}else if( utente.isAmministratore() && (!utenteDB.isAmministratore()) ){
//			//Inserisco il profilo amministratore
//			RuoloUtente ru = new RuoloUtente();
//			ru.setUtente(utente);
//			ru.setRuolo(ruoloDAO.findByCodiceRuolo(Ruolo.ROLE_ADMIN));
//			ruoloUtenteDAO.save(ru);
//		}
//		
//		if( utenteDB.getRuolo()!=null &&  utenteDB.getRuolo()==null ){
//			//Elimino il ruolo associato diverso da user e admin
//			List<RuoloUtente> rus = ruoloUtenteDAO.findByProperty("utente.id", utente.getId());
//			for(RuoloUtente tmp: rus){
//				if( (!tmp.getRuolo().getCodice().equals(Ruolo.ROLE_ADMIN)) && (!tmp.getRuolo().getCodice().equals(Ruolo.ROLE_USER)) )
//					ruoloUtenteDAO.delete(tmp);
//			}
//		}else if( utenteDB.getRuolo()==null &&  utenteDB.getRuolo()!=null ){
//			//Inserisco il ruolo
//			RuoloUtente ru = new RuoloUtente();
//			ru.setUtente(utente);
//			ru.setRuolo(utenteDB.getRuolo());
//			ruoloUtenteDAO.save(ru);
//		}else if( utenteDB.getRuolo()!=null &&  utenteDB.getRuolo()!=null ){
//			if(!(utenteDB.getRuolo().getCodice().equals( utente.getRuolo().getCodice()))){
//				//E' cambiato il ruolo
//				List<RuoloUtente> rus = ruoloUtenteDAO.findByProperty("utente.id", utente.getId());
//				for(RuoloUtente tmp: rus){
//					if( (!tmp.getRuolo().getCodice().equals(Ruolo.ROLE_ADMIN)) && (!tmp.getRuolo().getCodice().equals(Ruolo.ROLE_USER)) ){
//						tmp.setRuolo(utenteDB.getRuolo());
//						ruoloUtenteDAO.merge(tmp);
//					}
//				}	
//			}
//		}
		
		if( "E".equals(utente.getFlagIntEst()) && !(utente.getOrganoUteEsterno().equals( utente.getOrgano().getId() )) ){
			utente.setOrgano( organoDAO.findById( utente.getOrganoUteEsterno() ) );
		}
		
		utente = utenteDAO.merge(utente);	
		recuperaRuoli(utente);
		return utente;
	}

	public void eliminazioneLogica(Integer id) {
		if(id != null){
			Utente utente = utenteDAO.findById(id);
			utente.setFlagAttivo("N");
			utenteDAO.merge(utente);
		}		
	}
	
	public List<Ruolo> recuperaListaRuoli(){
		List<String> orderByParams = new ArrayList<String>();
		orderByParams.add("descrizione");
		return ruoloDAO.findAll(orderByParams);
	}
	
	public Ruolo recuperaRuoloById(int id){
		return ruoloDAO.findById(id);
	}
	
//	@Autowired
//	private UtenteDAO utenteDAO;
//
//	@Autowired
//	private RuoloDAO ruoloDAO;
//	
//	@Autowired
//	private StatoDAO statoDAO;
//	
//	
//	@Autowired
//	private ProvvedimentoDAO provvedimentoDAO;
//	
//	public void inserisci(Stato stato1, Stato stato2){
//		System.out.println(statoDAO.save(stato1));
//		System.out.println(statoDAO.save(stato2));
//	}
//
//
//	public void testSelect() {
//		System.out.println(" A ");
//		Provvedimento p = provvedimentoDAO.findById(1);
//		System.out.println(" B ");
//		
//		System.out.println(p.getId());
//		
//		System.out.println("<" + p.getProvvedimentiParent() + ">");
//		
//		System.out.println("<" + p.getProvvedimentiParent().size() + ">");
//		
//		
//		System.out.println(" C ");
//		
//		for(ProvvedimentiParent tmp:p.getProvvedimentiParent()){
//			//System.out.println("* " + tmp.getProvvedimentoCollegato().getTipologia() );
//			//System.out.println("* " + tmp.getIdProvvedimentoCollegato());
//		}
//		
//	}
	

}
