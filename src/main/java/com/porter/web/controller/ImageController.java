package com.porter.web.controller;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/images")
public class ImageController implements InitializingBean {

	private static final Logger logger = LoggerFactory
			.getLogger(ImageController.class);

	@Value("file:#{conf['dir_part_images']}")
	private Resource uploadPath;

	@RequestMapping(value = "/{name}.jpg", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> jpg(@PathVariable String name) {
		return getImage(uploadPath, name + ".jpg", MediaType.IMAGE_JPEG);
	}
	
	@RequestMapping(value = "/{name}.png", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> png(@PathVariable String name) {
		return getImage(uploadPath, name + ".png", MediaType.IMAGE_PNG);
	}
	
	@RequestMapping(value = "/{name}.gif", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> gif(@PathVariable String name) {
		return getImage(uploadPath, name + ".gif", MediaType.IMAGE_GIF);
	}	
	
	private ResponseEntity<byte[]> getImage(Resource uploadPath, String name, MediaType type) {
		try {
			File file = new File(uploadPath.getFile().getAbsoluteFile() + "/" + name);
			if (file.exists()) {
				return toByteArrayResponse(file, type);
			} else {
				return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
			}
		} catch (IOException e) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}

	private ResponseEntity<byte[]> toByteArrayResponse(File file,
			MediaType imageJpeg) throws IOException {
		byte[] content = org.apache.commons.io.FileUtils.readFileToByteArray(file);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(imageJpeg);
		headers.setContentLength(content.length);
		return new ResponseEntity<byte[]>(content, headers,
				HttpStatus.OK);
	}

	public void afterPropertiesSet() throws Exception {

		// tomcat start 할 때 uploadPath가 설정되었는지 체크한다.
		Assert.notNull(uploadPath, "FileUpload Path must be defined!");

		logger.debug(" ######### uploadPath : "
				+ uploadPath.getFile().getAbsolutePath());
		// 디렉토리가 존재하지 않는다면, 디렉토리를 만든다.
		if (!uploadPath.getFile().exists()) {
			uploadPath.getFile().mkdir();
		}
	}

}
