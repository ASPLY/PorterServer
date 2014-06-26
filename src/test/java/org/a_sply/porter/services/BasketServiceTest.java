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
import org.a_sply.porter.repository.BasketRepository;
import org.a_sply.porter.repository.UserRepository;
import org.a_sply.porter.services.Impl.BasketServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BasketServiceTest {

	@InjectMocks
	private BasketService basketService = new BasketServiceImpl();

	@Mock
	private BasketRepository basketRepository;
	
	@Mock
	private AuthenticationService authenticationService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void put_() {
		// when
		int articleId = 1;
		User userA = UnitTestUtil.userA();
		when(authenticationService.getCurrentUser()).thenReturn(userA);
		
		// given
		basketService.put(articleId);

		// then
		verify(basketRepository).save(userA.getId(), articleId);
		verify(authenticationService).getCurrentUser();
	}
}
