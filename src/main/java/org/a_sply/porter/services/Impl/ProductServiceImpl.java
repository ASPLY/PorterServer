package org.a_sply.porter.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.domain.User;
import org.a_sply.porter.domain.product.CarInfo;
import org.a_sply.porter.domain.product.ImageFile;
import org.a_sply.porter.domain.product.ImageUrls;
import org.a_sply.porter.domain.product.PartType;
import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.domain.product.ProductCondition;
import org.a_sply.porter.dto.product.CountProductDTO;
import org.a_sply.porter.dto.product.CountedProductDTO;
import org.a_sply.porter.dto.product.CreateProductDTO;
import org.a_sply.porter.dto.product.CreatedProductDTO;
import org.a_sply.porter.dto.product.RequestProductDTO;
import org.a_sply.porter.dto.product.RequestedProductDTO;
import org.a_sply.porter.dto.product.SearchProductDTO;
import org.a_sply.porter.dto.product.SearchedProductDTO;
import org.a_sply.porter.repository.ProductRepository;
import org.a_sply.porter.services.ProductService;
import org.a_sply.porter.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private org.a_sply.porter.domain.CDNServer cdnServer;

	@Override
	public CreatedProductDTO create(CreateProductDTO createProductDTO) {
		User owner = AuthenticationUtil.getCurrentUser();
		CarInfo carInfo = new CarInfo(createProductDTO.getCarMakerNo(), createProductDTO.getCarTypeNo(), createProductDTO.getCarModelNo(), createProductDTO.getCarYear());
		PartType partType = new PartType(createProductDTO.getMainCategoryNo(), createProductDTO.getSubCategoryNo());
		String name = createProductDTO.getName();
		double price = createProductDTO.getPrice();
		
		ImageFile imageFile = new ImageFile(createProductDTO.getListImage());
		String listImageUrl = cdnServer.upload(imageFile.getFile(), "list");
		
		List<String> normalImageUrls = new ArrayList<String>();
		for (MultipartFile multipartFile : createProductDTO.getNomalImages()){
			imageFile = new ImageFile(multipartFile);
			normalImageUrls.add(cdnServer.upload(imageFile.getFile(), "normal"));
		}

		List<String> zoomInImageUrls = new ArrayList<String>();
		for (MultipartFile multipartFile : createProductDTO.getZoomInImages()){
			imageFile = new ImageFile(multipartFile);
			zoomInImageUrls.add(cdnServer.upload(imageFile.getFile(), "zoomIn"));
		}
		
		int amount = createProductDTO.getAmount();
		
		ImageUrls images = new ImageUrls(listImageUrl, normalImageUrls, zoomInImageUrls);
		
		String state = createProductDTO.getState();


		Product product = new Product
							.Builder()
							.owner(owner)
							.carInfo(carInfo)
							.partType(partType)
							.name(name)
							.price(price)
							.amount(amount)
							.images(images)
							.state(state)
							.build();
		
		long id = productRepository.insert(product);
		return new CreatedProductDTO(id);
	}

	@Override
	public RequestedProductDTO request(RequestProductDTO requestProductDTO) {
		Product product = productRepository.selectById(requestProductDTO.getId());
		return product == null ? null : new RequestedProductDTO(product);
	}

	@Override
	public SearchedProductDTO search(SearchProductDTO searchProductDTO) {
		ProductCondition productCondition = new ProductCondition();
		productCondition.setCarInfo(new CarInfo(
				searchProductDTO.getCarMakerNo(), searchProductDTO.getCarTypeNo(), 
				searchProductDTO.getCarModelNo(), searchProductDTO.getCarYear()));
		productCondition.setPartType(new PartType(searchProductDTO.getMainCategoryNo(), searchProductDTO.getSubCategoryNo()));
		productCondition.setKeyword(searchProductDTO.getKeyword());
		productCondition.setCount(searchProductDTO.getCount());
		productCondition.setOffset(searchProductDTO.getOffset());
		
		List<Product> products = productRepository.selectByCondition(productCondition);
		
		return products == null ? null : new SearchedProductDTO(products);
	}
	
	@Override
	public SearchedProductDTO search() {
		ProductCondition productCondition = new ProductCondition();
		productCondition.setUserId(AuthenticationUtil.getCurrentUser().getId());
		
		List<Product> products = productRepository.selectByCondition(productCondition);
		return products == null ? null : new SearchedProductDTO(products);
	}

	@Override
	public CountedProductDTO count(CountProductDTO countProductDTO) {
		ProductCondition productCondition = new ProductCondition();
		productCondition.setCarInfo(new CarInfo(
				countProductDTO.getCarMakerNo(), countProductDTO.getCarTypeNo(), 
				countProductDTO.getCarModelNo(), countProductDTO.getCarYear()));
		productCondition.setPartType(new PartType(countProductDTO.getMainCategoryNo(), countProductDTO.getSubCategoryNo()));
		
		int count = productRepository.countByCondition(productCondition);
		return new CountedProductDTO(count);
	}


	
}
