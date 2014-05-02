package com.porter.web.model.part;

public class Search {

	private String keyword;
	private int largeCategory;
	private int middleCategory;
	private int offset;
	private int count;

	public Search(){
	}
	
	public Search(String keyword, Integer largeCategory, Integer middleCategory,
			Integer smallCategory, int offset, Integer count) {
		this.keyword = keyword;
		this.largeCategory = largeCategory;
		this.middleCategory = middleCategory;
		this.offset = offset;
		this.count = count;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getLargeCategory() {
		return largeCategory;
	}

	public void setLargeCategory(Integer largeCategory) {
		this.largeCategory = largeCategory;
	}

	public Integer getMiddleCategory() {
		return middleCategory;
	}

	public void setMiddleCategory(Integer middleCategory) {
		this.middleCategory = middleCategory;
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
