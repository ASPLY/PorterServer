package org.a_sply.porter.dao.interfaces;

import org.a_sply.porter.domain.User;

public interface UserDao {

	int insert(User user);
	
	boolean containsEmail(String email);
	boolean contains(User user);
	
	boolean containsName(String name);
	
	User selectByEmail(String email);
	User selectById(int id);
	User selectByName(String name);
}
