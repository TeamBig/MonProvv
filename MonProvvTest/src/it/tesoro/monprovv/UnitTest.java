package it.tesoro.monprovv;

import it.tesoro.monprovv.dto.RicercaProvvedimentoDto;
import it.tesoro.monprovv.dto.UtenteDto;
import it.tesoro.monprovv.facade.GestioneEntiFacade;
import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.facade.GestioneUtenteFacade;
import it.tesoro.monprovv.service.ReportService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.AntPathMatcher;

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
	
	
	@Test
	public void ricercaProvv(){
		
		int page = 1;
		RicercaProvvedimentoDto provvDto =  new RicercaProvvedimentoDto();
//		provvDto.setComma("comma");
		provvDto.setTitoloOggetto("p");
		provvedimentoFacade.ricercaProvvedimenti(provvDto , page );
		
	}
	
	
	@Test
	public void ricercaOrgano(){
		
		int id = 142;
		gestioneEntiFacade.recuperaOrganoById( id );
		
	}
	
	@Test
	public void ricercaUtenti(){
		
		int page = 142;
		UtenteDto filtro = new UtenteDto();
		gestioneUtenteFacade.recupera(page, filtro );
		
	}
	
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
	
//	@Test
//	public void report() throws IOException {
//		List<ProvvedimentoStampaDto> provvedimenti = provvedimentoFacade.recuperaProvvedimentiPerExport();
//		
//		ByteArrayOutputStream baos = reportService.generaReport(Constants.TIPO_XLS, "ExportXls", null, provvedimenti);
//		
//		OutputStream outputStream = new FileOutputStream("C:\\Temp\\provvedimenti.xls"); 
//		baos.writeTo(outputStream);
//			
//	}
	
	
	@Test 
	public void path() {
		AntPathMatcher matcher = new AntPathMatcher();
		
		System.out.println("Ris = " + matcher.match("/private/home/**", "/private/home"));
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
