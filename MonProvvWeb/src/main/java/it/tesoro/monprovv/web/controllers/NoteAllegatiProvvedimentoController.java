package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Nota;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Storico;
import it.tesoro.monprovv.sicurezza.CustomUser;
import it.tesoro.monprovv.utils.Constants;
import it.tesoro.monprovv.utils.StringUtils;
import it.tesoro.monprovv.web.utils.AlertUtils;
import it.tesoro.monprovv.web.utils.ProvvedimentiUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteAllegatiProvvedimentoController {
	
	protected static Logger logger = Logger.getLogger(NoteAllegatiProvvedimentoController.class);
	
	@Autowired
	private GestioneProvvedimentoFacade gestioneProvvedimentoFacade;
	
	@Autowired
	private AlertUtils alertUtils;
	
	@RequestMapping(value = { "/private/provvedimenti/ricerca/noteAllegatiProv" } , method = RequestMethod.GET)
	public String modificaProvvedimento(Model model, @RequestParam(required = false) String id) {
		String retVal = "redirect:/private/ricercaProv/dettaglio?id="+id;
		
		if(StringUtils.isNotEmpty(id)){
			Provvedimento provvedimento = gestioneProvvedimentoFacade.ricercaProvvedimentoById(Integer.valueOf(id));

			Assegnazione assegnazione = trovaAssegnazione(provvedimento);
			List<Allegato> listaAllegati = assegnazione.getAllegatoList();
			
			Nota nota = new Nota();
			if(assegnazione!=null){
				model.addAttribute("listaAllegati", listaAllegati );
				nota = gestioneProvvedimentoFacade.recuperaNotaByAssegnazione(assegnazione);
			}
			if( nota != null ){
				provvedimento.setIdNotaAssegnazione(nota.getId());
				try {
					provvedimento.setTestoNotaAssegnazione(nota.getTestoAsText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
//			List<Integer> idAllegatiList = new ArrayList<Integer>();
//			for( Allegato tmp : listaAllegati ){
//				idAllegatiList.add(tmp.getId());
//			}
//			provvedimento.setIdAllegatiUpdList( idAllegatiList );
			
			model.addAttribute("provvedimento", provvedimento);

			retVal = "noteAllegatiProv";
		}
		
		return retVal;
	}


	private Assegnazione trovaAssegnazione(Provvedimento provvedimento) {
		List<Assegnazione> listaAssegnazione = provvedimento.getAssegnazioneList();	
		CustomUser principal = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Assegnazione assegnazione = null;
		for(Assegnazione tmp: listaAssegnazione){
			if( tmp.getOrgano().getId().equals( principal.getUtente().getOrgano().getId() ) ){
				assegnazione = tmp;
			}
		}
		return assegnazione;
	}
	
	
	@RequestMapping(value = { "/private/provvedimenti/ricerca/noteAllegatiProv" } , method = RequestMethod.POST, params="cancelNoteAllegati")
	public String noteAllegatiProvCancelPOST(Model model, 
			@ModelAttribute("provvedimento") Provvedimento provvedimento) {
		String retVal = "redirect:/private/provvedimenti/ricerca/dettaglio?id="+provvedimento.getId();
		Allegato ele;
		//Setto il provvedimento per i nuovi allegati
		for(Integer tmp: provvedimento.getIdAllegatiUpdList()){
			if(tmp!=null){
				ele = gestioneProvvedimentoFacade.getAllegatoById(tmp);
				if(ele!=null && ele.getProvvedimento()==null){
					gestioneProvvedimentoFacade.eliminaAllegato(tmp);
				}
			}
		}

		return retVal;
	}

	@RequestMapping(value = { "/private/provvedimenti/ricerca/noteAllegatiProv" } , method = RequestMethod.POST, params="saveNoteAllegati")
	public String noteAllegatiProvSalvaPOST(Model model, 
			@ModelAttribute("provvedimento") Provvedimento provvedimento) {
		
		String retVal = "redirect:/private/provvedimenti/ricerca/dettaglio?id="+provvedimento.getId();

		Provvedimento provInDb = gestioneProvvedimentoFacade.ricercaProvvedimentoById(provvedimento.getId());
		Assegnazione assegnazione = trovaAssegnazione(provInDb);

		ProvvedimentiUtil.gestioneSalvaAllegati4Assegnazioni(provvedimento, provInDb, gestioneProvvedimentoFacade);
		
		Integer idNota =  provvedimento.getIdNotaAssegnazione();
		Nota notaDB = null;
		Nota notaDBagg = null;
		
		String testoNota = provvedimento.getTestoNotaAssegnazione();
		if( idNota != null ){
			notaDB = gestioneProvvedimentoFacade.recuperaNotaById(idNota);
			//Aggiorno una nota esistente
			notaDBagg = gestioneProvvedimentoFacade.aggiornaNota( idNota, testoNota );
		}else{
			Nota nuovaNota = new Nota();
			nuovaNota.setAssegnazione(assegnazione);
			nuovaNota.setFlagVisibile("S");
			gestioneProvvedimentoFacade.inserisciNota( nuovaNota, testoNota );
		}

		//GESTIONE STORICO
		Storico storNota = getStoricoModifiche(provvedimento, provInDb, true, idNota, notaDB, notaDBagg);
		
		Storico storAllegati = getStoricoModifiche(provvedimento, provInDb, false);

		if(storNota.getTipoOperazione()!=null){
			gestioneProvvedimentoFacade.inserisciStoricoAssegnatario(storNota);
		}
		if(storAllegati.getTipoOperazione()!=null){
			gestioneProvvedimentoFacade.inserisciStoricoAssegnatario(storAllegati);
		}
			 
		return retVal;
	}
	
	private Storico getStoricoModifiche(Provvedimento provvedimento, Provvedimento provInDb, boolean b, Integer idNota, Nota notaDB, Nota notaDBagg) {
		Storico stor = new Storico();
		CustomUser principal = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if( idNota != null ){
			try {
				if( notaDBagg.getTestoAsText() != null  ){
					if( ! (notaDBagg.getTestoAsText().equals( notaDB.getTestoAsText() ) ) ){
						stor.setTipoEntita("Nota");
						stor.setTipoOperazione(Constants.AGGIORNAMENTO);
					}
				}else if( notaDB.getTestoAsText() != null  ){
					if( ! (notaDB.getTestoAsText().equals( notaDBagg.getTestoAsText() ) ) ){
						stor.setTipoEntita("Nota");
						stor.setTipoOperazione(Constants.AGGIORNAMENTO);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			stor.setTipoEntita("Nota");
			stor.setTipoOperazione(Constants.INSERIMENTO);
		}


		for(Assegnazione asse : provInDb.getAssegnazioneList()){
			if(asse.getOrgano().equals(principal.getUtente().getOrgano())){
				stor.setIdEntita(asse.getId());
				break;
			}
		}
		stor.setUtenteOperazione(principal.getUtente());
		stor.setDataOperazione(new Date());

		return stor;
	}


	private Storico getStoricoModifiche(Provvedimento provInPag, Provvedimento provInDb,boolean isNota){
		Storico stor = new Storico();
		CustomUser principal = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(provInPag.getIdAllegatiUpdList()!=null && provInPag.getIdAllegatiUpdList().size()>0){
			stor.setTipoEntita("Allegati");
			stor.setTipoOperazione(Constants.INSERIMENTO);
		}
		else if((provInPag.getIdAllegatiUpdList()==null || (provInPag.getIdAllegatiUpdList()!=null && provInPag.getIdAllegatiUpdList().size()==0))
				&& (provInPag.getIdAllegatiDelList()!=null && provInPag.getIdAllegatiDelList().size()>0)){
			stor.setTipoEntita("Allegati");
			stor.setTipoOperazione(Constants.CANCELLAZIONE);				
		}
		for(Assegnazione asse : provInDb.getAssegnazioneList()){
			if(asse.getOrgano().equals(principal.getUtente().getOrgano())){
				stor.setIdEntita(asse.getId());
				break;
			}
		}
		stor.setUtenteOperazione(principal.getUtente());
		stor.setDataOperazione(new Date());

		return stor;
	}
	
	
}
