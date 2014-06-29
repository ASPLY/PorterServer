package org.a_sply.porter.dao.impl_jdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class ExtendedBeanPropertyRowMapper<T> extends BeanPropertyRowMapper<T> {
	
    public ExtendedBeanPropertyRowMapper(Class<T> class1) {
        super(class1);
        this.setPrimitivesDefaultedForNullValue(true);
    }
    
}
