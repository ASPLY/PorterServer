package org.a_sply.porter.dto.product;

public class CountProductDTO {
	
	private int carMakerNo;
	private int carTypeNo;
	private int carModelNo;
	private int carYear;
	
	private int mainCategoryNo;
	private int subCategoryNo;

	public int getCarMakerNo() {
		return carMakerNo;
	}

	public void setCarMakerNo(int carMakerNo) {
		this.carMakerNo = carMakerNo;
	}

	public int getCarTypeNo() {
		return carTypeNo;
	}

	public void setCarTypeNo(int carTypeNo) {
		this.carTypeNo = carTypeNo;
	}

	public int getCarModelNo() {
		return carModelNo;
	}

	public void setCarModelNo(int carModelNo) {
		this.carModelNo = carModelNo;
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
}
