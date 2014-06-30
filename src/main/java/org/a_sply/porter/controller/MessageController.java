package org.a_sply.porter.controller;

import org.a_sply.porter.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Process request related to message. 
 * @author LCH
 */

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping("/messages")
public class MessageController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private MessageService messageService;
	
	/**
	 * Process request that send other user the message.
	 * @param sendMessageDTO message content to send.
	 * @author LCH
	 */
	/*
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void send(@Valid SendMessageDTO sendMessageDTO) {
		LOGGER.debug("send : {}", sendMessageDTO);
		messageService.send(sendMessageDTO);
	}*/

	/**
	 * Process request that get message content.
	 * @param id message id.
	 * @return message content.
	 * @author LCH
	 */
/*
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable int id) {
		LOGGER.debug("get : {}", id);
		MessageDTO requestMessageDTO = messageService.get(id);
		if (requestMessageDTO == null)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<MessageDTO>(requestMessageDTO, HttpStatus.OK);
	}
	*/
	/**
	 * Process request that delete user owned a message. 
	 * @param id message id.
	 * @author LCH
	 */

	/*
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable long id) {
		LOGGER.debug("delete : {}", id);
		messageService.remove(id);
	}*/

}
