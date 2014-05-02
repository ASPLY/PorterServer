package com.porter.web.model.part;

public class PartList {
	
	private static final int PART_LIST_DES_LENGTH = 20;

	private int partId;
	
	private Integer largeCategory;
	private Integer middleCategory;
	private String name;
	private String imageUrl;
	private Integer price;
	private String description;
	
	public PartList(int partId, Integer largeCategory, Integer middleCategory,
			String name, String imageUrl, Integer price, String description) {
		super();
		this.partId = partId;
		this.largeCategory = largeCategory;
		this.middleCategory = middleCategory;
		this.name = name;
		this.imageUrl = imageUrl;
		this.price = price;
		this.description = description;
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
	public Integer getPartId() {
		return partId;
	}
	public void setPartId(Integer partId) {
		this.partId = partId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		int length = description.length();
		if(length > PART_LIST_DES_LENGTH)
			return description.substring(0, PART_LIST_DES_LENGTH) + "...";
		
		return description + "...";
	}
}
