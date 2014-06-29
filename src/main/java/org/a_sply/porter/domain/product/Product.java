package org.a_sply.porter.domain.product;

import java.util.List;

public class Product {
	
	private long productId;
	private int userId;
	
	private int carMakerNo;
	private int carModelNo;
	private int carTypeNo;
	private int carYear;
	
	private int mainCategoryNo;
	private int subCategoryNo;
	
	private String name;
	private double price;
	private int quantity;
	private String listImageUrl;
	private List<String> normalImageUrls;
	private List<String> zoomInImageUrls;
	
	private String state;
	
	public Product() {
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

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

