package org.a_sply.porter.dto.article;

import org.a_sply.porter.dto.part.PartDTO;
import org.a_sply.porter.dto.user.UserDTO;

public class ArticleDTO {

	private UserDTO user;
	private PartDTO part;
	private boolean isSold;

	public ArticleDTO() {
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public PartDTO getPart() {
		return part;
	}

	public void setPart(PartDTO part) {
		this.part = part;
	}

	public boolean getIsSold() {
		return isSold;
	}

	public void setIsSold(boolean isSold) {
		this.isSold = isSold;
	}
}
