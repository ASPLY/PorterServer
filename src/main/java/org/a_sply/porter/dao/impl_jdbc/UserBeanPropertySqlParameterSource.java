package org.a_sply.porter.dao.impl_jdbc;

public class UserBeanPropertySqlParameterSource extends MappedBeanPropertySqlParameterSource{

	public UserBeanPropertySqlParameterSource(Object object) {
		super(object);
        //map("emailCrc", "emailCrc"); 
        //map("groupid", "group.id");
	}

}
