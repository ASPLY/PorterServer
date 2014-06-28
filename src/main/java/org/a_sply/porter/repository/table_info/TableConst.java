package org.a_sply.porter.repository.table_info;

import org.a_sply.porter.repository.table_info.orders.OrdersTable;
import org.a_sply.porter.repository.table_info.products.ProductsDesImageUrlsTable;
import org.a_sply.porter.repository.table_info.products.ProductsStateTable;
import org.a_sply.porter.repository.table_info.products.ProductsTable;
import org.a_sply.porter.repository.table_info.users.UsersAuthoritiesTable;
import org.a_sply.porter.repository.table_info.users.UsersTable;

public class TableConst {
	
	public static final ProductsTable PRODUCTS = new ProductsTable();
	public static final ProductsStateTable PRODUCTS_STATE = new ProductsStateTable();
	public static final ProductsDesImageUrlsTable PRODUCTS_DES_IMAGE_URLS = new ProductsDesImageUrlsTable();
	
	public final static UsersAuthoritiesTable USERS_AUTHORITIES = new UsersAuthoritiesTable();
	public final static UsersTable USERS = new UsersTable();
	
	public final static OrdersTable ORDERS = new OrdersTable();
}
