package org.a_sply.porter.domain;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class BasicAuthHeaderValue {

	private String basicAuthHeaderValue;

	public BasicAuthHeaderValue(String username, String password) {
        String authHeaderFormat = "Basic ";
        String encodingRawData = String.format("%s:%s", username, password);
        String encodingData;
		try {
			encodingData = authHeaderFormat + new String(Base64.encodeBase64(encodingRawData.getBytes("utf-8")));
			basicAuthHeaderValue = encodingData;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}	}

	public String getBasicAuthHeaderValue() {
		return basicAuthHeaderValue;
	}

	public void setBasicAuthHeaderValue(String basicAuthHeaderValue) {
		this.basicAuthHeaderValue = basicAuthHeaderValue;
	}
}
