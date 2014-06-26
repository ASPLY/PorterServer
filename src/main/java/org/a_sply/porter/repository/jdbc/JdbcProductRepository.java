package org.a_sply.porter.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.repository.ProductRepository;
import org.a_sply.porter.repository.jdbc.extractor.ProductExtractor;
import org.a_sply.porter.repository.table_info.products.ProductsDesImageUrlsTable;
import org.a_sply.porter.repository.table_info.products.ProductsStateTable;
import org.a_sply.porter.repository.table_info.products.ProductsTable;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductRepository implements ProductRepository{
	
	private static final ProductsTable PRODUCTS = new ProductsTable();
	private static final ProductsStateTable PRODUCTS_STATE = new ProductsStateTable();
	private static final ProductsDesImageUrlsTable PRODUCTS_DES_IMAGE_URLS = new ProductsDesImageUrlsTable();
	
	@Autowired
	private KeyHolder keyHolder;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_SQL1 = new SQL(){{
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
	
	private static final String INSERT_SQL2 = new SQL(){{
				INSERT_INTO(PRODUCTS_STATE.toString());
				VALUES(PRODUCTS_STATE.PRODUCT_ID(), "?"); // 1
				VALUES(PRODUCTS_STATE.STATE(), 		"?"); // 2
			}}.toString();
	
	private static final String INSERT_SQL3 = new SQL(){{
				INSERT_INTO(PRODUCTS_DES_IMAGE_URLS.toString());
				VALUES(PRODUCTS_DES_IMAGE_URLS.PRODUCT_ID(), "?"); // 1
				VALUES(PRODUCTS_DES_IMAGE_URLS.NORMAL_URL(), "?"); // 2
				VALUES(PRODUCTS_DES_IMAGE_URLS.ZOOMIN_URL(), "?"); // 3
			}}.toString();
	
	@Override
	public long insert(final Product product) {
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
		
		jdbcTemplate.update(INSERT_SQL2, productId, product.getState());
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
	
	private static final String SELECT_BY_ID_SQL = 
			"select * from products p left join products_des_image_urls d on p.ID = d.PRODUCT_ID left join products_state s on p.ID = s.PRODUCT_ID where p.ID = ?";

	@Override
	public Product selectById(int productId) {
		
		return jdbcTemplate.query(SELECT_BY_ID_SQL, new Object[]{productId}, new ProductExtractor());
	}

}
