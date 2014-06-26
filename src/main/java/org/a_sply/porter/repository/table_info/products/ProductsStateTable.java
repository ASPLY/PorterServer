package org.a_sply.porter.repository.table_info.products;

import org.a_sply.porter.repository.table_info.BaseTable;

public class ProductsStateTable extends BaseTable{

	public ProductsStateTable(){
		super("products_state");
	}
	
	public String PRODUCT_ID(){
		return field("PRODUCT_ID");
	}
	
	public String STATE(){
		return field("STATE");
	}
}
