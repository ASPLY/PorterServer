package org.a_sply.porter.domain;

import org.a_sply.porter.dto.article_list.RequestArticleListsDTO;

public class RequestArticleLists {

	private int count;				// count of auto part wanted.
	private int offset;				// offset, start point in auto parts.
	private int middleCategory;		// middle category of auto part.
	private int largeCategory;		// large category of auto part.

	public RequestArticleLists() {
	}

	public RequestArticleLists(int count, int offset, int middleCategory, int largeCategory) {
		super();
		this.count = count;
		this.offset = offset;
		this.middleCategory = middleCategory;
		this.largeCategory = largeCategory;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getMiddleCategory() {
		return middleCategory;
	}

	public void setMiddleCategory(int middleCategory) {
		this.middleCategory = middleCategory;
	}

	public int getLargeCategory() {
		return largeCategory;
	}

	public void setLargeCategory(int largeCategory) {
		this.largeCategory = largeCategory;
	}

	public static RequestArticleLists from(
			RequestArticleListsDTO getArticleListsDTO) {
		return new RequestArticleLists(getArticleListsDTO.getCount(),
				getArticleListsDTO.getOffset(),
				getArticleListsDTO.getMiddleCategory(),
				getArticleListsDTO.getLargeCategory());
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof RequestArticleLists))
			return false;

		return (this.largeCategory == ((RequestArticleLists) obj).largeCategory)
				&& (this.middleCategory == ((RequestArticleLists) obj).middleCategory)
				&& (this.count == ((RequestArticleLists) obj).count)
				&& (this.offset == ((RequestArticleLists) obj).offset);
	}
}
