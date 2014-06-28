
package org.a_sply.porter.repository.jdbc.extractor;
import static org.a_sply.porter.repository.table_info.TableConst.PRODUCTS;
import static org.a_sply.porter.repository.table_info.TableConst.PRODUCTS_DES_IMAGE_URLS;
import static org.a_sply.porter.repository.table_info.TableConst.PRODUCTS_STATE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.a_sply.porter.domain.User;
import org.a_sply.porter.domain.product.CarInfo;
import org.a_sply.porter.domain.product.ImageUrls;
import org.a_sply.porter.domain.product.PartType;
import org.a_sply.porter.domain.product.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ProductExtractor implements ResultSetExtractor<Product> {
	
	private ExtractType extractType;
	private UserExtractor userExtractor;
	
	public ProductExtractor(ExtractType extractType) {
		this.extractType = extractType;
		this.userExtractor = new UserExtractor();
	}

	private Product extractData(ExtractType extractType, ResultSet rs) throws SQLException, DataAccessException{
		
		Long nextRowId = null;
		Long id = null;
		
		User owner = null;

		CarInfo carInfo = null;
		PartType partType = null;
		String name = null;
		Double price = null;
		
		String listImageUrl = null;
		
		Set<String> normalImageUrls = new LinkedHashSet<String>();
		Set<String> zoomInImageUrls = new LinkedHashSet<String>();
		
		Integer amount = null;
		
		String state = null;
		
		if(!rs.next())
			return null;
		else
			do{
	        	nextRowId = rs.getLong(PRODUCTS.ID());
	        	if(id != null && id != nextRowId){
	        		rs.previous();
	        		break;
	        	}
	        	
	        	if(owner==null && extractType == ExtractType.WITH_DETAIL){
	        		rs.previous();
	        		owner = userExtractor.extractData(rs);
	        	}else
	        		owner = new User();

	        	if(carInfo==null)
	        		carInfo = new CarInfo(
	        				rs.getInt(PRODUCTS.CAR_MAKER_NO()), 
	        				rs.getInt(PRODUCTS.CAR_TYPE_NO()),
	        				rs.getInt(PRODUCTS.CAR_MODEL_NO()),
	        				rs.getInt(PRODUCTS.CAR_YEAR()));
	        	
	        	if(partType==null)
	        		partType = new PartType(
	        				rs.getInt(PRODUCTS.MAIN_CATEGORY_NO()),
	        				rs.getInt(PRODUCTS.SUB_CATEGORY_NO()));
	        	
	        	if(name == null)
	        		name = rs.getString(PRODUCTS.NAME());
	        	
	        	if(price == null)
	        		price = rs.getDouble(PRODUCTS.PRICE());
	        	
	        	if(listImageUrl == null)
	        		listImageUrl = rs.getString(PRODUCTS.LIST_IMAGE_URL());
	        	
	        	if(extractType == ExtractType.WITH_DETAIL){
	        		normalImageUrls.add(rs.getString(PRODUCTS_DES_IMAGE_URLS.NORMAL_URL()));
	        		zoomInImageUrls.add(rs.getString(PRODUCTS_DES_IMAGE_URLS.ZOOMIN_URL()));
	        	}
	        	
	        	if(amount == null)
	        		amount = rs.getInt(PRODUCTS.AMOUNT());
	        	
	        	if(state == null && extractType == ExtractType.WITH_DETAIL)
	        		state = rs.getString(PRODUCTS_STATE.STATE());
	
	        	id = nextRowId;
	        }while(rs.next());
        
        ImageUrls images = new ImageUrls(listImageUrl, Arrays.asList(normalImageUrls.toArray(new String[0])), Arrays.asList(zoomInImageUrls.toArray(new String[0])));
        
        return new Product
				.Builder()
				.id(id)
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
	
	@Override
	public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
		return extractData(extractType, rs);
	}
}