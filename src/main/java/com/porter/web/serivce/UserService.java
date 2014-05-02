package com.porter.web.serivce;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.porter.web.dao.UserDao;
import com.porter.web.dto.UserDTO;
import com.porter.web.model.user.User;

@Service
public class UserService {

	@Resource
	private UserDao userDao;

	public void createUser(User user) {
		userDao.insert(user.dto());
	}

	public User get(String email) {
		UserDTO userDTO = userDao.findByEmail(email);
		return userDTO.toModel();
	}

	public boolean login(String email, String password) {
		if(userDao.findByEmailAndPassword(email, password) == null)
			return false;
		return true;
	}

	public User get(Long id) {
		UserDTO userDTO = userDao.findById(id.intValue());
		return userDTO.toModel();
	}
}
