package org.a_sply.porter.domain.base;

public class NoAndName {
	
	protected int no;
	protected String name;
	
	public NoAndName(int no, String name) {
		this.no = no;
		this.name = name;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
