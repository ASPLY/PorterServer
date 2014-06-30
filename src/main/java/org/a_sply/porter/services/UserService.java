package org.a_sply.porter.services;

import org.a_sply.porter.domain.user.User;
import org.a_sply.porter.domain.user.UserCondition;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService extends UserDetailsService {
	
	void create(User user);
	boolean isContains(UserCondition userCondition);
	
	boolean login(String email, String password);
	
	void setPasswordEncoder(PasswordEncoder passwordEncoder);
	PasswordEncoder getPasswordEncoder();
}

