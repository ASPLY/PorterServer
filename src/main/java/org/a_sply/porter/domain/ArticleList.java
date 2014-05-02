package org.a_sply.porter.domain;

import org.a_sply.porter.dto.article_list.ArticleListDTO;

public class ArticleList {

	private int articleId;

	private String largeCategory;
	private String middleCategory;
	private String name;
	private String imageUrl;
	private String price;
	private String description;
	private String isSold;

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
