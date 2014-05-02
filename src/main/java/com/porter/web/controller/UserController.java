package com.porter.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.porter.web.dto.ApiKeyDTO;
import com.porter.web.dto.UserDTO;
import com.porter.web.model.api_key.ApiKey;
import com.porter.web.model.user.User;
import com.porter.web.serivce.UserService;
import com.porter.web.util.ValidationErrorUtil;

@Controller
@RequestMapping(value = "/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userSerivce;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity create(@Valid UserDTO userDTO, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ValidationErrorUtil.extractValidationError(bindingResult);
		
		userSerivce.createUser(userDTO.toModel());
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity auth(@Valid ApiKeyDTO apiKeyDTO, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return ValidationErrorUtil.extractValidationError(bindingResult);
		
		ApiKey apiKey = apiKeyDTO.toModel();
		
		try{
			if(apiKey.vaild())
				return new ResponseEntity(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<User> get(@PathVariable Long id) {
		User user = userSerivce.get(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity login(@Valid UserDTO userDTO, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ValidationErrorUtil.extractValidationError(bindingResult);
		
		ResponseEntity<ApiKey> response;
		if(userSerivce.login(userDTO.getEmail(), userDTO.getPassword())){
			ApiKey apiKey = new ApiKey(userDTO.getEmail(), String.valueOf(System.currentTimeMillis()));
			response = new ResponseEntity<ApiKey>(apiKey, HttpStatus.OK);
			return response;
		}
		return new ResponseEntity<ApiKey>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity check(@PathVariable String email) {
		if(userSerivce.get(email) == null)
			return new ResponseEntity(HttpStatus.OK);
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

}
