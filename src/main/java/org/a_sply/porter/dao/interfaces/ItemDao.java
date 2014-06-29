package org.a_sply.porter.dao.interfaces;

import java.util.List;

import org.a_sply.porter.domain.item.Item;

public interface ItemDao {
	
	void insert(Item item);
	void delete(long itemId);
	void delete(long[] itemIds);
	List<Item> selectByUserId(int userId);
}
