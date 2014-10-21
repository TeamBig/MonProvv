package it.tesoro.monprovv.facade;



import it.tesoro.monprovv.dao.OrganoDAO;
import it.tesoro.monprovv.dao.UnitaOrgAstageDAO;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.UnitaOrgAstage;
import it.tesoro.monprovv.util.SearchPatternUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	public List<Organo> recuperaOrgano(int page) {
		List<String> order = new ArrayList<String>();
		order.add("denominazione");
		return organoDAO.findAll(page, order);
	}
	
	public int countOrgano() {
		return organoDAO.countAll();
	}
	
	public List<Organo> recuperaOrgano(int page, Organo criteria) {
		List<String> order = new ArrayList<String>();
		order.add("denominazione");
		List<SearchPatternUtil> searchPatternObjects = new ArrayList<SearchPatternUtil>();
		if(criteria.getDenominazione() != null){
			SearchPatternUtil pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "denominazione" );
			pattern.setPattern(criteria.getDenominazione());
			pattern.setPreponi(true);
			pattern.setPostponi(true);
			searchPatternObjects.add(pattern);
		}
		return organoDAO.findByPattern(searchPatternObjects, page, order);
	}
	
	public int countOrgano(Organo criteria) {
		List<SearchPatternUtil> searchPatternObjects = new ArrayList<SearchPatternUtil>();
		if(criteria.getDenominazione() != null){
			SearchPatternUtil pattern = new SearchPatternUtil();
			pattern.setNomeCampo( "denominazione" );
			pattern.setPattern(criteria.getDenominazione());
			pattern.setPreponi(true);
			pattern.setPostponi(true);
			searchPatternObjects.add(pattern);
		}
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
		return unitaOrgAstageDAO.findByHqlQuery(hql, params);
		
		
		
	}

	public void inserisciOrgano(Organo organo) {
		if(organo.getUnitaOrgAstage()!=null){
			UnitaOrgAstage uoa = this.recuperaunitaOrgAstageById(organo.getUnitaOrgAstage().getId());
			organo.setUnitaOrgAstage(uoa);
		}
		organoDAO.save(organo);
	}
	
}
