package org.a_sply.porter.services.Impl;

import java.util.List;

import org.a_sply.porter.dao.interfaces.ItemDao;
import org.a_sply.porter.dao.interfaces.ProductDao;
import org.a_sply.porter.dao.interfaces.SelectType;
import org.a_sply.porter.domain.item.Cart;
import org.a_sply.porter.domain.item.Item;
import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.exception.ProductQuantityShortageException;
import org.a_sply.porter.services.ItemService;
import org.a_sply.porter.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public void add(Item item) {
		Product product = productDao.selectById(SelectType.WITHOUT_DETAIL, item.getProductId());
		item.setUserId(AuthenticationUtil.getCurrentUser().getUserId());
		
		if(item.getQuantity() > product.getQuantity())
			throw new ProductQuantityShortageException();
		
		itemDao.insert(item);
	}

	@Override
	public void remove(long itemId) {
		itemDao.delete(itemId);
	}
	
	@Override
	public void remove(long[] itemIds) {
		itemDao.delete(itemIds);
	}

	@Override
	public Cart getMine() {
		List<Item> items = itemDao.selectByUserId(AuthenticationUtil.getCurrentUser().getUserId());
		for (Item item : items) 
			item.setProduct(productDao.selectById(SelectType.WITHOUT_DETAIL, item.getProductId()));
		return new Cart(items);
	}	

}
