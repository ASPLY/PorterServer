package org.a_sply.porter.repository.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.a_sply.porter.domain.order.Order;
import org.a_sply.porter.domain.product.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import static org.a_sply.porter.repository.table_info.TableConst.*;

public class OrderExtractor implements ResultSetExtractor<Order> {
	
	private ProductExtractor productExtractor;

	public OrderExtractor(ExtractType extractType) {
		this.productExtractor = new ProductExtractor(extractType);
	}

	@Override
	public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
		Product product = null;
		Integer amount = null;
		Long nextRowId = null;
		Long id = null;
		
		if(!rs.next())
			return null;
		else
			do{
	        	nextRowId = rs.getLong(ORDERS.ID());
	        	if(id != null && id != nextRowId){
	        		rs.previous();
	        		break;
	        	}
				
				if(amount == null)
					amount = rs.getInt(ORDERS.AMOUNT());
				
				if(product == null)
					product = productExtractor.extractData(rs);
				
	        	id = nextRowId;
			}while(rs.next());
		
		return new Order(id, product.getOwner(), product, amount);
	}
}
