package org.a_sply.porter.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.a_sply.porter.dto.user.CreateUserDTO;
import org.a_sply.porter.dto.user.LoginUserDTO;
import org.a_sply.porter.dto.user.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {

	private int id;														// user id using in db.
	private String name;												// name.	
	private String email;												// email.		
	private String password;											// password.	
	private String telephone;											// telephone.	
	private Collection<? extends GrantedAuthority> authorities;			// user authorities.
	
	public User() {
	}
	
	public User(int id, String name, String email, String telephone, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.authorities = authorities;
	}

	public User(String name, String email, String telephone, String password) {
		this(-1, name, email, telephone, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
	}

	public static User from(CreateUserDTO createUserDTO) {
		return new User(createUserDTO.getName(), createUserDTO.getEmail(), createUserDTO.getTelephone(), createUserDTO.getPassword());
	}

	public static User from(LoginUserDTO loginUserDTO) {
		return new User(null, loginUserDTO.getEmail(), null, loginUserDTO.getPassword());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
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

	public UserDTO userDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(email);
		userDTO.setTelephone(telephone);
		userDTO.setName(name);
		return userDTO;
	}

	public String getName() {
		return name;
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
