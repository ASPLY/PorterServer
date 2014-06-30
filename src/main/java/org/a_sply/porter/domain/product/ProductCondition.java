package org.a_sply.porter.domain.product;

import org.a_sply.porter.domain.base.Named;


public class ProductCondition extends Named{
	
	private long userId;
	public final static String USER_ID = "userId";
	public final static String NAMED_USER_ID = named(USER_ID);
	
	private int carMakerNo;
	public final static String CAR_MAKER_NO = "carMakerNo";
	public final static String NAMED_CAR_MAKER_NO = named(CAR_MAKER_NO);
	
	private int carModelNo;
	public final static String CAR_MODEL_NO = "carModelNo";
	public final static String NAMED_CAR_MODEL_NO = named(CAR_MODEL_NO);
	
	private int carTypeNo;
	public final static String CAR_TYPE_NO = "carTypeNo";
	public final static String NAMED_CAR_TYPE_NO = named(CAR_TYPE_NO);
	
	private int carYear;
	public final static String CAR_YEAR = "carYear";
	public final static String NAMED_CAR_YEAR = named(CAR_YEAR);
	
	private int mainCategoryNo;
	public final static String MAIN_CATEGORY_NO = "mainCategoryNo";
	public final static String NAMED_MAIN_CATEGORY_NO = named(MAIN_CATEGORY_NO);
	
	private int subCategoryNo;
	public final static String SUB_CATEGORY_NO = "subCategoryNo";
	public final static String NAMED_SUB_CATEGORY_NO = named(SUB_CATEGORY_NO);
	
	private String keyword;
	public final static String KEYWORD = "keyword";
	public final static String NAMED_KEYWORD = named(KEYWORD);
	
	private int offset;
	public final static String OFFSET = "offset";
	public final static String NAMED_OFFSET = named(OFFSET);
	
	private int count;
	public final static String COUNT = "count";
	public final static String NAMED_COUNT = named(COUNT);
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
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
