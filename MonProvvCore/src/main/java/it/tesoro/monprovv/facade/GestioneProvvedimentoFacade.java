package it.tesoro.monprovv.facade;

import it.tesoro.monprovv.dao.AllegatoDAO;
import it.tesoro.monprovv.dao.AssegnazioneDAO;
import it.tesoro.monprovv.dao.GovernoDAO;
import it.tesoro.monprovv.dao.NotaDAO;
import it.tesoro.monprovv.dao.NotificaDAO;
import it.tesoro.monprovv.dao.OrganoDAO;
import it.tesoro.monprovv.dao.ProvvedimentiParentDAO;
import it.tesoro.monprovv.dao.ProvvedimentoDAO;
import it.tesoro.monprovv.dao.StatoDAO;
import it.tesoro.monprovv.dao.StoricoDAO;
import it.tesoro.monprovv.dao.TipoAttoDAO;
import it.tesoro.monprovv.dao.TipoProvvDaAdottareDAO;
import it.tesoro.monprovv.dao.TipoProvvedimentoDAO;
import it.tesoro.monprovv.dao.UtenteDAO;
import it.tesoro.monprovv.dto.AssegnazioneDto;
import it.tesoro.monprovv.dto.InserisciProvvedimentoDto;
import it.tesoro.monprovv.dto.Mail;
import it.tesoro.monprovv.dto.ProvvedimentoStampaDto;
import it.tesoro.monprovv.dto.RicercaProvvedimentoDto;
import it.tesoro.monprovv.dto.SollecitoDto;
import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Nota;
import it.tesoro.monprovv.model.Notifica;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.ProvvedimentiParent;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.Storico;
import it.tesoro.monprovv.model.TipoAtto;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;
import it.tesoro.monprovv.model.Utente;
import it.tesoro.monprovv.service.MailService;
import it.tesoro.monprovv.sicurezza.CustomUser;
import it.tesoro.monprovv.utils.Constants;
import it.tesoro.monprovv.utils.SearchPatternUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("gestioneProvvedimentoFacade")
public class GestioneProvvedimentoFacade {
	
	@Autowired
	private ProvvedimentoDAO provvedimentoDAO;
	
	@Autowired
	private StatoDAO statoDAO;
	
	@Autowired
	private GovernoDAO governoDAO;
	
	@Autowired 
	private TipoProvvedimentoDAO tipoProvvedimentoDAO;
	
	@Autowired 
	private TipoProvvDaAdottareDAO tipoProvvDaAdottareDAO;

	@Autowired 
	private AllegatoDAO allegatoDAO;
	
	@Autowired 
	private TipoAttoDAO tipoAttoDAO;
	
	@Autowired 
	private OrganoDAO organoDAO;
	
	@Autowired 
	private AssegnazioneDAO assegnazioneDAO;
	
	@Autowired
	private ProvvedimentiParentDAO provvedimentiParentDAO;
	
	@Autowired
	private StoricoDAO storicoDAO;
	
	@Autowired 
	private NotificaDAO notificaDAO;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private NotaDAO notaDAO;
	
	@Autowired
	private UtenteDAO utenteDAO;
	
	public List<Stato> initStato(){
		List<String> order = new ArrayList<String>();
		order.add("id");
		List<SearchPatternUtil> searchPatternObjects = new ArrayList<SearchPatternUtil>();
		SearchPatternUtil pattern = new SearchPatternUtil("tipo","P",true,true);
		searchPatternObjects.add(pattern);
		List<Stato> listaStati = statoDAO.findByPattern(searchPatternObjects, 1, order);
		return listaStati;
	}
	
	public List<Governo> initGoverno(){
		List<String> order = new ArrayList<String>();
		order.add("denominazione");
		List<Governo> listaGoverno = governoDAO.findAll(order);
		return listaGoverno;
	}
	
	
	public List<Provvedimento> ricercaProvvedimenti(RicercaProvvedimentoDto provvDto, int page) {
		List<String> order = new ArrayList<String>();
		order.add("id");
		List<SearchPatternUtil> searchPatternObjects = new ArrayList<SearchPatternUtil>();
		searchPatternObjects = getCriteriRicercaList(provvDto);
		return provvedimentoDAO.findByPattern(searchPatternObjects, page, order);
	}
	
