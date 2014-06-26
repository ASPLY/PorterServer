package org.a_sply.porter.util;

import org.a_sply.porter.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtil {
	
	public static User getCurrentUser(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return (User) principal;
	}
}
