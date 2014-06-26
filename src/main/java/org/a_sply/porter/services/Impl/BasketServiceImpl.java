package org.a_sply.porter.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.domain.ArticleList;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.article_list.ArticleListDTO;
import org.a_sply.porter.dto.article_list.ArticleListsDTO;
import org.a_sply.porter.repository.BasketRepository;
import org.a_sply.porter.services.AuthenticationService;
import org.a_sply.porter.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BasketServiceImpl implements BasketService {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private BasketRepository basketRepository;

	@Override
	public void put(int articleId) {
		User user = authenticationService.getCurrentUser();
		basketRepository.save(user.getId(), articleId);
	}

	@Override
	public ArticleListsDTO findByUser() {
		User user = authenticationService.getCurrentUser();
		List<ArticleList> articleList = basketRepository.findByUserId(user.getId());
		return toDTO(articleList);
	}
	
	private ArticleListsDTO toDTO(List<ArticleList> articleLists) {
		ArticleListsDTO articleListsDTO = new ArticleListsDTO();
		List<ArticleListDTO> articleListDTOs = new ArrayList<ArticleListDTO>();
		
		for (ArticleList articleList : articleLists) 
			articleListDTOs.add(articleList.articleListDTO());
		
		articleListsDTO.setArticleLists(articleListDTOs);
		return articleListsDTO;
	}

}
