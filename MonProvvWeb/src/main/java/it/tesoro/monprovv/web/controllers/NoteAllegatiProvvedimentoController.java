package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Storico;
import it.tesoro.monprovv.sicurezza.CustomUser;
import it.tesoro.monprovv.utils.Constants;
import it.tesoro.monprovv.utils.StringUtils;
import it.tesoro.monprovv.web.utils.AlertUtils;
import it.tesoro.monprovv.web.utils.ProvvedimentiUtil;

import java.util.ArrayList;
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
			
			List<Integer> idAllegatiList = new ArrayList<Integer>();
			for( Allegato tmp : provvedimento.getAllegatiList() ){
				idAllegatiList.add(tmp.getId());
			}
			provvedimento.setIdAllegatiUpdList( idAllegatiList );
			
			model.addAttribute("provvedimento", provvedimento);
			caricaTabelleInferiore(model, provvedimento);
			retVal = "noteAllegatiProv";
		}
		return retVal;
	}
	
	private void caricaTabelleInferiore(Model model, Provvedimento provvedimentoModifica) {
		List<Allegato> listaAllegati = provvedimentoModifica.getAllegatiList();
		model.addAttribute("listaAllegati", listaAllegati);
		List<Assegnazione> listaAssegnazione = provvedimentoModifica.getAssegnazioneList();
		model.addAttribute("listaAssegnazione", listaAssegnazione);
		Assegnazione assegnazioneNew = new Assegnazione();
		assegnazioneNew.setProvvedimento(provvedimentoModifica);
		model.addAttribute("assegnatarioNew", assegnazioneNew);
	}
	
	@RequestMapping(value = { "/private/provvedimenti/ricerca/noteAllegatiProv" } , method = RequestMethod.POST)
	public String noteAllegatiProvPOST(Model model, 
			@ModelAttribute("provvedimento") Provvedimento provvedimento,
			@RequestParam String action) {
		String retVal = "redirect:/private/provvedimenti/ricerca/dettaglio?id="+provvedimento.getId();
		if("save".equals( action )){
			Provvedimento provInDb = gestioneProvvedimentoFacade.ricercaProvvedimentoById(provvedimento.getId());
			 Provvedimento p = gestioneProvvedimentoFacade.ricercaProvvedimentoById(provvedimento.getId());
			 p.setNoteInterne( provvedimento.getNoteInterne() );
			 gestioneProvvedimentoFacade.aggiornaProvvedimento(p);
			
			 ProvvedimentiUtil.gestioneSalvaAllegati(provvedimento, p, gestioneProvvedimentoFacade);
			 
			 //GESTIONE STORICO
			 Storico storNota = getStoricoModifiche(provvedimento, provInDb, true);
			 Storico storAllegati = getStoricoModifiche(provvedimento, provInDb, false);
			 
			 if(storNota.getTipoOperazione()!=null){
				 gestioneProvvedimentoFacade.inserisciStoricoAssegnatario(storNota);
			 }
			 if(storAllegati.getTipoOperazione()!=null){
				 gestioneProvvedimentoFacade.inserisciStoricoAssegnatario(storAllegati);
			 }
			 
		}else if("cancel".equals( action )){
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
		}
		return retVal;
	}
	
	private Storico getStoricoModifiche(Provvedimento provInPag, Provvedimento provInDb,boolean isNota){
		Storico stor = new Storico();
		CustomUser principal = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(isNota) {
			if(provInDb.getNoteInterne()==null && provInPag.getNoteInterne()!=null){
				stor.setTipoEntita("Nota");
				stor.setTipoOperazione(Constants.INSERIMENTO);
			}
			else if(provInDb.getNoteInterne()!=null && !provInPag.getNoteInterne().equals(provInDb.getNoteInterne())){
				stor.setTipoEntita("Nota");
				stor.setTipoOperazione(Constants.AGGIORNAMENTO);
			}
		} else {
			if(provInPag.getIdAllegatiUpdList()!=null && provInPag.getIdAllegatiUpdList().size()>0){
				stor.setTipoEntita("Allegati");
				stor.setTipoOperazione(Constants.INSERIMENTO);
			}
			else if((provInPag.getIdAllegatiUpdList()==null || (provInPag.getIdAllegatiUpdList()!=null && provInPag.getIdAllegatiUpdList().size()==0))
					&& (provInPag.getIdAllegatiDelList()!=null && provInPag.getIdAllegatiDelList().size()>0)){
				stor.setTipoEntita("Nota");
				stor.setTipoOperazione(Constants.CANCELLAZIONE);				
			}
		}
		for(Assegnazione asse : provInDb.getAssegnazioneList()){
			if(asse.getOrgano().equals(principal.getUtente().getOrgano())){
				stor.setIdEntita(asse.getId());
				break;
			}
		}
		stor.setIdUtenteOperazione(principal.getUtente());
		stor.setDataOperazione(new Date());
		
		return stor;
	}
	
	
}
