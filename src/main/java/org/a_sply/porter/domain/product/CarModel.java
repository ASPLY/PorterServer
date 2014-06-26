package org.a_sply.porter.domain.product;

import org.a_sply.porter.domain.base.NoAndName;

public class CarModel extends NoAndName{

	public CarModel(int no) {
		super(no, null);
	}

	public CarModel(int no, String name) {
		super(no, name);
	}

}
