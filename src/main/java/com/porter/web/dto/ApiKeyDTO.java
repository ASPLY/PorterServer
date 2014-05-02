package com.porter.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.porter.web.model.api_key.ApiKey;

public class ApiKeyDTO {
	
	@NotEmpty
	private String apiKey;
	
	public ApiKeyDTO(){
	}
	
	public void setApiKey(String apiKey){
		this.apiKey = apiKey;
	}
	
	// get으로 json화하는구나 !
	public String getApiKey() {
		return apiKey;
	}

	public String toString(){
		return apiKey;
	}

	public ApiKey toModel() {
		return new ApiKey(apiKey);
	}


}