	public int countRicercaProvvedimenti(RicercaProvvedimentoDto provvDto) {
		List<SearchPatternUtil> searchPatternObjects = new ArrayList<SearchPatternUtil>();
		searchPatternObjects = getCriteriRicercaList(provvDto);
		return provvedimentoDAO.countByPattern(searchPatternObjects);
	}
	
	private List<SearchPatternUtil> getCriteriRicercaList(RicercaProvvedimentoDto provvDto){
		List<SearchPatternUtil> searchPatternObjects = new ArrayList<SearchPatternUtil>();
		if(!StringUtils.isEmpty(provvDto.getArt())){
			SearchPatternUtil pattern = new SearchPatternUtil("articolo",provvDto.getArt(),true,true);
			searchPatternObjects.add(pattern);
		}
		if(!StringUtils.isEmpty(provvDto.getComma())){
			SearchPatternUtil pattern = new SearchPatternUtil("comma",provvDto.getComma(),true,true);
			searchPatternObjects.add(pattern);
		}
		if(!StringUtils.isEmpty(provvDto.getTitoloOggetto())){
			SearchPatternUtil pattern = new SearchPatternUtil("oggetto",provvDto.getTitoloOggetto(),true,true);
			searchPatternObjects.add(pattern);
		}
		if(!StringUtils.isEmpty(provvDto.getFonteNormativa())){
			SearchPatternUtil pattern = new SearchPatternUtil("fonteNormativa",provvDto.getFonteNormativa(),true,true);
			searchPatternObjects.add(pattern);
		}
		if(!StringUtils.isEmpty(provvDto.getTipologia())){
			SearchPatternUtil pattern = new SearchPatternUtil("tipoProvvedimento",provvDto.getTipologia().getId().toString(),false,false);
			searchPatternObjects.add(pattern);
		}
		if(!StringUtils.isEmpty(provvDto.getTipoGoverno())){
			SearchPatternUtil pattern = new SearchPatternUtil("governo",provvDto.getTipoGoverno().getId().toString(),false,false);
			searchPatternObjects.add(pattern);
		}
		if(!StringUtils.isEmpty(provvDto.getStatoDiAttuazione())){
			SearchPatternUtil pattern = new SearchPatternUtil("stato",provvDto.getStatoDiAttuazione().getId().toString(),false,false);
			searchPatternObjects.add(pattern);
		}
		if(!StringUtils.isEmpty(provvDto.getTipoProvvDaAdottare())){
			SearchPatternUtil pattern = new SearchPatternUtil("tipoProvvDaAdottare",provvDto.getTipoProvvDaAdottare().getId().toString(),false,false);
			searchPatternObjects.add(pattern);
		}
		return searchPatternObjects;		
	}
	
	
//	public List<Provvedimento> ricercaProvvedimenti(RicercaProvvedimentoDto provvDto, int page){
//		HashMap<String, Object> params = new HashMap<String, Object>();
//		if(!StringUtils.isEmpty(provvDto.getArt())){
//			params.put("articolo", provvDto.getArt());
//		}
//		if(!StringUtils.isEmpty(provvDto.getComma())){
//			params.put("comma", provvDto.getComma());
//		}
//		if(!StringUtils.isEmpty(provvDto.getTitoloOggetto())){
//			params.put("oggetto", provvDto.getTitoloOggetto());
//		}
//		if(!StringUtils.isEmpty(provvDto.getFonteNormativa())){
//			params.put("fonteNormativa", provvDto.getFonteNormativa());
//		}
//		if(!StringUtils.isEmpty(provvDto.getTipologia())){
//			params.put("tipologia", provvDto.getTipologia());
//		}
//		if(!StringUtils.isEmpty(provvDto.getTipoGoverno())){
//			params.put("governo", provvDto.getTipoGoverno());
//		}
//		if(!StringUtils.isEmpty(provvDto.getStatoDiAttuazione())){
//			params.put("stato", provvDto.getStatoDiAttuazione());
//		}
//		if(!StringUtils.isEmpty(provvDto.getTipoProvvDaAdottare())){
//			params.put("tipoProvvDaAdottare", provvDto.getTipoProvvDaAdottare());
//		}
//		List<Provvedimento> listaProvvedimenti = provvedimentoDAO.findByProperty(params);
//		return listaProvvedimenti;
//	}
	
