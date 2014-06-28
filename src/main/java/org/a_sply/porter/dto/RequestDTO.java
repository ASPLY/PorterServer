package org.a_sply.porter.dto;

public class RequestDTO {
	
	protected long id;
	
	public RequestDTO(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
}
