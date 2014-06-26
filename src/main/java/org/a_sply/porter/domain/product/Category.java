package org.a_sply.porter.domain.product;

import org.a_sply.porter.domain.base.NoAndName;

public class Category extends NoAndName{
	
	public Category(int no) {
		super(no, null);
	}

	public Category(int no, String name) {
		super(no, name);
	}

}
