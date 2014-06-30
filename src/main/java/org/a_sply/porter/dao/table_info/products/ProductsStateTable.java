package org.a_sply.porter.dao.table_info.products;

import org.a_sply.porter.dao.table_info.BaseTable;

public class ProductsStateTable extends BaseTable{

	public final String PRODUCT_ID = "PRODUCT_ID";
	public final String FIELD_PRODUCT_ID = field(PRODUCT_ID);
	
	public final String STATE = "STATE";
	public final String FIELD_STATE = field(STATE);
	
	public ProductsStateTable(){
		super("products_state");
	}
}
