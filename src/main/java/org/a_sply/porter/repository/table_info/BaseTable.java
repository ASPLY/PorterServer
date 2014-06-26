package org.a_sply.porter.repository.table_info;

public class BaseTable {
	
	protected String table;
	
	public BaseTable(String table) {
		this.table = table;
	}
	
	public String toString(){
		return table;
	}
	
	protected String field(String field){
		return table + "." + field;
	}
}
