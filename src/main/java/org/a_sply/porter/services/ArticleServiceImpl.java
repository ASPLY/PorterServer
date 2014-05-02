package org.a_sply.porter.services;

import java.util.List;

import org.a_sply.porter.domain.Article;
import org.a_sply.porter.domain.Image;
import org.a_sply.porter.domain.Part;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.article.ArticleDTO;
import org.a_sply.porter.dto.article.CreateArticleDTO;
import org.a_sply.porter.dto.article.CreatedArticleDTO;
import org.a_sply.porter.model.ImageManager;
import org.a_sply.porter.model.ImageManagerImpl;
import org.a_sply.porter.repository.ArticleRepository;
import org.a_sply.porter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationService authenticationService;

	private ImageManager imageManager = new ImageManagerImpl();

	@Override
	public ArticleDTO get(int id) {
		Article article = articleRepository.get(id);
		if (article == null)
			return null;
		return article.articleDTO();
	}

	@Override
	public CreatedArticleDTO create(CreateArticleDTO createArticleDTO) {
		User user = authenticationService.getCurrentUser();
		List<Image> images = imageManager.uploadImageAndMakeThumbnails(createArticleDTO.getImageFiles());
		Part part = Part.from(createArticleDTO.getPart());
		part.setImages(images);
		Article article = new Article(user, part);
		articleRepository.save(article);
		return article.createdArticleDTO();
	}

	@Override
	public boolean sold(int id) {
		Article article = articleRepository.get(id);
		if (article == null)
			return false;
		article.setIsSold(true);
		articleRepository.sold(id);
		return true;
	}

}