	public Allegato inserisciAllegato(Allegato allegato) {
		allegatoDAO.save(allegato);
		return allegato;
	}
	
	public Allegato aggiornaAllegato(Allegato allegato) {
		allegatoDAO.merge(allegato);
		return allegato;
	}

	public List<TipoProvvedimento> initTipologia() {
		List<String> order = new ArrayList<String>();
		order.add("descrizione");
		List<TipoProvvedimento> listaTipoProvvedimento = tipoProvvedimentoDAO.findAll(order);
		return listaTipoProvvedimento;
	}

	public List<TipoProvvDaAdottare> initTipoProvvDaAdottare() {
		List<String> order = new ArrayList<String>();
		order.add("descrizione");
		List<TipoProvvDaAdottare> listaTipoProvvedimento = tipoProvvDaAdottareDAO.findAll(order);
		return listaTipoProvvedimento;
	}
	
	public List<Provvedimento> initAllProvvedimenti(Integer page){
		List<String> order = new ArrayList<String>();
		List<Provvedimento> list = provvedimentoDAO.findAll(page, order);
		return list;
	}
	
	public Provvedimento ricercaProvvedimentoById(Integer id){
		Provvedimento prov = provvedimentoDAO.findById(id);
		return prov;
	}

	public Allegato getAllegatoById(Integer allegatoId) {
		Allegato allegato  = allegatoDAO.findById(allegatoId);
		return allegato;
	}
	
	public Allegato getAllegatoByIdnoProv(Integer allegatoId) {
		Allegato allegato = null;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", allegatoId);
		String hql = "from Allegato u where u.id = :id and u.provvedimento is null";
		List<Allegato> allegati = allegatoDAO.findByHqlQueryNumeroRecord(hql, params, 1);
		for(Allegato tmp: allegati){
			allegato = tmp;
		}
		return allegato;
	}
	
	public Allegato getAllegatoByIdnoAssegnazione(Integer allegatoId) {
		Allegato allegato = null;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", allegatoId);
		String hql = "from Allegato u where u.id = :id and u.assegnazione is null";
		List<Allegato> allegati = allegatoDAO.findByHqlQueryNumeroRecord(hql, params, 1);
		for(Allegato tmp: allegati){
			allegato = tmp;
		}
		return allegato;
	}

	public Integer countAllProvvedimenti() {
		return provvedimentoDAO.countAll();
	}
	
	public List<TipoAtto> initTipoAtto() {
		List<String> order = new ArrayList<String>();
		order.add("descrizione");
		List<TipoAtto> listaTipoAtto = tipoAttoDAO.findAll(order);
		return listaTipoAtto;
	}

	public Provvedimento aggiornaProvvedimento(Provvedimento provvedimento) {
		Provvedimento provvRecuperato = provvedimentoDAO.findById(provvedimento.getId());
		
		boolean inviaNotificaCambioStato = false;
		if (provvedimento.getStato().getId() != provvRecuperato.getStato().getId()) {
			inviaNotificaCambioStato = true;
		}
		
		provvRecuperato = provvRecuperato.getProvvedimentoToUpdate(provvedimento);
		if(provvedimento.getProvvedimentiParentSelected()!=null && Arrays.asList(provvedimento.getProvvedimentiParentSelected()).size()>0){
//			List<String> list = Arrays.asList(provvedimento.getProvvedimentiParentSelected());
//			for(Provvedimento provCollegato : provvedimento.getListaProvvedimenti()){
//				if(list.contains(provCollegato.getId().toString())){
//					ProvvedimentiParent provParent = new ProvvedimentiParent();
//					provParent.setProvvedimento(provvRecuperato);
//					provParent.setProvvedimentoCollegato(provCollegato);
//					provvedimentiParentDAO.save(provParent);
//				}
//			}
		}
		Provvedimento provMerge = provvedimentoDAO.merge(provvRecuperato);
		
		if (inviaNotificaCambioStato) {
			invioNotificaCambioStato(provMerge);
		}
		
		return provMerge;
	}

