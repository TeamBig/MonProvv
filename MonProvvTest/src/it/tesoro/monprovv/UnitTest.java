package it.tesoro.monprovv;

import it.tesoro.monprovv.facade.GestioneUtenteFacade;
import it.tesoro.monprovv.model.Stato;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" , "classpath:applicationContext.xml" })
public class UnitTest {

	@Autowired
	private GestioneUtenteFacade gestioneUtenteFacade;
	
	@Test
	public void test() {
<<<<<<< HEAD
		try{
			Stato stato1 = new Stato();
			stato1.setTipo("tipo");
			stato1.setCodice("codice");
			stato1.setDescrizione("descrizione");
			
			stato1.setUtenteInserimento("TEST INS");
			stato1.setDataInserimento(new Date());
			
			stato1.setUtenteAggiornamento("TEST AGG");
			stato1.setDataAggiornamento(new Date());
			
					
			Stato stato2 = new Stato();
			stato2.setTipo("tipo");
			stato2.setCodice("codice");
			stato2.setDescrizione("descrizione");
			
			gestioneUtenteFacade.inserisci(stato1, stato2);
		}catch(Exception e){
			e.printStackTrace();
		}

		gestioneUtenteFacade.testSelect();
=======
		
		Stato stato = new Stato();
		stato.setTipo("tipo");
		stato.setCodice("codice");
		stato.setDescrizione("descrizione");
		
		stato.setUtenteInserimento("TEST INS");
		stato.setDataInserimento(new Date());
		
		stato.setUtenteAggiornamento("TEST AGG");
		stato.setDataAggiornamento(new Date());
		
		gestioneUtenteFacade.inserisciStato(stato);
		
		
		//gestioneUtenteFacade.testSelect();
>>>>>>> ea7a6353450365b496cba237e2b413a7a912123c
		System.out.println("OK");
	}

}
