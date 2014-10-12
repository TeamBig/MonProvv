package it.tesoro.monprovv.web.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class AlertUtils {
	
	public final static String ALERT_TYPE_ERROR = "alert-error";
	public final static String ALERT_TYPE_WARNING = "alert-warning";
	public final static String ALERT_TYPE_SUCCESS = "alert-success";
	public final static String ALERT_TYPE_INFO = "alert-info";
	
	private final static String ALERT_MESSAGE_DELIMETER = "*##*";

	@Autowired
	private MessageSource messageSource;


	public void message(RedirectAttributes redirectAttributes, String type, String message, 
			boolean fromProperty) {
		
		if (redirectAttributes == null)
			return;
		
		redirectAttributes.addFlashAttribute("alertType", type);
		
		redirectAttributes.addFlashAttribute("alertDelimeter", ALERT_MESSAGE_DELIMETER);
		
		String alertMessage = message;
		if (fromProperty)
			alertMessage = messageSource.getMessage(message, null, null);
		
		Map<String, ?> attributes = redirectAttributes.getFlashAttributes();
		
		if (attributes != null) {
			String oldAlertMessage = (String)attributes.get("alertMessage");
			
			if (StringUtils.isNotBlank(oldAlertMessage)) {
				alertMessage = oldAlertMessage + ALERT_MESSAGE_DELIMETER + alertMessage;
				redirectAttributes.addFlashAttribute( "alertStyle", "multiple");	
			} else {
				redirectAttributes.addFlashAttribute( "alertStyle", "single");	
			}
		}
		redirectAttributes.addFlashAttribute( "alertMessage", alertMessage);
		
	}
	
	public void message(Model model, String type, String message, 
			boolean fromProperty) {
		
		if (model == null)
			return;
		
		model.addAttribute("alertType", type);
		
		model.addAttribute("alertDelimeter", ALERT_MESSAGE_DELIMETER);
		
		String alertMessage = message;
		if (fromProperty)
			alertMessage = messageSource.getMessage(message, null, null);
		
		Map<String, Object> attributes = model.asMap();
		
		if (attributes != null) {
			String oldAlertMessage = (String)attributes.get("alertMessage");
			
			if (StringUtils.isNotBlank(oldAlertMessage)) {
				alertMessage = oldAlertMessage + ALERT_MESSAGE_DELIMETER + alertMessage;
				model.addAttribute( "alertStyle", "multiple");	
			} else {
				model.addAttribute( "alertStyle", "single");	
			}
		}
		model.addAttribute( "alertMessage", alertMessage);
		
	}	
	
	
	public void message(Model model, String type, FieldError f) {
		String message = null;
		try {
			message = messageSource.getMessage(f.getCodes()[0], null, null);
		} catch (Exception e) {
			message = f.getDefaultMessage();
		}
				
		message(model, type, message, false);
	}
	
	public void message(RedirectAttributes redirectAttributes, String type, FieldError f) {
		String message = null;
		try {
			message = messageSource.getMessage(f.getCodes()[0], null, null);
		} catch (Exception e) {
			message = f.getDefaultMessage();
		}
				
		message(redirectAttributes, type, message, false);
	}
}
