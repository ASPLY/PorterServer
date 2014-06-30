package org.a_sply.porter.domain.product;

import java.util.List;

import org.a_sply.porter.domain.base.Named;

public class Product extends Named{
	
	private long productId;
	public final static String PRODUCT_ID = "productId";
	public final static String NAMED_PRODUCT_ID = named(PRODUCT_ID);
	
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
	
	private String name;
	public final static String NAME = "name";
	public final static String NAMED_NAME = named(NAME);
	
	private double price;
	public final static String PRICE = "price";
	public final static String NAMED_PRICE = named(PRICE);
	
	private int quantity;
	public final static String QUANTITY = "quantity";
	public final static String NAMED_QUANTITY = named(QUANTITY);
	
	private String listImageUrl;
	public final static String LIST_IMAGE_URL = "listImageUrl";
	public final static String NAMED_LIST_IMAGE_URL = named(LIST_IMAGE_URL);
	
	private List<String> normalImageUrls;
	public final static String NORMAL_URL = "normalImageUrl";
	public final static String NAMED_NORMAL_URL = named(NORMAL_URL);
	
	private List<String> zoomInImageUrls;
	public final static String ZOOMIN_URL = "zoomInImageUrl";
	public final static String NAMED_ZOOMIN_URL = named(ZOOMIN_URL);
	
	private String state;
	public final static String STATE = "state";
	public final static String NAMED_STATE = named(STATE);
	
	public Product() {
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getListImageUrl() {
		return listImageUrl;
	}

	public void setListImageUrl(String listImageUrl) {
		this.listImageUrl = listImageUrl;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<String> getNormalImageUrls() {
		return normalImageUrls;
	}

	public void setNormalImageUrls(List<String> normalImageUrls) {
		this.normalImageUrls = normalImageUrls;
	}

	public List<String> getZoomInImageUrls() {
		return zoomInImageUrls;
	}

	public void setZoomInImageUrls(List<String> zoomInImageUrls) {
		this.zoomInImageUrls = zoomInImageUrls;
	}

	public int getCarYear() {
		return carYear;
	}

	public void setCarYear(int carYear) {
		this.carYear = carYear;
	}

}

