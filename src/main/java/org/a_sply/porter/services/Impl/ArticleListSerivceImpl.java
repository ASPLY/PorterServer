package org.a_sply.porter.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.dao.interfaces.ArticleListDao;
import org.a_sply.porter.domain.ArticleList;
import org.a_sply.porter.domain.RequestArticleLists;
import org.a_sply.porter.domain.Search;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.article_list.ArticleListDTO;
import org.a_sply.porter.dto.article_list.ArticleListsDTO;
import org.a_sply.porter.dto.article_list.RequestArticleListsDTO;
import org.a_sply.porter.dto.article_list.SearchArticleListDTO;
import org.a_sply.porter.services.ArticleListService;
import org.a_sply.porter.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ArticleListService implementation. 
 * @author LCH
 */

@Service
@Transactional
public class ArticleListSerivceImpl implements ArticleListService {

	@Autowired
	private ArticleListDao articleListDao;
	
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public ArticleListsDTO search(SearchArticleListDTO searchPartListDTO) {
		Search search = Search.from(searchPartListDTO);
		List<ArticleList> articleLists = articleListDao.search(search);
		return toDTO(articleLists);
	}

	private ArticleListsDTO toDTO(List<ArticleList> articleLists) {
		ArticleListsDTO articleListsDTO = new ArticleListsDTO();
		List<ArticleListDTO> articleListDTOs = new ArrayList<ArticleListDTO>();
		
		for (ArticleList articleList : articleLists) 
			articleListDTOs.add(articleList.articleListDTO());
		
		articleListsDTO.setArticleLists(articleListDTOs);
		return articleListsDTO;
	}

	@Override
	public ArticleListsDTO searchByUser() {
		User currentUser = authenticationService.getCurrentUser();
		List<ArticleList> articleLists = articleListDao.searchByEmail(currentUser.getEmail());
		return toDTO(articleLists);
	}

	@Override
	public ArticleListsDTO get(RequestArticleListsDTO getArticleListsDTO) {
		RequestArticleLists getArticleLists = RequestArticleLists.from(getArticleListsDTO);
		List<ArticleList> articleLists = articleListDao.get(getArticleLists);
		return toDTO(articleLists);
	}

}
