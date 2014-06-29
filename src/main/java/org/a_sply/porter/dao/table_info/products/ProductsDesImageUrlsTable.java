package org.a_sply.porter.dao.table_info.products;

import org.a_sply.porter.dao.table_info.BaseTable;

public class ProductsDesImageUrlsTable extends BaseTable{

	public ProductsDesImageUrlsTable(){
		super("products_des_image_urls");
	}
	
	public String PRODUCT_ID(){
		return field("PRODUCT_ID");
	}
	
	public String NORMAL_URL(){
		return field("NORMAL_URL");
	}
	
	public String ZOOMIN_URL(){
		return field("ZOOMIN_URL");
	}
}
