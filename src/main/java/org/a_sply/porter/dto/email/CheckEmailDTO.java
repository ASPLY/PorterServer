package org.a_sply.porter.dto.email;

import org.hibernate.validator.constraints.Email;

public class CheckEmailDTO {

	@Email
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof CheckEmailDTO))
			return false;

		return this.email.equals(((CheckEmailDTO) obj).email);
	}
}
