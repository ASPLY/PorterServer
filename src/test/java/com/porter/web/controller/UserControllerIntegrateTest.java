package com.porter.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

//@Ignore
public class UserControllerIntegrateTest {

	private static final String LOCALHOST = "http://localhost:8080/porter/";
	private static final String CLOUD = "http://117.52.91.145:8080/porter/";
	private static final String DEFAULT = LOCALHOST;

	@Test
	public void get_성공(){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response  = restTemplate.getForEntity(DEFAULT+"users/1", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
	}
	
	@Test
	public void create_성공() {
		
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("email", "kd980311@naver.com");
		params.add("name", "chanaa");
		params.add("password", "aaaasdsdqwe");
		params.add("telephone","010-6555-2221");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response  = restTemplate.postForEntity(DEFAULT+"users", params, String.class);
		System.out.println(response.getBody());
		//System.out.println(response.getStatusCode());
		
		//assertThat(response.getStatusCode().value(), is(201));
	}
	
	@Test
	public void apikey_성공() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(DEFAULT+"users/auth");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		  params.add(new BasicNameValuePair("apiKey", "gd@nate.com+1393962805527+eVO25o8hwfOZF+BC3uQq1FuRP0AmroRZ97WOQsT2W9U=")); 

		//Post 방식으로 name, addr 파람이 넘어간다.
		  try {
			post.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = httpClient.execute(post); 
			System.out.println(response.getStatusLine());//응답결과 값이 넘어온다.
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void login_성공() {
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(DEFAULT+"users/login");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		  params.add(new BasicNameValuePair("email", "kd980311@naver.com")); 
		  params.add(new BasicNameValuePair("password", "pass"));

		//Post 방식으로 name, addr 파람이 넘어간다.
		  try {
			post.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = httpClient.execute(post); 
			System.out.println(response.getStatusLine());//응답결과 값이 넘어온다.
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		       

//		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
//		params.add("email", "gd@nate.com");
//		params.add("password", "123123");
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> response  = restTemplate.postForEntity(DEFAULT+"users/login", params, String.class);
//		System.out.println(response.getStatusCode());
//		System.out.println(response.getBody());
	}
	
	@Test
	public void login_실패() {
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("email", "kd980311@naver.com");
		params.add("password", "passaa");
		RestTemplate restTemplate = new RestTemplate();
		try{
			ResponseEntity<String> response  = restTemplate.postForEntity(DEFAULT+"users/login", params, String.class);
		}catch(HttpClientErrorException e){
			System.out.println(e.getStatusCode());
		}
	}
	
	@Test
	public void email_중복확인_성공(){
		RestTemplate restTemplate = new RestTemplate();
		try{
			ResponseEntity<String> response  = restTemplate.exchange(DEFAULT+"users/email/kd6480@naver.com", HttpMethod.GET, null, String.class);
			System.out.println(response.getStatusCode());
		}catch(HttpClientErrorException e){
			System.out.println(e.getMessage());
		}
	}
}
