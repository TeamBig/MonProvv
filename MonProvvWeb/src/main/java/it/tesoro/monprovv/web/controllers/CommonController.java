package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.facade.GestioneSicurezzaFacade;
import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.Funzione;
import it.tesoro.monprovv.model.Governo;
import it.tesoro.monprovv.model.Menu;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.model.TipoProvvDaAdottare;
import it.tesoro.monprovv.model.TipoProvvedimento;
import it.tesoro.monprovv.web.propertyeditors.DataPropertyEditor;
import it.tesoro.monprovv.web.propertyeditors.GovernoPropertyEditor;
import it.tesoro.monprovv.web.propertyeditors.StatoPropertyEditor;
import it.tesoro.monprovv.web.propertyeditors.TipoProvvDaAdottarePropertyEditor;
import it.tesoro.monprovv.web.propertyeditors.TipoProvvedimentoPropertyEditor;

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
	
	@InitBinder
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Stato.class, new StatoPropertyEditor(tipologicaFacade));
		binder.registerCustomEditor(Governo.class, new GovernoPropertyEditor(tipologicaFacade));
		binder.registerCustomEditor(TipoProvvedimento.class, new TipoProvvedimentoPropertyEditor(tipologicaFacade));
		binder.registerCustomEditor(TipoProvvDaAdottare.class, new TipoProvvDaAdottarePropertyEditor(tipologicaFacade));
		binder.registerCustomEditor(Date.class, new DataPropertyEditor());
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
