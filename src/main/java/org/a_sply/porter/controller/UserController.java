package org.a_sply.porter.controller;

import javax.validation.Valid;

import org.a_sply.porter.dto.email.CheckEmailDTO;
import org.a_sply.porter.dto.user.CheckNameDTO;
import org.a_sply.porter.dto.user.CreateUserDTO;
import org.a_sply.porter.dto.user.LoginUserDTO;
import org.a_sply.porter.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("users")
public class UserController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void create(@Valid CreateUserDTO createuserDTO) {
		LOGGER.debug("create : {}", createuserDTO);
		userService.createUser(createuserDTO);
	}
	
	@RequestMapping(value = "/check/email", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> check(@Valid CheckEmailDTO checkEmailDTO) {
		LOGGER.debug("check : {}", checkEmailDTO);
		if (userService.check(checkEmailDTO))
			return new ResponseEntity(HttpStatus.OK);
		else 
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		
	}

	@RequestMapping(value = "/check/name", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> check(@Valid CheckNameDTO checkNameDTO) {
		LOGGER.debug("check : {}", checkNameDTO);
		if (userService.check(checkNameDTO)) 
			return new ResponseEntity(HttpStatus.OK);
		 else 
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity login(@Valid LoginUserDTO loginUserDTO) {
		LOGGER.debug("login : {}", loginUserDTO);
		if (userService.login(loginUserDTO)) 
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void logout() {
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
