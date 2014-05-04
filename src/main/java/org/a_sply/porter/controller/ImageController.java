package org.a_sply.porter.controller;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/images")
public class ImageController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

	@RequestMapping(value = "/original/{name}.jpg", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> original(@PathVariable String name) {
		LOGGER.debug("original : {}", name);
		return getImage("/storage/images/original", name + ".jpg", MediaType.IMAGE_JPEG);
	}

	@RequestMapping(value = "/article_thumbnail/{name}.jpg", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> articleThumbnail(@PathVariable String name) {
		LOGGER.debug("articleThumbnail : {}", name);
		return getImage("/storage/images/article_thumbnail", name + ".jpg",MediaType.IMAGE_JPEG);
	}

	@RequestMapping(value = "/article_list_thumbnail/{name}.jpg", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> articleListThumbnail(@PathVariable String name) {
		LOGGER.debug("articleListThumbnail : {}", name);
		return getImage("/storage/images/article_list_thumbnail", name + ".jpg", MediaType.IMAGE_JPEG);
	}

	private ResponseEntity<byte[]> getImage(String uploadPath, String name,
			MediaType type) {
		try {
			File file = new File(uploadPath + "/" + name);
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
		byte[] content = org.apache.commons.io.FileUtils
				.readFileToByteArray(file);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(imageJpeg);
		headers.setContentLength(content.length);
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	}
}
