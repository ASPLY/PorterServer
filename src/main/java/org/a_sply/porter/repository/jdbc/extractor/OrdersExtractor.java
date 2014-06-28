package org.a_sply.porter.repository.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.a_sply.porter.domain.order.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class OrdersExtractor implements ResultSetExtractor<List<Order>> {

	@Override
	public List<Order> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		return null;
	}

}
