package org.a_sply.porter.dao.table_info.items;

import org.a_sply.porter.dao.table_info.BaseTable;

public class ItemsTable extends BaseTable{

	public ItemsTable() {
		super("items");
	}
	
	public String ITEM_ID(){
		return field("ITEM_ID");
	}
	
	public String USER_ID(){
		return field("USER_ID");
	}
	
	public String PRODUCT_ID(){
		return field("PRODUCT_ID");
	}

	public String QUANTITY() {
		return field("QUANTITY");
	}

}
