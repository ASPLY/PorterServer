package com.porter.web.model.api_key;


public class ApiKey {
	
	private String apiKey;
	
	private String email;
	private final static String delimiter = "-";
	private HMacAlgorithms hMacAlgorithms = new ShaHMacAlgorithms(256, true);
	
	public ApiKey(){
	}
	
	public ApiKey(String apiKey){
		this.apiKey = apiKey;
	}

	public ApiKey(String email, String timestamp) {
		String rawData = email+delimiter+timestamp;
		apiKey = rawData+delimiter+hMacAlgorithms.encrypt(rawData);
	}
	
	public boolean vaild(){
		email = apiKey.split("\\"+delimiter)[0];
		String timestamp = apiKey.split("\\"+delimiter)[1];
		String raw = email + delimiter + timestamp; 
		String encrypt = apiKey.split("\\"+delimiter)[2];
		return hMacAlgorithms.equals(encrypt, hMacAlgorithms.encrypt(raw));
	}
	
	public void setApiKey(String apiKey){
		this.apiKey = apiKey;
	}
	
	public String email(){
		return email;
	}
	
	// get으로 json화하는구나 !
	public String getApiKey() {
		return apiKey;
	}

	public String toString(){
		return apiKey;
	}
}
