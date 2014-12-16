package it.tesoro.monprovv.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LegendaController {
	@RequestMapping(value = { "/private/legenda/home" } , method = RequestMethod.GET)
	public String init(
			Model model) {
		
		return "legendaMonProvv";
	}
}
