package org.a_sply.porter.domain;

import org.a_sply.porter.dto.image.ImageDTO;

public class Image {

	private String original;
	private String articleThumbnail;
	private String articleListThumbnail;

	public Image(String original, String articleThumbnail,
			String articleListThumbnail) {
		super();
		this.original = original;
		this.articleThumbnail = articleThumbnail;
		this.articleListThumbnail = articleListThumbnail;
	}

	public Image() {
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getArticleThumbnail() {
		return articleThumbnail;
	}

	public void setArticleThumbnail(String articleThumbnail) {
		this.articleThumbnail = articleThumbnail;
	}

	public String getArticleListThumbnail() {
		return articleListThumbnail;
	}

	public void setArticleListThumbnail(String articleListThumbnail) {
		this.articleListThumbnail = articleListThumbnail;
	}

	public ImageDTO imageDTO() {
		return null;
	}

}