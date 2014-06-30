package org.a_sply.porter.dao.impl_jdbc;

import static org.a_sply.porter.dao.table_info.TableConst.PRODUCTS;
import static org.a_sply.porter.dao.table_info.TableConst.PRODUCTS_DES_IMAGE_URLS;
import static org.a_sply.porter.dao.table_info.TableConst.PRODUCTS_STATE;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.a_sply.porter.dao.interfaces.ProductDao;
import org.a_sply.porter.dao.interfaces.SelectType;
import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.domain.product.ProductCondition;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductDao implements ProductDao{

	@Autowired
	private KeyHolder keyHolder;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public long insert(Product product) {
		long productId = insertProduct(product);
		product.setProductId(productId);
		insertProductState(product);
		insertProductDesImageUrls(product);
		return productId;
	}

	public long insertProduct(final Product product) {
		return new SimpleJdbcInsert(dataSource)
				.withTableName(PRODUCTS.toString())
				.usingGeneratedKeyColumns(PRODUCTS.FIELD_PRODUCT_ID)
				.executeAndReturnKeyHolder(new MappedBeanPropertySqlParameterSource(product))
				.getKey().longValue();
	}


	public void insertProductState(Product product) {
		new SimpleJdbcInsert(dataSource)
			.withTableName(PRODUCTS_STATE.toString())
			.execute(new MappedBeanPropertySqlParameterSource(product));
	}


	public void insertProductDesImageUrls(final Product product) {
		final String INSERT_SQL3 = new SQL(){{
			INSERT_INTO(PRODUCTS_DES_IMAGE_URLS.toString());
			VALUES(PRODUCTS_DES_IMAGE_URLS.FIELD_PRODUCT_ID, Product.NAMED_PRODUCT_ID); // 1
			VALUES(PRODUCTS_DES_IMAGE_URLS.FIELD_NORMAL_URL, Product.NAMED_NORMAL_URL); // 2
			VALUES(PRODUCTS_DES_IMAGE_URLS.FIELD_ZOOMIN_URL, Product.NAMED_ZOOMIN_URL); // 3
		}}.toString();
		
		List<MapSqlParameterSource> parameters = new ArrayList<MapSqlParameterSource>();
		for(int i=0; i<product.getNormalImageUrls().size(); i++){
			parameters.add(new MapSqlParameterSource()
							.addValue(Product.PRODUCT_ID, product.getProductId())
							.addValue(Product.NORMAL_URL, product.getNormalImageUrls().get(i))
							.addValue(Product.ZOOMIN_URL, product.getZoomInImageUrls().get(i)));
		}

		namedParameterJdbcTemplate.batchUpdate(INSERT_SQL3, parameters.toArray(new MapSqlParameterSource[0]));
	}

	@Override
	public Product selectById(final SelectType selectType, final long productId) {
		Product product;
		try {
			product = selectProductById(selectType, productId);
			if(selectType == SelectType.WITH_DETAIL)
				selectDesImgUrlsAndSetValue(product);
		}catch(EmptyResultDataAccessException e){
			product = null;
		}
		return product;
	}

		private Product selectProductById(final SelectType selectType, final long productId) {
			String SELECT_BY_ID_SQL = new SQL(){{
				SELECT("*");
				FROM(PRODUCTS.toString());
				if(selectType == SelectType.WITH_DETAIL)
					LEFT_OUTER_JOIN(PRODUCTS_STATE.toString() + " on " + PRODUCTS_STATE.FIELD_PRODUCT_ID +" = "+ PRODUCTS.FIELD_PRODUCT_ID);
				WHERE(PRODUCTS.FIELD_PRODUCT_ID + "=" + Product.NAMED_PRODUCT_ID);
			}}.toString();

			return namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID_SQL, 
					new MapSqlParameterSource(Product.PRODUCT_ID, productId), 
					new ExtendedBeanPropertyRowMapper<Product>(Product.class));
		}

		private void selectDesImgUrlsAndSetValue(Product product) {
			List<String> zoomInImageUrls = selectProductDesUrlsById(PRODUCTS_DES_IMAGE_URLS.FIELD_NORMAL_URL, product.getProductId());
			List<String> normalImageUrls = selectProductDesUrlsById(PRODUCTS_DES_IMAGE_URLS.FIELD_ZOOMIN_URL, product.getProductId());

			product.setZoomInImageUrls(zoomInImageUrls);
			product.setNormalImageUrls(normalImageUrls);
		}
	
			private List<String> selectProductDesUrlsById(final String colName, final long productId) {
				String SELECT_BY_ID_SQL = new SQL(){{
					SELECT(colName);
					FROM(PRODUCTS_DES_IMAGE_URLS.toString());
					WHERE(PRODUCTS_DES_IMAGE_URLS.PRODUCT_ID + "=" + Product.NAMED_PRODUCT_ID);
				}}.toString();
				
				
				return namedParameterJdbcTemplate.queryForList(SELECT_BY_ID_SQL, 
						new MapSqlParameterSource(Product.PRODUCT_ID , productId), String.class); 
			}


	@Override
	public List<Product> selectByCondition(final SelectType selectType, final ProductCondition productCondition) {
		final String SELECT_BY_CONDITION_SQL = new SQL(){{
			SELECT(PRODUCTS.FIELD_PRODUCT_ID);
			SELECT(PRODUCTS.FIELD_USER_ID);
			SELECT(PRODUCTS.FIELD_CAR_MAKER_NO); 
			SELECT(PRODUCTS.FIELD_CAR_TYPE_NO); 
			SELECT(PRODUCTS.FIELD_CAR_MODEL_NO);
			SELECT(PRODUCTS.FIELD_CAR_YEAR); 
			SELECT(PRODUCTS.FIELD_MAIN_CATEGORY_NO); 
			SELECT(PRODUCTS.FIELD_SUB_CATEGORY_NO); 
			SELECT(PRODUCTS.FIELD_NAME); 
			SELECT(PRODUCTS.FIELD_PRICE); 
			SELECT(PRODUCTS.FIELD_QUANTITY); 
			SELECT(PRODUCTS.FIELD_LIST_IMAGE_URL);
			
			if(selectType == SelectType.WITH_DETAIL)
				SELECT(PRODUCTS_STATE.FIELD_STATE);
			
			FROM(PRODUCTS.toString());
			LEFT_OUTER_JOIN(PRODUCTS_STATE.toString() + " on " + PRODUCTS_STATE.FIELD_PRODUCT_ID +" = "+ PRODUCTS.FIELD_PRODUCT_ID);
			
			if(productCondition.getCarMakerNo() != 0)
				WHERE(PRODUCTS.FIELD_CAR_MAKER_NO + " = " + ProductCondition.NAMED_CAR_MAKER_NO);

			if(productCondition.getCarTypeNo() != 0)
				WHERE(PRODUCTS.FIELD_CAR_TYPE_NO + " = " + ProductCondition.NAMED_CAR_TYPE_NO);
			
			if(productCondition.getCarModelNo() != 0)
				WHERE(PRODUCTS.FIELD_CAR_MODEL_NO + " = " + ProductCondition.NAMED_CAR_MODEL_NO);
			
			if(productCondition.getCarYear() != 0)
				WHERE(PRODUCTS.FIELD_CAR_YEAR + " = " + ProductCondition.NAMED_CAR_YEAR);
			
			if(productCondition.getMainCategoryNo() != 0)
				WHERE(PRODUCTS.FIELD_MAIN_CATEGORY_NO + " = " + ProductCondition.NAMED_MAIN_CATEGORY_NO);
			
			if(productCondition.getSubCategoryNo() != 0)
				WHERE(PRODUCTS.FIELD_SUB_CATEGORY_NO + " = " + ProductCondition.NAMED_SUB_CATEGORY_NO);
			
			if(productCondition.getKeyword() != null){
				WHERE(PRODUCTS.FIELD_NAME + " like " + "concat('%',"+ProductCondition.NAMED_KEYWORD+",'%')" );
				//OR();
				//WHERE(PRODUCTS_STATE.FIELD_STATE + " like " +"'%"+ProductCondition.NAMED_KEYWORD+"%'");
			}
			
			if(productCondition.getUserId() != 0)
				WHERE(PRODUCTS.FIELD_USER_ID + " = " + ProductCondition.NAMED_USER_ID);
			
			WHERE(PRODUCTS.FIELD_QUANTITY + " > " +0);
			
			ORDER_BY(PRODUCTS.FIELD_PRODUCT_ID + " DESC limit "+ ProductCondition.NAMED_OFFSET +" , " + ProductCondition.NAMED_COUNT);

		}}.toString();
		
		List<Product> products = namedParameterJdbcTemplate.query(SELECT_BY_CONDITION_SQL,
				new MappedBeanPropertySqlParameterSource(productCondition), 
				new ExtendedBeanPropertyRowMapper<Product>(Product.class));
		
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
			
			if(productCondition.getCarMakerNo() != 0)
				WHERE(PRODUCTS.FIELD_CAR_MAKER_NO + " = " + ProductCondition.NAMED_CAR_MAKER_NO);

			if(productCondition.getCarTypeNo() != 0)
				WHERE(PRODUCTS.FIELD_CAR_TYPE_NO + " = " + ProductCondition.NAMED_CAR_TYPE_NO);
			
			if(productCondition.getCarModelNo() != 0)
				WHERE(PRODUCTS.FIELD_CAR_MODEL_NO + " = " + ProductCondition.NAMED_CAR_MODEL_NO);
			
			if(productCondition.getCarYear() != 0)
				WHERE(PRODUCTS.FIELD_CAR_YEAR + " = " + ProductCondition.NAMED_CAR_YEAR);
			
			if(productCondition.getMainCategoryNo() != 0)
				WHERE(PRODUCTS.FIELD_MAIN_CATEGORY_NO + " = " + ProductCondition.NAMED_MAIN_CATEGORY_NO);
			
			if(productCondition.getSubCategoryNo() != 0)
				WHERE(PRODUCTS.FIELD_SUB_CATEGORY_NO + " = " + ProductCondition.NAMED_SUB_CATEGORY_NO);
			
		}}.toString();
		
		return namedParameterJdbcTemplate.queryForObject(COUNT_BY_CONDITION_SQL
				,new MappedBeanPropertySqlParameterSource(productCondition)
				,Integer.class);
	}

}
