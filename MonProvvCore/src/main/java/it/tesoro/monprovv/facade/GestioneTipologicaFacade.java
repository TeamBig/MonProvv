package it.tesoro.monprovv.facade;

import java.sql.Clob;

import it.tesoro.monprovv.dao.GovernoDAO;
import it.tesoro.monprovv.dao.StatoDAO;
import it.tesoro.monprovv.dao.TipoAttoDAO;
import it.tesoro.monprovv.dao.TipoProvvDaAdottareDAO;
import it.tesoro.monprovv.dao.TipoProvvedimentoDAO;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoAtto;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;

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

	public TipoProvvedimento recuperaTipoProvvedimentoById(Integer id) {
		return tipoProvvedimentoDAO.findById(id);
	}

	public TipoProvvDaAdottare recuperaTipoProvvDaAdottareById(Integer id) {
		return tipoProvvDaAdottareDAO.findById(id);
	}

	public TipoAtto recuperaTipoAttoById(Integer id) {
		return tipoAttoDAO.findById(id);
	}
	
	public Clob creaClob(String data) {
		return statoDAO.createClob(data);
	}
}
