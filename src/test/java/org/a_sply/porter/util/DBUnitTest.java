package org.a_sply.porter.util;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.a_sply.porter.config.PersistentConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.mssql.MsSqlDataTypeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistentConfig.class })
public class DBUnitTest {

	@Resource
	private DataSource dataSource;

	@Test
	public void test() throws SQLException, Exception {
		IDataSet dataSet = getConnection().createDataSet();
		// CsvDataSetWriter.write(dataSet, new File("c://test"));
		XmlDataSet.write(dataSet, new FileOutputStream(new File("test.xml")));
		// XlsDataSet.write(dataSet, new FileOutputStream(new
		// File("c://test/test.xls")));
	}

	private IDatabaseConnection getConnection() throws Exception {
		// get connection
		Connection con = ((BasicDataSource) dataSource).getConnection();
		DatabaseMetaData databaseMetaData = con.getMetaData();
		// oracle schema name is the user name
		IDatabaseConnection connection = new DatabaseConnection(con);
		// IDatabaseConnection connection = new
		// DatabaseConnection(con,databaseMetaData.getUserName().toLowerCase());
		// IDatabaseConnection connection = new
		// DatabaseConnection(con,databaseMetaData.getUserName().toUpperCase(),true);
		DatabaseConfig config = connection.getConfig();
		// oracle 10g

		// ms sql
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
				new MsSqlDataTypeFactory());
		// receycle bin
		// config.setFeature(DatabaseConfig.FEATURE_SKIP_ORACLE_RECYCLEBIN_TABLES,
		// Boolean.TRUE);
		return connection;
	}

}
