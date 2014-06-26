package org.a_sply.porter.dto.article_list;

public class SearchArticleListDTO {

	private String keyword;
	private String largeCategory;
	private String middleCategory;
	private int count;
	private String offest;

	public SearchArticleListDTO() {
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof SearchArticleListDTO))
			return false;

		return this.keyword == ((SearchArticleListDTO) obj).keyword;
	}

	public String getOffset() {
		return offest;
	}

	public String getOffest() {
		return offest;
	}

	public void setOffest(String offest) {
		this.offest = offest;
	}
}
