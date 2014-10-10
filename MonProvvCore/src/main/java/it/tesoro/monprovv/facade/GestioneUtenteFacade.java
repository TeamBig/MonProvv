package it.tesoro.monprovv.facade;



import it.tesoro.monprovv.dao.ProvvedimentoDAO;
import it.tesoro.monprovv.dao.StatoDAO;
import it.tesoro.monprovv.model.ProvvedimentiParent;
import it.tesoro.monprovv.model.Provvedimento;
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
	
	
	@Autowired
	private ProvvedimentoDAO provvedimentoDAO;
	
	public void inserisciStato(Stato stato){
		statoDAO.save(stato);
		stato.setUtenteInserimento(null);
		statoDAO.save(stato);
	}


	public void testSelect() {
		System.out.println(" A ");
		Provvedimento p = provvedimentoDAO.findById(1);
		System.out.println(" B ");
		
		System.out.println(p.getId());
		
		System.out.println("<" + p.getProvvedimentiParent() + ">");
		
		System.out.println("<" + p.getProvvedimentiParent().size() + ">");
		
		
		System.out.println(" C ");
		
		for(ProvvedimentiParent tmp:p.getProvvedimentiParent()){
			System.out.println("* " + tmp.getProvvedimentoCollegato().getTipologia() );
			//System.out.println("* " + tmp.getIdProvvedimentoCollegato());
		}
		
	}
	

}
