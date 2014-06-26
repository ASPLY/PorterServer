package org.a_sply.porter.dto.article;

import javax.validation.constraints.NotNull;

import org.a_sply.porter.dto.part.CreatePartDTO;
import org.springframework.web.multipart.MultipartFile;

public class CreateArticleDTO {

	@NotNull
	private CreatePartDTO part;

	public CreateArticleDTO() {
		part = new CreatePartDTO();
	}

	public String getName() {
		return part.getName();
	}

	public void setName(String name) {
		part.setName(name);
	}

	public String getMiddleCategory() {
		return part.getMiddleCategory();
	}

	public void setMiddleCategory(String middleCategory) {
		part.setMiddleCategory(middleCategory);
	}

	public String getLargeCategory() {
		return part.getLargeCategory();
	}

	public void setLargeCategory(String largeCategory) {
		part.setLargeCategory(largeCategory);
	}

	public String[] getKeywords() {
		return part.getKeywords();
	}

	public void setKeywords(String[] keywords) {
		part.setKeywords(keywords);
	}

	public String getPrice() {
		return part.getPrice();
	}

	public void setPrice(String price) {
		part.setPrice(price);
	}

	public String getMaker() {
		return part.getMaker();
	}

	public void setMaker(String maker) {
		part.setMaker(maker);
	}

	public String getState() {
		return part.getState();
	}

	public void setState(String state) {
		part.setState(state);
	}

	public String getQuantity() {
		return part.getQuantity();
	}

	public void setQuantity(String quantity) {
		part.setQuantity(quantity);
	}

	public String getDescription() {
		return part.getDescription();
	}

	public void setDescription(String description) {
		part.setDescription(description);
	}

	public MultipartFile[] getImageFiles() {
		return part.getImageFiles();
	}

	public void setImageFiles(MultipartFile[] imageFiles) {
		part.setImageFiles(imageFiles);
	}

	public CreatePartDTO getPart() {
		return part;
	}

	public String getRegion() {
		return part.getRegion();
	}

	public void setRegion(String region) {
		this.part.setRegion(region);
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof CreateArticleDTO))
			return false;

		return (this.part.getName().equals(((CreateArticleDTO) obj).part.getName()));
	}

}
