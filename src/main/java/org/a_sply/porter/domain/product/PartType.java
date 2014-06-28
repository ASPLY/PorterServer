package org.a_sply.porter.domain.product;

public class PartType {
	
	private Category mainCategory;
	private Category subCategory;
	
	public PartType(){
		this.mainCategory = new Category(0);
		this.subCategory = new Category(0);
	}
	
	public PartType(int mainCategoryNo, int subCategoryNo){
		this.mainCategory = new Category(mainCategoryNo);
		this.subCategory = new Category(subCategoryNo);
	}

	public Category getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(Category mainCategory) {
		this.mainCategory = mainCategory;
	}

	public Category getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Category subCategory) {
		this.subCategory = subCategory;
	}
	
}
