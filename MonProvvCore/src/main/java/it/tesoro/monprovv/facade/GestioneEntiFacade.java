package it.tesoro.monprovv.facade;



import it.tesoro.monprovv.dao.OrganoDAO;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.Utente;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("gestioneEntiFacade")
public class GestioneEntiFacade {

	@Autowired
	private OrganoDAO organoDAO;
	
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
		String[] order = new String[] { "denominazione" };
		return organoDAO.findByPropertyNotNull("denominazione", page, Arrays.asList(order) );
	}
	
}