	public void eliminaAllegato(Integer idAllegato) {
		Allegato allegatoToDelete = allegatoDAO.findById(idAllegato);
		allegatoDAO.delete(allegatoToDelete);
	}

	public Assegnazione inserisciAssegnazione(Integer idProvv, Integer idOrgano) {
		Provvedimento provv = provvedimentoDAO.findById(idProvv);
		Organo organo = organoDAO.findById(idOrgano);
		Stato stato = findStatoById(Constants.ASSEGNATO_ID);
		Assegnazione assegnazione = new Assegnazione();
		assegnazione.setOrgano(organo);
		assegnazione.setProvvedimento(provv);
		assegnazione.setStato(stato);
		assegnazioneDAO.save(assegnazione);
		return assegnazione;
	}
	
	public Assegnazione inserisciRichiestaAssegnazione(Integer idProvv, Integer idOrgano, String motivazioneRichiesta) {
		Provvedimento provv = provvedimentoDAO.findById(idProvv);
		Organo organo = organoDAO.findById(idOrgano);
		Stato stato = statoDAO.findByCodice(Constants.RICHIESTO);
		Assegnazione assegnazione = new Assegnazione();
		assegnazione.setOrgano(organo);
		assegnazione.setProvvedimento(provv);
		assegnazione.setStato(stato);
		if (!StringUtils.isEmpty(motivazioneRichiesta)) {
			assegnazione.setMotivazioneRichiesta(assegnazioneDAO.createClob(motivazioneRichiesta));
		}
		Integer idAss = (Integer)assegnazioneDAO.save(assegnazione);
		
		// invio notifiche
		CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String testo = "L'utente " + user.getUtente().getNome() + " " + user.getUtente().getCognome() + " ha richiesto l'assegnazione del provvedimento " 
				+ provv.getGoverno().getDenominazione() + " " + provv.getId() + ", fonte normativa " + provv.getFonteNormativa() + " per conto dell'organo " + user.getUtente().getOrgano().getDenominazione(); 
		Notifica notifica = new Notifica();
		notifica.setFlagLettura(Notifica.NON_LETTA);
		notifica.setTipoNotifica(Notifica.OPERATIVA);
		notifica.setOggetto("Richiesta assegnazione provvedimento");
		notifica.setTesto(testo);
		notifica.setOrganoDestinatario(provv.getOrganoCapofila());
		notifica.setUtenteMittente(user.getUtente());
		notifica.setLinkOperazione("/private/provvedimenti/confermaassegnazione?id=" + idAss);
		notificaDAO.save(notifica);
		
		return assegnazione;
	}
	
