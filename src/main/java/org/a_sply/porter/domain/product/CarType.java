package org.a_sply.porter.domain.product;

import org.a_sply.porter.domain.base.NoAndName;

public class CarType extends NoAndName{
	
	public CarType(int no) {
		super(no, null);
	}
	
	public CarType(int no, String name) {
		super(no, name);
	}

}
