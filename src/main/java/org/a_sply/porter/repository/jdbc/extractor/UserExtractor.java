package org.a_sply.porter.repository.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.a_sply.porter.domain.User;
import org.a_sply.porter.repository.table_info.users.UsersAuthoritiesTable;
import org.a_sply.porter.repository.table_info.users.UsersTable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserExtractor implements ResultSetExtractor<User> {
	
	private final static UsersAuthoritiesTable USERS_AUTHORITIES = new UsersAuthoritiesTable();
	private final static UsersTable USERS = new UsersTable();

	@Override
	public User extractData(ResultSet rs) throws SQLException, DataAccessException {
		User user = null;
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		while(rs.next()){
			if(user == null)
				user = new User(
						rs.getInt(USERS.ID()), 
						rs.getString(USERS.NAME()), 
						rs.getString(USERS.EMAIL()), 
						rs.getString(USERS.TELEPHONE()), 
						rs.getString(USERS.PASSWORD()), null);
			authorities.add(new SimpleGrantedAuthority(rs.getString(USERS_AUTHORITIES.AUTHORITY())));
		}
		 
		user.setAuthorities(authorities);
		return user;
	}

}