	public Assegnazione aggiornaAssegnazione(Assegnazione assegnazione, Notifica notifica, boolean accettata, String motivazioneRifiuto) {
				
		if (accettata) {
			assegnazione.setStato(statoDAO.findByCodice(Constants.ACCETTATO));
		} else {
			assegnazione.setStato(statoDAO.findByCodice(Constants.RIFIUTATO));
			assegnazione.setMotivazioneRifiuto(assegnazioneDAO.createClob(motivazioneRifiuto));
		}
		assegnazioneDAO.saveOrUpdate(assegnazione);
		
		// insert storico operazione
		// TODO
		
		// segno la notifica operativa come letta
		CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		notifica.setFlagLettura(Notifica.LETTA);
		notifica.setUtenteOperatore(user.getUtente());
		notificaDAO.saveOrUpdate(notifica);
		
		// invio notifiche informative a utenti capofila
		
		
		String testo = "";
		if (accettata) {
			testo = "L'utente " + user.getUtente().getNome() + " " + user.getUtente().getCognome() + " ha accettato la richiesta di assegnazione del provvedimento " 
					+ assegnazione.getProvvedimento().getGoverno().getDenominazione() + " " + assegnazione.getProvvedimento().getId() + ", fonte normativa " + assegnazione.getProvvedimento().getFonteNormativa() + " per conto dell'organo " + user.getUtente().getOrgano().getDenominazione();
		} else {
			testo = "L'utente " + user.getUtente().getNome() + " " + user.getUtente().getCognome() + " ha rifiutato la richiesta di assegnazione del provvedimento " 
					+ assegnazione.getProvvedimento().getGoverno().getDenominazione() + " " + assegnazione.getProvvedimento().getId() + ", fonte normativa " + assegnazione.getProvvedimento().getFonteNormativa() + " per conto dell'organo " + user.getUtente().getOrgano().getDenominazione() +
					" con la seguente motivazione: " + motivazioneRifiuto;
		}
		
		Notifica notificaInfo = new Notifica();
		notificaInfo.setFlagLettura(Notifica.NON_LETTA);
		notificaInfo.setTipoNotifica(Notifica.INFORMATIVA);
		notificaInfo.setOggetto("Assegnazione provvedimento");
		notificaInfo.setTesto(testo);
		notificaInfo.setUtenteMittente(user.getUtente());

		for (Utente utenteDestinatario : utenteDAO.findAttiviByOrgano(assegnazione.getProvvedimento().getOrganoCapofila().getId()) ) {
			notificaInfo.setUtenteDestinatario(utenteDestinatario);
			notificaDAO.save(notificaInfo);
		}
		
		return assegnazione;
	}
	
