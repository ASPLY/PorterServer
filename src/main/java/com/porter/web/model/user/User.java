package com.porter.web.model.user;

import com.porter.web.dto.UserDTO;

public class User {

	private String email;
	private String password;
	private String telephone;
	
	public User() {
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

	public User(String email, String password, String telephone) {
		super();
		this.email = email;
		this.password = password;
		this.telephone = telephone;
	}

	public void verification(){
		if(email == null || "".equals(email) ||
				password == null || "".equals(password) ||
				telephone == null || "".equals(telephone)) 
			throw new IllegalArgumentException("email : "+email+", password : "+password+", telephone : "+telephone);
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
		User other = (User) obj;
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

	public String getEmail() {
		return email;
	}

	public String getTelephone() {
		return telephone;
	}
	
	public UserDTO dto() {
		return new UserDTO(email, password, telephone);
	}
	
	private String getPassword() {
		return password;
	}
}
