package org.a_sply.porter.services.Impl;

import java.util.List;

import org.a_sply.porter.dao.interfaces.ArticleDao;
import org.a_sply.porter.dao.interfaces.UserDao;
import org.a_sply.porter.domain.Article;
import org.a_sply.porter.domain.Image;
import org.a_sply.porter.domain.Part;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.article.ArticleDTO;
import org.a_sply.porter.dto.article.CreateArticleDTO;
import org.a_sply.porter.dto.article.CreatedArticleDTO;
import org.a_sply.porter.services.ArticleService;
import org.a_sply.porter.services.AuthenticationService;
import org.a_sply.porter.util.ImageManager;
import org.a_sply.porter.util.ImageManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ArticleService implementation. 
 * @author LCH
 */

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AuthenticationService authenticationService;

	private ImageManager imageManager = new ImageManagerImpl();

	@Override
	public ArticleDTO get(int id) {
		Article article = articleDao.get(id);
		if (article == null)
			return null;
		return article.articleDTO();
	}

	@Override
	public CreatedArticleDTO create(CreateArticleDTO createArticleDTO) {
		User user = authenticationService.getCurrentUser();
		List<Image> images = imageManager.upload(createArticleDTO.getImageFiles());
		Part part = Part.from(createArticleDTO.getPart());
		part.setImages(images);
		Article article = new Article(user, part);
		articleDao.save(article);
		return article.createdArticleDTO();
	}

	@Override
	public boolean sold(int id) {
		Article article = articleDao.get(id);
		if (article == null)
			return false;
		article.setIsSold(true);
		articleDao.sold(id);
		return true;
	}

}
