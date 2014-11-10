package it.tesoro.monprovv.facade;

import it.tesoro.monprovv.dao.AllegatoDAO;
import it.tesoro.monprovv.dao.AssegnazioneDAO;
import it.tesoro.monprovv.dao.GovernoDAO;
import it.tesoro.monprovv.dao.OrganoDAO;
import it.tesoro.monprovv.dao.ProvvedimentiParentDAO;
import it.tesoro.monprovv.dao.ProvvedimentoDAO;
import it.tesoro.monprovv.dao.StatoDAO;
import it.tesoro.monprovv.dao.StoricoDAO;
import it.tesoro.monprovv.dao.TipoAttoDAO;
import it.tesoro.monprovv.dao.TipoProvvDaAdottareDAO;
import it.tesoro.monprovv.dao.TipoProvvedimentoDAO;
import it.tesoro.monprovv.dto.InserisciProvvedimentoDto;
import it.tesoro.monprovv.dto.RicercaProvvedimentoDto;
import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.ProvvedimentiParent;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.Storico;
import it.tesoro.monprovv.model.TipoAtto;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;
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
	
	
	public List<Stato> initStato(){
		List<String> order = new ArrayList<String>();
		order.add("descrizione");
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
		provvRecuperato = provvRecuperato.getProvvedimentoToUpdate(provvedimento);
		Provvedimento provMerge = provvedimentoDAO.merge(provvRecuperato);
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

}
