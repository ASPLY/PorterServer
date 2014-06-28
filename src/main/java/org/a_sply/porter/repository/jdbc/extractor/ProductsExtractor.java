
package org.a_sply.porter.repository.jdbc.extractor;
import static org.a_sply.porter.repository.table_info.TableConst.PRODUCTS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.a_sply.porter.domain.product.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ProductsExtractor implements ResultSetExtractor<List<Product>> {
	
	private ProductExtractor prodcutExtractor;
	
	public ProductsExtractor(ExtractType extractType) {
		this.prodcutExtractor = new ProductExtractor(extractType);
	}
	
	@Override
	public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, Product> products = new HashMap<Long, Product>();
		
		if(!rs.isBeforeFirst())
			return null;
		
		while(rs.next()){
			long id = rs.getLong(PRODUCTS.ID());
			if(!products.containsKey(id)){
				rs.previous();
				Product product = prodcutExtractor.extractData(rs);
				products.put(product.getId(), product);
			}
		}
		
		return new ArrayList<Product>(products.values());
	}
	
	
}