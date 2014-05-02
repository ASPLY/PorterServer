package com.porter.web.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.porter.web.model.api_key.ApiKey;
import com.porter.web.model.part.Part;
import com.porter.web.serivce.PartService;


@Controller
@RequestMapping(value = "/parts")
public class PartController implements InitializingBean {

	private static final Logger logger = LoggerFactory
			.getLogger(PartController.class);

	@Value("file:#{conf['dir_part_images']}")
	private Resource uploadPath;

	@Autowired
	private PartService partSerivce;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Part> get(@PathVariable Long id) {
		Part part = partSerivce.get(id);
		if (part == null)
			return new ResponseEntity<Part>(part, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Part>(part, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Part> getAll(ApiKey apiKey) {
		if (apiKey == null ||!apiKey.vaild())
			new ResponseEntity<Part>(HttpStatus.UNAUTHORIZED);

		List<Part> allParts = partSerivce.getAll();
		return allParts;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Part> create(ApiKey apiKey, Part part) {
		if (apiKey == null || !apiKey.vaild())
			return new ResponseEntity<Part>(HttpStatus.UNAUTHORIZED);
		
			try {
				part.upload(uploadPath.getFile().getAbsolutePath(), "/images");
			} catch (IOException e) {
				return new ResponseEntity<Part>(HttpStatus.BAD_REQUEST);
			}
		
		part = partSerivce.create(apiKey.email(), part);
		
		return new ResponseEntity<Part>(part, HttpStatus.CREATED);
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
