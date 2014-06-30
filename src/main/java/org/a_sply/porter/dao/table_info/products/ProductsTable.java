package org.a_sply.porter.dao.table_info.products;

import org.a_sply.porter.dao.table_info.BaseTable;


public class ProductsTable extends BaseTable{
	
	public final String PRODUCT_ID = "PRODUCT_ID";
	public final String FIELD_PRODUCT_ID = field(PRODUCT_ID);
	
	public final String USER_ID = "USER_ID";
	public final String FIELD_USER_ID = field(USER_ID);
	
	public final String CAR_MAKER_NO = "CAR_MAKER_NO";
	public final String FIELD_CAR_MAKER_NO = field(CAR_MAKER_NO);
	
	public final String CAR_TYPE_NO = "CAR_TYPE_NO";
	public final String FIELD_CAR_TYPE_NO = field(CAR_TYPE_NO);
	
	public final String CAR_MODEL_NO = "CAR_MODEL_NO";
	public final String FIELD_CAR_MODEL_NO = field(CAR_MODEL_NO);
	
	public final String CAR_YEAR = "CAR_YEAR";
	public final String FIELD_CAR_YEAR = field(CAR_YEAR);
	
	public final String MAIN_CATEGORY_NO = "MAIN_CATEGORY_NO";
	public final String FIELD_MAIN_CATEGORY_NO = field(MAIN_CATEGORY_NO);
	
	public final String SUB_CATEGORY_NO = "SUB_CATEGORY_NO";
	public final String FIELD_SUB_CATEGORY_NO = field(SUB_CATEGORY_NO);
	
	public final String NAME = "NAME";
	public final String FIELD_NAME = field(NAME);
	
	public final String PRICE = "PRICE";
	public final String FIELD_PRICE = field(PRICE);
	
	public final String QUANTITY = "QUANTITY";
	public final String FIELD_QUANTITY = field(QUANTITY);
	
	public final String LIST_IMAGE_URL = "LIST_IMAGE_URL";
	public final String FIELD_LIST_IMAGE_URL = field(LIST_IMAGE_URL);

	public ProductsTable(){
		super("products");
	}
}
