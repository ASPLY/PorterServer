package org.a_sply.porter.domain;

import org.a_sply.porter.dto.article_list.SearchArticleListDTO;

public class Search {

	private String keyword;				// keyword of auto part wanted.
	private String largeCategory;       // middle category of auto part.
	private String middleCategory;      // large category of auto part. 
	private int count;					// count of auto part wanted.
	private String offset;				// offset, start point in auto parts.

	public Search() {
	}

	public Search(String keyword, String largeCategory, String middleCategory,
			int count, String offset) {
		this.keyword = keyword;
		this.largeCategory = largeCategory;
		this.middleCategory = middleCategory;
		this.count = count;
		this.offset = offset;
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

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Search))
			return false;

		return this.keyword.equals(((Search) obj).keyword)
				&& (this.largeCategory.equals(((Search) obj).largeCategory) && (this.middleCategory
						.equals(((Search) obj).middleCategory)));
	}

	public static Search from(SearchArticleListDTO searchPartListDTO) {
		return new Search(searchPartListDTO.getKeyword(),
				searchPartListDTO.getLargeCategory(),
				searchPartListDTO.getMiddleCategory(),
				searchPartListDTO.getCount(), searchPartListDTO.getOffset());
	}

}
