package it.tesoro.monprovv.facade;

import it.tesoro.monprovv.dao.GovernoDAO;
import it.tesoro.monprovv.dao.ProvvedimentoDAO;
import it.tesoro.monprovv.dao.StatoDAO;
import it.tesoro.monprovv.dao.TipoAttoDAO;
import it.tesoro.monprovv.dao.TipoProvvDaAdottareDAO;
import it.tesoro.monprovv.dao.TipoProvvedimentoDAO;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoAtto;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("gestioneTipologicaFacade")
public class GestioneTipologicaFacade {

	@Autowired
	private StatoDAO statoDAO;

	@Autowired
	private GovernoDAO governoDAO;

	@Autowired
	private TipoProvvedimentoDAO tipoProvvedimentoDAO;
	
	@Autowired
	private TipoProvvDaAdottareDAO tipoProvvDaAdottareDAO;
	
	@Autowired
	private TipoAttoDAO tipoAttoDAO;
	
	@Autowired
	private ProvvedimentoDAO provvedimentoDAO;

	
//	public Stato recuperaStatoByCod(String cod) {
//		List<Stato> statoList = statoDAO.findByProperty("codice", cod);
//		return statoList.size()>0?statoList.get(0):null;
//	}
	
	public Stato recuperaStatoById(Integer id) {
		return statoDAO.findById(id);
	}

	public Governo recuperaGovernoById(Integer id) {
		return governoDAO.findById(id);
	}
	
	public List<Governo> initGoverno(){
		List<String> order = new ArrayList<String>();
		order.add("denominazione");
		List<Governo> listaGoverno = governoDAO.findByPropertyOrdered("flagAttivo", "S", order);
		return listaGoverno;
	}

	public TipoProvvedimento recuperaTipoProvvedimentoById(Integer id) {
		return tipoProvvedimentoDAO.findById(id);
	}

	public TipoProvvDaAdottare recuperaTipoProvvDaAdottareById(Integer id) {
		return tipoProvvDaAdottareDAO.findById(id);
	}
	
	public List<TipoProvvDaAdottare> initTipoProvvDaAdottare() {
		List<String> order = new ArrayList<String>();
		order.add("descrizione");
		List<TipoProvvDaAdottare> listaTipoProvvedimento = tipoProvvDaAdottareDAO.findByPropertyOrdered("flagAttivo", "S", order);
		return listaTipoProvvedimento;
	}

	public TipoAtto recuperaTipoAttoById(Integer id) {
		return tipoAttoDAO.findById(id);
	}
	
	public List<TipoAtto> initTipoAtto() {
		List<String> order = new ArrayList<String>();
		order.add("descrizione");
		List<TipoAtto> listaTipoAtto = tipoAttoDAO.findByPropertyOrdered("flagAttivo", "S", order);
		return listaTipoAtto;
	}
	
	public Clob creaClob(String data) {
		return statoDAO.createClob(data);
	}
	
	public Provvedimento recuperaProvvedimentoById(Integer id) {
		return provvedimentoDAO.findById(id);
	}

	public Governo inserisciAggiornaGoverno(Governo governo) {
		Integer id = (Integer) governoDAO.saveOrUpdate(governo);
		return governoDAO.findById(id);
	}

	public TipoAtto inserisciAggiornaTipoAtto(TipoAtto tipoAtto) {
		Integer id = (Integer) tipoAttoDAO.saveOrUpdate(tipoAtto);
		return tipoAttoDAO.findById(id);
	}

	public TipoProvvDaAdottare inserisciAggiornaTipoProvvDaAdottare(TipoProvvDaAdottare tipoProvvDaAdottare) {
		Integer id = (Integer) tipoProvvDaAdottareDAO.saveOrUpdate(tipoProvvDaAdottare);
		return tipoProvvDaAdottareDAO.findById(id);
	}
}
