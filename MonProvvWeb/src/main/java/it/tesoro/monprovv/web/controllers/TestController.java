package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.facade.TestFacade;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.web.utils.AlertUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
	
	protected static Logger logger = Logger.getLogger(TestController.class);
	
	@Autowired
	private AlertUtils alertUtils;
	
	@Autowired
	private TestFacade testFacade;
	
	@RequestMapping(value="/public/testexp")
	public String testexp(Model model)  {
		
		Provvedimento provvedimento = testFacade.recuperaProvvedimentoById(1);
		model.addAttribute("provv", provvedimento);
		//alertUtils.message(model, AlertUtils.ALERT_TYPE_INFO, "OK", false);
		return "test";
	}

	@RequestMapping(value="/private/testexp2", method = RequestMethod.GET)	
	public String testexp2(@RequestParam String id, Model model)  {
		
		testFacade.prova(id);
		
		alertUtils.message(model, AlertUtils.ALERT_TYPE_INFO, "OK", false);
		return "home";
	}
	

	@RequestMapping(value="/private/testexp3", method = RequestMethod.GET)	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PreAuthorize("hasPermission(#id, 'modifica')")
	public String testexp3(@RequestParam String id, Model model)  {
		
		alertUtils.message(model, AlertUtils.ALERT_TYPE_INFO, "OK", false);
		return "home";
	}

}
