package it.tesoro.monprovv.facade;



import it.tesoro.monprovv.dao.OrganoDAO;
import it.tesoro.monprovv.dao.UtenteAstageDAO;
import it.tesoro.monprovv.dao.UtenteDAO;
import it.tesoro.monprovv.dto.IdDescrizioneDto;
import it.tesoro.monprovv.dto.UtenteDto;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.Utente;
import it.tesoro.monprovv.model.UtenteAstage;
import it.tesoro.monprovv.util.SearchPatternUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	public Utente recuperaUtenteById(Integer id) {
		Utente utente = null;
		if(id!=null){
			utente = utenteDAO.findById(id);
		}
		return utente;
	}

	public List<Utente> recupera(int page) {
		List<String> order = new ArrayList<String>();
		order.add("cognome");
		order.add("nome");
		return utenteDAO.findAll(page, order);
	}

	public List<Utente> recupera(int page, UtenteDto criteria) {
		List<String> order = new ArrayList<String>();
		order.add("cognome");
		order.add("nome");
		List<SearchPatternUtil> searchPatternObjects = new ArrayList<SearchPatternUtil>();
		if(criteria.getNome() != null){
			SearchPatternUtil pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "nome" );
			pattern.setPattern(criteria.getNome());
			pattern.setPreponi(true);
			pattern.setPostponi(true);
			searchPatternObjects.add(pattern);
		}
		if(criteria.getCognome() != null){
			SearchPatternUtil pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "cognome" );
			pattern.setPattern(criteria.getCognome());
			pattern.setPreponi(true);
			pattern.setPostponi(true);
			searchPatternObjects.add(pattern);
		}
		if(criteria.getCodiceFiscale() != null){
			SearchPatternUtil pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "codiceFiscale" );
			pattern.setPattern(criteria.getCodiceFiscale());
			pattern.setPreponi(true);
			pattern.setPostponi(true);
			searchPatternObjects.add(pattern);
		}
		return utenteDAO.findByPattern(searchPatternObjects, page, order);
	}

	public int count() {
		return utenteDAO.countAll();
	}

	public int count(UtenteDto criteria) {
		List<SearchPatternUtil> searchPatternObjects = new ArrayList<SearchPatternUtil>();
		if(criteria.getNome() != null){
			SearchPatternUtil pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "nome" );
			pattern.setPattern(criteria.getNome());
			pattern.setPreponi(true);
			pattern.setPostponi(true);
			searchPatternObjects.add(pattern);
		}
		if(criteria.getCognome() != null){
			SearchPatternUtil pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "cognome" );
			pattern.setPattern(criteria.getCognome());
			pattern.setPreponi(true);
			pattern.setPostponi(true);
			searchPatternObjects.add(pattern);
		}
		if(criteria.getCodiceFiscale() != null){
			SearchPatternUtil pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "codiceFiscale" );
			pattern.setPattern(criteria.getCodiceFiscale());
			pattern.setPreponi(true);
			pattern.setPostponi(true);
			searchPatternObjects.add(pattern);
		}
		return utenteDAO.countByPattern(searchPatternObjects);
	}

	public List<IdDescrizioneDto> recuperaOrganiEsterni(String denominazione) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("denominazione", "%"+denominazione.toUpperCase()+"%");
		String hql = "from Organo u where upper(u.denominazione) like :denominazione and u.unitaOrgAstage is null order by denominazione asc";
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
				+ "and u.id not in (select ute.utenteAstage.id from Utente ute where ute.utenteAstage is not null) "
				+ "order by cognome, nome asc";
		List<UtenteAstage> utenteAstage = utenteAstageDAO.findByHqlQueryNumeroRecord(hql, params, 10);
		
		List<UtenteDto> retval = new ArrayList<UtenteDto>();
		UtenteDto ele = null;
		List<SearchPatternUtil> parametri = new ArrayList<SearchPatternUtil>();
		parametri.add(new SearchPatternUtil("unitaOrgAstage", "is not null"));
		List<Organo> organi = organoDAO.findByProperty(parametri);
		
		for(UtenteAstage tmp: utenteAstage){
			ele = new UtenteDto();
			ele.setId( tmp.getId() );
			ele.setNome( tmp.getNome() );
			ele.setCognome( tmp.getCognome() );
			ele.setCodiceFiscale( tmp.getCodiceFiscale() );
			ele.setEmail( tmp.getEmail() );
			
			for (int i = 6; i > 0; i--) {
				try {
					Method method = tmp.getUo().getClass().getMethod("getOrgIdLiv" + i, new Class[] {});
					Integer orgId = (Integer)method.invoke(tmp.getUo(), new Object[] {});
					for (Organo organo : organi) {
						if (organo.getUnitaOrgAstage().getId().equals(orgId)) {
							ele.setOrgano(organo.getDenominazione());
							ele.setIdOrgano(organo.getId());
						}
					}
				} catch (SecurityException e) {
				} catch (NoSuchMethodException e) {
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				} catch (InvocationTargetException e) {
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
	}

	public Object recuperaUtenteAstageById(int id) {
		return utenteAstageDAO.findById(id);
	}

	public Utente aggiornaUtente(Utente utente) {
		return utenteDAO.merge(utente);	
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
