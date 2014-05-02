package com.porter.web.serivce;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.porter.web.dao.PartDao;
import com.porter.web.dto.PartDTO;
import com.porter.web.model.part.PartList;
import com.porter.web.model.part.Search;

@Service
public class PartListService {
	
	@Resource
	private PartDao partDao;

	public PartList get(Long id) {
		PartDTO partDTO = partDao.findById(id);
		return partDTO.partList();
	}

	public List<PartList> get(Search search) {
		List<PartDTO> partDTOs = partDao.findBySearch(search);
		List<PartList> partLists = new ArrayList<PartList>();
		for (PartDTO partDTO : partDTOs)
			partLists.add(partDTO.partList());
		
		return partLists;
	}

}
