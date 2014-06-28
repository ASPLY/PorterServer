package org.a_sply.porter.repository.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public abstract class AbstractExtractor<T> implements ResultSetExtractor<T>{

	@Override
	public T extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Long nextRowId = null;
		Long id = null;
		initArgs();
		
		if(!rs.next())
			return null;
		else
			do{
				nextRowId =	getId(rs);
	        	if(id != null && id != nextRowId){
	        		rs.previous();
	        		break;
	        	}

	        	getArgs(rs);

	        	id = nextRowId;
			}while(rs.next());		
		
		return create();
	}

	abstract void initArgs();
	abstract Long getId(ResultSet rs) throws SQLException, DataAccessException;
	abstract void getArgs(ResultSet rs) throws SQLException, DataAccessException;
	abstract T create();
}
