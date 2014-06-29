package org.a_sply.porter.dao.impl_jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.a_sply.porter.dao.table_info.users.UsersAuthoritiesTable;
import org.a_sply.porter.dao.table_info.users.UsersTable;
import org.a_sply.porter.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserExtractor implements ResultSetExtractor<User> {
	
	private final static UsersAuthoritiesTable USERS_AUTHORITIES = new UsersAuthoritiesTable();
	private final static UsersTable USERS = new UsersTable();

	@Override
	public User extractData(ResultSet rs) throws SQLException, DataAccessException {

		System.out.println("current row : "+rs.getRow());
		Long nextRowId = null;
		Long id = null;
		
		String name = null;
		String email = null;
		String telephone = null;
		String password = null;
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

		int processedRowCount = 0;
		if(!rs.next())
			return null;
		else
			do{
				nextRowId = rs.getLong(USERS.USER_ID());
	        	if(id != null && id != nextRowId)
	        		break;
	        	
	        	if(name == null)
	        		name = rs.getString(USERS.NAME());
	        	
	        	if(email == null)
	        		email = rs.getString(USERS.EMAIL());
	        	
	        	if(telephone == null)
	        		telephone = rs.getString(USERS.TELEPHONE());
	        	
	        	if(password == null)
	        		password = rs.getString(USERS.PASSWORD());
	        	
				authorities.add(new SimpleGrantedAuthority(rs.getString(USERS_AUTHORITIES.AUTHORITY())));
				
	        	id = nextRowId;
	        	
	        	processedRowCount++;
			}while(rs.next());			
		
		System.out.println("processed row : "+rs.getRow());
		rs.relative(-processedRowCount);
		System.out.println("relative row : "+rs.getRow());
		return new User(id.intValue(), name, email, telephone, password, authorities);
	}
}
