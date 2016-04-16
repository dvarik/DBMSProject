package com.relcare.authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.relcare.db.RelcareDao;

/**
 * @author dvarik 
 * custom authentication logic
 *
 */
@Component("relAuthenticator")
public class RelAuthenticator implements AuthenticationProvider {

	@Autowired
	private RelcareDao dao;

	@Autowired
	private RelUserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken auth = null;

		String username = String.valueOf(authentication.getPrincipal());
		String givenPassword = String.valueOf(authentication.getCredentials());
		UserDetails user = null;
		try {
			if (dao.authenticateUser(username, givenPassword)) {
				user = userService.loadUserByUsername(username);
			} else {
				throw new BadCredentialsException("Wrong password.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return auth;
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
