package org.a_sply.porter.services.Impl;

import java.util.List;

import org.a_sply.porter.dao.interfaces.SelectType;
import org.a_sply.porter.dao.interfaces.UserDao;
import org.a_sply.porter.domain.BasicAuthHeaderValue;
import org.a_sply.porter.domain.user.User;
import org.a_sply.porter.domain.user.UserCondition;
import org.a_sply.porter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
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
	public void create(User user) {
		System.out.println("BasicAuthHeaderValue : " + new BasicAuthHeaderValue(user.getEmail(), user.getPassword()).getBasicAuthHeaderValue());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
		userDao.insert(user);
	}

	@Override
	public boolean isContains(UserCondition userCondition) {
		return userDao.selectByCondition(SelectType.WITHOUT_DETAIL, userCondition).size() != 0 ? true : false;
	}

	@Override
	public boolean login(String email, String password) {
		UserCondition userCondition = new UserCondition();
		userCondition.setEmail(email);
		
		List<User> users = userDao.selectByCondition(SelectType.WITHOUT_DETAIL, userCondition);
		if(users.size() == 0)
			return false;
		
		UserDetails userDetails = users.get(0);
		return passwordEncoder.matches(password, userDetails.getPassword());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCondition userCondition = new UserCondition();
		userCondition.setEmail(username);
		List<User> selectByCondition = userDao.selectByCondition(SelectType.WITH_DETAIL, userCondition);
		return selectByCondition.size() == 0 ? null : selectByCondition.get(0);
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
