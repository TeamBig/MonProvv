package it.tesoro.monprovv.sicurezza;

import it.tesoro.monprovv.facade.GestioneSicurezzaFacade;
import it.tesoro.monprovv.model.Condizione;
import it.tesoro.monprovv.model.RuoloFunzione;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component("permissionEvaluator")
public class CustomPermissionEvaluator implements PermissionEvaluator {
	
	private class CustomContext {
		private Authentication authentication;
		private Object target;
		
		public CustomContext(Authentication authentication,
				Object target) {

			this.authentication = authentication;
			this.target = target;
		}

		public Authentication getAuthentication() {
			return authentication;
		}

		public Object getTarget() {
			return target;
		}
		
	}
	
	@Autowired
	private GestioneSicurezzaFacade gestioneSicurezzaFacade;
	
	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		throw new RuntimeException("Id and Class permissions are not supperted by " + this.getClass().toString());
	}
	
	private Map<String, List<String>> alberoPermessi;
	private Map<String, Condizione> alberoCondizioni;
	
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
	
	public void refreshAlberoCondizioni() {
		List<Condizione> condizioneList = gestioneSicurezzaFacade.recuperaCondizione();
		
		alberoCondizioni = new HashMap<String, Condizione>();
		
		for (Condizione condizione : condizioneList) {
			String elemento = condizione.getElemento(); 
						
			alberoCondizioni.put(elemento, condizione);
		}
	}
	
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

		// GESTIONE URL PERMISSION
		if (permission instanceof String && "urlPermission".equals((String)permission)) {

			if (alberoPermessi == null) 
				refreshAlberoPermessi();
			
			String requestUrl = (String)targetDomainObject; 
						
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
		
		// visibilità pulsanti
//		- modifica 
//		- stato provvedimento = 1
//		- ruolo = inseritore
//		- condizione aggiuntiva: organo appartenenza = capofila oppure organo inseritore
		if (permission instanceof String) {
			
			if (alberoCondizioni == null) 
				refreshAlberoCondizioni();
			
			String elemento = (String)permission;
			Condizione condizione = alberoCondizioni.get(elemento);
			
			if (condizione == null) {
				return false;
			}
			
			Object target = targetDomainObject;
			
			if (targetDomainObject instanceof String) {
				// presumibilmente si tratta dell'id, utilizzo il facade per leggere l'opportuno elemento
				if ("Provvedimento".equals(condizione.getTarget())) {
					target = gestioneSicurezzaFacade.recuperaProvvedimento((String)targetDomainObject);
				}
				
				if ("Assegnazione".equals(condizione.getTarget())) {
					target = gestioneSicurezzaFacade.recuperaAssegnazione((String)targetDomainObject);
				}
				
				if ("Notifica".equals(condizione.getTarget())) {
					target = gestioneSicurezzaFacade.recuperaNotifica((String)targetDomainObject);
				}
			}
			
			CustomContext ctx = new CustomContext(authentication, target);
			EvaluationContext context = new StandardEvaluationContext(ctx);
			
			//String expression = "provvedimento.stato.id == 1 and authentication.authorities.?[authority == 'ROLE_INSERITORE'].size() != 0 and authentication.principal.utente.organo.id == provvedimento.organoInseritore.id";
			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(condizione.getEspressione());
			
			boolean result = exp.getValue(context, Boolean.class);
			
			return result;
		}
		
		

		throw new RuntimeException("Specificare un tipo di permesso da controllare!!");
	}	
}
