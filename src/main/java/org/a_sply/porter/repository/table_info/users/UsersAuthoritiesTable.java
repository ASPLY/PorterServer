package org.a_sply.porter.repository.table_info.users;

import org.a_sply.porter.repository.table_info.BaseTable;


public class UsersAuthoritiesTable extends BaseTable{
	
	public UsersAuthoritiesTable(){
		super("users_authorities");
	}
	
	public String USER_ID(){
		return field("USER_ID");
	}
	
	public String AUTHORITY(){
		return field("AUTHORITY");
	}

}
