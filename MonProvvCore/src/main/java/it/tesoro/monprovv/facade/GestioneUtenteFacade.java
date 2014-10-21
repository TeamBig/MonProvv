package it.tesoro.monprovv.facade;



import it.tesoro.monprovv.dao.UtenteAstageDAO;
import it.tesoro.monprovv.dao.UtenteDAO;
import it.tesoro.monprovv.model.Utente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("gestioneUtenteFacade")
public class GestioneUtenteFacade {

	@Autowired
	private UtenteDAO utenteDAO;
	
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

	public List<Utente> recupera(int page, Utente ricercaUtente) {
		// TODO Auto-generated method stub
		return null;
	}

	public int count() {
		return utenteDAO.countAll();
	}

	public int count(Utente ricercaUtente) {
		// TODO Auto-generated method stub
		return 0;
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
