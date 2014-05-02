package com.porter.web.dto;

import com.porter.web.model.part.Part;
import com.porter.web.model.part.PartList;
import com.porter.web.model.user.User;


public class PartDTO {
	
	private int userId;
	
	private int id;
	
	private String name;
	private Integer middleCategory;
	private Integer largeCategory;
	
	private String keyword1;
	private String keyword2;
	private String keyword3;
	private String keyword4;
	private String keyword5;
	
	private Integer price;
	private String maker;
	private String state;
	private Integer quantity;

	private String description;
	private Integer region;
	
	private String imageUrl1;
	private String imageUrl2;
	private String imageUrl3;
	private String imageUrl4;
	
	public PartDTO(){};
	
	public PartDTO(int userId, int id, String name, int middleCategory,
			int largeCategory, String keyword1, String keyword2,
			String keyword3, String keyword4, String keyword5, Integer price,
			String maker, String state, Integer quantity, String description,
			Integer region, String imageUrl1, String imageUrl2,
			String imageUrl3, String imageUrl4) {
		super();
		this.userId = userId;
		this.id = id;
		this.name = name;
		this.middleCategory = middleCategory;
		this.largeCategory = largeCategory;
		this.keyword1 = keyword1;
		this.keyword2 = keyword2;
		this.keyword3 = keyword3;
		this.keyword4 = keyword4;
		this.keyword5 = keyword5;
		this.price = price;
		this.maker = maker;
		this.state = state;
		this.quantity = quantity;
		this.description = description;
		this.region = region;
		this.imageUrl1 = imageUrl1;
		this.imageUrl2 = imageUrl2;
		this.imageUrl3 = imageUrl3;
		this.imageUrl4 = imageUrl4;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMiddleCategory() {
		return middleCategory;
	}
	public void setMiddleCategory(int middleCategory) {
		this.middleCategory = middleCategory;
	}
	public int getLargeCategory() {
		return largeCategory;
	}
	public void setLargeCategory(int largeCategory) {
		this.largeCategory = largeCategory;
	}
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	public String getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}
	public String getKeyword4() {
		return keyword4;
	}
	public void setKeyword4(String keyword4) {
		this.keyword4 = keyword4;
	}
	public String getKeyword5() {
		return keyword5;
	}
	public void setKeyword5(String keyword5) {
		this.keyword5 = keyword5;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRegion() {
		return region;
	}
	public void setRegion(Integer region) {
		this.region = region;
	}
	public String getImageUrl1() {
		return imageUrl1;
	}
	public void setImageUrl1(String imageUrl1) {
		this.imageUrl1 = imageUrl1;
	}
	public String getImageUrl2() {
		return imageUrl2;
	}
	public void setImageUrl2(String imageUrl2) {
		this.imageUrl2 = imageUrl2;
	}
	public String getImageUrl3() {
		return imageUrl3;
	}
	public void setImageUrl3(String imageUrl3) {
		this.imageUrl3 = imageUrl3;
	}
	public String getImageUrl4() {
		return imageUrl4;
	}
	public void setImageUrl4(String imageUrl4) {
		this.imageUrl4 = imageUrl4;
	}
	public Part part(User user) {
		
		return new Part(user, id, name, middleCategory, largeCategory, 
				new String[]{keyword1, keyword2, keyword3, keyword4, keyword5}, price, maker, state, quantity, description, region,
				new String[]{imageUrl1, imageUrl2, imageUrl3, imageUrl4});
	}

	public PartList partList() {
		return new PartList(id, largeCategory, middleCategory, name, imageUrl1, price, description);
	}

	
}
