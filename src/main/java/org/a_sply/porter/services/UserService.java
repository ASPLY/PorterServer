package org.a_sply.porter.services;

import org.a_sply.porter.dto.email.CheckEmailDTO;
import org.a_sply.porter.dto.user.CheckNameDTO;
import org.a_sply.porter.dto.user.CreateUserDTO;
import org.a_sply.porter.dto.user.LoginUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService extends UserDetailsService {

	void createUser(CreateUserDTO createUserDTO);

	boolean check(CheckEmailDTO checkEmailDTO);

	boolean login(LoginUserDTO loginUserDTO);

	boolean check(CheckNameDTO checkNameDTO);
	
	void setPasswordEncoder(PasswordEncoder passwordEncoder);
	
	PasswordEncoder getPasswordEncoder();
}

