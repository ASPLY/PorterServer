package org.a_sply.porter.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/**
 * FileUploader interface. 
 * @author LCH
 */

public interface FileUploader {

	/**
	 * Upload image list.
	 * @param uploadDir directory to upload.
	 * @param multipartFiles imagefiles in MultipartFile class.
	 * @return imagefiles in File class.
	 * @author LCH
	 */
	
	File[] upload(String uploadDir, MultipartFile[] multipartFiles);

}
