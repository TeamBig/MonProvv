package it.tesoro.monprovv;

import it.tesoro.monprovv.dto.ProvvedimentoStampaDto;
import it.tesoro.monprovv.facade.GestioneEntiFacade;
import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.facade.GestioneUtenteFacade;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.service.ReportService;
import it.tesoro.monprovv.sicurezza.CustomUser;
import it.tesoro.monprovv.utils.Constants;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" , "classpath:applicationContext.xml" })
public class UnitTest {

	@Autowired
	private GestioneUtenteFacade gestioneUtenteFacade;
	
	@Autowired 
	private GestioneEntiFacade gestioneEntiFacade;
	
	@Autowired 
	private ReportService reportService;
	
	@Autowired 
	private GestioneProvvedimentoFacade provvedimentoFacade;
	
//	@Test
//	public void testOrgano(){
//		
//		CustomUser user = new CustomUser("BATCH", "batch", true, true, true, true, new ArrayList<GrantedAuthority>() );
//    	
//		Authentication authentication = new UsernamePasswordAuthenticationToken(user, "batch");
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//		
//		Organo o = new Organo();
//		o.setDenominazione("aaaa");
//		o.setFlagConcertante("N");
//		o.setUnitaOrgAstage(gestioneEntiFacade.recuperaUnitaOrgAstageNonUsati().get(0) );
//		
//		gestioneEntiFacade.inserisciOrgano(o);
//		
//		
//	}
	
	@Test
	public void report() throws IOException {
		List<ProvvedimentoStampaDto> provvedimenti = provvedimentoFacade.recuperaProvvedimentiPerExport();
		
		ByteArrayOutputStream baos = reportService.generaReport(Constants.TIPO_XLS, "ExportXls", null, provvedimenti);
		
		OutputStream outputStream = new FileOutputStream("C:\\Temp\\provvedimenti.xls"); 
		baos.writeTo(outputStream);
			
	}
	
	
	
	
	
//	@Test
//	public void test() {
//		try{
//			Stato stato1 = new Stato();
//			stato1.setTipo("tipo");
//			stato1.setCodice("codice");
//			stato1.setDescrizione("descrizione");
//			
//			stato1.setUtenteInserimento("TEST INS");
//			stato1.setDataInserimento(new Date());
//			
//			stato1.setUtenteAggiornamento("TEST AGG");
//			stato1.setDataAggiornamento(new Date());
//			
//					
//			Stato stato2 = new Stato();
//			stato2.setTipo("tipo");
//			stato2.setCodice("codice");
//			stato2.setDescrizione("descrizione");
//			
//			gestioneUtenteFacade.inserisci(stato1, stato2);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//
//		gestioneUtenteFacade.testSelect();
//		System.out.println("OK");
//	}

}
