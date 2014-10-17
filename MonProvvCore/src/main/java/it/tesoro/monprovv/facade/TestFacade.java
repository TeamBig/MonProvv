package it.tesoro.monprovv.facade;

import it.tesoro.monprovv.dao.ProvvedimentoDAO;
import it.tesoro.monprovv.model.Provvedimento;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;



@Component("testFacade")
public class TestFacade {
	
	protected static Logger logger = Logger.getLogger(TestFacade.class);
	
	@Autowired 
	private ProvvedimentoDAO provvedimentoDAO;
	
	
	public Provvedimento recuperaProvvedimentoById(int id) {
		return provvedimentoDAO.findById(id);
	}
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PreAuthorize("hasPermission(#id, 'modifica')")
	public void prova(String id) {
		logger.debug("############################# OK preauth id = " + id);
	}

}
