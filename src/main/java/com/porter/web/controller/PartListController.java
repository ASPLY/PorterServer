package com.porter.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.porter.web.model.part.PartList;
import com.porter.web.model.part.Search;
import com.porter.web.serivce.PartListService;


@Controller
@RequestMapping(value = "/partLists")
public class PartListController{

	private static final Logger logger = LoggerFactory.getLogger(PartListController.class);

	@Autowired
	private PartListService partListService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<PartList> get(@PathVariable Long id) {
		PartList partList = partListService.get(id);
		if (partList == null)
			return new ResponseEntity<PartList>(partList, HttpStatus.NOT_FOUND);
		return new ResponseEntity<PartList>(partList, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<PartList>> search(Search search) {
		List<PartList> partLists = partListService.get(search);
		if (partLists.size() == 0)
			return new ResponseEntity<List<PartList>>(partLists, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<PartList>>(partLists, HttpStatus.OK);
	}

}
