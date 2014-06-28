package org.a_sply.porter.repository.table_info.orders;

import org.a_sply.porter.repository.table_info.BaseTable;

public class OrdersTable extends BaseTable{

	public OrdersTable() {
		super("orders");
	}
	
	public String ID(){
		return field("ID");
	}
	
	public String USER_ID(){
		return field("USER_ID");
	}
	
	public String PRODUCT_ID(){
		return field("PRODUCT_ID");
	}
	
	public String AMOUNT(){
		return field("AMOUNT");
	}

}
