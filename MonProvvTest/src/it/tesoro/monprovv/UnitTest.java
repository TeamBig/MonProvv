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
		System.out.println("OK");
	}

}
