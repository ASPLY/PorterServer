package org.a_sply.porter.dao.table_info;

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
