package org.a_sply.porter.dto.user;

public class LoginUserDTO {

	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof LoginUserDTO))
			return false;

		return (this.email == ((LoginUserDTO) obj).email)
				&& (this.password == ((LoginUserDTO) obj).password);
	}
}
