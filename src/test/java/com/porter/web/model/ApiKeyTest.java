package com.porter.web.model;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Test;

import com.porter.web.model.api_key.ApiKey;

public class ApiKeyTest {

	@Test
	public void createt_성공() {
		String timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
		ApiKey apiKey = new ApiKey("kd980311@naver.com", timestamp);
		System.out.println(apiKey);
	}
	
	@Test
	public void valid_성공() {
		ApiKey apiKey = new ApiKey();
		apiKey.setApiKey("kd980311@naver.com-1393931346920-w5eePS+x0WBA88cxtOeOXx1bKMgbLEwkS36GXVBcmws=");
		System.out.println(apiKey.vaild());
	}

}
