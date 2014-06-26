package org.a_sply.porter.repository.table_info.products;

import org.a_sply.porter.repository.table_info.BaseTable;


public class ProductsTable extends BaseTable{
	
	public ProductsTable(){
		super("products");
	}
	
	public String ID(){
		return field("ID");
	}
	
	public String USER_ID(){
		return field("USER_ID");
	}
	
	public String CAR_MAKER_NO(){
		return field("CAR_MAKER_NO");
	}
	
	public String CAR_TYPE_NO(){
		return field("CAR_TYPE_NO");
	}
	
	public String CAR_MODEL_NO(){
		return field("CAR_MODEL_NO");
	}

	public String CAR_YEAR(){
		return field("CAR_YEAR");
	}
	
	public String MAIN_CATEGORY_NO(){
		return field("MAIN_CATEGORY_NO");
	}
	
	public String SUB_CATEGORY_NO(){
		return field("SUB_CATEGORY_NO");
	}
	
	public String NAME(){
		return field("NAME");
	}
	
	public String PRICE(){
		return field("PRICE");
	}
	
	public String AMOUNT(){
		return field("AMOUNT");
	}
	
	public String LIST_IMAGE_URL(){
		return field("LIST_IMAGE_URL");
	}
}
