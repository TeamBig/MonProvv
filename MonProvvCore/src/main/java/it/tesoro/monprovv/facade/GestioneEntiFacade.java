package it.tesoro.monprovv.facade;



import it.tesoro.monprovv.dao.OrganoDAO;
import it.tesoro.monprovv.dao.UnitaOrgAstageDAO;
import it.tesoro.monprovv.exception.DatabaseException;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.UnitaOrgAstage;
import it.tesoro.monprovv.utils.SearchPatternUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("gestioneEntiFacade")
public class GestioneEntiFacade {

	@Autowired
	private OrganoDAO organoDAO;
	
	@Autowired
	private UnitaOrgAstageDAO unitaOrgAstageDAO;
	
	public Organo recuperaOrganoById(Integer id) {
		Organo organo = null;
		if(id!=null){
			organo = organoDAO.findById(id);
		}
		return organo;
	}
	
//	public List<Organo> recuperaOrgano(int page) {
//		List<String> order = new ArrayList<String>();
//		order.add("denominazione");
//		return organoDAO.findAll(page, order);
//	}
	
//	public int countOrgano() {
//		return organoDAO.countAll();
//	}
	
	public List<Organo> recuperaOrgano(int page, Organo criteria) {
		List<String> order = new ArrayList<String>();
		order.add("denominazione");
		List<SearchPatternUtil> searchPatternObjects = popolaCritera(criteria);
		return organoDAO.findByPattern(searchPatternObjects, page, order);
	}

	private List<SearchPatternUtil> popolaCritera(Organo criteria) {
		List<SearchPatternUtil> searchPatternObjects = new ArrayList<SearchPatternUtil>();
		SearchPatternUtil pattern = null;
		if(criteria.getDenominazione() != null){
			pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "denominazione" );
			pattern.setPattern(criteria.getDenominazione());
			pattern.setPreponi(true);
			pattern.setPostponi(true);
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
	
	public int countOrgano(Organo criteria) {
		List<SearchPatternUtil> searchPatternObjects = popolaCritera(criteria);
		return organoDAO.countByPattern(searchPatternObjects);
	}

	public UnitaOrgAstage recuperaunitaOrgAstageById(Integer id) {
		return unitaOrgAstageDAO.findById(id);
	}
	
	public Organo aggiornaOrgano(Organo organo){
		if(organo.getUnitaOrgAstage()!=null){
			UnitaOrgAstage uoa = this.recuperaunitaOrgAstageById(organo.getUnitaOrgAstage().getId());
			organo.setUnitaOrgAstage(uoa);
		}
		return organoDAO.merge(organo);		
	}
	
	public List<UnitaOrgAstage> recuperaUnitaOrgAstageNonUsati() {
		return unitaOrgAstageDAO.findByHqlQuery("from UnitaOrgAstage u where u.id not in ( select o.unitaOrgAstage.id from Organo o where o.unitaOrgAstage is not null ) order by nome ");
	}
	
	public List<UnitaOrgAstage> recuperaUnitaOrgAstageNonUsati(String nome) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("nome", "%"+nome.toUpperCase()+"%");
		String hql = "from UnitaOrgAstage u where upper(u.nome) like :nome and u.id not in ( select o.unitaOrgAstage.id from Organo o where o.unitaOrgAstage is not null ) order by nome asc";
		return unitaOrgAstageDAO.findByHqlQueryNumeroRecord(hql, params, 10);
		
		
		
	}

	public void inserisciOrgano(Organo organo) {
		if(organo.getUnitaOrgAstage()!=null){
			UnitaOrgAstage uoa = this.recuperaunitaOrgAstageById(organo.getUnitaOrgAstage().getId());
			organo.setUnitaOrgAstage(uoa);
		}
		organoDAO.save(organo);
	}

	public void eliminazioneLogica(Integer id) throws DatabaseException {
		if(id != null){
			Organo organo = organoDAO.findById(id);
			
			if (!CollectionUtils.isEmpty(organo.getUtenteList())) {
				throw new DatabaseException("NOT_EMPTY_USER_LIST");
			}
			
			organo.setFlagAttivo("N");
			organoDAO.merge(organo);
		}		
	}
	
}
