package org.a_sply.porter.domain;

import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.dto.image.ImageDTO;
import org.a_sply.porter.dto.part.CreatePartDTO;
import org.a_sply.porter.dto.part.CreatedPartDTO;
import org.a_sply.porter.dto.part.PartDTO;

public class Part {

	private String name;						// auto part name.
	private String middleCategory;				// auto part middle category.
	private String largeCategory;				// auto part large category.
	private List<String> keywords;				// auto part related keywords.
	private String price;						// auto part price.
	private String maker;						// auto part maker.
	private String state;						// auto part state.
	private String quantity;					// auto part quantity of selling.
	private Description description;			// auto part description.	
	private String region;						// auto part region of selling.
	private List<Image> images;					// auto part image.

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

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
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

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	public List<Image> getImages() {
		return images;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public static Part from(CreatePartDTO createPartDTO) {
		Part part = new Part();
		part.setDescription(new Description(createPartDTO.getDescription()));
		// part.setImages(createPartDTO.getImageFiles());
		List<String> keywords = new ArrayList<String>();
		for (String keyword : createPartDTO.getKeywords())
			keywords.add(keyword);

		part.setKeywords(keywords);
		part.setLargeCategory(createPartDTO.getLargeCategory());
		part.setMaker(createPartDTO.getMaker());
		part.setMiddleCategory(createPartDTO.getMiddleCategory());
		part.setName(createPartDTO.getName());
		part.setPrice(createPartDTO.getPrice());
		part.setQuantity(createPartDTO.getQuantity());
		part.setState(createPartDTO.getState());
		part.setRegion(createPartDTO.getRegion());
		return part;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public CreatedPartDTO createdPartDTO() {
		CreatedPartDTO createdPartDTO = new CreatedPartDTO();
		createdPartDTO.setDescription(description.getContent());
		List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
		for (Image image : images) {
			imageDTOs.add(image.imageDTO());
		}
		createdPartDTO.setImages(imageDTOs.toArray(new ImageDTO[0]));
		createdPartDTO.setKeywords(keywords.toArray(new String[0]));
		createdPartDTO.setLargeCategory(largeCategory);
		createdPartDTO.setMaker(maker);
		createdPartDTO.setMiddleCategory(middleCategory);
		createdPartDTO.setName(name);
		createdPartDTO.setPrice(price);
		createdPartDTO.setQuantity(quantity);
		createdPartDTO.setState(state);
		createdPartDTO.setRegion(region);
		return createdPartDTO;
	}

	public PartDTO partDTO() {
		PartDTO partDTO = new PartDTO();
		partDTO.setDescription(description.getContent());
		List<String> imageUrls = new ArrayList<String>();
		for (Image image : images) {
			imageUrls.add(image.getOriginal());
		}
		partDTO.setImageUrls(imageUrls.toArray(new String[0]));
		partDTO.setKeywords(keywords.toArray(new String[0]));
		partDTO.setLargeCategory(largeCategory);
		partDTO.setMaker(maker);
		partDTO.setMiddleCategory(middleCategory);
		partDTO.setName(name);
		partDTO.setPrice(price);
		partDTO.setQuantity(quantity);
		partDTO.setRegion(region);
		partDTO.setState(state);
		return partDTO;
	}

}
