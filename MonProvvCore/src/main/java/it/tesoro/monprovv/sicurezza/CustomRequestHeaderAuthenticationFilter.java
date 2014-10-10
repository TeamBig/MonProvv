package it.tesoro.monprovv.sicurezza;

import it.tesoro.monprovv.facade.GestioneSicurezzaFacade;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;



public class CustomRequestHeaderAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
	
    private GestioneSicurezzaFacade gestioneSicurezzaFacade;
    private boolean exceptionIfHeaderMissing = true;

    private String oamAbilitato;
    private String oamLogoutUrl;
    
    
    
    /**
     * Read and returns the header named by {@code principalRequestHeader} from the request.
     *
     * @throws PreAuthenticatedCredentialsNotFoundException if the header is missing and {@code exceptionIfHeaderMissing}
     *          is set to {@code true}.
     */
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    	
        String principal = gestioneSicurezzaFacade.encodeJSON(request);
        
        if (principal == null && exceptionIfHeaderMissing) {
            throw new PreAuthenticatedCredentialsNotFoundException(" headers not found in request.");
        }
        
        return principal;
    }

    /**
     * Credentials aren't usually applicable, but if a {@code credentialsRequestHeader} is set, this
     * will be read and used as the credentials value. Otherwise a dummy value will be used.
     */
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }


    /**
     * Defines whether an exception should be raised if the principal header is missing. Defaults to {@code true}.
     *
     * @param exceptionIfHeaderMissing set to {@code false} to override the default behaviour and allow
     *          the request to proceed if no header is found.
     */
    public void setExceptionIfHeaderMissing(boolean exceptionIfHeaderMissing) {
        this.exceptionIfHeaderMissing = exceptionIfHeaderMissing;
    }

	public void setGestioneSicurezzaFacade(
			GestioneSicurezzaFacade gestioneSicurezzaFacade) {
		this.gestioneSicurezzaFacade = gestioneSicurezzaFacade;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult) {
		
		super.successfulAuthentication(request, response, authResult);
		CustomUser user = (CustomUser)authResult.getPrincipal();

		//gestioneSicurezzaFacade.updateDataUltimoAccessoUtente(user.getIdUtente());

		if (user.getRuoliDaScegliere() != null) {
			try {
				// memorizzo la target url nell'oggetto PdaUser
				String queryString = request.getQueryString();
				
				user.setTargetURL(request.getServletPath() + ( (queryString != null) ? "?" + queryString : ""));
				
				response.sendRedirect(request.getContextPath() + "/private/sceltaRuolo");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public String getOamAbilitato() {
		return oamAbilitato;
	}

	public void setOamAbilitato(String oamAbilitato) {
		this.oamAbilitato = oamAbilitato;
	}

	public String getOamLogoutUrl() {
		return oamLogoutUrl;
	}

	public void setOamLogoutUrl(String oamLogoutUrl) {
		this.oamLogoutUrl = oamLogoutUrl;
	}

	
}
