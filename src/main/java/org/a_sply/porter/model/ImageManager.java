package org.a_sply.porter.model;

import java.util.List;

import org.a_sply.porter.domain.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageManager {

	List<Image> uploadImageAndMakeThumbnails(MultipartFile[] multipartFiles);

}
