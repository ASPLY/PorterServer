package org.a_sply.porter.services.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.a_sply.porter.dao.interfaces.ProductDao;
import org.a_sply.porter.dao.interfaces.SelectType;
import org.a_sply.porter.domain.product.ImageFile;
import org.a_sply.porter.domain.product.MultipartImageFile;
import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.domain.product.ProductCondition;
import org.a_sply.porter.services.ProductService;
import org.a_sply.porter.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private org.a_sply.porter.domain.CDNServer cdnServer;

	@Override
	@Transactional
	public void create(Product product, MultipartImageFile multipartImageFile) {
		
		ImageFile imageFile = new ImageFile(multipartImageFile.getListImage());
		String listImageUrl = cdnServer.upload(imageFile.getFile(), "list");
		
		List<String> normalImageUrls = new ArrayList<String>();
		for (MultipartFile multipartFile : multipartImageFile.getNomalImages()){
			imageFile = new ImageFile(multipartFile);
			normalImageUrls.add(cdnServer.upload(imageFile.getFile(), "normal"));
		}

		List<String> zoomInImageUrls = new ArrayList<String>();
		for (MultipartFile multipartFile : multipartImageFile.getZoomInImages()){
			imageFile = new ImageFile(multipartFile);
			zoomInImageUrls.add(cdnServer.upload(imageFile.getFile(), "zoomIn"));
		}

		product.setUserId(AuthenticationUtil.getCurrentUser().getId());
		product.setListImageUrl(listImageUrl);
		product.setNormalImageUrls(normalImageUrls);
		product.setZoomInImageUrls(zoomInImageUrls);
		
		productDao.insert(product);
	}

	@Override
	public Product get(long productId) {
		return productDao.selectById(SelectType.WITH_DETAIL, productId);
	}

	@Override
	public List<Product> search(ProductCondition productCondition) {
		List<Product> products = productDao.selectByCondition(SelectType.WITHOUT_DETAIL, productCondition);
		return products;
	}

	@Override
	public int count(ProductCondition productCondition) {
		return productDao.countByCondition(productCondition);
	}

	@Override
	public List<Product> getMine(ProductCondition productCondition) {
		productCondition.setUserId(AuthenticationUtil.getCurrentUser().getId());
		return search(productCondition);
	}
}
