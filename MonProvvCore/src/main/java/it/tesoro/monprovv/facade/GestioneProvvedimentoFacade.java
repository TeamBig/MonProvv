package it.tesoro.monprovv.facade;

import it.tesoro.monprovv.dao.ProvvedimentoDAO;
import it.tesoro.monprovv.dao.TipoProvvedimentoDAO;
import it.tesoro.monprovv.model.TipoProvvedimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("gestioneProvvedimentoFacade")
public class GestioneProvvedimentoFacade {
	
	@Autowired
	private ProvvedimentoDAO provvedimentoDAO;
	
	@Autowired 
	private TipoProvvedimentoDAO tipoProvvedimentoDAO;
	
	public List<TipoProvvedimento> initDdl(){
		List<String> orderTipoProvvedimento = new ArrayList<String>();
		orderTipoProvvedimento.add("descrizione");
		List<TipoProvvedimento> listaTipoProvvedimento = tipoProvvedimentoDAO.findAll(1, orderTipoProvvedimento);
		return listaTipoProvvedimento;
	}
	
	public void ricercaProvvedimenti(){

	}

}
