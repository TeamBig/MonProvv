package it.tesoro.monprovv.facade;



import it.tesoro.monprovv.dao.GovernoDAO;
import it.tesoro.monprovv.dao.OrganoDAO;
import it.tesoro.monprovv.dao.ProvvedimentoDAO;
import it.tesoro.monprovv.dao.StatoDAO;
import it.tesoro.monprovv.dao.TipoProvvDaAdottareDAO;
import it.tesoro.monprovv.dao.TipoProvvedimentoDAO;
import it.tesoro.monprovv.dao.UtenteDAO;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


@Component("gestionePregressoFacade")
public class GestionePregressoFacade {

	@Autowired
	private UtenteDAO utenteDAO;
	
	@Autowired
	private OrganoDAO organoDAO;
	
	@Autowired
	private GovernoDAO governoDAO; 
	
	@Autowired
	private TipoProvvedimentoDAO tipoProvvedimentoDAO;
	
	@Autowired
	private TipoProvvDaAdottareDAO tipoProvvDaAdottareDAO;
	
	@Autowired
	private StatoDAO statoDAO;
	
	@Autowired
	private ProvvedimentoDAO provvedimentoDAO;
	
	public Governo recuperaGovernoByCodice(String codice) {

		String hql = "from Governo g where upper(g.denominazione) = upper(:codice)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codice", codice);
		
		List<Governo> governi = governoDAO.findByHqlQuery(hql, params);
		if (!CollectionUtils.isEmpty(governi)) {
			return governi.get(0);
		}
		return null;
	}


	public TipoProvvedimento recuperaTipoProvvedimentoByDescrizione(String descrizione) {

		String hql = "from TipoProvvedimento tp where upper(tp.descrizione) = upper(:descrizione)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("descrizione", descrizione);
		
		List<TipoProvvedimento> tipi = tipoProvvedimentoDAO.findByHqlQuery(hql, params);
		if (!CollectionUtils.isEmpty(tipi)) {
			return tipi.get(0);
		}
		return null;
	}
	
	public TipoProvvDaAdottare recuperaTipoProvvedimentoDaAdottareByDescrizione(String descrizione) {

		String hql = "from TipoProvvDaAdottare tp where upper(tp.descrizione) = upper(:descrizione)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("descrizione", descrizione);
		
		List<TipoProvvDaAdottare> tipi = tipoProvvDaAdottareDAO.findByHqlQuery(hql, params);
		if (!CollectionUtils.isEmpty(tipi)) {
			return tipi.get(0);
		}
		return null;
	}
	
	public Stato recuperaStatoByDescrizione(String descrizione) {

		String hql = "from Stato tp where upper(tp.descrizione) = upper(:descrizione)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("descrizione", descrizione);
		
		List<Stato> stati = statoDAO.findByHqlQuery(hql, params);
		if (!CollectionUtils.isEmpty(stati)) {
			return stati.get(0);
		}
		return null;
	}
	
	
	public void inserisciProvvedimento(Provvedimento provvedimento) {
		provvedimentoDAO.save(provvedimento);
	}
	
	public Organo recuperaOrganoByDenominazione(String denominazione) {
		String hql = "from Organo o where upper(o.denominazione) = upper(:denominazione)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("denominazione", denominazione);
		
		List<Organo> organi = organoDAO.findByHqlQuery(hql, params);
		if (!CollectionUtils.isEmpty(organi)) {
			return organi.get(0);
		}
		return null;
	}
	
	
	
	public void eliminaPregresso() {
		String hql = "delete from Provvedimento p where p.utenteInserimento = 'PREGRESSO'";
		
		provvedimentoDAO.executeQuery(hql);
	}
}
