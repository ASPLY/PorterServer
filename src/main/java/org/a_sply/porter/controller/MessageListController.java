package org.a_sply.porter.controller;

import org.a_sply.porter.dto.message.MessageListsDTO;
import org.a_sply.porter.services.MessageListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/messageLists")
public class MessageListController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageListController.class);

	@Autowired
	private MessageListService messageListService;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> user() {
		LOGGER.debug("user");
		MessageListsDTO messageListsDTO = messageListService.searchByUser();
		return new ResponseEntity<MessageListsDTO>(messageListsDTO, HttpStatus.OK);
	}
}
