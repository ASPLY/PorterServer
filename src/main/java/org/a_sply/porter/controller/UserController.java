package org.a_sply.porter.controller;

import javax.validation.Valid;

import org.a_sply.porter.domain.user.User;
import org.a_sply.porter.domain.user.UserCondition;
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

/**
 * Process request related to user. 
 * @author LCH
 */

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("users")
public class UserController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * Process request that register a new user. 
	 * @param createUserDTO a new user information to register.
	 * @return a registered user content.
	 * @author LCH
	 */

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void create(@Valid User user) {
		LOGGER.debug("create : {}", user);
		userService.create(user);
	}
	
	/**
	 * Process request that check email duplication. 
	 * @param checkEmailDTO email.
	 * @author LCH
	 */
	
	@RequestMapping(value = "/check/email", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> checkEmail(@Valid UserCondition userCondition) {
		LOGGER.debug("check : {}", userCondition);
		return userService.isContains(userCondition) ? new ResponseEntity(HttpStatus.BAD_REQUEST) : new ResponseEntity(HttpStatus.OK);
	}
	
	/**
	 * Process request that check name duplication. 
	 * @param checkNameDTO name.
	 * @author LCH
	 */

	@RequestMapping(value = "/check/name", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> checkName(@Valid UserCondition userCondition) {
		LOGGER.debug("check : {}", userCondition);
		return userService.isContains(userCondition) ? new ResponseEntity(HttpStatus.BAD_REQUEST) : new ResponseEntity(HttpStatus.OK);
	}
	
	/**
	 * Process request that login user. 
	 * @param loginUserDTO user's id and password.
	 * @author LCH
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity login(String email, String password) {
		LOGGER.debug("login : {}", email + " , " + password);
		return userService.login(email, password) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void logout() {
	}
}
