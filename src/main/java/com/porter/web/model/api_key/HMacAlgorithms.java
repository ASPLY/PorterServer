package com.porter.web.model.api_key;


public interface HMacAlgorithms {

	public String encrypt(String rawDataToBeEncrypted);
	public boolean isValid(String enc, String raw);
	boolean equals(String expected, String actual);
	
}
