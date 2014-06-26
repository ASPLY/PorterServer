package org.a_sply.porter.dto.product;

import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class CreateProductDTO {
	
	private int carMakerNo;
	private int carTypeNo;
	private int carModelNo;
	private int carYear;
	
	private int mainCategoryNo;
	private int subCategoryNo;
	
	private String name;
	
	private double price;
	
	private int amount;
	
	private String state;
	
	private MultipartFile listImage;
	private List<MultipartFile> nomalImages;
	private List<MultipartFile> zoomInImages;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MultipartFile getListImage() {
		return listImage;
	}
	public void setListImage(MultipartFile listImage) {
		this.listImage = listImage;
	}
	public List<MultipartFile> getNomalImages() {
		return nomalImages;
	}
	public void setNomalImages(List<MultipartFile> nomalImages) {
		this.nomalImages = nomalImages;
	}
	public List<MultipartFile> getZoomInImages() {
		return zoomInImages;
	}
	public void setZoomInImages(List<MultipartFile> zoomInImages) {
		this.zoomInImages = zoomInImages;
	}
}
