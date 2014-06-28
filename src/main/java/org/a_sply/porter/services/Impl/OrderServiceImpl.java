package org.a_sply.porter.services.Impl;


import java.util.List;

import org.a_sply.porter.domain.User;
import org.a_sply.porter.domain.order.Order;
import org.a_sply.porter.domain.order.OrderCondition;
import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.dto.order.AddOrderDTO;
import org.a_sply.porter.dto.order.AddedOrderDTO;
import org.a_sply.porter.dto.order.SearchedOrderDTO;
import org.a_sply.porter.repository.OrderRepository;
import org.a_sply.porter.services.AuthenticationService;
import org.a_sply.porter.services.OrderService;
import org.a_sply.porter.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public AddedOrderDTO add(AddOrderDTO addOrderDTO) {
		User user = AuthenticationUtil.getCurrentUser();
		Product product = new Product.Builder().id(addOrderDTO.getProductId()).build();
		Order order = new Order(user, product, addOrderDTO.getAmount());
		long id = orderRepository.insert(order);
		return new AddedOrderDTO(id);
	}
/*
	@Override
	public ArticleListsDTO findByUser() {
		User user = authenticationService.getCurrentUser();
		List<ArticleList> articleList = orderRepository.selectByUserId(user.getId());
		return toDTO(articleList);
	}
	
	private ArticleListsDTO toDTO(List<ArticleList> articleLists) {
		ArticleListsDTO articleListsDTO = new ArticleListsDTO();
		List<ArticleListDTO> articleListDTOs = new ArrayList<ArticleListDTO>();
		
		for (ArticleList articleList : articleLists) 
			articleListDTOs.add(articleList.articleListDTO());
		
		articleListsDTO.setArticleLists(articleListDTOs);
		return articleListsDTO;
	}*/

	@Override
	public SearchedOrderDTO search() {
		List<Order> orders = orderRepository.selectByCondition(new OrderCondition(AuthenticationUtil.getCurrentUser().getId()));
		return new SearchedOrderDTO(orders);
	}




}
