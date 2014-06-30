package org.a_sply.porter.dao.table_info.users;

import org.a_sply.porter.dao.table_info.BaseTable;

public class UsersTable extends BaseTable{
	
	private final String USER_ID = "USER_ID";
	public final String FEILD_USER_ID = field(USER_ID);
	
	private final String NAME = "NAME";
	public final String FEILD_NAME = field(NAME);
	
	private final String NAME_CRC = "NAME_CRC";
	public final String FEILD_NAME_CRC = field(NAME_CRC);
	
	private final String EMAIL_CRC = "EMAIL_CRC";
	public final String FEILD_EMAIL_CRC = field(EMAIL_CRC);
	
	private final String EMAIL = "EMAIL";
	public final String FEILD_EMAIL = field(EMAIL);
	
	private final String PASSWORD = "PASSWORD";
	public final String FEILD_PASSWORD = field(PASSWORD);
	
	private final String TELEPHONE = "TELEPHONE";
	public final String FEILD_TELEPHONE = field(TELEPHONE);

	public UsersTable(){
		super("users");
	}
}
