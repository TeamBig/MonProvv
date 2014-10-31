package it.tesoro.monprovv.utils;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.sicurezza.CustomUser;

public class SicurezzaUtils {

	public static Assegnazione assegnazioneCorrente(List<Assegnazione> listaAssegnazioni ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal() instanceof CustomUser) {
			CustomUser user = (CustomUser)auth.getPrincipal();
			for (Assegnazione ass : listaAssegnazioni) {
				if (ass.getOrgano().getId().equals(user.getUtente().getOrgano().getId()) ) {
					return ass;
				}
			}
		}
		
		
		return null;
	}
}
