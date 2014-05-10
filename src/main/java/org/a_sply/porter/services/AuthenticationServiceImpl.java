package org.a_sply.porter.services;

import org.a_sply.porter.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * AuthenticationService implementation. 
 * @author LCH
 */

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Override
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return (User) principal;
	}
}