	public Assegnazione aggiornaFineLavorazioneAssegnazione(Assegnazione assegnazione) {
		
		assegnazione.setStato(statoDAO.findByCodice(Constants.FINE_LAVORAZIONE));
		assegnazioneDAO.saveOrUpdate(assegnazione);
		
		// insert storico operazione
		// TODO
		
		// invio notifiche informative a utenti capofila
		CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String testo = "L'utente " + user.getUtente().getNome() + " " + user.getUtente().getCognome() + " ha completato la lavorazione del provvedimento " 
					+ assegnazione.getProvvedimento().getGoverno().getDenominazione() + " " + assegnazione.getProvvedimento().getId() + ", fonte normativa " + assegnazione.getProvvedimento().getFonteNormativa() + " per conto dell'organo " + user.getUtente().getOrgano().getDenominazione();
		
		Notifica notificaInfo = new Notifica();
		notificaInfo.setFlagLettura(Notifica.NON_LETTA);
		notificaInfo.setTipoNotifica(Notifica.INFORMATIVA);
		notificaInfo.setOggetto("Fine lavorazione");
		notificaInfo.setTesto(testo);
		notificaInfo.setUtenteMittente(user.getUtente());

		for (Utente utenteDestinatario : utenteDAO.findAttiviByOrgano(assegnazione.getProvvedimento().getOrganoCapofila().getId()) ) {
			notificaInfo.setUtenteDestinatario(utenteDestinatario);
			notificaDAO.save(notificaInfo);
		}
		
		// invio notifica al capofila se tutte le assegnazioni sono in fine lavorazione
		Provvedimento provvedimento = provvedimentoDAO.findById(assegnazione.getProvvedimento().getId());
		
		int countInLavorazione = 0;
		for (Assegnazione ass : provvedimento.getAssegnazioneList()) {
			if (ass.getStato().getCodice().equals(Constants.ACCETTATO) || ass.getStato().getCodice().equals(Constants.ASSEGNATO)) {
				countInLavorazione ++;
			}
		}
		
		if (countInLavorazione > 0) {
			String testoFineLav = "Si comunica che tutti gli organi assegnatari hanno concluso la lavorazione del provvedimento " 
					+ provvedimento.getGoverno().getDenominazione() + " " + provvedimento.getId() + ", fonte normativa " + provvedimento.getFonteNormativa();
			
			Notifica notificaFineLav = new Notifica();
			notificaFineLav.setFlagLettura(Notifica.NON_LETTA);
			notificaFineLav.setTipoNotifica(Notifica.INFORMATIVA);
			notificaFineLav.setOggetto("Fine lavorazione per tutte le assegnazioni");
			notificaFineLav.setTesto(testoFineLav);
			notificaFineLav.setUtenteMittente(user.getUtente());
			
			for (Utente utenteDestinatario : utenteDAO.findAttiviByOrgano(assegnazione.getProvvedimento().getOrganoCapofila().getId()) ) {
				notificaFineLav.setUtenteDestinatario(utenteDestinatario);
				notificaDAO.save(notificaFineLav);
			}
		}
		return assegnazione;
	}
	
	
	public Assegnazione aggiornaRichiestaAssegnazione(Assegnazione assegnazione, boolean accettata, Notifica notifica) {
		Stato stato = null;
		if (accettata) {
			stato = statoDAO.findByCodice(Constants.ASSEGNATO);
		} else {
			stato = statoDAO.findByCodice(Constants.RIFIUTATO);
		}
		assegnazione.setStato(stato);
		assegnazioneDAO.saveOrUpdate(assegnazione);
		
		// contrassegno la notifica come eseguita
		CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		notifica.setFlagLettura(Notifica.LETTA);
		notifica.setUtenteOperatore(user.getUtente());
		
		notificaDAO.saveOrUpdate(notifica);
		
		// invio notifica di accettazione assegnazione
		String testo = "L'utente " + user.getUtente().getNome() + " " + user.getUtente().getCognome() + " ha " + (accettata ? "accettato" : "rifiutato") + " la richiesta di assegnazione del provvedimento " 
				+ assegnazione.getProvvedimento().getGoverno().getDenominazione() + " " + assegnazione.getProvvedimento().getId() + ", fonte normativa " + assegnazione.getProvvedimento().getFonteNormativa() + " per conto dell'organo " + user.getUtente().getOrgano().getDenominazione(); 
		Notifica notificaInfo = new Notifica();
		notificaInfo.setFlagLettura(Notifica.NON_LETTA);
		notificaInfo.setTipoNotifica(Notifica.INFORMATIVA);
		notificaInfo.setOggetto("Accettazione richiesta di assegnazione provvedimento");
		notificaInfo.setTesto(testo);
		notificaInfo.setUtenteMittente(user.getUtente());

		for (Utente utenteDestinatario : utenteDAO.findAttiviByOrgano(assegnazione.getOrgano().getId()) ) {
			notificaInfo.setUtenteDestinatario(utenteDestinatario);
			notificaDAO.save(notificaInfo);
		}

		
		return assegnazione;
	}
	
	public Stato findStatoById(Integer id){
		Stato stato = statoDAO.findById(id);
		return stato;
	}

	public List<Organo> initOrgani() {
		List<String> order = new ArrayList<String>();
		order.add("denominazione");
		List<Organo> listaOrgani = organoDAO.findAll(order);
		return listaOrgani;
	}

	public Assegnazione inserisciAssegnazione(Integer idOrgano) {
		Organo organo = organoDAO.findById(idOrgano);
		Stato stato = statoDAO.findById(Constants.ASSEGNATO_ID);
		Assegnazione assegnazione = new Assegnazione();
		assegnazione.setOrgano(organo);
		assegnazione.setStato(stato);
		assegnazioneDAO.save(assegnazione);
		return assegnazione;
	}

