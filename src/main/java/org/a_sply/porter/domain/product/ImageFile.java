package org.a_sply.porter.domain.product;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImageFile {
	
	private MultipartFile multipartFile;
	private File file;

	public ImageFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	
	public File getFile(){
		if(file == null){
			try {
				File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + getUniqueFileName(multipartFile.getOriginalFilename()));
				multipartFile.transferTo(tmpFile);
				return tmpFile;
			} catch (IllegalStateException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		return file;
	}
	
	private String getUniqueFileName(String fileName) {
		String uniqueFilename = "";
		String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-')
				.toUpperCase();

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
}
