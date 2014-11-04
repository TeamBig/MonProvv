package it.tesoro.monprovv.facade;

import it.tesoro.monprovv.dao.AllegatoDAO;
import it.tesoro.monprovv.dao.AssegnazioneDAO;
import it.tesoro.monprovv.dao.GovernoDAO;
import it.tesoro.monprovv.dao.OrganoDAO;
import it.tesoro.monprovv.dao.ProvvedimentoDAO;
import it.tesoro.monprovv.dao.StatoDAO;
import it.tesoro.monprovv.dao.TipoAttoDAO;
import it.tesoro.monprovv.dao.TipoProvvDaAdottareDAO;
import it.tesoro.monprovv.dao.TipoProvvedimentoDAO;
import it.tesoro.monprovv.dto.RicercaProvvedimentoDto;
import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoAtto;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;
import it.tesoro.monprovv.utils.Constants;
import it.tesoro.monprovv.utils.SearchPatternUtil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	public List<Stato> initStato(){
		List<String> order = new ArrayList<String>();
		order.add("descrizione");
		List<Stato> listaStati = statoDAO.findAll(order);
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
		Stato stato = statoDAO.findById(Constants.ASSEGNATO_ID);
		Assegnazione assegnazione = new Assegnazione();
		assegnazione.setOrgano(organo);
		assegnazione.setProvvedimento(provv);
		assegnazione.setStato(stato);
		assegnazioneDAO.save(assegnazione);
		return assegnazione;
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

}
