package org.a_sply.porter.dao.table_info.products;

import org.a_sply.porter.dao.table_info.BaseTable;

public class ProductsDesImageUrlsTable extends BaseTable{

	public final String PRODUCT_ID = "PRODUCT_ID";
	public final String FIELD_PRODUCT_ID = field(PRODUCT_ID);
	
	public final String NORMAL_URL = "NORMAL_URL";
	public final String FIELD_NORMAL_URL = field(NORMAL_URL);
	
	public final String ZOOMIN_URL = "ZOOMIN_URL";
	public final String FIELD_ZOOMIN_URL = field(ZOOMIN_URL);

	public ProductsDesImageUrlsTable(){
		super("products_des_image_urls");
	}
}
