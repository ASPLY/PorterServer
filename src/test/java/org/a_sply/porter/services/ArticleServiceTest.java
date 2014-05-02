package org.a_sply.porter.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.article.CreateArticleDTO;
import org.a_sply.porter.dto.article.CreatedArticleDTO;
import org.a_sply.porter.repository.ArticleRepository;
import org.a_sply.porter.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ArticleServiceTest {

	@InjectMocks
	private ArticleService articleService = new ArticleServiceImpl();

	@Mock
	private ArticleRepository articleRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private AuthenticationService authenticationService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create_성공() {
		// when
		CreateArticleDTO createArticleDTO = UnitTestUtil.createArticleDTO();
		User userA = UnitTestUtil.userA();
		when(authenticationService.getCurrentUser()).thenReturn(userA);

		// given
		CreatedArticleDTO createdArticleDTO = articleService.create(createArticleDTO);

		// then
		assertThat(createdArticleDTO.getUser().getEmail(), is(userA.getEmail()));
		verify(authenticationService, times(1));
	}

	@Test
	public void get_성공() {
		// when
		int id = 1;
		when(articleRepository.get(id)).thenReturn(UnitTestUtil.article());

		// given
		articleService.get(id);

		// then
		verify(articleRepository, times(1)).get(id);
	}

	@Test(expected = RuntimeException.class)
	public void get_실패() {
		// when
		int id = 1;
		when(articleRepository.get(id)).thenThrow(RuntimeException.class);

		// given
		articleService.get(id);

		// then
		verify(articleRepository, times(1)).get(id);
	}
}
