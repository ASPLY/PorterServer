package org.a_sply.porter.domain;

import org.a_sply.porter.dto.article_list.ArticleListDTO;

public class ArticleList {

	private int articleId;					// article id.

	private String largeCategory;			// auto part large category.
	private String middleCategory;			// auto part middle category.
	private String name;					// auto part name.
	private String imageUrl;				// auto part image url.
	private String price;					// auto part price.
	private String description;				// auto part description.
	private String isSold;					// auto part is sold or not sold.

	public ArticleListDTO articleListDTO() {
		ArticleListDTO articleListDTO = new ArticleListDTO();
		articleListDTO.setDescription(description);
		articleListDTO.setImageUrl(imageUrl);
		articleListDTO.setLargeCategory(largeCategory);
		articleListDTO.setMiddleCategory(middleCategory);
		articleListDTO.setName(name);
		articleListDTO.setArticleId(articleId);
		articleListDTO.setPrice(price);
		articleListDTO.setIsSold(isSold);
		return articleListDTO;
	}

	public String getLargeCategory() {
		return largeCategory;
	}

	public void setLargeCategory(String largeCategory) {
		this.largeCategory = largeCategory;
	}

	public String getMiddleCategory() {
		return middleCategory;
	}

	public void setMiddleCategory(String middleCategory) {
		this.middleCategory = middleCategory;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public ArticleList() {
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsSold() {
		return isSold;
	}

	public void setIsSold(String isSold) {
		this.isSold = isSold;
	}
}
