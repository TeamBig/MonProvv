package it.tesoro.monprovv.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import it.tesoro.monprovv.facade.GestioneSicurezzaFacade;
import it.tesoro.monprovv.facade.GestioneTipologicaFacade;
import it.tesoro.monprovv.model.Funzione;
import it.tesoro.monprovv.model.Menu;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.web.propertyeditors.StatoPropertyEditor;

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
