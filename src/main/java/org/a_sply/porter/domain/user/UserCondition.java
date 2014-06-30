package org.a_sply.porter.domain.user;

import org.a_sply.porter.domain.base.Named;
import org.a_sply.porter.util.CRC32Util;

public class UserCondition extends Named{
	
	public static final String NAME = "name";
	public static final String NAMED_NAME = named(NAME);
	private String name;								// name.
	
	public static final String EMAIL = "email";
	public static final String NAMED_EMAIL = named(EMAIL);	
	private String email;												// email.
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public static final String NAME_CRC = "nameCrc";
	public static final String NAMED_NAME_CRC = named(NAME_CRC);
	public int getNameCrc() {
		if(name == null) return 0;
		return CRC32Util.crcValue(name);
	}
	
	public static final String EMAIL_CRC = "emailCrc";
	public static final String NAMED_EMAIL_CRC = named(EMAIL_CRC);	
	public int getEmailCrc() {
		if(email == null) return 0;
		return CRC32Util.crcValue(email);
	}
}
