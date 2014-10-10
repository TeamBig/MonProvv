package it.tesoro.monprovv.sicurezza;

import it.tesoro.monprovv.facade.GestioneSicurezzaFacade;
import it.tesoro.monprovv.model.RuoloFunzione;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component("permissionEvaluator")
public class CustomPermissionEvaluator implements PermissionEvaluator {
	
    @Value("#{config['oam.abilitato']}")
    private String oamAbilitato;
	
	@Autowired
	private GestioneSicurezzaFacade gestioneSicurezzaFacade;
	
	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		throw new RuntimeException("Id and Class permissions are not supperted by " + this.getClass().toString());
	}
	
	private Map<String, List<String>> alberoPermessi;
	
	public void refreshAlberoPermessi() {
		List<RuoloFunzione> ruoloFunzioneList = gestioneSicurezzaFacade.recuperaRuoliFunzione();
		
		alberoPermessi = new HashMap<String, List<String>>();
		
		for (RuoloFunzione ruoloFunzione : ruoloFunzioneList) {
			String url = ruoloFunzione.getFunzione().getUrl();
			List<String> ruoliList = alberoPermessi.get(url);
			if (ruoliList == null) {
				ruoliList = new ArrayList<String>();
			}
			ruoliList.add(ruoloFunzione.getRuolo().getCodice());
			
			alberoPermessi.put(url, ruoliList);
		}
	}
	
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if (alberoPermessi == null) 
			refreshAlberoPermessi();
		
		// il parametro "permission" e' ignorato in questa configurazione
		// targetDomainObject == oggetto request 
		String requestUrl = (String)targetDomainObject; //((SecurityContextHolderAwareRequestWrapper)targetDomainObject).getServletPath();
		
		
		AntPathMatcher matcher = new AntPathMatcher();
		int matchCount = 0;
		int authCount = 0;
		
		Iterator<String> iter = alberoPermessi.keySet().iterator();
		
		while (iter.hasNext()) {
			
			String url = iter.next();
			if (matcher.match(url, requestUrl)) {
				matchCount++;
				for (GrantedAuthority roleUser : authentication.getAuthorities()) {
					if (alberoPermessi.get(url).contains(roleUser.getAuthority()))
						authCount++;
				}
				
			}
			
		}
		
		return (matchCount == authCount);
	}	
}
