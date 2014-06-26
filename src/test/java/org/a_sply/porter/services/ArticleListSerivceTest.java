package org.a_sply.porter.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.ArticleList;
import org.a_sply.porter.domain.RequestArticleLists;
import org.a_sply.porter.domain.Search;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.article_list.ArticleListsDTO;
import org.a_sply.porter.dto.article_list.RequestArticleListsDTO;
import org.a_sply.porter.dto.article_list.SearchArticleListDTO;
import org.a_sply.porter.repository.ArticleListRepository;
import org.a_sply.porter.services.Impl.ArticleListSerivceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ArticleListSerivceTest {

	@InjectMocks
	private ArticleListService articleService = new ArticleListSerivceImpl();

	@Mock
	private ArticleListRepository articleListRepository;

	@Mock
	private AuthenticationService authenticationService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void search_성공() {
		// when
		SearchArticleListDTO searchPartListDTO = UnitTestUtil.searchArticleListDTO();
		List<ArticleList> articles = new ArrayList<ArticleList>();
		articles.add(new ArticleList());
		articles.add(new ArticleList());
		Search search = Search.from(searchPartListDTO);
		when(articleListRepository.search(search)).thenReturn(articles);

		// given
		ArticleListsDTO results = articleService.search(searchPartListDTO);

		// then
		verify(articleListRepository).search(search);
		assertThat(articles.size(), is(results.getArticleLists().size()));
	}

	@Test
	public void searchByUser_성공() {
		// when
		List<ArticleList> articles = new ArrayList<ArticleList>();
		articles.add(new ArticleList());
		articles.add(new ArticleList());
		User userA = UnitTestUtil.userA();
		when(authenticationService.getCurrentUser()).thenReturn(userA);
		when(articleListRepository.searchByEmail(userA.getEmail())).thenReturn(articles);

		// given
		ArticleListsDTO results = articleService.searchByUser();

		// then
		assertThat(articles.size(), is(results.getArticleLists().size()));
	}

	@Test
	public void get_성공() {
		// when
		List<ArticleList> articles = new ArrayList<ArticleList>();
		articles.add(new ArticleList());
		articles.add(new ArticleList());
		RequestArticleListsDTO getArticleListsDTO = UnitTestUtil.getArticleListsDTO();
		when(articleListRepository.get(RequestArticleLists.from(getArticleListsDTO))).thenReturn(articles);

		// given
		ArticleListsDTO results = articleService.get(getArticleListsDTO);

		// then
		verify(articleListRepository).get(RequestArticleLists.from(getArticleListsDTO));
		assertThat(articles.size(), is(results.getArticleLists().size()));
	}
}
