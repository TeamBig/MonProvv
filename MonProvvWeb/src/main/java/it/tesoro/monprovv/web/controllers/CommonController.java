package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.facade.GestioneEntiFacade;
import it.tesoro.monprovv.facade.GestioneSicurezzaFacade;
import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.facade.GestioneUtenteFacade;
import it.tesoro.monprovv.model.Funzione;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Menu;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoAtto;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;
import it.tesoro.monprovv.model.UnitaOrgAstage;
import it.tesoro.monprovv.web.propertyeditors.ClobPropertyEditor;
import it.tesoro.monprovv.web.propertyeditors.DataPropertyEditor;
import it.tesoro.monprovv.web.propertyeditors.GovernoPropertyEditor;
import it.tesoro.monprovv.web.propertyeditors.OrganoPropertyEditor;
import it.tesoro.monprovv.web.propertyeditors.StatoPropertyEditor;
import it.tesoro.monprovv.web.propertyeditors.TipoAttoPropertyEditor;
import it.tesoro.monprovv.web.propertyeditors.TipoProvvDaAdottarePropertyEditor;
import it.tesoro.monprovv.web.propertyeditors.TipoProvvedimentoPropertyEditor;
import it.tesoro.monprovv.web.propertyeditors.UnitaOrgAstagePropertyEditor;

import java.sql.Clob;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CommonController {
	
	@Autowired
	private GestioneSicurezzaFacade sicurezzaFacade;
	
	@Autowired
	private GestioneTipologicaFacade tipologicaFacade;
	
	@Autowired
	private GestioneEntiFacade gestioneEntiFacade;
	
	@Autowired
	private GestioneUtenteFacade gestioneUtenteFacade;
	
	@InitBinder
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Stato.class, new StatoPropertyEditor(tipologicaFacade));
		binder.registerCustomEditor(Governo.class, new GovernoPropertyEditor(tipologicaFacade));
		binder.registerCustomEditor(TipoProvvedimento.class, new TipoProvvedimentoPropertyEditor(tipologicaFacade));
		binder.registerCustomEditor(TipoProvvDaAdottare.class, new TipoProvvDaAdottarePropertyEditor(tipologicaFacade));
		binder.registerCustomEditor(Date.class, new DataPropertyEditor());
		binder.registerCustomEditor(UnitaOrgAstage.class, new UnitaOrgAstagePropertyEditor(gestioneEntiFacade));
		binder.registerCustomEditor(Clob.class, new ClobPropertyEditor());
		binder.registerCustomEditor(Organo.class, new OrganoPropertyEditor(gestioneEntiFacade));
		binder.registerCustomEditor(TipoAtto.class, new TipoAttoPropertyEditor(tipologicaFacade));
	}
	
	
	@ModelAttribute("firstLevelMenu")
	public List<Menu> getFirstLevelMenu() {
		return sicurezzaFacade.getFirstLevelMenu();
	}
	
	@ModelAttribute("funzioni")
	public List<Funzione> getFunzioni() {
		return sicurezzaFacade.getFunzioni();
	}
}
