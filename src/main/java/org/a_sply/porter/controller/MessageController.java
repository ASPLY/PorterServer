package org.a_sply.porter.controller;

import javax.validation.Valid;

import org.a_sply.porter.domain.Message;
import org.a_sply.porter.dto.message.MessageDTO;
import org.a_sply.porter.dto.message.SendMessageDTO;
import org.a_sply.porter.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping("/messages")
public class MessageController extends BaseController {

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void send(@Valid SendMessageDTO sendMessageDTO) {
		messageService.send(sendMessageDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable int id) {
		MessageDTO requestMessageDTO = messageService.get(id);
		if (requestMessageDTO == null)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<MessageDTO>(requestMessageDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable int id) {
		messageService.remove(id);
	}

}
