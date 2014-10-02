package it.tesoro.monprovv.facade;



import it.tesoro.monprovv.dao.RuoloDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("gestioneUtenteFacade")
public class GestioneUtenteFacade {

//	@Autowired
//	private UtenteDAO utenteDAO;

	@Autowired
	private RuoloDAO ruoloDAO;
	
	

}
