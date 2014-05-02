package com.porter.web.dao;

import java.util.List;

import com.porter.web.dto.PartDTO;
import com.porter.web.model.part.PartList;
import com.porter.web.model.part.Search;

public interface PartDao {

	PartDTO findById(long id);
	List<PartDTO> getAll();
	int insert(PartDTO partDTO);
	List<PartDTO> findBySearch(Search search);
	
}