	public Provvedimento inserisciProvvedimento(InserisciProvvedimentoDto provvedimentoIns) {
		CustomUser principal = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Stato statoInserito = findStatoById(Constants.INSERITO_ID);
		if(provvedimentoIns.getTipologia().getCodice().equals(Constants.CONCERTANTE_MEF)){
			provvedimentoIns.setProponente(null);
		}		
		Provvedimento provvRecuperato = provvedimentoIns.getProvvedimento();
		provvRecuperato.setStato(statoInserito);
		provvRecuperato.setOrganoInseritore(principal.getUtente().getOrgano());
		provvedimentoDAO.save(provvRecuperato);
		//SALVATAGGIO PROVVEDIMENTI SELEZIONATI
		if(provvedimentoIns.getProvvedimentiSelected()!=null && Arrays.asList(provvedimentoIns.getProvvedimentiSelected()).size()>0){
			List<String> list = Arrays.asList(provvedimentoIns.getProvvedimentiSelected());
			for(Provvedimento provCollegato : provvedimentoIns.getListaProvvedimenti()){
				if(list.contains(provCollegato.getId().toString())){
					ProvvedimentiParent provParent = new ProvvedimentiParent();
					provParent.setProvvedimento(provvRecuperato);
					provParent.setProvvedimentoCollegato(provCollegato);
					provvedimentiParentDAO.save(provParent);
				}
			}
		}
		//SALVATAGGIO ASSEGNAZIONI ORGANI FK -> Provvedimento
		for(Integer idAssegnazione :provvedimentoIns.getIdAssegnatariUpdList()){
			Assegnazione ass = assegnazioneDAO.findById(idAssegnazione);
			if(ass!=null){
				ass.setProvvedimento(provvRecuperato);
				assegnazioneDAO.save(ass);
			}
		}
		//SALVATAGGIO ALLEGATI FK -> Provvedimento
		for(Integer idAllegato :provvedimentoIns.getIdAllegatiUpdList()){
			Allegato all = allegatoDAO.findById(idAllegato);
			if(all!=null){
				all.setProvvedimento(provvRecuperato);
				allegatoDAO.save(all);
			}
		}
		return provvRecuperato;
	}

	public List<Assegnazione> getListaAssegnazioneInserimento(
			List<Integer> idAllegatiUpdList) {
		List<Assegnazione> listAssegnazioneRet = new ArrayList<Assegnazione>();
		for(Integer idAss : idAllegatiUpdList){
			Assegnazione ass  = assegnazioneDAO.findById(idAss);
			if(ass!=null){
				listAssegnazioneRet.add(ass);
			}
		}
		return listAssegnazioneRet;
	}

	public List<Allegato> getListaAllegatiInserimento(List<Integer> idAllegatiUpdList) {
		List<Allegato> listAllegatoRet = new ArrayList<Allegato>();
		for(Integer idAll : idAllegatiUpdList){
			Allegato all  = allegatoDAO.findById(idAll);
			if(all!=null){
				listAllegatoRet.add(all);
			}
		}
		return listAllegatoRet;
	}

