package org.a_sply.porter.dao.impl_jdbc;

import static org.a_sply.porter.dao.table_info.TableConst.PRODUCTS;
import static org.a_sply.porter.dao.table_info.TableConst.PRODUCTS_DES_IMAGE_URLS;
import static org.a_sply.porter.dao.table_info.TableConst.PRODUCTS_STATE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.a_sply.porter.dao.interfaces.ProductDao;
import org.a_sply.porter.dao.interfaces.SelectType;
import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.domain.product.ProductCondition;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductDao implements ProductDao{

	@Autowired
	private KeyHolder keyHolder;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public long insert(Product product) {
		long productId = insertProduct(product);
		product.setProductId(productId);
		insertProductState(product);
		insertProductDesImageUrls(product);
		return productId;
	}

	public long insertProduct(final Product product) {
		final String INSERT_PRODUCT_SQL = new SQL(){{
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
			VALUES(PRODUCTS.QUANTITY(), 			"?"); // 10
			VALUES(PRODUCTS.LIST_IMAGE_URL(), 	"?"); // 11
		}}.toString();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT_SQL, new String[] { "id" });
				ps.setInt(1, product.getUserId());
				ps.setInt(2, product.getCarMakerNo());
				ps.setInt(3, product.getCarTypeNo());
				ps.setInt(4, product.getCarModelNo());
				ps.setInt(5, product.getCarYear());
				ps.setInt(6, product.getMainCategoryNo());
				ps.setInt(7, product.getSubCategoryNo());
				ps.setString(8, product.getName());
				ps.setDouble(9, product.getPrice());
				ps.setInt(10, product.getQuantity());
				ps.setString(11, product.getListImageUrl());
				return ps;
			}
		}, keyHolder);
		
		return keyHolder.getKey().longValue();
	}


	public void insertProductState(Product product) {
		final String INSERT_SQL2 = new SQL(){{
			INSERT_INTO(PRODUCTS_STATE.toString());
			VALUES(PRODUCTS_STATE.PRODUCT_ID(), "?"); // 1
			VALUES(PRODUCTS_STATE.STATE(), 		"?"); // 2
		}}.toString();
		
		jdbcTemplate.update(INSERT_SQL2, product.getProductId(), product.getState());
	}


	public void insertProductDesImageUrls(final Product product) {
		final String INSERT_SQL3 = new SQL(){{
			INSERT_INTO(PRODUCTS_DES_IMAGE_URLS.toString());
			VALUES(PRODUCTS_DES_IMAGE_URLS.PRODUCT_ID(), "?"); // 1
			VALUES(PRODUCTS_DES_IMAGE_URLS.NORMAL_URL(), "?"); // 2
			VALUES(PRODUCTS_DES_IMAGE_URLS.ZOOMIN_URL(), "?"); // 3
		}}.toString();
		
		jdbcTemplate.batchUpdate(INSERT_SQL3, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, product.getProductId());
				ps.setString(2, product.getNormalImageUrls().get(i));
				ps.setString(3, product.getZoomInImageUrls().get(i));
			}
			@Override
			public int getBatchSize() {
				return product.getNormalImageUrls().size();
			}
		});
	}

	@Override
	public Product selectById(final SelectType selectType, final long productId) {
		Product product2;
		try {
			product2 = selectProductById(selectType, productId);
			if(selectType == SelectType.WITH_DETAIL)
				selectDesImgUrlsAndSetValue(product2);
		}catch(EmptyResultDataAccessException e){
			product2 = null;
		}
		return product2;
	}

		private Product selectProductById(final SelectType selectType, final long productId) {
			String SELECT_BY_ID_SQL = new SQL(){{
				SELECT("*");
				FROM(PRODUCTS.toString());
				if(selectType == SelectType.WITH_DETAIL)
					LEFT_OUTER_JOIN(PRODUCTS_STATE.toString() + " on " + PRODUCTS_STATE.PRODUCT_ID() +" = "+ PRODUCTS.PRODUCT_ID());
				WHERE(PRODUCTS.PRODUCT_ID() + "=" + productId);
			}}.toString();

			return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new ExtendedBeanPropertyRowMapper<Product>(Product.class));
		}

		private void selectDesImgUrlsAndSetValue(Product product2) {
			List<String> zoomInImageUrls = selectProductDesUrlsById(PRODUCTS_DES_IMAGE_URLS.NORMAL_URL(), product2.getProductId());
			List<String> normalImageUrls = selectProductDesUrlsById(PRODUCTS_DES_IMAGE_URLS.ZOOMIN_URL(), product2.getProductId());

			product2.setZoomInImageUrls(zoomInImageUrls);
			product2.setNormalImageUrls(normalImageUrls);
		}
	
			private List<String> selectProductDesUrlsById(final String colName, final long productId) {
				String SELECT_BY_ID_SQL = new SQL(){{
					SELECT(colName);
					FROM(PRODUCTS_DES_IMAGE_URLS.toString());
					WHERE(PRODUCTS_DES_IMAGE_URLS.PRODUCT_ID() + "=" + productId);
				}}.toString();
				
				return jdbcTemplate.queryForList(SELECT_BY_ID_SQL, String.class);
			}


	@Override
	public List<Product> selectByCondition(final SelectType selectType, final ProductCondition productCondition) {
		final String SELECT_BY_CONDITION_SQL = new SQL(){{
			SELECT(PRODUCTS.PRODUCT_ID());
			SELECT(PRODUCTS.USER_ID());
			SELECT(PRODUCTS.CAR_MAKER_NO()); 
			SELECT(PRODUCTS.CAR_TYPE_NO()); 
			SELECT(PRODUCTS.CAR_MODEL_NO());
			SELECT(PRODUCTS.CAR_YEAR()); 
			SELECT(PRODUCTS.MAIN_CATEGORY_NO()); 
			SELECT(PRODUCTS.SUB_CATEGORY_NO()); 
			SELECT(PRODUCTS.NAME()); 
			SELECT(PRODUCTS.PRICE()); 
			SELECT(PRODUCTS.QUANTITY()); 
			SELECT(PRODUCTS.LIST_IMAGE_URL());
			
			if(selectType == SelectType.WITH_DETAIL)
				SELECT(PRODUCTS_STATE.STATE());
			
			FROM(PRODUCTS.toString());
			LEFT_OUTER_JOIN(PRODUCTS_STATE.toString() + " on " + PRODUCTS_STATE.PRODUCT_ID() +" = "+ PRODUCTS.PRODUCT_ID());
			
			int carMakerNo = productCondition.getCarMakerNo();
			if(carMakerNo != 0)
				WHERE(PRODUCTS.CAR_MAKER_NO() + " = " + carMakerNo);

			int carTypeNo = productCondition.getCarTypeNo();
			if(carTypeNo != 0)
				WHERE(PRODUCTS.CAR_TYPE_NO() + " = "+carTypeNo);
			
			int carModelNo = productCondition.getCarModelNo();
			if(carModelNo != 0)
				WHERE(PRODUCTS.CAR_MODEL_NO() + " = " + carModelNo);
			
			int carYear = productCondition.getCarYear();
			if(carYear != 0)
				WHERE(PRODUCTS.CAR_YEAR() + " = " +carYear);
			
			int mainCategoryNo = productCondition.getMainCategoryNo();
			if(mainCategoryNo != 0)
				WHERE(PRODUCTS.MAIN_CATEGORY_NO() + " = " + mainCategoryNo);
			
			int subCaregoryNo = productCondition.getSubCategoryNo();
			if(subCaregoryNo != 0)
				WHERE(PRODUCTS.SUB_CATEGORY_NO() + " = " + subCaregoryNo);
			
			String keyword = productCondition.getKeyword();
			if(keyword != null){
				WHERE(PRODUCTS.NAME() + " like " +"'%"+keyword+"%'");
				OR();
				WHERE(PRODUCTS_STATE.STATE() + " like " +"'%"+keyword+"%'");
			}
			
			int userId = productCondition.getUserId();
			if(userId != 0)
				WHERE(PRODUCTS.USER_ID() + " = " + userId);
			
			WHERE(PRODUCTS.QUANTITY() + " > " +0);
			
			ORDER_BY(PRODUCTS.PRODUCT_ID() + " DESC limit "+ productCondition.getOffset() +"," + productCondition.getCount());

		}}.toString();
		
		List<Product> products = jdbcTemplate.query(SELECT_BY_CONDITION_SQL, new ExtendedBeanPropertyRowMapper<Product>(Product.class));
		
		if(selectType == SelectType.WITH_DETAIL)
			for (Product product : products)
				selectDesImgUrlsAndSetValue(product);
		
		return products;
	}


	@Override
	public int countByCondition(final ProductCondition productCondition) {
		final String COUNT_BY_CONDITION_SQL = new SQL(){{
			SELECT("COUNT(*)");
			FROM(PRODUCTS.toString());
			
			int carMakerNo = productCondition.getCarMakerNo();
			if(carMakerNo != 0)
				WHERE(PRODUCTS.CAR_MAKER_NO() + " = " + carMakerNo);

			int carTypeNo = productCondition.getCarTypeNo();
			if(carTypeNo != 0)
				WHERE(PRODUCTS.CAR_TYPE_NO() + " = "+carTypeNo);
			
			int carModelNo = productCondition.getCarModelNo();
			if(carModelNo != 0)
				WHERE(PRODUCTS.CAR_MODEL_NO() + " = " + carModelNo);
			
			int carYear = productCondition.getCarYear();
			if(carYear != 0)
				WHERE(PRODUCTS.CAR_YEAR() + " = " +carYear);
			
			int mainCategoryNo = productCondition.getMainCategoryNo();
			if(mainCategoryNo != 0)
				WHERE(PRODUCTS.MAIN_CATEGORY_NO() + " = " + mainCategoryNo);
			
			int subCaregoryNo = productCondition.getSubCategoryNo();
			if(subCaregoryNo != 0)
				WHERE(PRODUCTS.SUB_CATEGORY_NO() + " = " + subCaregoryNo);
		}}.toString();
		
		return jdbcTemplate.queryForObject(COUNT_BY_CONDITION_SQL, Integer.class);
	}

}
