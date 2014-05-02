package com.porter.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.porter.web.model.user.User;


public class UserDTO {

	private Long id;

	@NotNull
	@Email
	private String email;

	@NotNull
	@Size(min=8, max=25)
	private String password;
	
	private String telephone;
	
	public UserDTO() {
		super();
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public UserDTO(String email, String password, String telephone) {
		super();
		this.email = email;
		this.password = password;
		this.telephone = telephone;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	// Person objects are equal if they have the same name.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	public String toString(){
		return "email : "+email+", password : "+password+", telephone : "+telephone;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getTelephone() {
		return telephone;
	}
	
	public User toModel() {
		return new User(email, password, telephone);
	}
}
