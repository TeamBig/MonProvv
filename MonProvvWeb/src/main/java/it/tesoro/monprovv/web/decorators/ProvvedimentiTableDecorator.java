package it.tesoro.monprovv.web.decorators;

import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.sicurezza.CustomUser;

import org.displaytag.decorator.TableDecorator;
import org.springframework.security.core.context.SecurityContextHolder;

public class ProvvedimentiTableDecorator extends TableDecorator {

	@Override
	public String addRowClass() {
		
		CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int orgId = user.getUtente().getOrgano().getId();
		
		Provvedimento provvedimento = (Provvedimento)getCurrentRowObject();
		String colorClass = "";
		if (provvedimento != null) {
			
			if ( (provvedimento.getOrganoCapofila().getId() == orgId)
				|| (provvedimento.getOrganoInseritore().getId() == orgId) ) {
				colorClass = "color-capofila";
			}
			
			for (Assegnazione ass : provvedimento.getAssegnazioneList()) {
				if (ass.getOrgano().getId() == orgId ) {
					colorClass = "color-assegnatario";
				}
			}				
		}
		
		
		return colorClass;
	}
	
	

}
