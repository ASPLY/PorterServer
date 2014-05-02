package com.porter.web.serivce;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.porter.web.dao.PartDao;
import com.porter.web.dao.UserDao;
import com.porter.web.dto.PartDTO;
import com.porter.web.dto.UserDTO;
import com.porter.web.model.part.Part;

@Service
public class PartService {
	
	@Resource
	private PartDao partDao;
	@Resource
	private UserDao userDao;
	
	public Part get(Long id) {
		PartDTO partDTO = partDao.findById(id);
		UserDTO userDTO = userDao.findById(partDTO.getUserId());
		return partDTO.part(userDTO.toModel());
	}

	public List<Part> getAll() {
		List<PartDTO> partDTOs = partDao.getAll();
		
		List<Part> parts = new ArrayList<Part>();
		for (PartDTO partDTO : partDTOs){
			UserDTO userDTO = userDao.findById(partDTO.getUserId());
			parts.add(partDTO.part(userDTO.toModel()));
		}
		return parts;
	}

	public Part create(String userEmail, Part part) {
		UserDTO userDTO = userDao.findByEmail(userEmail);
		PartDTO partDTO = part.dto(userDTO.getId());
		partDao.insert(partDTO);
		return partDTO.part(userDTO.toModel());
	}
	
//	public Long register(Part part, CookieValue cookieValue) {
//		if(part == null && cookieValue != null)
//			throw new IllegalArgumentException("part : null, cookieValue : "+cookieValue.getValue());
//		if(part == null && cookieValue == null)
//			throw new IllegalArgumentException("part : null, cookieValue : null");
//		
//		part.verification();
//		part.setUserId(cookieValue.getId());
//		part.setUserCrcEmail(cookieValue.getCrcEmail());
//		
//		//partRepository.save(part); id 값을 추상화 !! 해라.
//		Integer id = partRepository.save(part);
//		return id.longValue();
//	}
//
//	public List<PartList> getList(Search search) {
//		search.verification();
//		return partRepository.find(search);
//	}
//
//	public Part get(Long partId) {
//		if(partId == null)
//			throw new IllegalArgumentException("partId : "+partId);
//		return partRepository.get(partId);
//	}
//
//	public List<PartList> search(Search search) {
//		search.verification();
//		return partRepository.find(search);
//	}

}
