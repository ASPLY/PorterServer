package org.a_sply.porter.model;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {

	File[] upload(String uploadDir, MultipartFile[] multipartFiles);

}
