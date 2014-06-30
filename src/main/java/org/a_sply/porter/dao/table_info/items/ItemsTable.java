package org.a_sply.porter.dao.table_info.items;

import org.a_sply.porter.dao.table_info.BaseTable;

public class ItemsTable extends BaseTable{

	private final String ITEM_ID = "ITEM_ID";
	public final String FIELD_ITEM_ID = field(ITEM_ID);
	
	private final String USER_ID = "USER_ID";
	public final String FIELD_USER_ID = field(USER_ID);
	
	private final String PRODUCT_ID = "PRODUCT_ID";
	public final String FIELD_PRODUCT_ID = field(PRODUCT_ID);
	
	private final String QUANTITY = "QUANTITY";
	public final String FIELD_QUANTITY = field(QUANTITY);

	public ItemsTable() {
		super("items");
	}
}
