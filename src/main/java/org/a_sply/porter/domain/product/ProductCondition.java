package org.a_sply.porter.domain.product;


public class ProductCondition {
	
	private int userId;
	
	private int carMakerNo;
	private int carModelNo;
	private int carTypeNo;
	private int carYear;
	
	private int mainCategoryNo;
	private int subCategoryNo;
	
	private String keyword;
	private int offset;
	private int count;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCarMakerNo() {
		return carMakerNo;
	}
	public void setCarMakerNo(int carMakerNo) {
		this.carMakerNo = carMakerNo;
	}
	public int getCarModelNo() {
		return carModelNo;
	}
	public void setCarModelNo(int carModelNo) {
		this.carModelNo = carModelNo;
	}
	public int getCarTypeNo() {
		return carTypeNo;
	}
	public void setCarTypeNo(int carTypeNo) {
		this.carTypeNo = carTypeNo;
	}
	public int getCarYear() {
		return carYear;
	}
	public void setCarYear(int carYear) {
		this.carYear = carYear;
	}
	public int getMainCategoryNo() {
		return mainCategoryNo;
	}
	public void setMainCategoryNo(int mainCategoryNo) {
		this.mainCategoryNo = mainCategoryNo;
	}
	public int getSubCategoryNo() {
		return subCategoryNo;
	}
	public void setSubCategoryNo(int subCategoryNo) {
		this.subCategoryNo = subCategoryNo;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
