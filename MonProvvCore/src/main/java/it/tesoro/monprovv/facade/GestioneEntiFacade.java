package it.tesoro.monprovv.facade;



import it.tesoro.monprovv.dao.OrganoDAO;
import it.tesoro.monprovv.dao.UnitaOrgAstageDAO;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.UnitaOrgAstage;

import java.util.ArrayList;
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
	
	public List<Organo> recuperaAllOrgano() {
		return organoDAO.findAll();
	}
	
	public List<Organo> recuperaOrgano(int page) {
		List<String> order = new ArrayList<String>();
		order.add("denominazione");
		return organoDAO.findAll(page, order);
	}
	
	public List<Organo> recuperaOrgano(int page, Organo criteria) {
		List<String> order = new ArrayList<String>();
		order.add("denominazione");
		return organoDAO.findAll(page, order);
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

	public void inserisciOrgano(Organo organo) {
		if(organo.getUnitaOrgAstage()!=null){
			UnitaOrgAstage uoa = this.recuperaunitaOrgAstageById(organo.getUnitaOrgAstage().getId());
			organo.setUnitaOrgAstage(uoa);
		}
		organoDAO.save(organo);
	}

	public int countAllOrgano() {
		return organoDAO.countAll();
	}
	
}
