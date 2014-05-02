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

public class Images {
	
	private CommonsMultipartFile[] imageFiles;
	private List<String> imageUrls;
	
	public Images(){
	}
	
	public List<String> getImageUrls() {
		return imageUrls;
	}

	public CommonsMultipartFile[] getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(CommonsMultipartFile[] imageFiles) {
		this.imageFiles = imageFiles;
	}
	
	public void upload(String uploadDir, String urlPrefix){
		List<String> uniqueFileNames = write(uploadDir);
		List<String> imageUrls = new ArrayList<String>();
		for (String unique : uniqueFileNames)
			imageUrls.add(urlPrefix + "/" + unique);
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
            uniqueFilename = uuid + "." + extension;
        } else {
            uniqueFilename = uuid;
        }
        return uniqueFilename;
    }
}
