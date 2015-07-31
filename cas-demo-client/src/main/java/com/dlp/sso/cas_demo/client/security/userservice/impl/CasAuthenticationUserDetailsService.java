package com.dlp.sso.cas_demo.client.security.userservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CasAuthenticationUserDetailsService implements
		AuthenticationUserDetailsService<Authentication> {

	public UserDetails loadUserDetails(Authentication authentication)
			throws UsernameNotFoundException {

		CasAssertionAuthenticationToken assertionToken = (CasAssertionAuthenticationToken) authentication;
		Map<String, Object> serverAttributes = null;
		if (assertionToken != null && assertionToken.getAssertion() != null
				&& assertionToken.getAssertion().getPrincipal() != null) {
			serverAttributes = assertionToken.getAssertion().getPrincipal()
					.getAttributes();
		}

		// roles from cas server can be merged here
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		CasAuthenticatedUser user = new CasAuthenticatedUser(
				authentication.getName(), authentication.getCredentials()
						.toString(), authorities);
		if (!serverAttributes.isEmpty()) {
			user.setEmailId(serverAttributes.get("email").toString());
			user.setFirstName(serverAttributes.get("first_name").toString());
			user.setLastName(serverAttributes.get("last_name").toString());
		}

		return user;
	}

}
