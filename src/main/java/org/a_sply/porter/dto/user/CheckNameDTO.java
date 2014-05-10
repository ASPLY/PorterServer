package org.a_sply.porter.dto.user;

import javax.validation.constraints.NotNull;

public class CheckNameDTO {

	@NotNull
	private String name;

	public CheckNameDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof CheckNameDTO))
			return false;

		return this.name.equals(((CheckNameDTO) obj).name);
	}

}
