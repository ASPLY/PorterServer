package com.porter.web.dao;

import org.apache.ibatis.annotations.Param;

import com.porter.web.dto.UserDTO;

public interface UserDao {
	void insert(UserDTO userDTO);
	UserDTO findByEmail(String email);
	UserDTO findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	UserDTO findById(int id);
}
