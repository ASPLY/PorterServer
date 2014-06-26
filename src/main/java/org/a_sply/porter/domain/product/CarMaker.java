package org.a_sply.porter.domain.product;

import org.a_sply.porter.domain.base.NoAndName;

public class CarMaker extends NoAndName{
	
	public CarMaker(int no) {
		super(no, null);
	}

	public CarMaker(int no, String name) {
		super(no, name);
	}

}
