package org.a_sply.porter.dto.article;

import org.a_sply.porter.dto.part.CreatedPartDTO;
import org.a_sply.porter.dto.user.UserDTO;

public class CreatedArticleDTO {

	private int id;
	private CreatedPartDTO part;
	private UserDTO user;
	private boolean isSold;

	public CreatedArticleDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CreatedPartDTO getPart() {
		return part;
	}

	public void setPart(CreatedPartDTO part) {
		this.part = part;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public boolean getIsSold() {
		return isSold;
	}

	public void setIsSold(boolean isSold) {
		this.isSold = isSold;
	}

}
