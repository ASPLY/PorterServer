package org.a_sply.porter.services;

import org.a_sply.porter.domain.item.Cart;
import org.a_sply.porter.domain.item.Item;

public interface ItemService {
	
	void add(Item item);
	void remove(long itemId);
	void remove(long[] itemIds);
	Cart getMine();
	
}
