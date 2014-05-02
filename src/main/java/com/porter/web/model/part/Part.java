package com.porter.web.model.part;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.porter.web.dto.PartDTO;
import com.porter.web.model.user.User;

public class Part {

	private User user;

	private int id;

	private String name;
	private int middleCategory;
	private int largeCategory;

	private String keywords[];
	private Integer price;
	private String maker;
	private String state;
	private Integer quantity;

	private String description;
	private Integer region;
	private String imageUrls[];
	private CommonsMultipartFile[] imageFiles;
	
	public Part(){
	}

	public Part(User user, int id, String name, int middleCategory,
			int largeCategory, String[] keywords, Integer price, String maker,
			String state, Integer quantity, String description, Integer region,
			String[] imageUrls) {
		super();
		this.user = user;
		this.id = id;
		this.name = name;
		this.middleCategory = middleCategory;
		this.largeCategory = largeCategory;
		this.keywords = keywords;
		this.price = price;
		this.maker = maker;
		this.state = state;
		this.quantity = quantity;
		this.description = description;
		this.region = region;
		this.imageUrls = imageUrls;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
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

	public String[] getImageUrls() {
		return imageUrls;
	}

	public void setImageFiles(CommonsMultipartFile[] imageFiles) {
		this.imageFiles = imageFiles;
	}
	
	public void upload(String uploadDir, String urlPrefix){
		List<String> uniqueFileNames = write(uploadDir);
		List<String> imageUrls = new ArrayList<String>();
		for (String unique : uniqueFileNames)
			imageUrls.add(urlPrefix + "/" + unique);
		
		this.imageUrls = new String[imageUrls.size()];
		imageUrls.toArray(this.imageUrls);
	}

	private List<String> write(String absolutePath) {
		OutputStream out = null;
        List<String> uniqueFileNames = new ArrayList<String>();

        try {
            for (CommonsMultipartFile multipartFile : imageFiles) {
            	String uniqueFileName = getUniqueFileName(multipartFile.getOriginalFilename());
            	uniqueFileNames.add(uniqueFileName);
				out = new FileOutputStream(absolutePath + "/" + uniqueFileName);
                BufferedInputStream bis = new BufferedInputStream(multipartFile.getInputStream());
                byte[] buffer = new byte[8106];
                int read;

                while ((read = bis.read(buffer)) > 0) {
                    out.write(buffer, 0, read);
                }
                IOUtils.closeQuietly(out);
            }

        } catch (IOException ioe) {
        	throw new RuntimeException(ioe.getMessage());
        } finally {
            IOUtils.closeQuietly(out);
        }
        
        return uniqueFileNames;
	}
	
	private String getUniqueFileName(String fileName) {
        String uniqueFilename = "";
        String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-').toUpperCase();

        int fileIndex = StringUtils.lastIndexOf(fileName, '.');

        // 파일명과 확장자를 분리
        if (fileIndex != -1) {
            String extension = StringUtils.substring(fileName, fileIndex + 1);
            uniqueFilename = uuid + "." + extension.toLowerCase();
        } else {
            uniqueFilename = uuid;
        }
        return uniqueFilename;
    }

	public void verification() {
		nullOrEmptyString("name", name);
		nullInteger("middleCategory", middleCategory);
		nullInteger("largeCategory", largeCategory);
		nullOrEmptyString("maker", maker);
		nullOrEmptyString("state", state);
		nullOrNegativeInteger("price", price);
		nullOrNegativeInteger("quantity", quantity);
		nullOrNegativeInteger("region", region);
	}

	private void nullInteger(String fieldName, Integer value) {
		if (value == null) {
			throw new IllegalArgumentException(fieldName + " : " + value);
		}
	}

	private void nullOrNegativeInteger(String fieldName, Integer value) {
		if (value == null || value <= 0) {
			throw new IllegalArgumentException(fieldName + " : " + value);
		}
	}

	private void nullOrEmptyString(String fieldName, String value) {
		if ("".equals(value) || value == null)
			throw new IllegalArgumentException(fieldName + " : " + value);
	}

	public PartDTO dto(Long userId) {
		String keyword1 = get(keywords, 0);
		String keyword2 = get(keywords, 1);
		String keyword3 = get(keywords, 2);
		String keyword4 = get(keywords, 3);
		String keyword5 = get(keywords, 4);
		
		String imageUrl1 = get(imageUrls, 0);
		String imageUrl2 = get(imageUrls, 1);
		String imageUrl3 = get(imageUrls, 2);
		String imageUrl4 = get(imageUrls, 3);
		
		return new PartDTO(userId.intValue(), id, name, middleCategory, largeCategory, keyword1, keyword2, keyword3, keyword4, keyword5, price, maker, state, quantity, description, region, imageUrl1, imageUrl2, imageUrl3, imageUrl4);
	}

	private String get(String[] array, int index) {
		if(array.length > index)
			return array[index];
		return null;
	}
}
