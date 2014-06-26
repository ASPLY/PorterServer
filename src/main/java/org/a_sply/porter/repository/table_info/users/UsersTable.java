package org.a_sply.porter.repository.table_info.users;

import org.a_sply.porter.repository.table_info.BaseTable;

public class UsersTable extends BaseTable{
	
	public UsersTable(){
		super("users");
	}
	
	public String ID(){
		return field("ID");
	}
	
	public String NAME(){
		return field("NAME");
	}
	
	public String NAME_CRC(){
		return field("NAME_CRC");
	}
	
	public String EMAIL(){
		return field("EMAIL");
	}
	
	public String EMAIL_CRC(){
		return field("EMAIL_CRC");
	}
	
	public String PASSWORD(){
		return field("PASSWORD");
	}
	
	public String TELEPHONE(){
		return field("TELEPHONE");
	}
}
