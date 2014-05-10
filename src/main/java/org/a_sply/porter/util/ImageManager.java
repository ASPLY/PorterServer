package org.a_sply.porter.util;

import java.util.List;

import org.a_sply.porter.domain.Image;
import org.springframework.web.multipart.MultipartFile;

/**
 * ImageManager interface. 
 * @author LCH
 */

public interface ImageManager {
	
	/**
	 * Upload original image And make thumbnail image.
	 * @param multipartFiles imagefiles in MultipartFile class.
	 * @return imagefiles in {@link Image} class.
	 * @author LCH
	 */

	List<Image> upload(MultipartFile[] multipartFiles);

}
