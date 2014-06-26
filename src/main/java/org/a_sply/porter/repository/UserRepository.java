package org.a_sply.porter.repository;

import org.a_sply.porter.domain.User;

public interface UserRepository {

	User save(User user);

	boolean containsEmail(String email);

	boolean contains(User user);

	User findByEmail(String email);

	boolean containsName(String name);

	User findByName(String name);

}
