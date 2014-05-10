package org.a_sply.porter.domain;

import org.a_sply.porter.dto.article.ArticleDTO;
import org.a_sply.porter.dto.article.CreatedArticleDTO;

public class Article {
	
	private int id;				// article id.
	private User user;			// user information registering a article.
	private Part part;			// auto part information.
	private boolean isSold;		// auto part is sold or not sold.

	public Article(User user, Part part) {
		this.user = user;
		this.part = part;
	}

	public Article() {
	}

	public CreatedArticleDTO createdArticleDTO() {
		CreatedArticleDTO createdArticleDTO = new CreatedArticleDTO();
		createdArticleDTO.setId(id);
		createdArticleDTO.setUser(user.userDTO());
		createdArticleDTO.setPart(part.createdPartDTO());
		createdArticleDTO.setIsSold(isSold);
		return createdArticleDTO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public boolean getIsSold() {
		return isSold;
	}

	public void setIsSold(boolean isSold) {
		this.isSold = isSold;
	}

	public ArticleDTO articleDTO() {
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setIsSold(isSold);
		articleDTO.setPart(part.partDTO());
		articleDTO.setUser(user.userDTO());
		return articleDTO;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Article))
			return false;

		return this.id == ((Article) obj).id;
	}
}
