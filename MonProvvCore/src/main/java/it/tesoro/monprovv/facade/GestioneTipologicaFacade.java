package it.tesoro.monprovv.facade;



import it.tesoro.monprovv.dao.StatoDAO;
import it.tesoro.monprovv.model.Stato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("gestioneTipologicaFacade")
public class GestioneTipologicaFacade {

	@Autowired
	private StatoDAO statoDAO;
	

	public Stato recuperaStatoById(Integer id) {
		return statoDAO.findById(id);
	}
}
