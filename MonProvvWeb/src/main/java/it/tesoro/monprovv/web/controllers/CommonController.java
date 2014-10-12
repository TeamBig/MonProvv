package it.tesoro.monprovv.web.controllers;

import java.util.List;

import it.tesoro.monprovv.facade.GestioneSicurezzaFacade;
import it.tesoro.monprovv.model.Funzione;
import it.tesoro.monprovv.model.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CommonController {
	
	@Autowired
	private GestioneSicurezzaFacade sicurezzaFacade;
	
	@InitBinder
	public void initBinder() {
		// TODO
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