	public List<Storico> getCronologiaAssegnatario(Integer idAss) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(idAss)){
			params.put("idEntita", idAss);
		}
		List<Storico> listaStorico = storicoDAO.findByProperty(params);
		return listaStorico;
	}
	
	public Storico inserisciStoricoAssegnatario(Storico stor) {
		storicoDAO.save(stor);
		return stor;
	}

	
	public Assegnazione recuperaAssegnazioneById(Integer id) {
		return assegnazioneDAO.findById(id);
	}

	public void inserisciInviaSolleciti(SollecitoDto sollecitoDto) {
		Assegnazione assegnazione = assegnazioneDAO.findById( Integer.valueOf( sollecitoDto.getIdAssegnatarioSollecito() ) );
		Mail mail = new Mail();
		mail.setSubject(sollecitoDto.getOggettoSollecito());
		mail.setContent(sollecitoDto.getTestoSollecito());
		mail.setHtmlFormat(false);
		for( Utente tmp : assegnazione.getOrgano().getUtenteList() ){
			mail.setDestinatario(tmp.getEmail());
			mailService.eseguiInvioMail(mail);
		}
	}

	public void invioMail(Mail mail) {
		mailService.eseguiInvioMail(mail);
		
	}

	private void invioNotificaCambioStato(Provvedimento provvedimento) {
		CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		// invio notifica operativa
		if (provvedimento.getStato().getCodice().equals(Constants.STATO_IN_ISTRUTTORIA)) {
			
			
			for (Assegnazione assegnazione : provvedimento.getAssegnazioneList()) {
				String testo = "L'utente " + user.getUtente().getNome() + " " + user.getUtente().getCognome() + " richiede che l'organo " + assegnazione.getOrgano().getDenominazione() + " prenda in carico la gestione del provvedimento  " 
						+ provvedimento.getGoverno().getDenominazione() + " " + provvedimento.getId() + ", fonte normativa " + provvedimento.getFonteNormativa() + " avente capofila l'organo " + provvedimento.getOrganoCapofila().getDenominazione(); 
				Notifica notifica = new Notifica();
				notifica.setFlagLettura(Notifica.NON_LETTA);
				notifica.setTipoNotifica(Notifica.OPERATIVA);
				notifica.setOggetto("Richiesta presa in carico del provvedimento");
				notifica.setTesto(testo);
				notifica.setOrganoDestinatario(assegnazione.getOrgano());
				notifica.setUtenteMittente(user.getUtente());
				notifica.setLinkOperazione("/private/provvedimenti/ricerca/dettaglio?id=" + provvedimento.getId());
				notificaDAO.save(notifica);			
			}
		} else {
			// invio notifica informativa in tutti i casi diversi da non inserito
			if (!provvedimento.getStato().getCodice().equals(Constants.STATO_INSERITO)) {
				for (Assegnazione assegnazione : provvedimento.getAssegnazioneList()) {
					String testo = "Il provvedimento " 	+ provvedimento.getGoverno().getDenominazione() + " " + provvedimento.getId() + ", fonte normativa " + provvedimento.getFonteNormativa() 
								+ " ha assunto lo stato " + provvedimento.getStato().getDescrizione(); 
					Notifica notifica = new Notifica();
					notifica.setFlagLettura(Notifica.NON_LETTA);
					notifica.setTipoNotifica(Notifica.INFORMATIVA);
					notifica.setOggetto("Cambio stato del provvedimento");
					notifica.setTesto(testo);
					
					for (Utente utenteDestinatario : utenteDAO.findAttiviByOrgano(assegnazione.getOrgano().getId()) ) {
						notifica.setUtenteDestinatario(utenteDestinatario);
						notificaDAO.save(notifica);
					}					
				}	
			}
		}
		
	}
	
	public Assegnazione recuperaAssegnazioneByProvvOrgano(AssegnazioneDto assDto) {
		Assegnazione retval = null;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("idOrgano", assDto.getIdOrgano());
		params.put("idProvvedimento", assDto.getIdProvvedimento());
		
		String hql = "from Assegnazione u where u.organo.id = :idOrgano and u.provvedimento.id = :idProvvedimento";
		List<Assegnazione> assegnazioni = assegnazioneDAO.findByHqlQuery(hql, params);                    
		for(Assegnazione tmp: assegnazioni){
			retval = tmp;
		}
		return retval;
	}
	

	public Nota recuperaNotaByAssegnazione(Assegnazione assegnazione){
		Nota retval = null;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("idAssegnazione", assegnazione.getId());
		
		String hql = "from Nota u where u.assegnazione.id = :idAssegnazione";
		List<Nota> note = notaDAO.findByHqlQuery(hql, params);                    
		for(Nota tmp: note){
			retval = tmp;
		}
		return retval;
	}
	
	public Nota recuperaNotaById(Integer id) {
		return notaDAO.findById(id);
	}

	public Nota aggiornaNota(Integer idNota, String testoNota) {
		Nota nota = recuperaNotaById(idNota);
		nota.setTesto(notaDAO.createClob(testoNota));
		return notaDAO.merge(nota);
	}

	public void inserisciNota(Nota nota, String testoNota) {
		nota.setTesto(notaDAO.createClob(testoNota));
		notaDAO.save(nota);
	}

	public List<ProvvedimentoStampaDto> recuperaProvvedimentiPerExport(){
		List<String> order = new ArrayList<String>();
		order.add("dataInserimento desc");
		List<Provvedimento> provvedimenti = provvedimentoDAO.findAll(order);
		
		List<ProvvedimentoStampaDto> provvedimentiDto = new ArrayList<ProvvedimentoStampaDto>();
		for (Provvedimento provvedimento : provvedimenti) {
			provvedimentiDto.add(new ProvvedimentoStampaDto(provvedimento));
		}
		
		return provvedimentiDto;	
	}
}
