package org.a_sply.porter.dto.article_list;

public class RequestArticleListsDTO {

	private int largeCategory;
	private int middleCategory;
	private int count;
	private int offset;

	public RequestArticleListsDTO() {
	}

	public RequestArticleListsDTO(int largeCategory, int middleCategory,
			int count, int offest) {
		super();
		this.largeCategory = largeCategory;
		this.middleCategory = middleCategory;
		this.count = count;
		this.offset = offest;
	}

	public int getLargeCategory() {
		return largeCategory;
	}

	public void setLargeCategory(int largeCategory) {
		this.largeCategory = largeCategory;
	}

	public int getMiddleCategory() {
		return middleCategory;
	}

	public void setMiddleCategory(int middleCategory) {
		this.middleCategory = middleCategory;
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

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof RequestArticleListsDTO))
			return false;

		return (this.largeCategory == ((RequestArticleListsDTO) obj).largeCategory)
				&& (this.middleCategory == ((RequestArticleListsDTO) obj).middleCategory)
				&& (this.count == ((RequestArticleListsDTO) obj).count)
				&& (this.offset == ((RequestArticleListsDTO) obj).offset);
	}
}
