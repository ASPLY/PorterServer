package org.a_sply.porter.services.Impl;

import java.io.UnsupportedEncodingException;

import org.a_sply.porter.dao.interfaces.UserDao;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.email.CheckEmailDTO;
import org.a_sply.porter.dto.user.CheckNameDTO;
import org.a_sply.porter.dto.user.CreateUserDTO;
import org.a_sply.porter.dto.user.LoginUserDTO;
import org.a_sply.porter.services.UserService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService implementation. 
 * @author LCH
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void create(CreateUserDTO createUserDTO) {
		String name = createUserDTO.getName();				
		String email = createUserDTO.getEmail();														
		String password = passwordEncoder.encode(createUserDTO.getPassword());						
		String telephone = createUserDTO.getTelephone();											
		User user = new User(name, email, telephone, password);
		System.out.println(buildBasicAuthHeaderValue(createUserDTO.getEmail(), createUserDTO.getPassword()));
		userDao.insert(user);
	}

	@Override
	public boolean check(CheckEmailDTO checkEmailDTO) {
		if (userDao.containsEmail(checkEmailDTO.getEmail()))
			return false;
		return true;
	}


	@Override
	public boolean login(LoginUserDTO loginUserDTO) {
		UserDetails userDetails = userDao.selectByEmail(loginUserDTO.getEmail());
		if(userDetails == null)
			return false;
		return passwordEncoder.matches(loginUserDTO.getPassword(), userDetails.getPassword());
	}

	public void setUserRepository(UserDao userRepository) {
		this.userDao = userRepository;
	}

	@Override
	public boolean check(CheckNameDTO checkNameDTO) {
		if (userDao.containsName(checkNameDTO.getName()))
			return false;
		return true;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User findByEmail = userDao.selectByEmail(username);
		return findByEmail;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	public static final String buildBasicAuthHeaderValue(String username, String password){
        String authHeaderFormat = "Basic ";
        String encodingRawData = String.format("%s:%s", username, password);
        String encodingData;
		try {
			encodingData = authHeaderFormat + new String(Base64.encodeBase64(encodingRawData.getBytes("utf-8")));
			return encodingData;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
    }
}
