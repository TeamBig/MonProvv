package it.tesoro.monprovv.facade;



import it.tesoro.monprovv.dao.StatoDAO;
import it.tesoro.monprovv.model.Stato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("gestioneUtenteFacade")
public class GestioneUtenteFacade {

//	@Autowired
//	private UtenteDAO utenteDAO;
//
//	@Autowired
//	private RuoloDAO ruoloDAO;
	
	@Autowired
	private StatoDAO statoDAO;
	
	
	public void addStato(Stato stato){
		statoDAO.save(stato);
	}
	

}
