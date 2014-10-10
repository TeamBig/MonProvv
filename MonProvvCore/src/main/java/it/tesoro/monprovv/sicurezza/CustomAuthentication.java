package it.tesoro.monprovv.sicurezza;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthentication implements Authentication {
	
	private static final long serialVersionUID = -4035273961342095057L;
	
	
	private Authentication wrapped;
    private List<GrantedAuthority> authorities;
        

	public CustomAuthentication(Authentication wrapped) {
		super();
		this.wrapped = wrapped;
		this.authorities = new ArrayList<GrantedAuthority>(wrapped.getAuthorities());
	}

	@Override
	public String getName() {
		return wrapped.getName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public Object getCredentials() {
		return wrapped.getCredentials();
	}

	@Override
	public Object getDetails() {
		return wrapped.getDetails();
	}

	@Override
	public Object getPrincipal() {
		return wrapped.getPrincipal();
	}

	@Override
	public boolean isAuthenticated() {
		return wrapped.isAuthenticated();
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
		wrapped.setAuthenticated(arg0);
	}

	public boolean addAuthority(GrantedAuthority authority) {
		return authorities.add(authority);
	}

}
