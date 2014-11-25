package it.tesoro.monprovv.web.controllers;

import java.net.InetAddress;

import it.tesoro.monprovv.dto.Mail;
import it.tesoro.monprovv.facade.TestFacade;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.service.MailService;
import it.tesoro.monprovv.web.utils.AlertUtils;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	protected static Logger logger = Logger.getLogger(TestController.class);
	
	@Autowired
	private AlertUtils alertUtils;
	
	@Autowired
	private TestFacade testFacade;
	
	@Autowired
	private MailService mailService;
	
	@Value("#{config['email.on']}")
	private String emailSwitch;

	@Value("#{config['email.smtp.server']}")
	private String smtpServer;

	@Value("#{config['email.smtp.port']}")
	private String smtpPort;

	@Value("#{config['email.smtp.user']}")
	private String smtpUser;

	@Value("#{config['email.smtp.pwd']}")
	private String smtpPassword;

	@Value("#{config['email.from.address']}")
	private String emailFromAddress;

	@Value("#{config['email.from.name']}")
	private String emailFromName;
	
	@Value("#{config['email.smtp.authreq']}")
	private String smtpAuthReq;
	
	@RequestMapping(value="/public/testexp")
	public String testexp(Model model)  {
		
		Provvedimento provvedimento = testFacade.recuperaProvvedimentoById(3);
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
	
	@RequestMapping(value = "/private/testmail", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String testmail(@RequestParam("to") String to) throws Exception {

		StringBuffer sb = new StringBuffer();
		
		String nodo = System.getProperty("weblogic.Name");
		String hostname = InetAddress.getLocalHost().getHostName();
		
		sb.append("<html><body><pre>");
		sb.append("Nodo: " + nodo + "\n");
		sb.append("Hostname: " + hostname + "\n");
		sb.append("Mail server: " + smtpServer + ":" + smtpPort + "\n");
		sb.append("Username: " + smtpUser + "\n");
		sb.append("Password: " + smtpPassword + "\n");
		sb.append("AuthReq: " + smtpAuthReq + "\n");
		
		try {
			Mail mail = new Mail();
			mail.setDestinatario(to);
			mail.setSubject("prova invio mail");
			mail.setContent("prova testo");
			mail.setHtmlFormat(false);
			
			mailService.eseguiInvioMail(mail);
		} catch (Exception e) {
			sb.append(ExceptionUtils.getFullStackTrace(e));
		}
		
		sb.append("</pre></body></html>");
		return sb.toString();
	}	
	
}
