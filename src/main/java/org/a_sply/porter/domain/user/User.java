package org.a_sply.porter.domain.user;

import java.util.Collection;

import org.a_sply.porter.domain.base.Named;
import org.a_sply.porter.util.CRC32Util;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User  extends Named implements UserDetails{

	public static final String USER_ID = "userId";
	public static final String NAMED_USER_ID = named(USER_ID);
	private long userId;													// user id using in db.
	
	public static final String NAME_CRC = "nameCrc";
	public static final String NAMED_NAME_CRC = named(NAME_CRC);
	
	public static final String NAME = "name";
	public static final String NAMED_NAME = named(NAME);
	private String name;												// name.	
	
	public static final String EMAIL_CRC = "emailCrc";
	public static final String NAMED_EMAIL_CRC = named(EMAIL_CRC);	
	
	public static final String EMAIL = "email";
	public static final String NAMED_EMAIL = named(EMAIL);	
	private String email;												// email.		
	
	public static final String PASSWORD = "password";
	public static final String NAMED_PASSWORD = named(PASSWORD);	
	private String password;											// password.	
	
	public static final String TELEPHONE = "telephone";
	public static final String NAMED_TELEPHONE = named(TELEPHONE);		
	private String telephone;											// telephone.	
	
	public static final String AUTHORITY = "authority";
	public static final String NAMED_AUTHORITY = named(AUTHORITY);	
	private Collection<? extends GrantedAuthority> authorities;			// user authorities.
	
	public User() {
	}
	
	public User(long userId, String name, String email, String telephone, String password, Collection<? extends GrantedAuthority> authorities) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.authorities = authorities;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getEmailCrc(){
		if(email == null) return 0;
		return CRC32Util.crcValue(email);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof User))
			return false;

		return this.email.equals(((User) obj).email);
	}

	public String getName() {
		return name;
	}
	
	public int getNameCrc(){
		if(name == null) return 0;
		return CRC32Util.crcValue(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
