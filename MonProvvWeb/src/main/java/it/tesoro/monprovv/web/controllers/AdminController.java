package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.sicurezza.CustomUser;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AdminController {

	protected static Logger logger = Logger.getLogger(AdminController.class);
	
	@RequestMapping(value= {"/private/admin"}, method = RequestMethod.GET)
	public String caricaHomepagePrivata(Model model, SecurityContextHolderAwareRequestWrapper request)  {
		String redirectUrl = null;
		
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomUser) {
			redirectUrl = "/private/admin/enti";
		} else {
			redirectUrl = "/public";
		}
		
		return "redirect:" + redirectUrl;
	}
	
}