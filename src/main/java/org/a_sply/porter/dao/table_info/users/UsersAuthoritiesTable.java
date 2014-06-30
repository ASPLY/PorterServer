package org.a_sply.porter.dao.table_info.users;

import org.a_sply.porter.dao.table_info.BaseTable;

public class UsersAuthoritiesTable extends BaseTable{
	
	private final String USER_ID = "USER_ID";
	public final String FEILD_USER_ID = field(USER_ID);
	
	private final String AUTHORITY = "AUTHORITY";
	public final String FEILD_AUTHORITY = field(AUTHORITY);

	public UsersAuthoritiesTable(){
		super("users_authorities");
	}

}
