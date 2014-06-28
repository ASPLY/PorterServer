package org.a_sply.porter.repository.jdbc;

import static org.a_sply.porter.repository.table_info.TableConst.PRODUCTS;
import static org.a_sply.porter.repository.table_info.TableConst.PRODUCTS_DES_IMAGE_URLS;
import static org.a_sply.porter.repository.table_info.TableConst.PRODUCTS_STATE;
import static org.a_sply.porter.repository.table_info.TableConst.USERS;
import static org.a_sply.porter.repository.table_info.TableConst.USERS_AUTHORITIES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.domain.product.ProductCondition;
import org.a_sply.porter.repository.ProductRepository;
import org.a_sply.porter.repository.jdbc.extractor.ExtractType;
import org.a_sply.porter.repository.jdbc.extractor.ProductExtractor;
import org.a_sply.porter.repository.jdbc.extractor.ProductsExtractor;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductRepository implements ProductRepository{
	
	@Autowired
	private KeyHolder keyHolder;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public long insert(final Product product) {
		final String INSERT_SQL1 = new SQL(){{
			INSERT_INTO(PRODUCTS.toString());
			VALUES(PRODUCTS.USER_ID(), 			"?"); // 1
			VALUES(PRODUCTS.CAR_MAKER_NO(), 	"?"); // 2
			VALUES(PRODUCTS.CAR_TYPE_NO(), 		"?"); // 3
			VALUES(PRODUCTS.CAR_MODEL_NO(), 	"?"); // 4
			VALUES(PRODUCTS.CAR_YEAR(), 		"?"); // 5
			VALUES(PRODUCTS.MAIN_CATEGORY_NO(), "?"); // 6
			VALUES(PRODUCTS.SUB_CATEGORY_NO(), 	"?"); // 7
			VALUES(PRODUCTS.NAME(), 			"?"); // 8
			VALUES(PRODUCTS.PRICE(), 			"?"); // 9
			VALUES(PRODUCTS.AMOUNT(), 			"?"); // 10
			VALUES(PRODUCTS.LIST_IMAGE_URL(), 	"?"); // 11
		}}.toString();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL1, new String[] { "id" });
				ps.setInt(1, product.getOwner().getId());
				ps.setInt(2, product.getCarInfo().getCarMaker().getNo());
				ps.setInt(3, product.getCarInfo().getCarType().getNo());
				ps.setInt(4, product.getCarInfo().getCarModel().getNo());
				ps.setInt(5, product.getCarInfo().getCarYear());
				ps.setInt(6, product.getPartType().getMainCategory().getNo());
				ps.setInt(7, product.getPartType().getSubCategory().getNo());
				ps.setString(8, product.getName());
				ps.setDouble(9, product.getPrice());
				ps.setInt(10, product.getAmount());
				ps.setString(11, product.getImageUrls().getListImageUrl());
				return ps;
			}
		}, keyHolder);
		
		final long productId = keyHolder.getKey().longValue();
		
		final String INSERT_SQL2 = new SQL(){{
			INSERT_INTO(PRODUCTS_STATE.toString());
			VALUES(PRODUCTS_STATE.PRODUCT_ID(), "?"); // 1
			VALUES(PRODUCTS_STATE.STATE(), 		"?"); // 2
		}}.toString();
		
		jdbcTemplate.update(INSERT_SQL2, productId, product.getState());
		
		final String INSERT_SQL3 = new SQL(){{
			INSERT_INTO(PRODUCTS_DES_IMAGE_URLS.toString());
			VALUES(PRODUCTS_DES_IMAGE_URLS.PRODUCT_ID(), "?"); // 1
			VALUES(PRODUCTS_DES_IMAGE_URLS.NORMAL_URL(), "?"); // 2
			VALUES(PRODUCTS_DES_IMAGE_URLS.ZOOMIN_URL(), "?"); // 3
		}}.toString();
		
		jdbcTemplate.batchUpdate(INSERT_SQL3, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, productId);
				ps.setString(2, product.getImageUrls().getNormalImageUrls().get(i));
				ps.setString(3, product.getImageUrls().getZoomInImageUrls().get(i));
			}
			@Override
			public int getBatchSize() {
				return product.getImageUrls().getNormalImageUrls().size();
			}
		});
		
		return productId;
	}
	

	@Override
	public Product selectById(final long productId) {
		
		final String SELECT_BY_ID_SQL = new SQL(){{
			SELECT("*");
			FROM(PRODUCTS.toString());
			LEFT_OUTER_JOIN(USERS.toString() + " on " + USERS.ID() +" = "+ PRODUCTS.USER_ID());
			LEFT_OUTER_JOIN(USERS_AUTHORITIES.toString() + " on " + USERS_AUTHORITIES.USER_ID() +" = "+ USERS.ID());
			LEFT_OUTER_JOIN(PRODUCTS_DES_IMAGE_URLS.toString() + " on " + PRODUCTS_DES_IMAGE_URLS.PRODUCT_ID() +" = "+ PRODUCTS.ID());
			LEFT_OUTER_JOIN(PRODUCTS_STATE.toString() + " on " + PRODUCTS_STATE.PRODUCT_ID() +" = "+ PRODUCTS.ID());
			WHERE(PRODUCTS.ID() + "=" + productId);
		}}.toString();
		
		return jdbcTemplate.query(SELECT_BY_ID_SQL, new ProductExtractor(ExtractType.WITH_DETAIL));
	}

	@Override
	public List<Product> selectByCondition(final ProductCondition productCondition) {
		final String SELECT_BY_CONDITION_SQL = new SQL(){{
			SELECT("*");
			FROM(PRODUCTS.toString());
			LEFT_OUTER_JOIN(USERS.toString() + " on " + USERS.ID() +" = "+ PRODUCTS.USER_ID());
			LEFT_OUTER_JOIN(USERS_AUTHORITIES.toString() + " on " + USERS_AUTHORITIES.USER_ID() +" = "+ USERS.ID());
			
			int carMakerNo = productCondition.getCarInfo().getCarMaker().getNo();
			if(carMakerNo != 0)
				WHERE(PRODUCTS.CAR_MAKER_NO() + " = " + carMakerNo);

			int carTypeNo = productCondition.getCarInfo().getCarType().getNo();
			if(carTypeNo != 0)
				WHERE(PRODUCTS.CAR_TYPE_NO() + " = "+carTypeNo);
			
			int carModelNo = productCondition.getCarInfo().getCarModel().getNo();
			if(carModelNo != 0)
				WHERE(PRODUCTS.CAR_MODEL_NO() + " = " + carModelNo);
			
			int carYear = productCondition.getCarInfo().getCarYear();
			if(carYear != 0)
				WHERE(PRODUCTS.CAR_YEAR() + " = " +carYear);
			
			int mainCategoryNo = productCondition.getPartType().getMainCategory().getNo();
			if(mainCategoryNo != 0)
				WHERE(PRODUCTS.MAIN_CATEGORY_NO() + " = " + mainCategoryNo);
			
			int subCaregoryNo = productCondition.getPartType().getSubCategory().getNo();
			if(subCaregoryNo != 0)
				WHERE(PRODUCTS.SUB_CATEGORY_NO() + " = " + subCaregoryNo);
			
			String keyword = productCondition.getKeyword();
			if(keyword != null)
				WHERE(PRODUCTS.NAME() + " like " +"'%"+keyword+"%'");
			
			
			int userId = productCondition.getUserId();
			if(userId != 0)
				WHERE(PRODUCTS.USER_ID() + " = " + userId);
			

			ORDER_BY(PRODUCTS.ID() + " DESC limit "+ productCondition.getOffset() +"," + productCondition.getCount());
			//ORDER BY ID DESC limit ?, ?
		}}.toString();
		
		return jdbcTemplate.query(SELECT_BY_CONDITION_SQL, new ProductsExtractor(ExtractType.WITHOUT_DETAIL));
	}


	@Override
	public int countByCondition(final ProductCondition productCondition) {
		final String COUNT_BY_CONDITION_SQL = new SQL(){{
			SELECT("COUNT(*)");
			FROM(PRODUCTS.toString());
			
			int carMakerNo = productCondition.getCarInfo().getCarMaker().getNo();
			if(carMakerNo != 0)
				WHERE(PRODUCTS.CAR_MAKER_NO() + " = " + carMakerNo);

			int carTypeNo = productCondition.getCarInfo().getCarType().getNo();
			if(carTypeNo != 0)
				WHERE(PRODUCTS.CAR_TYPE_NO() + " = "+carTypeNo);
			
			int carModelNo = productCondition.getCarInfo().getCarModel().getNo();
			if(carModelNo != 0)
				WHERE(PRODUCTS.CAR_MODEL_NO() + " = " + carModelNo);
			
			int carYear = productCondition.getCarInfo().getCarYear();
			if(carYear != 0)
				WHERE(PRODUCTS.CAR_YEAR() + " = " +carYear);
			
			int mainCategoryNo = productCondition.getPartType().getMainCategory().getNo();
			if(mainCategoryNo != 0)
				WHERE(PRODUCTS.MAIN_CATEGORY_NO() + " = " + mainCategoryNo);
			
			int subCaregoryNo = productCondition.getPartType().getSubCategory().getNo();
			if(subCaregoryNo != 0)
				WHERE(PRODUCTS.SUB_CATEGORY_NO() + " = " + subCaregoryNo);
		}}.toString();
		
		return jdbcTemplate.queryForObject(COUNT_BY_CONDITION_SQL, Integer.class);
	}

}
