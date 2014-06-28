package org.a_sply.porter.dto.product;

import java.util.List;

import org.a_sply.porter.domain.product.Product;

public class RequestedProductDTO {
	
	private String userName;												// name.	
	private String email;												// email.	
	private String telephone;											// telephone.	
	
	private int carMakerNo;
	private String carMakerName;
	private int carTypeNo;
	private String carTypeName;
	private int carModelNo;
	private String carModelName;
	private int carYear;
	
	private int mainCategoryNo;
	private String mainCategoryName;
	private int subCategoryNo;
	private String subCategoryName;
	
	private String productName;
	
	private double price;
	
	private int amount;
	
	private String state;
	
	private String listImageUrl;
	private List<String> nomalImageUrls;
	private List<String> zoomInImageUrls;

	public RequestedProductDTO(Product product) {
		userName = product.getOwner().getName();
		email = product.getOwner().getEmail();
		telephone = product.getOwner().getTelephone();
		carMakerNo = product.getCarInfo().getCarMaker().getNo();
		carTypeNo = product.getCarInfo().getCarType().getNo();
		carModelNo = product.getCarInfo().getCarModel().getNo();
		carYear = product.getCarInfo().getCarYear();
		mainCategoryNo = product.getPartType().getMainCategory().getNo();
		subCategoryNo = product.getPartType().getSubCategory().getNo();
		productName = product.getName();
		price = product.getPrice();
		amount = product.getAmount();
		state = product.getState();
		listImageUrl = product.getImageUrls().getListImageUrl();
		nomalImageUrls = product.getImageUrls().getNormalImageUrls();
		zoomInImageUrls = product.getImageUrls().getZoomInImageUrls();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getCarMakerNo() {
		return carMakerNo;
	}

	public void setCarMakerNo(int carMakerNo) {
		this.carMakerNo = carMakerNo;
	}

	public String getCarMakerName() {
		return carMakerName;
	}

	public void setCarMakerName(String carMakerName) {
		this.carMakerName = carMakerName;
	}

	public int getCarTypeNo() {
		return carTypeNo;
	}

	public void setCarTypeNo(int carTypeNo) {
		this.carTypeNo = carTypeNo;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	public int getCarModelNo() {
		return carModelNo;
	}

	public void setCarModelNo(int carModelNo) {
		this.carModelNo = carModelNo;
	}

	public String getCarModelName() {
		return carModelName;
	}

	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
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

	public String getMainCategoryName() {
		return mainCategoryName;
	}

	public void setMainCategoryName(String mainCategoryName) {
		this.mainCategoryName = mainCategoryName;
	}

	public int getSubCategoryNo() {
		return subCategoryNo;
	}

	public void setSubCategoryNo(int subCategoryNo) {
		this.subCategoryNo = subCategoryNo;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getListImageUrl() {
		return listImageUrl;
	}

	public void setListImageUrl(String listImageUrl) {
		this.listImageUrl = listImageUrl;
	}

	public List<String> getNomalImageUrls() {
		return nomalImageUrls;
	}

	public void setNomalImageUrls(List<String> nomalImageUrls) {
		this.nomalImageUrls = nomalImageUrls;
	}

	public List<String> getZoomInImageUrls() {
		return zoomInImageUrls;
	}

	public void setZoomInImageUrls(List<String> zoomInImageUrls) {
		this.zoomInImageUrls = zoomInImageUrls;
	}
}
