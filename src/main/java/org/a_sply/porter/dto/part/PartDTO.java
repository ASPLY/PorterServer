package org.a_sply.porter.dto.part;

import org.a_sply.porter.dto.image.ImageDTO;

public class PartDTO {

	private String name;
	private String middleCategory;
	private String largeCategory;
	private String[] keywords;
	private String price;
	private String maker;
	private String state;
	private String quantity;
	private String description;
	private String region;

	private ImageDTO[] images;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMiddleCategory() {
		return middleCategory;
	}

	public void setMiddleCategory(String middleCategory) {
		this.middleCategory = middleCategory;
	}

	public String getLargeCategory() {
		return largeCategory;
	}

	public void setLargeCategory(String largeCategory) {
		this.largeCategory = largeCategory;
	}

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ImageDTO[] getImages() {
		return images;
	}

	public void setImages(ImageDTO[] images) {
		this.images = images;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}
