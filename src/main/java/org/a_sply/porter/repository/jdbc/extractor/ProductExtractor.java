package org.a_sply.porter.repository.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.domain.User;
import org.a_sply.porter.domain.product.CarInfo;
import org.a_sply.porter.domain.product.ImageUrls;
import org.a_sply.porter.domain.product.PartType;
import org.a_sply.porter.domain.product.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ProductExtractor implements ResultSetExtractor<Product> {
	
	@Override
	public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		User owner = null;
		
		CarInfo carInfo = null;
		PartType partType = null;
		String name = null;
		Double price = null;
		
		String listImageUrl = null;
		
		List<String> normalImageUrls = new ArrayList<String>();
		List<String> zoomInImageUrls = new ArrayList<String>();
		
		Integer amount = null;
		
		ImageUrls images = new ImageUrls(listImageUrl, normalImageUrls, zoomInImageUrls);
		
		String state = null;
		
        while(rs.next()) {
        	if(owner==null){
        		owner = new User();
        	}
        	
        }
		
		return new Product
				.Builder()
				.owner(owner)
				.carInfo(carInfo)
				.partType(partType)
				.name(name)
				.price(price)
				.amount(amount)
				.images(images)
				.state(state)
				.build();
	}
	
	
}